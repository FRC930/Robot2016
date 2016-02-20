package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;
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
		WAIT_ONE,
		INTAKE_FORWARD,
		NO_BALL,
		INTAKE_OFF,
		WAIT_THREE,
		SHOOTER_OFF, END;
	}

	Timer timer = new Timer();
	double startTime; // time the command starts running (seconds)
	double currentTime; // the current time (seconds)
	State state; // state of shooting, from 1 to 6

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
			Robot.shooter.setShooter(Shooter.HIGH_GOAL_RPM);
			startTime = timer.get(); // gets the starting time in seconds
			state = State.WAIT_ONE; // move onto the next state
			break;
		case WAIT_ONE:
			/*
			 * STATE 2 - get current time - wait 1 second
			 */
			currentTime = timer.get(); // gets current time
			// after a total 1 second, the state moves on
			if ((currentTime - startTime) >= 1) {

				state = State.INTAKE_FORWARD;
			}
			break;
		case INTAKE_FORWARD:
			/*
			 * STATE 3 - set intake rollers to forward
			 */

			// intake rollers move forward
			Robot.intakeRoller.setState(IntakeRoller.Direction.FORWARD);
			state = State.NO_BALL;
			break;
		case NO_BALL:
			/*
			 * STATE 4 - ball sensor must be false (the ball isn't in the
			 * intake)
			 */

			// once the ball isn't in the intake, the state moves on
			if (Robot.intakeRoller.seeBall() == false) {
				state = State.INTAKE_OFF;
			}
			break;
		case INTAKE_OFF:
			/*
			 * STATE 5 - intake rollers are set to off
			 */

			// the intake rollers shut off
			Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
			state = State.WAIT_THREE;
			break;
		case WAIT_THREE:
			/*
			 * STATE 6 - get current time - wait 2 more seconds (3 total)
			 */
			currentTime = timer.get(); // gets current time
			// after a total 3 seconds, the state moves on
			if ((currentTime - startTime) >= 3) {
				state = State.SHOOTER_OFF;
			}
			break;
		case SHOOTER_OFF:
			/*
			 * STATE 7 - turn off shooter
			 */
			Robot.shooter.setShooter(0); // shooter is set to 0 rpm
			state = State.END;
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
		Robot.shooter.setShooter(0);
		Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
	}
}