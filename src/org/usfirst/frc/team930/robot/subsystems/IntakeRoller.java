package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeRoller extends Subsystem {

	public enum Direction {
		
		FORWARD = new Direction(1f),
		BACKWARD = new Direction(-1f),
		STOP = new Direction(0f);
		
		private final double speed;
		
		private Direction(double s) {
			speed = s;
		}
		
		public double getSpeed() {
			return speed;
		}
	}
	
	Victor intakeRoller = new Victor(RobotMap.I1Port);

	DigitalInput lightSensor = new DigitalInput(RobotMap.lightSensorPort);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setintakeRoller(double speed) {
		intakeRoller.set(speed);
	}
	
	public boolean seeBall() {
		return lightSensor.get();
	}
	
}