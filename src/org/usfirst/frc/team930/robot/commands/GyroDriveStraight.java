package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GyroDriveStraight extends Command {

	double Kp = 0.03;

	public GyroDriveStraight() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//Robot.drivetrain.drive(-1.0, -Robot.drivetrain.getAngle()*Kp);                    // drive towards heading 0
		
		double angle = Robot.drivetrain.getAngle();

		double x = 0;
		double y = 0;

		if(angle > 0 && angle < 180) {                      // When the robot is facing right
			Robot.drivetrain.setL(-0.5);
			Robot.drivetrain.setR(0.5);
		}

		else {
			if(angle < 180 && angle > 360)  {               // When the robot is facing left
				Robot.drivetrain.setL(0.5);
				Robot.drivetrain.setR(-0.5);	
			}

			else {
				if(angle == 180) {                          // When the robot is facing backwards
					Robot.drivetrain.setL(-0.5);
					Robot.drivetrain.setR(0.5);
				}
			}
		}

		x = x * x * Math.signum(x);
		y = y * y * Math.signum(y);

		//SmartDashboard.putNumber("Angle", angle);
		//SmartDashboard.putNumber("X Value", x);
		//SmartDashboard.putNumber("Y Value", y);

		//Timer.delay(0.005);
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