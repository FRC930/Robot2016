package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.controller.AlignOutput;
import org.usfirst.frc.team930.robot.controller.AngleSource;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain.Positions;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GyroDriveRight extends Command {

	public PIDController drivePID;
	AlignOutput alignOutput;
	AngleSource angleSource;
	
    public GyroDriveRight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

 // Called just before this Command runs the first time
 	protected void initialize() {
 		Robot.drivetrain.throttleInt.useJoystick(true);
 		Robot.drivetrain.drivePID.setSetpoint(Robot.drivetrain.getGoalAngle(Positions.RIGHT));
 	}

 	// Called repeatedly when this Command is scheduled to run
 	protected void execute() {
 		System.out.println("GYRO ANGLE: " + Robot.drivetrain.gyro.getAngle() + " : " + Robot.drivetrain.drivePID.getError());
 		Robot.drivetrain.drivePID.enable();
 	}

 	// Make this return true when this Command no longer needs to run execute()
 	protected boolean isFinished() {
 		return Math.abs(Robot.drivetrain.drivePID.getError()) < 2;
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
