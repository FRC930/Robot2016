//package org.usfirst.frc.team930.robot.commands;
//
//import edu.wpi.first.wpilibj.command.Command;
//import org.usfirst.frc.team930.robot.Robot;
//import org.usfirst.frc.team930.robot.subsystems.HangerLifter;
//
//
//
///**
// *
// */
//public class LiftHanger extends Command {
//	
//    public LiftHanger() {
//        // Use requires() here to declare subsystem dependencies
//        // eg. requires(chassis);
//    	requires(Robot.hangerLifter);
//    	
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	Robot.hangerWinch.initializeCount();
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//    	Robot.hangerLifter.extend();
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return false;
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    }
//    
//}
//