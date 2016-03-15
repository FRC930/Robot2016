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

	private enum State{
		START,
		DRIVE,
		TURN,
		DRIVE_2,
		TURNING_OFF,
		END
	}
	State state;
	
	double startTime; // time the command starts running (seconds)
	double currentTime; // the current time (seconds)
	double startTime2; // time the command starts running (seconds)
	double currentTime2; // the current time (seconds)
	
	// TIMES -----------------------------------------------
	public static final double ARM_DOWN = 0.1;
	public static final double DRIVE_TIME = 4.6; 
	public static final double TURN_TIME = 2;
	public static final double DRIVE_2_TIME = 3;
	public static final double LOWER_INTAKE_AND_DRIVE_TIME = 3; 
	

	public AutoLowBarShoot() {
		requires(Robot.drivetrain);
		requires(Robot.intakeRoller);
		requires(Robot.intakeLifter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drivetrain.drivePID.setSetpoint(0.0);
		Robot.drivetrain.throttleInt.useJoystick(false);
		Robot.drivetrain.throttleInt.setThrottle(RobotConstants.autoLowBarshootDrivespeed);
		timer2.start();
		startTime2 = timer2.get();
		state = State.START; // initializes the state
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("state = " + state);
		
		

		//Robot.drivetrain.drivePID.enable();
		switch (state) {
			
		case START:
			Robot.intakeLifter.setAngle(IntakeLifter.Positions.PICKUP);
			if(currentTime2 - startTime2 >= ARM_DOWN){
			startTime2 = timer2.get();
			break;
			}
		
		case DRIVE:

//			Robot.drivetrain.setL(RobotConstants.autoLowBarshootDrivespeed);
//			Robot.drivetrain.setL(RobotConstants.autoLowBarshootDrivespeed);
			Robot.drivetrain.drivePID.enable();
			currentTime2 = timer2.get();
			if (currentTime2 - startTime2 >= DRIVE_TIME /*&& Robot.drivetrain.distance.getRangeInches() <=  RobotConstants.autoLowBarShootdistance1*/) {
				Robot.drivetrain.drivePID.disable();
				startTime2 = timer2.get();
				break;
			}
		
		case TURN:
			currentTime2 = timer2.get();
			Robot.drivetrain.throttleInt.setThrottle(0);
			Robot.drivetrain.drivePID.setSetpoint(60);
			Robot.drivetrain.drivePID.enable();
			if (currentTime2 - startTime2 >= TURN_TIME /*Robot.drivetrain.distance.getRangeInches() > RobotConstants.autoLowBarShootdistance2*/) {
				Robot.drivetrain.drivePID.disable();
				startTime2 = timer2.get();
				break;
			}
		
		case DRIVE_2:
			currentTime2 = timer2.get();
//			Robot.drivetrain.setL(RobotConstants.autoLowBarshootDrivespeed2);
//			Robot.drivetrain.setR(RobotConstants.autoLowBarshootDrivespeed2);
			Robot.drivetrain.throttleInt.setThrottle(RobotConstants.autoLowBarshootDrivespeed);
			Robot.drivetrain.drivePID.enable();
			
			if (currentTime2 - startTime2 >= DRIVE_2_TIME/*Robot.drivetrain.distance.getRangeInches() <=  RobotConstants.autoLowBarShootdistance3*/) {
				Robot.drivetrain.setL(0);
				Robot.drivetrain.setR(0);
				Robot.drivetrain.drivePID.disable();
				startTime2 = timer2.get();
				break;
			}
		
		case TURNING_OFF:
			Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
			Robot.drivetrain.drivePID.disable();
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
		Robot.drivetrain.drivePID.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drivetrain.drivePID.disable();
	}
}
