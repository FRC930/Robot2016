
package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;
import org.usfirst.frc.team930.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootHighGoal extends Command {
	
	private static final int START_STATE = 1;
	private static final int WAIT_ONE_STATE = 2;
	private static final int INTAKE_FORWARD_STATE = 3;
	private static final int NO_BALL_STATE = 4;
	private static final int INTAKE_OFF_STATE = 5;
	private static final int WAIT_THREE_STATE = 6;
	private static final int SHOOTER_OFF_STATE = 7;
	private static final int END_STATE = 8;
	
	Timer timer = new Timer();
	double startTime; // time the command starts running (seconds)
	double currentTime; // the current time (seconds)
	int state; // state of shooting, from 1 to 6

    public ShootHighGoal() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    	requires(Robot.intakeRoller);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	state = 1; // initializes the state
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(state == START_STATE){
    		/* STATE 1
    		 * - set shooter speed 
    		 * - set start time
    		 */
    		Robot.shooter.setShooter(Shooter.HIGH_GOAL_RPM); // turn on shooter wheels	
    		startTime = timer.get(); // gets the starting time in seconds
    		state = WAIT_ONE_STATE; // move onto the next state
    	}else if(state == WAIT_ONE_STATE){
    		/* STATE 2
    		 * - get current time
    		 * - wait 1 second 
    		 */
    		currentTime = timer.get(); // gets current time
    		if((currentTime - startTime) >= 1){ // after a total 1 second, the state moves on
    			state = INTAKE_FORWARD_STATE;
    		}
    	}else if(state == INTAKE_FORWARD_STATE){
    		/* STATE 3
    		 * - set intake rollers to forward
    		 */
    		Robot.intakeRoller.setState(IntakeRoller.Direction.FORWARD); // intake rollers move forward
    		state = NO_BALL_STATE;
    	}else if(state == NO_BALL_STATE){
    		/* STATE 4
    		 * - ball sensor must be false (the ball isn't in the intake)
    		 */
    		if(Robot.intakeRoller.seeBall() == false){ // once the ball isn't in the intake, the state moves on
    			state = INTAKE_OFF_STATE;
    		}
    	}else if(state == INTAKE_OFF_STATE){
    		/* STATE 5
    		 * - intake rollers are set to off
    		 */
    		Robot.intakeRoller.setState(IntakeRoller.Direction.STOP); // the intake rollers shut off
    		state = WAIT_THREE_STATE;
    	}else if(state == WAIT_THREE_STATE){
    		/* STATE 6
    		 * - get current time
    		 * - wait 2 more seconds (3 total)
    		 */
    		currentTime = timer.get(); // gets current time
    		if((currentTime - startTime) >= 3){ // after a total 3 seconds, the state moves on
    			state += 1;
    		}
    	}else if(state == SHOOTER_OFF_STATE){
    		/* STATE 7
    		 * - turn off shooter
    		 */
    		Robot.shooter.setShooter(0); // shooter is set to 0 rpm
    		state = END_STATE;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (state == END_STATE);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.setShooter(0);
    	Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
    }
}