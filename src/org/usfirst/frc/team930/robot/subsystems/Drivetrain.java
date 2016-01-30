
package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.RobotMap;
import org.usfirst.frc.team930.robot.commands.Drive;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	TalonSRX L1 = new TalonSRX(RobotMap.L1Port);
	TalonSRX L2 = new TalonSRX(RobotMap.L2Port);
	TalonSRX L3 = new TalonSRX(RobotMap.L3Port);
	TalonSRX R1 = new TalonSRX(RobotMap.R1Port);
	TalonSRX R2 = new TalonSRX(RobotMap.R2Port);
	TalonSRX R3 = new TalonSRX(RobotMap.R3Port);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Drive());
    }
    
    public void setL1(double speed)
    {
    	L1.set(speed);
    }
    
    public void setL2(double speed)
    {
    	L2.set(speed);
    }
    
    public void setL3(double speed)
    {
    	L3.set(speed);
    }
    
    public void setR1(double speed)
    {
    	R1.set(speed);
    }
    
    public void setR2(double speed)
    {
    	R2.set(speed);
    }
    
    public void setR3(double speed)
    {
    	R3.set(speed);
    }
    public void setDriveSpeed(double drive, double turn)
    {
   
    	if(Math.abs(OI.getYAxis())>0.4 && Math.abs(OI.getXAxis())>0.4)
    	{
    		/*L1.set((OI.getYAxis() + OI.getXAxis())/2);
    		L2.set((OI.getYAxis() + OI.getXAxis())/2);
    		L3.set((OI.getYAxis() + OI.getXAxis())/2);
    		R1.set((OI.getYAxis() - OI.getXAxis())/2);
    		R2.set((OI.getYAxis() - OI.getXAxis())/2);
    		R3.set((OI.getYAxis() - OI.getXAxis())/2);*/
    		System.out.println("Going sideways: " + ((OI.getYAxis()+OI.getXAxis())/2));
    		System.out.println("Going sideways: " + ((OI.getYAxis()-OI.getXAxis())/2));
    	}
    	 else if(OI.getYAxis() < -0.1 && Math.abs(OI.getXAxis()) < 0.15)
     	{
     		/*L1.set(OI.getYAxis());
     		L2.set(OI.getYAxis());
     		L3.set(OI.getYAxis());
     		R1.set(OI.getYAxis());
     		R2.set(OI.getYAxis());
     		R3.set(OI.getYAxis());*/
     		System.out.println("Drive Forward: " + OI.getYAxis());
     		
     	}
    	else if(OI.getYAxis() > 0.1 && Math.abs(OI.getXAxis()) < 0.15)
    	{
    		System.out.println("Going backwards: " + OI.getYAxis());
    		/*L1.set(OI.getYAxis());
    		L2.set(OI.getYAxis());
    		L3.set(OI.getYAxis());
    		R1.set(OI.getYAxis());
    		R2.set(OI.getYAxis());
    		R3.set(OI.getYAxis());*/
    	}
    	else if(OI.getXAxis()>0.1 && Math.abs(OI.getYAxis()) < 0.15)
    	{
    	System.out.println("Going Right: " + OI.getXAxis());
    	System.out.println("Going Right: " + -OI.getXAxis());
    		/*L1.set(OI.getXAxis());
    		L2.set(OI.getXAxis());
    		L3.set(OI.getXAxis());
    		R1.set(-OI.getXAxis());
    		R2.set(-OI.getXAxis());
    		R3.set(-OI.getXAxis());*/
    	}
    	else if(OI.getXAxis()<-0.1 && Math.abs(OI.getYAxis()) < 0.15)
    	{
    	System.out.println("Going Left " + OI.getXAxis());
    	System.out.println("Going Left " + -OI.getXAxis());
    		/*L1.set(-OI.getXAxis());
    		L2.set(-OI.getXAxis());
    		L3.set(-OI.getXAxis());
    		R1.set(OI.getXAxis());
    		R2.set(OI.getXAxis());
    		R3.set(OI.getXAxis());*/
    	}
    	
    		else
    		{
    		System.out.println("Staying still");
    		/*L1.set(0);
    		L2.set(0);
    		L3.set(0);
    		R1.set(0);
    		R2.set(0);
    		R3.set(0);*/
    		}
    		
    	}
    }
    


