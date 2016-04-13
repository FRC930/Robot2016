package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class A4PortS extends CommandGroup {
    
    public  A4PortS() {
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
		addSequential(new AutoPort2TurnDrive(RobotConstants.auto4PortdriveTime1, RobotConstants.auto4PortlowerDriveTime, RobotConstants.auto4PortraiseTime, RobotConstants.auto4PortturnAngle1, RobotConstants.auto4PortturnTime1, RobotConstants.auto4PortdriveTime2, RobotConstants.auto4PortturnTime2, RobotConstants.auto4PortdriveTime3, RobotConstants.auto4PortdriveSpeed));
		addSequential(new ShootHighGoal());
    }
}
