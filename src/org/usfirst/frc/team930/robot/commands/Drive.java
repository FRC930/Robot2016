
package org.usfirst.frc.team930.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain;

/** 
 *
 */
public class Drive extends Command {

    public Drive() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.Drivetrain1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double x = OI.getXAxis();
    	double y = OI.getYAxis();
    	x = x * x;
    	y = y * y;
    	
    	Robot.Drivetrain1.setL(y + x);
    	Robot.Drivetrain1.setR(y - x);
    	
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
