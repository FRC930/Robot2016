package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShootLowGoal extends Command {
	String intakeOuton = "off";
    public ShootLowGoal() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeRoller);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(intakeOuton.equals("off")) intakeOuton = "on";
    	else if(intakeOuton.equals("on")) intakeOuton ="off";
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(intakeOuton.equals("on"))Robot.intakeRoller.setintakeRoller(-1.0);
    	else if(intakeOuton.equals("off"))Robot.intakeRoller.setintakeRoller(0);
    	
		System.out.println("Pushing Out Ball");
		SmartDashboard.putString("Direction of Rollers", "Pushing Out");

		
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
    	Robot.intakeRoller.setintakeRoller(0);
    }
}
