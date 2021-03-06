package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;
/**
 *
 */
public class Pickup extends Command {

	
	
    public Pickup() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.intakeRoller);
    	requires(Robot.intakeLifter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intakeLifter.PID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//this makes the intake lifter go to the appropriate position for pickup
    	Robot.intakeLifter.setAngle(IntakeLifter.Positions.PICKUP);
    	//sets the intakeRollers to pull in the ball
//    	if(Robot.intakeLifter.getPOT() > 73 && Robot.intakeLifter.getPOT() < 82){
//    		Robot.intakeLifter.PID.disable();
//    		System.out.println("Disabling pickup");
//    	}
    	
    	
    	
    	
    	
    	
    	if(!Robot.intakeRoller.seeBall())
    		Robot.intakeRoller.setState(IntakeRoller.Direction.FORWARD);
    	else
    		Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
    	
    	
    	
    	
//    	Robot.intakeRoller.setState(IntakeRoller.Direction.FORWARD);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//when the lightsensor is covered then it will stop this command
        return false; // (OI.getInstance().getRightTrigger() < .75);
    }

    // Called once after isFinished returns true
    protected void end() {
    	//when it finishes it will stop the rollers and set the lifter to default
    	Robot.intakeLifter.setAngle(IntakeLifter.Positions.PICKUP);
    	Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);
    	Robot.intakeLifter.PID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//when it finishes it will stop the rollers and set the lifter to default
    	Robot.intakeLifter.setAngle(IntakeLifter.Positions.PICKUP);
    	Robot.intakeRoller.setState(IntakeRoller.Direction.STOP);   	
    	Robot.intakeLifter.PID.disable();
    }
}
