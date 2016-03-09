package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLowBarShoot extends Command {
	
	Timer timer = new Timer();
	
	final int START = 0;
	final int DRIVE = 1;
	final int SHOOT = 2;
	final int TURNING_OFF = 3;
	final int END = 4;
	int state = 0;
	
	double startTime; // time the command starts running (seconds)
	double currentTime; // the current time (seconds)
	
    public AutoLowBarShoot() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.shooter);
    	requires(Robot.intakeRoller);
    	requires(Robot.intakeLifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.drivePID.setSetpoint(0.0);
    	state = START;
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.drivetrain.drivePID.enable();
    	if(state == START){
    		startTime = timer.get(); // gets the starting time in seconds
    		state = DRIVE;
    	}
    	
    	if(state == DRIVE){
    		Robot.drivetrain.setL(0.25);
    		Robot.drivetrain.setL(0.25);

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
