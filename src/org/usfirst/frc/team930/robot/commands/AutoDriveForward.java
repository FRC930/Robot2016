package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveForward extends Command {
	final int START = 0;
	final int DRIVE_FORWARD = 1;
	final int WAIT = 2;
	final int DRIVE_BACKWARD = 3;
	final int TURNING_OFF = 4;
	final int END = 5; 
	int state = 0;
	final double driveSpeedForward = 1;
	final double driveSpeedBackward = -1;
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
    	startTime = timer.get(); // gets the starting time in seconds
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println(timer.get() - startTime);
    	if(state == START){
    		state = DRIVE_FORWARD;
    		//Robot.drivetrain.drivePID.enable();
    	}
    	
    	else if(state == DRIVE_FORWARD){
    		System.out.println("State is drive forward");
    		currentTime = timer.get();
    		Robot.drivetrain.setL(driveSpeedForward);
			Robot.drivetrain.setR(driveSpeedForward);
    		if(currentTime - startTime >= 2){
    			state = END;
    		}
    	}
    	
    	else if(state == WAIT){
    		System.out.println("State is wait");
    		currentTime = timer.get();
    		Robot.drivetrain.setL(0);
			Robot.drivetrain.setR(0);
    		if(currentTime - startTime >= 3){
    			state = DRIVE_BACKWARD;
    		}
    	}
    	
    	else if(state == DRIVE_BACKWARD){
    		System.out.println("State is drive backward");
    		currentTime = timer.get();
    		Robot.drivetrain.setL(driveSpeedBackward);
			Robot.drivetrain.setR(driveSpeedBackward);
    		if(currentTime - startTime >= 4.5){
    			state = TURNING_OFF;
    		}
    	}
    	
    	else if(state == TURNING_OFF){
    		System.out.println("State is turning off");
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
