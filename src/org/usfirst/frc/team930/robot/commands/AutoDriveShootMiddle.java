package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveShootMiddle extends Command {
	
	Timer timer = new Timer();
	
	final int START = 0;
	final int DRIVE = 1;
	final int SHOOT = 2;
	final int TURNING_OFF = 3;
	final int END = 4;
	int state = 0;
	
	double startTime; // time the command starts running (seconds)
	double currentTime; // the current time (seconds)
	
    public AutoDriveShootMiddle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.shooter);
    	requires(Robot.intakeRoller);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.drivePID.setSetpoint(0.0);
    	state = START;
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int count = 0;
    	Robot.drivetrain.drivePID.enable();
    	if(state == START){
    		startTime = timer.get(); // gets the starting time in seconds
    		state = DRIVE;
    	}
    	
    	else if(state == DRIVE){
    		Robot.drivetrain.drivePID.enable();
    		currentTime = timer.get();
    		Robot.drivetrain.setL(1);
			Robot.drivetrain.setR(1);
			
    		if(currentTime - startTime >= 6){
    			state = SHOOT;
    		}
    	}
    	
    	else if(state == SHOOT){
    		Robot.drivetrain.drivePID.enable();
    		count++;
    		Robot.shooter.setShooter(1);
    		if(count >= 50){
    			Robot.intakeRoller.setState(IntakeRoller.Direction.FORWARD);
    		}
    		if(count >= 150){
    			state = TURNING_OFF;
    		}
    	}
    	else if(state == TURNING_OFF){
    		Robot.drivetrain.drivePID.disable();
    		Robot.shooter.setShooter(0);
    		Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
    		count = 0;
    		state = END;
    	}
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return state == END;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
