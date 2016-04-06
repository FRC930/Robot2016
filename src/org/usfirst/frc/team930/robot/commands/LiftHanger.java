package org.usfirst.frc.team930.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotConstants;
import org.usfirst.frc.team930.robot.commands.ShootHighGoal.State;
import org.usfirst.frc.team930.robot.subsystems.HangerLifter;


/**
 *
 */
public class LiftHanger extends Command {
	
	private enum State {
		START_EXTEND,
		DRIVE_BACK,
		LOWER_HANGER,
		END;
	}	

	Timer timer = new Timer();
	double startTime; // time the command starts running (seconds)
	double currentTime; // the current time (seconds)
	State state; // state of shooting, from 1 to 6
	
	// TIMES -----------------------------------------------
	public static final double START_EXTEND_TIME = .1; 
	public static final double DRIVE_BACK_TIME = .1; 
	public static final double LOWER_HANGER_TIME = .1; 
	
    public LiftHanger() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.hangerLifter);
    	requires(Robot.drivetrain);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
		state = State.START_EXTEND; // initializes the state
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.hangerLifter.extend();
		
		switch (state) {
		
		case START_EXTEND:
			
			startTime = timer.get(); // gets the starting time in seconds
			currentTime = timer.get();
			Robot.hangerLifter.extend();
			if ((currentTime - startTime) >= START_EXTEND_TIME) {
				Robot.hangerLifter.stop();
				startTime = timer.get();
				state = State.DRIVE_BACK;
			}
			break;
		
		case DRIVE_BACK:
			
			currentTime = timer.get();
			Robot.drivetrain.setL(-.2);
			Robot.drivetrain.setR(-.2);
			if ((currentTime - startTime) >= DRIVE_BACK_TIME) {
				Robot.drivetrain.setL(0);
				Robot.drivetrain.setR(0);
				startTime = timer.get();
				state = State.LOWER_HANGER;
			}
			break;
			
		case LOWER_HANGER:
			
			currentTime = timer.get();
			Robot.hangerLifter.retract();
			if ((currentTime - startTime) >= DRIVE_BACK_TIME) {
				Robot.hangerLifter.stop();
				startTime = timer.get();
				state = State.END;
			}
			break;
		}
			
			
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (state == State.END);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.hangerLifter.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.hangerLifter.stop();
    }
    
}

