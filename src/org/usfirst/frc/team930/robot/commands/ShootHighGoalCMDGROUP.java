package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;
import org.usfirst.frc.team930.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootHighGoalCMDGROUP extends CommandGroup {
    
    public  ShootHighGoalCMDGROUP() {
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
    	System.out.println("Startng CMD group");
    	addParallel(new ShootHighGoal());
    	System.out.println("finished shooter ");
    	System.out.println("starting intake");
    	addSequential(new MoveIntakeRollers(IntakeRoller.Direction.FORWARD));
    	System.out.println("starting timer delay");
    	Timer.delay(3);
    	addSequential(new MoveIntakeRollers(IntakeRoller.Direction.STOP));
    	
    }
}
