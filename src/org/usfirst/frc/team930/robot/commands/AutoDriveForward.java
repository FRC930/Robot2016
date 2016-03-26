package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotConstants;

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
	
	
	Timer timer = new Timer();
	double startTime; // time the command starts running (seconds)
	double currentTime; // the current time (seconds)
	
	// TIMES -----------------------------------------------
	public static final double DRIVE_FORWARD_TIME = 2; 
	public static final double DRIVE_STOP_TIME = 1; 
	public static final double DRIVE_BACKWARD_TIME = 1.5; 

    public AutoDriveForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.intakeLifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	state = START;
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
    		Robot.drivetrain.setL(RobotConstants.autoDriveForwardSpeed);
			Robot.drivetrain.setR(RobotConstants.autoDriveForwardSpeed);
    		if(currentTime - startTime >= DRIVE_FORWARD_TIME){
    			state = TURNING_OFF;
    		}
    	}
    	
    	else if(state == WAIT){
    		System.out.println("State is wait");
    		currentTime = timer.get();
    		Robot.drivetrain.setL(0);
			Robot.drivetrain.setR(0);
    		if(currentTime - startTime >= (DRIVE_FORWARD_TIME + DRIVE_STOP_TIME)){
    			state = DRIVE_BACKWARD;
    		}
    	}
    	
    	else if(state == DRIVE_BACKWARD){
    		System.out.println("State is drive backward");
    		currentTime = timer.get();
    		Robot.drivetrain.setL(RobotConstants.autoDriveBackwardSpeed);
			Robot.drivetrain.setR(RobotConstants.autoDriveBackwardSpeed);
    		if(currentTime - startTime >= (DRIVE_FORWARD_TIME + DRIVE_STOP_TIME + DRIVE_BACKWARD_TIME)){
    			state = TURNING_OFF;
    		}
    	}
    	
    	else if(state == TURNING_OFF){
    		System.out.println("State is turning off");
    		Robot.drivetrain.setL(0);
    		Robot.drivetrain.setR(0);
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
