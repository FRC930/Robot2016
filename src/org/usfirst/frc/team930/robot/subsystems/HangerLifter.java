package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HangerLifter extends Subsystem {
	static final double extendSpeed = .75;
	static final double retractSpeed = -.75;
    Spark spark = new Spark(RobotMap.HangerArmPort);

    public void initDefaultCommand() {
    	
    }
    
    public void extend() {
    	spark.set(extendSpeed);
    }
    
    public void retract() {
    	spark.set(retractSpeed);
    }
    
}

