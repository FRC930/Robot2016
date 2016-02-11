package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {
	
    public AutoDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*double Kp = 0.03;
    	
    	Robot.drivetrain.gyro.reset();
    	
    	double angle = Robot.drivetrain.gyro.getAngle();            // get current heading
        Robot.drivetrain.drive(-1.0, -angle*Kp);                    // drive towards heading 0
        Timer.delay(0.005);
    	*/
        //Robot.drivetrain.ultra.setAutomaticMode(true);
        //double range = Robot.drivetrain.ultra.getRangeInches();
        
    
    
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
