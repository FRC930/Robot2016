package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrivePort extends Command {
	final int START = 0;
	final int DRIVE_1 = 1;
	final int LOWER_INTAKE_AND_DRIVE = 2;
	final int END = 3;
	int state = 0;
	Timer timer = new Timer();
	double startTime; // time the command starts running (seconds)
	double currentTime; // the current time (seconds)

    public AutoDrivePort() {
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
    		state = DRIVE_1;
    	}
    	
    	else if(state == DRIVE_1){
    		currentTime = timer.get();
    		Robot.drivetrain.setL(1);
			Robot.drivetrain.setR(1);
    		if(currentTime - startTime >= 3){
    			state = LOWER_INTAKE_AND_DRIVE;
    		}
    	}
    	
    	else if(state == LOWER_INTAKE_AND_DRIVE){
    		currentTime = timer.get();
    		Robot.intakeLifter.setAngle(IntakeLifter.Positions.PORT);

    		Robot.drivetrain.setL(1);
			Robot.drivetrain.setR(1);
			if(currentTime - startTime >= 3){
				state = END;
			}
    	}
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
