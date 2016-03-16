package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto2ndPositionCommandGroupShoot extends CommandGroup {
    
    public  Auto2ndPositionCommandGroupShoot() {
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
		System.out.println("Starting Command Group");
    	addSequential(new Auto2ndPositionShoot());
    	System.out.println("Done with autio low bar, starting high goal");
    	addSequential(new ShootHighGoal());
    	System.out.println("Done with all cmmands");
    }
}
