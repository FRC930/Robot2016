package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;


public class IntakeLifter extends Subsystem {
	AnalogPotentiometer potentiometer = new AnalogPotentiometer(RobotMap.ILiftPort);
	Spark intakeLifter = new Spark(RobotMap.I2Port);
	PIDController pidController = new PIDController(0,0,0,potentiometer,intakeLifter);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());

	}

	public void setintakeLifter(double angle) {
		intakeLifter.set(angle);
		pidController.setSetpoint(angle);
		System.out.println(potentiometer.get());
	}
}
