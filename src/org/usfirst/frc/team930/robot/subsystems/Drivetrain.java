
package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.RobotMap;
import org.usfirst.frc.team930.robot.commands.Drive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	CANTalon L1 = new CANTalon(RobotMap.LFrontPort);
	CANTalon L2 = new CANTalon(RobotMap.LTopPort);
	CANTalon L3 = new CANTalon(RobotMap.LBackPort);
	CANTalon R1 = new CANTalon(RobotMap.RFrontPort);
	CANTalon R2 = new CANTalon(RobotMap.RTopPort);
	CANTalon R3 = new CANTalon(RobotMap.RBackPort);
	
	//AnalogInput distanceSensor = new AnalogInput(0);

	//GyroBase gyro = new AnalogGyro(0);
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new Drive());
    }
    
    public void setL(double speed)
    {
    	L1.set(speed);
    	L2.set(speed);
    	L3.set(speed);
    }
    
    public void setR(double speed)
    {
    	R1.set(speed);
    	R2.set(speed);
    	R3.set(speed);
    }
    

}
    


