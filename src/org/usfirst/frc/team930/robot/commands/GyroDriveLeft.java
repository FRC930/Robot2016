package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.controller.AlignOutput;
import org.usfirst.frc.team930.robot.controller.AngleSource;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GyroDriveLeft extends Command {

	public PIDController drivePID;
	AlignOutput alignOutput;
	AngleSource angleSource;
	
    public GyroDriveLeft() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//alignOutput = new AlignOutput();
    	drivePID = new PIDController(0, 0, 0, new AngleSource(), new AlignOutput(Robot.drivetrain.L1, Robot.drivetrain.L2, Robot.drivetrain.L3, Robot.drivetrain.R1, Robot.drivetrain.R2, Robot.drivetrain.R3), 0.01);
		drivePID.reset();
		drivePID.setAbsoluteTolerance(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	drivePID.setSetpoint(-60.0);
    	
    	/*
    	double angle = Robot.drivetrain.getAngle();

		double x = 0;
		double y = 0;

		if(angle > 60 && angle < 240){
			Robot.drivetrain.setL(-0.5);
			Robot.drivetrain.setR(0.5);
		}

		else {
			if(angle < 300 && angle > 120) {
				Robot.drivetrain.setL(0.5);
				Robot.drivetrain.setR(-0.5);
			}

			else {
				if(angle == 120) {
					Robot.drivetrain.setL(-0.5);
					Robot.drivetrain.setR(0.5);
				}
			}
		}

		x = x * x * Math.signum(x);
		y = y * y * Math.signum(y);
		*/
		//SmartDashboard.putNumber("Angle", angle);
		//SmartDashboard.putNumber("X Value", x);
		//SmartDashboard.putNumber("Y Value", y);

		
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
