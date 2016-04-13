package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPort1TurnDrive extends Command {
	
	Timer timer2 = new Timer();

	private enum State{
		START_DRIVE,
		LOWER_INTAKE_DRIVE,
		RAISE_INTAKE,
		TURN,
		DRIVE_2,
		TURNING_OFF,
		END
	}
	State state;
	
	double startTime2; // time the command starts running (seconds)
	double currentTime2; // the current time (seconds)
	
	// TIMES -----------------------------------------------
	public static final double STRAIGHT_SET_POINT = 0;

	double thisDriveTime1;
	double thisLowerDriveTime;
	double thisRaiseTime;
	double thisTurnAngle;
	double thisTurnTime;
	double thisDriveTime2;
	double thisDriveSpeed;

    public AutoPort1TurnDrive(double driveTime1, double lowerDriveTime, double raiseTime, double turnAngle, double turnTime, double driveTime2, double driveSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
		requires(Robot.intakeRoller);
		requires(Robot.intakeLifter);
		
    	thisDriveTime1 = driveTime1;
    	thisLowerDriveTime = lowerDriveTime;
    	thisRaiseTime = raiseTime;
    	thisTurnAngle = turnAngle;
    	thisTurnTime = turnTime;
    	thisDriveTime2 = driveTime2;
    	thisDriveSpeed = driveSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetGyro();
		Robot.drivetrain.drivePID.setSetpoint(STRAIGHT_SET_POINT);
		Robot.drivetrain.throttleInt.useJoystick(false);
		Robot.drivetrain.throttleInt.setThrottle(thisDriveSpeed);
		timer2.start();
		startTime2 = timer2.get();
		state = State.START_DRIVE; // initializes the state
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("state = " + state);
		
		//Robot.drivetrain.drivePID.enable();
		switch (state) {
			
		case START_DRIVE:
			
			Robot.drivetrain.drivePID.enable();
			currentTime2 = timer2.get();
			if (currentTime2 - startTime2 >= thisDriveTime1 /*&& Robot.drivetrain.distance.getRangeInches() <=  RobotConstants.autoLowBarShootdistance1*/) {
				Robot.drivetrain.drivePID.disable();
				startTime2 = timer2.get();
				state = State.LOWER_INTAKE_DRIVE;
			}
			break;
			
		case LOWER_INTAKE_DRIVE:
	    	currentTime2 = timer2.get();
	    	Robot.intakeLifter.setAngle(IntakeLifter.Positions.PORT);
	   		Robot.drivetrain.setL(1);
			Robot.drivetrain.setR(1);
			if(currentTime2 - startTime2 >= thisLowerDriveTime) {
				Robot.drivetrain.setL(0.0);
				Robot.drivetrain.setR(0.0);
				state = State.RAISE_INTAKE;
			}
			break;
			
		case RAISE_INTAKE:
			currentTime2 = timer2.get();
    		Robot.intakeLifter.setAngle(IntakeLifter.Positions.DEFAULT);
			if(currentTime2 - startTime2 >= thisRaiseTime){
				state = State.TURN;
			}
			break;
		
		case TURN:
			currentTime2 = timer2.get();
			Robot.drivetrain.throttleInt.setThrottle(0);
			Robot.drivetrain.drivePID.setSetpoint(thisTurnAngle);
			Robot.drivetrain.drivePID.enable();
			if (currentTime2 - startTime2 >= thisTurnTime /*Robot.drivetrain.distance.getRangeInches() > RobotConstants.autoLowBarShootdistance2*/) {
				Robot.drivetrain.drivePID.disable();
				startTime2 = timer2.get();
				state = State.DRIVE_2;
			}
			break;
		
		case DRIVE_2:
			currentTime2 = timer2.get();
//			Robot.drivetrain.setL(RobotConstants.autoLowBarshootDrivespeed2);
//			Robot.drivetrain.setR(RobotConstants.autoLowBarshootDrivespeed2);
			Robot.drivetrain.throttleInt.setThrottle(thisDriveSpeed);
			Robot.drivetrain.drivePID.enable();
			
			if (currentTime2 - startTime2 >= thisDriveTime2/*Robot.drivetrain.distance.getRangeInches() <=  RobotConstants.autoLowBarShootdistance3*/) {
				Robot.drivetrain.setL(0);
				Robot.drivetrain.setR(0);
				Robot.drivetrain.drivePID.disable();
				startTime2 = timer2.get();
				state = State.TURNING_OFF;
			}
			break;
		
		case TURNING_OFF:
			Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
			Robot.drivetrain.drivePID.disable();
			state = State.END;
			break;
			//default:
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
