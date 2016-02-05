package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class IntakeLifter extends Subsystem {
	AnalogInput potentiometer = new AnalogInput(RobotMap.ILiftPort);
	Spark intakeLifter = new Spark(RobotMap.I2Port);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    public void setintakeLifter(double angle)
    {
    intakeLifter.set(angle);
    }
}

