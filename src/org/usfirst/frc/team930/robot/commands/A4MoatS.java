package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class A4MoatS extends CommandGroup {
    
    public  A4MoatS() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	requires(Robot.drivetrain);
		requires(Robot.intakeRoller);
		requires(Robot.intakeLifter);
		addSequential(new Auto2TurnDrive(RobotConstants.auto4MoatdriveTime1, RobotConstants.auto4MoatturnAngle1, RobotConstants.auto4MoatturnTime1, RobotConstants.auto4MoatdriveTime2, RobotConstants.auto4MoatturnTime2, RobotConstants.auto4MoatdriveTime3, RobotConstants.auto4MoatdriveSpeed));
		addSequential(new ShootHighGoal());
    }
}
