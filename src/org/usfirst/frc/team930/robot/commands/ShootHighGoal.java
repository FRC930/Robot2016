package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotConstants;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;
import org.usfirst.frc.team930.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootHighGoal extends Command {

	private enum State {
		START,
		START_2,
		START_3,
		START_4,
		SHOOTER_SPEED_UP_1_WAIT,
		SHOOTER_SPEED_UP_2_WAIT,
		SHOOTER_SPEED_UP_3_WAIT,
		SHOOTER_SPEED_UP_4_WAIT,
		INTAKE_FORWARD,
		INTAKE_FORWARD_WAIT,
		INTAKE_OFF,
		WAIT_THREE,
		SHOOTER_OFF, END;
	}

	Timer timer = new Timer();
	double startTime; // time the command starts running (seconds)
	double currentTime; // the current time (seconds)
	State state; // state of shooting, from 1 to 6
	
	// TIMES -----------------------------------------------
	public static final double SHOOTER_SPEED_UP_1_TIME = .1; 
	public static final double SHOOTER_SPEED_UP_2_TIME = .1; 
	public static final double SHOOTER_SPEED_UP_3_TIME = .1; 
	public static final double SHOOTER_SPEED_UP_4_TIME = 2; 
	public static final double INTAKE_FORWARD_TIME = 2; 

	public ShootHighGoal() {
		System.out.println("Making Command");
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.shooter);
		requires(Robot.intakeRoller);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer.start();
		state = State.START; // initializes the state
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		switch (state) {
		
		case START:
			/*
			 * STATE 1 - set shooter speed - set start time
			 */
			// turn on shooter wheels
			Robot.shooter.enable();
			Robot.shooter.setShooter(.25 * RobotConstants.shootHighGoalRPM);
			startTime = timer.get(); // gets the starting time in seconds
			state = State.SHOOTER_SPEED_UP_1_WAIT; // move onto the next state
			Robot.shooter.print();
			break;
		case SHOOTER_SPEED_UP_1_WAIT:
			/*
			 * STATE 2 - get current time - wait 1 second
			 */
			currentTime = timer.get(); // gets current time
			// after a total 1 second, the state moves on
			if ((currentTime - startTime) >= SHOOTER_SPEED_UP_1_TIME) {

				state = State.START_2;
			}
			Robot.shooter.print();
			break;
		case START_2:
			// turn on shooter wheels
			Robot.shooter.setShooter(.5 * RobotConstants.shootHighGoalRPM);
			state = State.SHOOTER_SPEED_UP_2_WAIT; // move onto the next state
			Robot.shooter.print();
			break;
		case SHOOTER_SPEED_UP_2_WAIT:
			currentTime = timer.get(); // gets current time
			// after a total 1 second, the state moves on
			if ((currentTime - startTime) >= (SHOOTER_SPEED_UP_1_TIME + SHOOTER_SPEED_UP_2_TIME)) {

				state = State.START_3;
			}
			Robot.shooter.print();
			break;
		case START_3:
			// turn on shooter wheels
			Robot.shooter.setShooter(.75 * RobotConstants.shootHighGoalRPM);
			state = State.SHOOTER_SPEED_UP_3_WAIT; // move onto the next state
			Robot.shooter.print();
			break;
		case SHOOTER_SPEED_UP_3_WAIT:
			currentTime = timer.get(); // gets current time
			// after a total 1 second, the state moves on
			if ((currentTime - startTime) >= (SHOOTER_SPEED_UP_1_TIME + SHOOTER_SPEED_UP_2_TIME + SHOOTER_SPEED_UP_3_TIME)) {

				state = State.START_4;
			}
			Robot.shooter.print();
			break;
		case START_4:
			// turn on shooter wheels
			Robot.shooter.setShooter(RobotConstants.shootHighGoalRPM);
			state = State.SHOOTER_SPEED_UP_4_WAIT; // move onto the next state
			Robot.shooter.print();
			break;
		case SHOOTER_SPEED_UP_4_WAIT:
			currentTime = timer.get(); // gets current time
			Robot.shooter.setShooter(RobotConstants.shootHighGoalRPM);
			// after a total 1 second, the state moves on
			if ((currentTime - startTime) >= (SHOOTER_SPEED_UP_1_TIME + SHOOTER_SPEED_UP_2_TIME + SHOOTER_SPEED_UP_3_TIME + SHOOTER_SPEED_UP_4_TIME)) {

				state = State.INTAKE_FORWARD;
			}
			Robot.shooter.print();
			break;
		case INTAKE_FORWARD:
			/*
			 * STATE 3 - set intake rollers to forward
			 */

			// intake rollers move forward
			Robot.intakeRoller.setState(IntakeRoller.Direction.FORWARD);
			System.out.println("                                   STATE 3");
			state = State.INTAKE_FORWARD_WAIT;
			Robot.shooter.print();
			break;
		case INTAKE_FORWARD_WAIT:
			/*
			 * STATE 4 - once 1 second has passed, the state moves on
			 */

			currentTime = timer.get(); // gets current time
			System.out.println("                                   STATE 4");
			if ((currentTime - startTime) >= (SHOOTER_SPEED_UP_1_TIME + SHOOTER_SPEED_UP_2_TIME + SHOOTER_SPEED_UP_3_TIME + SHOOTER_SPEED_UP_4_TIME + INTAKE_FORWARD_TIME)) {
				state = State.INTAKE_OFF;
			}
			Robot.shooter.print();
			break;
		case INTAKE_OFF:
			/*
			 * STATE 5 - intake rollers are set to off
			 */

			// the intake rollers shut off
			Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
			System.out.println("                                   STATE 5");
			state = State.SHOOTER_OFF;
			Robot.shooter.print();
			break;
		case SHOOTER_OFF:
			/*
			 * STATE 7 - turn off shooter
			 */
			Robot.shooter.setShooter(0); // shooter is set to 0 rpm
			System.out.println("                                   STATE 7");
			state = State.END;
			Robot.shooter.print();
			break;
		default:
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (state == State.END);
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.shooter.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		System.out.println("INTERUPTED INTERUPTED INTERUPTED INTERUPTED INTERUPTED INTERUPTED INTERUPTED INTERUPTED");
		Robot.shooter.setShooter(0);
		Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
	}
}