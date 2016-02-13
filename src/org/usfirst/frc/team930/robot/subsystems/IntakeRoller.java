package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeRoller extends Subsystem {

	public static enum Direction {
		
		STOP(0),
		FORWARD(.75),
		BACKWARD(-.75);
		
		private final double speed;
		
		private Direction(double d) {
			speed = d;
		}
		
		public double getSpeed() {
			return speed;
			
		}
		public String toString(){
			return "Speed: " + speed;
		}
	}
	
	Direction state;
	
	Victor intakeRoller = new Victor(RobotMap.I1Port);

	DigitalInput lightSensor = new DigitalInput(RobotMap.lightSensorPort);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public IntakeRoller() {
		super();
		state = Direction.STOP;
		
		intakeRoller.setInverted(true);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setState(Direction d) {
		System.out.println("Setting Direction: "+d);
		state = d;
		intakeRoller.set(state.getSpeed());
	}
	
	public boolean seeBall() {
		System.out.println("I see ball: "+lightSensor.get());
		return lightSensor.get();
		
	}
	
	public Direction getState() {
		return state;
	}
}