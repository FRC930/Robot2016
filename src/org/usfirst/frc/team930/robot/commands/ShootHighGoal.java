
package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootHighGoal extends Command {
	
    public ShootHighGoal() {
    	System.out.println("Making Command");
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Beginning of Execute");

    	double setPoint = Robot.prefs.getDouble("Rpm", 0);
    	double accel = Robot.prefs.getDouble("Accel", 0);
    	
    	setPoint = 	1000000;
    	accel = 1;
    	
    	Robot.shooter.enable();
    	
    	System.out.println("Accel: "+accel);
    	System.out.println("setPoint: "+setPoint);

		Robot.shooter.setAccel(accel);
    	Robot.shooter.setShooter(setPoint);
    	// warm up - adjust value later
    	Timer.delay(.1);
		//System.out.println("Shooter Wheel Spinning");
		
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.disable();
    }
}