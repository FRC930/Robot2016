package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.RobotMap;
import org.usfirst.frc.team930.robot.commands.ShootHighGoal;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 */
public class Shooter extends Subsystem {
	
	Victor shooter1 = new Victor(RobotMap.S1Port);
	Victor shooter2 = new Victor(RobotMap.S2Port);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ShootHighGoal());
    }
    
    public void setShooter(double speed)
    {
    	shooter1.set(speed);
    	shooter2.set(speed);
    }
    
    
}

