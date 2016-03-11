package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotConstants;
import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManualIntakeDown extends Command {

    public ManualIntakeDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intakeLifter.setSpeed(RobotConstants.manualIntakeLifterdown);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeLifter.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakeLifter.setSpeed(0);
    }
}
