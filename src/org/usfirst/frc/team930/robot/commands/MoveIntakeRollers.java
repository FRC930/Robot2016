package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveIntakeRollers extends Command {

	String intakeOn = "Off";
	
    public MoveIntakeRollers() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakeRoller);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(intakeOn.equals("Off")) intakeOn = "On";
    	else if(intakeOn.equals("On")) intakeOn = "Off";
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(intakeOn);

    	
    	if(intakeOn.equals("Off"))
    		{Robot.intakeRoller.setintakeRoller(0); 
    		SmartDashboard.putString("Direction of Rollers", "Pulling In: Waitning for Light Sensor");
    		}

    	else if(intakeOn.equals("On")) Robot.intakeRoller.setintakeRoller(1);
    	
    	
		System.out.println("Pulling in ball");
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
