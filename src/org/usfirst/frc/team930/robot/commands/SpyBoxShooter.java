package org.usfirst.frc.team930.robot.commands;

import java.awt.geom.Arc2D.Double;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpyBoxShooter extends Command {
Timer timer = new Timer();
double startTime = 0;
double currentTime = 0;
final int START = 0;
final int DRIVE = 1;
final int OFF = 2;
final int END = 3;
int state = 0;
final double driveSpeed = 1.0;
    public SpyBoxShooter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timer.start();
        state = START;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(state == START){
    		startTime = timer.get();
    		Robot.drivetrain.setGoalAngle(); // realigns the gyro stuff - we don't actually use the gyro here duh
    		// lean back, good squat
    		state = DRIVE;
    	}
    	if(state == DRIVE){
    		Robot.drivetrain.setL(driveSpeed);
			Robot.drivetrain.setR(driveSpeed);
			if(currentTime-startTime > 7){
				Robot.drivetrain.setL(0);
				Robot.drivetrain.setR(0);
				Robot.shooter.setShooter(1);
				if(currentTime- startTime > 8){
					Robot.intakeRoller.setState(IntakeRoller.Direction.SHOOTERPULL);
					if(currentTime-startTime > 10){
						state = OFF;
					}
				}
			}
    	}
    	if(state== OFF){
    		Robot.shooter.setShooter(0);
    		Robot.intakeRoller.setState(IntakeRoller.Direction.BACKWARD);
    		state = END;
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
