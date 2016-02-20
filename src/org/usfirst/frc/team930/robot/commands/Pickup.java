package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team930.robot.Robot;
/**
 *
 */
public class Pickup extends Command {

	
	
    public Pickup() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.intakeRoller);
    	requires(Robot.intakeLifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intakeLifter.setAngle(IntakeLifter.Position.LOW);
    	Robot.intakeRoller.setState(IntakeRoller.Direction.FORWARD);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.intakeRoller.seeBall();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeLifter.setAngle(IntakeLifter.Position.HIGH);
    	Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakeLifter.setAngle(IntakeLifter.Position.HIGH);
    	Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
    }
}
