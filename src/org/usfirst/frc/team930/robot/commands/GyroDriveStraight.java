package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.controller.AlignOutput;
import org.usfirst.frc.team930.robot.controller.AngleSource;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GyroDriveStraight extends Command {

	//double Kp = 0.03;
	public PIDController drivePID;
	AlignOutput alignOutput;
	final double P = 0.1;
	final double I = 0;
	final double D = 0;

	public GyroDriveStraight() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
		System.out.println("IN DRIVE STRAIGHT CONSTRUCTOR");
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("IN DRIVE STRAIGHT INIT");
    	alignOutput = new AlignOutput(Robot.drivetrain.L1, Robot.drivetrain.L2, Robot.drivetrain.L3, Robot.drivetrain.R1, Robot.drivetrain.R2, Robot.drivetrain.R3);
    	drivePID = new PIDController(P, I, D, Robot.drivetrain.gyro, alignOutput, 0.01);
		drivePID.reset();
		drivePID.setAbsoluteTolerance(1);
		drivePID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("IN DRIVE STRAIGHT EXECUTE");
		drivePID.setSetpoint(0.0);
		System.out.println("AFTER PID SETPOINT");
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		drivePID.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		drivePID.disable();
	}
}