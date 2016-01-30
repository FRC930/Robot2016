package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.RobotMap;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
	
	Victor intake1 = new Victor(RobotMap.I1Port);
	Victor intake2 = new Victor(RobotMap.I2Port);
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setIntake1(double speed)
    {
    	intake1.set(speed);
    	intake2.set(speed);
    }

	
		// TODO Auto-generated method stub
		
	
    
    
}

