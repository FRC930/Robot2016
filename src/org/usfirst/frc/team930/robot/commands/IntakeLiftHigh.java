package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotConstants;
import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 */
public class IntakeLiftHigh extends Command {

    public IntakeLiftHigh() {
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
    	Robot.intakeLifter.setAngle(IntakeLifter.Positions.DEFAULT);
    	if(Robot.intakeLifter.getPOT() > (RobotConstants.intakeLifterDEFAULT-25) && (Robot.intakeLifter.getPOT() < RobotConstants.intakeLifterDEFAULT)){
    		Robot.intakeLifter.PID.disable();
    	}
    	//this makes the PDI run the intakelifter until it gets to the default position
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakeLifter.setAngle(IntakeLifter.Positions.DEFAULT);
    	//when the command ends it will stay at the same position
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intakeLifter.setAngle(IntakeLifter.Positions.DEFAULT);
    	//when a command takes over the position will stay the same
    }
}