package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class IntakeLifter extends Subsystem {
	enum Positions
	{
		LOWGOAL, HIGH, PICKUP, TO_SHOOTER;
	}
	
	//Preferences will allow remotely inputted proportional, integrational, and derivative values
	static int P = Robot.prefs.getInt("P", 0);
	static int I = Robot.prefs.getInt("I", 0);
	static int D = Robot.prefs.getInt("D", 0);
	
	static AnalogPotentiometer potentiometer = new AnalogPotentiometer(RobotMap.ILiftPort);
	static Spark intakeLifter = new Spark(RobotMap.ILiftPort);
	public static PIDController PID = new PIDController(P,I,D,potentiometer,intakeLifter);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());

	}
	
	/**
	 * Updates PID Values in accordance to Preferences inputs and returns the new angle that will be set.
	 * 
	 * @author GuyAcrossTheStreet
	 */
	public static Sendable Update() {
		P = Robot.prefs.getInt("P", 0);
		I = Robot.prefs.getInt("I", 0);
		D = Robot.prefs.getInt("D", 0);
		PID = new PIDController(P,I,D,potentiometer,intakeLifter);
		
		return PID;
	}

	public void setintakeLifter(double angle) {
		//intakeLifter.set(angle);
		//System.out.println(potentiometer.get());
		
		PID.setSetpoint(angle);
		
		SmartDashboard.putData("Set Angle", PID);
    	SmartDashboard.putData("Actual Angle", potentiometer);
	}
}
