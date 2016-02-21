package org.usfirst.frc.team930.robot.commands;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain;

/** 
 *
 */
public class Drive extends Command {

	RobotDrive r;
	double oldWheel, quickStopAccumulator;
	private double throttleDeadband = 0.02;
	private double wheelDeadband = 0.02;

	public Drive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		// Robot.drivetrain.ultra.setEnabled(true);
		// Robot.drivetrain.ultra.getRangeInches();
		// SmartDashboard.putNumber("Distance", 0);
		double wheel = OI.getInstance().getXAxis();
		double throttle = OI.getInstance().getYAxis();
		// if (x > 0.75 || x < -.75) {
		// x = x;
		// } else {
		// x = .5 * x;
		// }
		// if (y < 0.25 || y > -.25) {
		// y = y;
		// } else {
		// x = .5 * x;
		// }
		// // x = x * x * x;
		// y = y * y * y;

		double wheelNonLinearity;

		wheel = handleDeadband(wheel, .10);
		throttle = handleDeadband(throttle, .10);

		double negInertia = wheel - oldWheel;
		oldWheel = wheel;
		wheelNonLinearity = 0.5;
		// Apply a sin function that's scaled to make it feel better.
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);
		wheel = Math.sin(Math.PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(Math.PI / 2.0 * wheelNonLinearity);

		double leftPwm, rightPwm, overPower;
		double sensitivity;

		double angularPower;
		double linearPower;

		// Negative inertia!
		double negInertiaAccumulator = 0.0;
		double negInertiaScalar;
		if (wheel * negInertia > 0) {
			negInertiaScalar = 2.5;
		} else {
			if (Math.abs(wheel) > 0.65) {
				negInertiaScalar = 5.0;
			} else {
				negInertiaScalar = 3.0;
			}
		}
		double negInertiaPower = negInertia * negInertiaScalar;
		negInertiaAccumulator += negInertiaPower;

		wheel = wheel + negInertiaAccumulator;
		if (negInertiaAccumulator > 1) {
			negInertiaAccumulator -= 1;
		} else if (negInertiaAccumulator < -1) {
			negInertiaAccumulator += 1;
		} else {
			negInertiaAccumulator = 0;
		}
		linearPower = throttle;

		overPower = 0.0;
		angularPower = Math.abs(throttle) * wheel * - quickStopAccumulator;
		if (quickStopAccumulator > 1) {
			quickStopAccumulator -= 1;
		} else if (quickStopAccumulator < -1) {
			quickStopAccumulator += 1;
		} else {
			quickStopAccumulator = 0.0;
		}

		rightPwm = leftPwm = linearPower;
		leftPwm += angularPower;
		rightPwm -= angularPower;

		if (leftPwm > 1.0) {
			rightPwm -= overPower * (leftPwm - 1.0);
			leftPwm = 1.0;
		} else if (rightPwm > 1.0) {
			leftPwm -= overPower * (rightPwm - 1.0);
			rightPwm = 1.0;
		} else if (leftPwm < -1.0) {
			rightPwm += overPower * (-1.0 - leftPwm);
			leftPwm = -1.0;
		} else if (rightPwm < -1.0) {
			leftPwm += overPower * (-1.0 - rightPwm);
			rightPwm = -1.0;
		}

		Robot.drivetrain.setL(throttle + wheel);
		Robot.drivetrain.setR(throttle - wheel);
		// System.out.println("X: " + x);
		// System.out.println("Y: " + y);

	}

	public double handleDeadband(double val, double deadband) {
		return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
