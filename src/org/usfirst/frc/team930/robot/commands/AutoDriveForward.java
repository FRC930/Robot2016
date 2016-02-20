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
	final int END = 2;
	int state = 0;
	Timer timer = new Timer();
	double startTime; // time the command starts running (seconds)
	double currentTime; // the current time (seconds)

    public AutoDriveForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	state = START;
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(state == START){
    		startTime = timer.get(); // gets the starting time in seconds
    		state = DRIVE;
    	}
    	
    	else if(state == DRIVE){
    		currentTime = timer.get();
    		Robot.drivetrain.setL(1);
			Robot.drivetrain.setR(1);
    		if(currentTime - startTime >= 3){
    			state = END;
    		}
    	}
    	
    	else if(state == END){
    		Robot.drivetrain.setL(0);
    		Robot.drivetrain.setR(0);
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
