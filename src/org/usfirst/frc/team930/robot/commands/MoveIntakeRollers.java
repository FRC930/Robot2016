package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveIntakeRollers extends Command {

	IntakeRoller.Direction command;

	public MoveIntakeRollers(IntakeRoller.Direction d) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		requires(Robot.intakeRoller);
		command = d;
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		if(Robot.intakeRoller.getState() != IntakeRoller.Direction.STOP) {
			Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
		} else {
			Robot.intakeRoller.setState(command);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.intakeRoller.seeBall();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
	}
}