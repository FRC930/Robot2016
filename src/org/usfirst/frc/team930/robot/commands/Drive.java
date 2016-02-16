package org.usfirst.frc.team930.robot.commands;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain;

/** 
 *
 */
public class Drive extends Command {

	RobotDrive r;
	
	public Drive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		
		//Robot.drivetrain.ultra.setEnabled(true);
        //Robot.drivetrain.ultra.getRangeInches();
        //SmartDashboard.putNumber("Distance", 0);
        double x = OI.getInstance().getXAxis();
		double y = OI.getInstance().getYAxis();

		x = x * x * Math.signum(x);
		y = y * y * Math.signum(y);

		Robot.drivetrain.setL(y + x);
		Robot.drivetrain.setR(y - x);
		//System.out.println("X: " + x);
		//System.out.println("Y: " + y);
        
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
