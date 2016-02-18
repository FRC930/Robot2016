package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftIntake extends Command {

	IntakeLifter.Position command;

	public LiftIntake(IntakeLifter.Position c) {
		requires(Robot.intakeLifter);
		command = c;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.intakeLifter.setintakeLifter(command.getAngle());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.intakeLifter.setintakeLifter(0);
	}
}
