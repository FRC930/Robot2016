package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLowBarShoot extends Command {

	Timer timer = new Timer();
	Timer timer2 = new Timer();

	final int START = 0;
	final int DRIVE = 1;
	final int TURN = 2;
	final int DRIVE_2 = 3;
	final int SHOOT = 4;
	final int TURNING_OFF = 5;
	final int END = 6;
	int state = 0;

	double startTime; // time the command starts running (seconds)
	double currentTime; // the current time (seconds)
	double startTime2; // time the command starts running (seconds)
	double currentTime2; // the current time (seconds)

	public AutoLowBarShoot() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		requires(Robot.shooter);
		requires(Robot.intakeRoller);
		requires(Robot.intakeLifter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.drivePID.setSetpoint(0.0);
		state = START;
		
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		Robot.drivetrain.drivePID.enable();
		if (state == START) {
			currentTime = timer.get(); // gets the current time
			Robot.intakeLifter.setAngle(IntakeLifter.Positions.PICKUP);
			state = DRIVE;
		}
		if (state == DRIVE) {
			timer2.start();
			startTime2 = timer2.get();
			Robot.drivetrain.setL(0.25);
			Robot.drivetrain.setL(0.25);
			currentTime2 = timer2.get();
			if (currentTime2 - startTime2 >= 4 && Robot.drivetrain.distance.getRangeInches() <=  48) {
				state = TURN;
			}
		}
		if (state == TURN) {
			Robot.drivetrain.drivePID.setSetpoint(60.0);
			currentTime = timer.get();
			if (Robot.drivetrain.distance.getRangeInches() > 70) {
				state = DRIVE_2;
			}
		}
		if (state == DRIVE_2) {
			Robot.drivetrain.setL(0.6);
			Robot.drivetrain.setR(0.6);
			currentTime = timer.get();
			if (Robot.drivetrain.distance.getRangeInches() <=  5) {
				Robot.drivetrain.setL(0);
				Robot.drivetrain.setR(0);
				state = SHOOT;
			}
		}
		if (state == SHOOT) {
			Robot.shooter.setShooter(0.5 * 6000);
			timer.start();
			startTime = timer.get();
			currentTime = timer.get();
			if (currentTime - startTime > 0.1) {
				Robot.shooter.setShooter(0.75 * 6000);
			}
			currentTime = timer.get();
			if (currentTime - startTime > 0.2) {
				Robot.shooter.setShooter(6000);
			}
			currentTime = timer.get();
			if (currentTime - startTime > 3.3) {
				Robot.intakeRoller.setState(IntakeRoller.Direction.SHOOTERPULL);
				state = TURNING_OFF;
			}
			
		}
		
		if (state == TURNING_OFF) {
			Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
			state = END;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return state == END;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
