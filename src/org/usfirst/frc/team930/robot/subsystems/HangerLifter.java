package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.RobotConstants;
import org.usfirst.frc.team930.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HangerLifter extends Subsystem {
	
    Spark spark = new Spark(RobotMap.HangerArmPort);

    public void initDefaultCommand() {
    	
    }
    
    public void extend() {
    	spark.set(RobotConstants.hangerLifterextendSpeed);
    }
    
    public void retract() {
    	spark.set(RobotConstants.hangerLifterretractSpeed);
    }
    
    public void stop(){
    	spark.set(0);
    }
    
}

