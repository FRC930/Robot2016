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

	public enum State {
		START,
		SHOOTER_SPEED_UP_1_WAIT,
		SHOOTER_SPEED_UP_2_WAIT,
		SHOOTER_SPEED_UP_3_WAIT,
		SHOOTER_SPEED_UP_4_WAIT,
		INTAKE_FORWARD_WAIT,
		INTAKE_SHOOTER_OFF,
		END;
	}	

	Timer timer = new Timer();
	double startTime; // time the command starts running (seconds)
	double currentTime; // the current time (seconds)
	State state; // state of shooting, from 1 to 6
	
	// TIMES -----------------------------------------------
	public static final double SHOOTER_SPEED_UP_1_TIME = .1; 
	public static final double SHOOTER_SPEED_UP_2_TIME = .1; 
	public static final double SHOOTER_SPEED_UP_3_TIME = .1; 
	public static final double SHOOTER_SPEED_UP_4_TIME = 1; 
	public static final double INTAKE_FORWARD_TIME = 2; 

	public ShootHighGoal() {
		System.out.println("Making Command");
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.shooter);
		requires(Robot.intakeRoller);
		requires(Robot.drivetrain);
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
			Robot.shooter.enableTalons();
			Robot.shooter.setShooter(.25 * RobotConstants.shootHighGoalRPM);
			Robot.drivetrain.setL(.2);
			Robot.drivetrain.setR(.2);
			startTime = timer.get(); // gets the starting time in seconds
			state = State.SHOOTER_SPEED_UP_2_WAIT; // move onto the next state
			Robot.shooter.print();
			
			break;
			
		case SHOOTER_SPEED_UP_1_WAIT:
			/*
			 * STATE 2 - get current time - wait 1 second
			 */
			Robot.shooter.setShooter(.25 * RobotConstants.shootHighGoalRPM);
			currentTime = timer.get(); // gets current time
			// after a total 1 second, the state moves on
			if ((currentTime - startTime) >= SHOOTER_SPEED_UP_1_TIME) {
				startTime = timer.get();
				state = State.SHOOTER_SPEED_UP_2_WAIT;
			}
			Robot.shooter.print();
			break;
			
		case SHOOTER_SPEED_UP_2_WAIT:
			currentTime = timer.get(); // gets current time
			Robot.shooter.setShooter(.5 * RobotConstants.shootHighGoalRPM);
			// after a total 1 second, the state moves on
			if ((currentTime - startTime) >= SHOOTER_SPEED_UP_2_TIME) {
				startTime = timer.get();
				state = State.SHOOTER_SPEED_UP_4_WAIT;
			}
			Robot.shooter.print();
			break;
			
		case SHOOTER_SPEED_UP_3_WAIT:
			currentTime = timer.get(); // gets current time
			Robot.shooter.setShooter(.75 * RobotConstants.shootHighGoalRPM);
			// after a total 1 second, the state moves on
			if ((currentTime - startTime) >= SHOOTER_SPEED_UP_3_TIME) {
				startTime = timer.get();
				state = State.SHOOTER_SPEED_UP_4_WAIT;
			}
			Robot.shooter.print();
			break;
			
		case SHOOTER_SPEED_UP_4_WAIT:
			currentTime = timer.get(); // gets current time
			Robot.shooter.setShooter(RobotConstants.shootHighGoalRPM);
			// after a total 1 second, the state moves on
			if ((currentTime - startTime) >= SHOOTER_SPEED_UP_4_TIME) {
				startTime = timer.get();
				state = State.INTAKE_FORWARD_WAIT;
			}
			Robot.shooter.print();
			break;
			
		case INTAKE_FORWARD_WAIT:
			/*
			 * STATE 3 - once 1 second has passed, the state moves on
			 */
			Robot.shooter.setShooter(RobotConstants.shootHighGoalRPM);
			Robot.intakeRoller.setState(IntakeRoller.Direction.SHOOTERPULL);
			currentTime = timer.get(); // gets current time
			System.out.println("                                   STATE 3");
			if ((currentTime - startTime) >= INTAKE_FORWARD_TIME) {
				startTime = timer.get();
				state = State.INTAKE_SHOOTER_OFF;
			}
			Robot.shooter.print();
			break;
			
		case INTAKE_SHOOTER_OFF:
			/*
			 * STATE 4 - turn off shooter
			 */
			Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
			Robot.shooter.disableTalons();
			//Robot.shooter.setShooter(0); // shooter is set to 0 rpm
			Robot.drivetrain.setL(0);
			Robot.drivetrain.setR(0);
			System.out.println("                                   STATE 4");
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
		Robot.shooter.disableTalons();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		System.out.println("INTERUPTED INTERUPTED INTERUPTED INTERUPTED INTERUPTED INTERUPTED INTERUPTED INTERUPTED");
		Robot.shooter.setShooter(0);
		Robot.shooter.disableTalons();
		Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
	}
}