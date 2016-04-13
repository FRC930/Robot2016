package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class A5PortS extends CommandGroup {
    
    public  A5PortS() {
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
		addSequential(new AutoPort1TurnDrive(RobotConstants.auto5PortdriveTime1, RobotConstants.auto5PortlowerDriveTime, RobotConstants.auto5PortraiseTime, RobotConstants.auto5PortturnAngle, RobotConstants.auto5PortturnTime, RobotConstants.auto5PortdriveTime2, RobotConstants.auto5PortdriveSpeed));
		addSequential(new ShootHighGoal());
    }
}
