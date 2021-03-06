package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeLiftPort extends Command {

    public IntakeLiftPort() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeLifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intakeLifter.PID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intakeLifter.setAngle(IntakeLifter.Positions.PORT);
//    	if(Robot.intakeLifter.getPOT() > 47 && Robot.intakeLifter.getPOT() < 52){
//    		Robot.intakeLifter.PID.disable();
//    	}
        // Make this return true when this Command no longer needs to run execute()
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeLifter.setAngle(IntakeLifter.Positions.PORT);
    	//when the command ends it will stay at the same position
    	Robot.intakeLifter.PID.disable();

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakeLifter.setAngle(IntakeLifter.Positions.PORT);
    	//when a command takes over the position will stay the same
    	Robot.intakeLifter.PID.disable();
    }
}