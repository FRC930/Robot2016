package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveForward extends Command {
	final int START = 0;
	final int DRIVE = 1;
	final int TURNING_OFF = 2;
	final int END = 3; 
	int state = 0;
	final double driveSpeed = 1;
	Timer timer = new Timer();
	double startTime; // time the command starts running (seconds)
	double currentTime; // the current time (seconds)

    public AutoDriveForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.intakeLifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	state = START;
    	Robot.drivetrain.drivePID.setSetpoint(0.0);
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(state == START){
    		startTime = timer.get(); // gets the starting time in seconds
    		state = DRIVE;
    		Robot.drivetrain.drivePID.enable();
    	}
    	
    	else if(state == DRIVE){
    		currentTime = timer.get();
    		Robot.drivetrain.setL(driveSpeed);
			Robot.drivetrain.setR(driveSpeed);
    		if(currentTime - startTime >= 3){
    			state = TURNING_OFF;
    		}
    	}
    	
    	else if(state == TURNING_OFF){
    		Robot.drivetrain.setL(0);
    		Robot.drivetrain.setR(0);
    		Robot.drivetrain.drivePID.disable();
    		state = END;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (state == END);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
