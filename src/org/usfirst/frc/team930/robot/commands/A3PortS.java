package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class A3PortS extends CommandGroup {
    
    public  A3PortS() {
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
		addSequential(new AutoPort2TurnDrive(RobotConstants.auto3PortdriveTime1, RobotConstants.auto3PortlowerDriveTime, RobotConstants.auto3PortraiseTime, RobotConstants.auto3PortturnAngle1, RobotConstants.auto3PortturnTime1, RobotConstants.auto3PortdriveTime2, RobotConstants.auto3PortturnTime2, RobotConstants.auto3PortdriveTime3, RobotConstants.auto3PortdriveSpeed));
		addSequential(new ShootHighGoal());
    }
}
