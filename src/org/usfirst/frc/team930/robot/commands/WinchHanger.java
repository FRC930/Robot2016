//package org.usfirst.frc.team930.robot.commands;
//
//import org.usfirst.frc.team930.robot.Robot;
//
//import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
//public class WinchHanger extends Command {
//	
//	int limit = 800; // when the winch will stop
//
//    public WinchHanger() {
//        // Use requires() here to declare subsystem dependencies
//        // eg. requires(chassis);
//    	requires(Robot.hangerWinch);
//    }
//
//    // Called just before this Command runs the first time
//    protected void initialize() {
//    	Robot.hangerWinch.initializeCount();
//    }
//
//    // Called repeatedly when this Command is scheduled to run
//    protected void execute() {
//		/*
//    	while(Robot.hangerWinch.getCount() < limit)
//    	{
//        	Robot.hangerWinch.turnWinch();	
//    	}
//    	Robot.hangerWinch.stopWinch();	
//      */
//		Robot.hangerWinch.turnWinch();	
//    }
//
//    // Make this return true when this Command no longer needs to run execute()
//    protected boolean isFinished() {
//        return false;
//    }
//
//    // Called once after isFinished returns true
//    protected void end() {
//    	Robot.hangerWinch.stopWinch();	
//    }
//
//    // Called when another command which requires one or more of the same
//    // subsystems is scheduled to run
//    protected void interrupted() {
//    	Robot.hangerWinch.turnWinch();	
//    }
//}
