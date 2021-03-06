package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotConstants;
import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain.Positions;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLowBar extends Command {
	final int START_ARM = 0;
	final int DRIVE = 1;
	final int STOP = 2;
	final int END = 3;
	int state = 0;
	Timer timer = new Timer();
	double startTime = 0;
	double currentTime = 0;
	
	// TIMES -----------------------------------------------
	public static final double DRIVE_TIME = 4.3;  

	public AutoLowBar() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.intakeLifter);
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.resetGyro();
		timer.start();
		state = START_ARM;
		startTime = timer.get();
		Robot.drivetrain.throttleInt.useJoystick(false);
		Robot.drivetrain.throttleInt.setThrottle(RobotConstants.autoLowBardriveSpeed);
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		currentTime = timer.get(); // gets the current time

		if (state == START_ARM) { // goes to the first stage to move arm
			Robot.intakeLifter.setAngle(IntakeLifter.Positions.PICKUP);
			state = DRIVE;
			Robot.drivetrain.drivePID.setSetpoint(Robot.drivetrain.getGoalAngle(Positions.STRAIGHT));
		}
		if (state == DRIVE) { // goes to the 2nd stage where it drives
			Robot.drivetrain.drivePID.enable();
//			Robot.drivetrain.setR(RobotConstants.autoLowBardriveSpeed);
//			Robot.drivetrain.setL(RobotConstants.autoLowBardriveSpeed);

			if (currentTime - startTime >=  DRIVE_TIME) { // goes to the next stage after
												// 5secs
				state = STOP;
			}
		}
		if (state == STOP) { // the next stage turns off the motors
			Robot.drivetrain.setR(0);
			Robot.drivetrain.setL(0);
			Robot.drivetrain.drivePID.disable();
			state = END;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return state == END; // ends when everything ran
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.drivePID.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drivetrain.drivePID.disable();
	}
}
