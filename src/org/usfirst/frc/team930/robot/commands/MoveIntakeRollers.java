package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller.Direction;

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
		System.out.println("Making new Command");
		requires(Robot.intakeRoller);
		command = d;
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		/*if (Robot.intakeRoller.getState() != IntakeRoller.Direction.STOP) {
			System.out.println("Toggling");
			Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
		} else {
			System.out.println("Setting Command");
			Robot.intakeRoller.setState(command);
		}*/
		Robot.intakeRoller.setState(Direction.FORWARD);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		System.out.println("Checking");
		return false;//Robot.intakeRoller.seeBall();
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("Stopping");
		Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		System.out.println("Interrupted");
		Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
	}
}