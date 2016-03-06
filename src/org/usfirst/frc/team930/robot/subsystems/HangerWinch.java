package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class HangerWinch extends Subsystem {

	int counter;
	
	Spark hangerWinch = new Spark(RobotMap.WinchPort); 
	DigitalInput hallEffect = new DigitalInput(RobotMap.hallPort);
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    public void initializeCount()
    {
    	counter = 0;
    }
    
    public int getCount()
    {
    	boolean magnet = false;
    	boolean nomagnet = false;
    	
    	if(hallEffect.get() == true)
    	{
    		magnet = true;
    	}
    	if(hallEffect.get() == false && magnet == true)
    	{
    		nomagnet = true;
    	}
    	if(magnet==true && nomagnet == true)
    	{
    		magnet = false;
    		nomagnet = false;
    		counter++;
    	}
    	
    	return counter;
    }
    
    public void turnWinch()
    {
    	hangerWinch.set(-1);
    }
    
    public void stopWinch()
    {
    	hangerWinch.set(0);
    }
    
    public void unwindWinch()
    {
    	hangerWinch.set(1);
    }
}

