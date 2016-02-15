package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.RobotMap;
import org.usfirst.frc.team930.robot.commands.ShootHighGoal;

import org.usfirst.frc.team930.robot.controller.BBSController;
import org.usfirst.frc.team930.robot.controller.CounterRPMSource;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;

public class Shooter extends Subsystem {

	public static final double HIGH_GOAL_RPM = 3800;
	
	Victor shooter1 = new Victor(RobotMap.S1Port);
	Victor shooter2 = new Victor(RobotMap.S2Port);
	
	DigitalInput lightSensorShooter = new DigitalInput(
			RobotMap.lightSensorShooterPort);
	
	CounterRPMSource rpmSource = new CounterRPMSource(lightSensorShooter);

	BBSController cont1, cont2;
	
	public Shooter() {
		super();
		cont1 = new BBSController(rpmSource, shooter1, 3000, .8);
		cont2 = new BBSController(rpmSource, shooter2, 3000, .8);
	}
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		// setDefaultCommand(new ShootHighGoal());
	}

	public void setShooter(double rpm) {
		
		cont1.setRPM(rpm);
		cont2.setRPM(rpm);
		
		//shooter1.set(speed);
		//shooter2.set(speed);
	}

}