package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotConstants;
import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;
import org.usfirst.frc.team930.robot.subsystems.Shooter;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain.Positions;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoLowBarShoot extends Command {

	Timer timer = new Timer();
	Timer timer2 = new Timer();

	final int START = 0;
	final int DRIVE = 1;
	final int TURN = 2;
	final int DRIVE_2 = 3;
	final int SHOOT = 4;
	final int TURNING_OFF = 5;
	final int END = 6;
	int state = 0;
	ShootingState shootingState = ShootingState.START;
	
	private enum ShootingState {
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

	double startTime; // time the command starts running (seconds)
	double currentTime; // the current time (seconds)
	double startTime2; // time the command starts running (seconds)
	double currentTime2; // the current time (seconds)
	
	// TIMES -----------------------------------------------
	public static final double DRIVE_TIME = 4.6; 
	public static final double TURN_TIME = 2;
	public static final double DRIVE_2_TIME = 3;
	public static final double LOWER_INTAKE_AND_DRIVE_TIME = 3; 
	
	// TIMES -----------------------------------------------
	public static final double SHOOTER_SPEED_UP_1_TIME = .1; 
	public static final double SHOOTER_SPEED_UP_2_TIME = .1; 
	public static final double SHOOTER_SPEED_UP_3_TIME = .1; 
	public static final double SHOOTER_SPEED_UP_4_TIME = 1; 
	public static final double INTAKE_FORWARD_TIME = 2; 

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
		Robot.drivetrain.throttleInt.useJoystick(false);
		Robot.drivetrain.throttleInt.setThrottle(RobotConstants.autoLowBarshootDrivespeed);
		timer2.start();
		startTime2 = timer2.get();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("state = " + state);

		//Robot.drivetrain.drivePID.enable();
		if (state == START) {
			
			Robot.intakeLifter.setAngle(IntakeLifter.Positions.PICKUP);
			state = DRIVE;
		}
		if (state == DRIVE) {

//			Robot.drivetrain.setL(RobotConstants.autoLowBarshootDrivespeed);
//			Robot.drivetrain.setL(RobotConstants.autoLowBarshootDrivespeed);
			Robot.drivetrain.drivePID.enable();
			currentTime2 = timer2.get();
			if (currentTime2 - startTime2 >= DRIVE_TIME /*&& Robot.drivetrain.distance.getRangeInches() <=  RobotConstants.autoLowBarShootdistance1*/) {
				state = TURN;
				Robot.drivetrain.drivePID.disable();
			}
		}
		if (state == TURN) {
			currentTime2 = timer2.get();
			Robot.drivetrain.throttleInt.setThrottle(0);
			Robot.drivetrain.drivePID.setSetpoint(60);
			Robot.drivetrain.drivePID.enable();
			if (currentTime2 - startTime2 >= DRIVE_TIME + TURN_TIME /*Robot.drivetrain.distance.getRangeInches() > RobotConstants.autoLowBarShootdistance2*/) {
				state = DRIVE_2;
				Robot.drivetrain.drivePID.disable();
			}
		}
		if (state == DRIVE_2) {
			currentTime2 = timer2.get();
//			Robot.drivetrain.setL(RobotConstants.autoLowBarshootDrivespeed2);
//			Robot.drivetrain.setR(RobotConstants.autoLowBarshootDrivespeed2);
			Robot.drivetrain.throttleInt.setThrottle(RobotConstants.autoLowBarshootDrivespeed);
			Robot.drivetrain.drivePID.enable();
			
			if (currentTime2 - startTime2 >= DRIVE_TIME + TURN_TIME + DRIVE_2_TIME/*Robot.drivetrain.distance.getRangeInches() <=  RobotConstants.autoLowBarShootdistance3*/) {
				Robot.drivetrain.setL(0);
				Robot.drivetrain.setR(0);
				Robot.drivetrain.drivePID.disable();
				state = SHOOT;
			}
		}
		if (state == SHOOT) {
			switch (shootingState) {
			
			case START:
				/*
				 * STATE 1 - set shooter speed - set start time
				 */
				// turn on shooter wheels
				Robot.shooter.enable();
				Robot.shooter.setShooter(.25 * RobotConstants.shootHighGoalRPM);
				Robot.drivetrain.setL(.2);
				Robot.drivetrain.setR(.2);
				timer.start();
				startTime = timer.get(); // gets the starting time in seconds
				shootingState = ShootingState.SHOOTER_SPEED_UP_1_WAIT; // move onto the next state
				Robot.shooter.print();
				System.out.println("shooting start");
				break;
			case SHOOTER_SPEED_UP_1_WAIT:
				/*
				 * STATE 2 - get current time - wait 1 second
				 */
				currentTime = timer.get(); // gets current time
				// after a total 1 second, the state moves on
				if ((currentTime - startTime) >= SHOOTER_SPEED_UP_1_TIME) {

					shootingState = ShootingState.START_2;
				}
				Robot.shooter.print();
				System.out.println("shooting start shooter speed up 1 wait");
				break;
			case START_2:
				// turn on shooter wheels
				Robot.shooter.setShooter(.5 * RobotConstants.shootHighGoalRPM);
				shootingState = ShootingState.SHOOTER_SPEED_UP_2_WAIT; // move onto the next state
				Robot.shooter.print();
				System.out.println("shooting start 2");
				break;
			case SHOOTER_SPEED_UP_2_WAIT:
				currentTime = timer.get(); // gets current time
				// after a total 1 second, the state moves on
				if ((currentTime - startTime) >= (SHOOTER_SPEED_UP_1_TIME + SHOOTER_SPEED_UP_2_TIME)) {

					shootingState = ShootingState.START_3;
				}
				Robot.shooter.print();
				System.out.println("shooting shooter speed up 2 wait");
				break;
			case START_3:
				// turn on shooter wheels
				Robot.shooter.setShooter(.75 * RobotConstants.shootHighGoalRPM);
				shootingState = ShootingState.SHOOTER_SPEED_UP_3_WAIT; // move onto the next state
				Robot.shooter.print();
				System.out.println("shooting start 3");
				break;
			case SHOOTER_SPEED_UP_3_WAIT:
				currentTime = timer.get(); // gets current time
				// after a total 1 second, the state moves on
				if ((currentTime - startTime) >= (SHOOTER_SPEED_UP_1_TIME + SHOOTER_SPEED_UP_2_TIME + SHOOTER_SPEED_UP_3_TIME)) {

					shootingState = ShootingState.START_4;
				}
				Robot.shooter.print();
				System.out.println("shooter shooter speed up 3 wait");
				break;
			case START_4:
				// turn on shooter wheels
				Robot.shooter.setShooter(RobotConstants.shootHighGoalRPM);
				shootingState = ShootingState.SHOOTER_SPEED_UP_4_WAIT; // move onto the next state
				Robot.shooter.print();
				System.out.println("shooting start 4");
				break;
			case SHOOTER_SPEED_UP_4_WAIT:
				currentTime = timer.get(); // gets current time
				Robot.shooter.setShooter(RobotConstants.shootHighGoalRPM);
				// after a total 1 second, the state moves on
				if ((currentTime - startTime) >= (SHOOTER_SPEED_UP_1_TIME + SHOOTER_SPEED_UP_2_TIME + SHOOTER_SPEED_UP_3_TIME + SHOOTER_SPEED_UP_4_TIME)) {

					shootingState = ShootingState.INTAKE_FORWARD;
				}
				Robot.shooter.print();
				System.out.println("shooting shooter speed up 4 wait");
				break;
			case INTAKE_FORWARD:
				/*
				 * STATE 3 - set intake rollers to forward
				 */

				// intake rollers move forward
				Robot.intakeRoller.setState(IntakeRoller.Direction.FORWARD);
				System.out.println("                                   STATE 3");
				shootingState = ShootingState.INTAKE_FORWARD_WAIT;
				Robot.shooter.print();
				System.out.println("shooting intake fordward");
				break;
			case INTAKE_FORWARD_WAIT:
				/*
				 * STATE 4 - once 1 second has passed, the state moves on
				 */

				currentTime = timer.get(); // gets current time
				System.out.println("                                   STATE 4");
				if ((currentTime - startTime) >= (SHOOTER_SPEED_UP_1_TIME + SHOOTER_SPEED_UP_2_TIME + SHOOTER_SPEED_UP_3_TIME + SHOOTER_SPEED_UP_4_TIME + INTAKE_FORWARD_TIME)) {
					shootingState = ShootingState.INTAKE_OFF;
				}
				Robot.shooter.print();
				System.out.println("shooting intake forward wait");
				break;
			case INTAKE_OFF:
				/*
				 * STATE 5 - intake rollers are set to off
				 */

				// the intake rollers shut off
				Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
				System.out.println("                                   STATE 5");
				shootingState = ShootingState.SHOOTER_OFF;
				Robot.shooter.print();
				System.out.println("shooting intake off");
				break;
			case SHOOTER_OFF:
				/*
				 * STATE 7 - turn off shooter
				 */
				Robot.shooter.setShooter(0); // shooter is set to 0 rpm
				Robot.drivetrain.setL(0);
				Robot.drivetrain.setR(0);
				System.out.println("                                   STATE 7");
				shootingState = ShootingState.END;
				Robot.shooter.print();
				System.out.println("shooting shooter off");
				break;
			default:
			}
			
		}
		
		if (state == TURNING_OFF) {
			Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
			Robot.drivetrain.drivePID.disable();
			state = END;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return state == END;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.drivePID.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drivetrain.drivePID.disable();
	}
}
