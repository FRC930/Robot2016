package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotMap;
import org.usfirst.frc.team930.robot.commands.IntakeLiftHigh;
import org.usfirst.frc.team930.robot.commands.ShootHighGoal;
import org.usfirst.frc.team930.robot.controller.BBSController;
import org.usfirst.frc.team930.robot.controller.CounterRPMSource;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Victor;

public class Shooter extends Subsystem {

	public static final double HIGH_GOAL_RPM = 6000;

	Victor shooter1 = new Victor(RobotMap.S1Port);
	Victor shooter2 = new Victor(RobotMap.S2Port);

	DigitalInput lightSensorShooter = new DigitalInput(RobotMap.lightSensorShooterPort); 

	CounterRPMSource rpmSource = new CounterRPMSource(lightSensorShooter);

	BBSController cont1, cont2;

	public Shooter() {
		super();
		
		shooter1.setInverted(true);
		
		// bang bang controllers
		cont1 = new BBSController(rpmSource, shooter1, 0, 1);
		cont2 = new BBSController(rpmSource, shooter2, 0, 1);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new IntakeLiftHigh());
	}

	public void setShooter(double rpm) {

		cont1.setRPM(rpm);
		cont2.setRPM(rpm);
		
		 //shooter1.set(rpm);
		 //shooter2.set(rpm);
	}
	
	public void print(){
		 System.out.println("Shooter1Speed    "+ 30.0/rpmSource.getPeriod());
		 SmartDashboard.putNumber("Shooter1Speed", 30.0/rpmSource.getPeriod());
	}
	
	public boolean seeBall(){
		return lightSensorShooter.get();
	}

	public void setAccel(double accel) {
		cont1.setAccel(accel);
		cont2.setAccel(accel);
	}
	
	public void enable() {
		cont1.enable();
		cont2.enable();
	}
	
	public void disable() {
		cont1.disable();
		cont2.disable();
	}
}