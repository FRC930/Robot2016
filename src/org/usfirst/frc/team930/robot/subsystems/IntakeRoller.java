package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.RobotConstants;
import org.usfirst.frc.team930.robot.RobotMap;
import org.usfirst.frc.team930.robot.commands.MoveIntakeRollers;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeRoller extends Subsystem {

	public static enum Direction {
	//sets the value for all the speeds the of rollers
		STOP(0),
		FORWARD(RobotConstants.intakeRollerforward),
		BACKWARD(RobotConstants.intakeRollerbackward),
		SHOOTERPULL(RobotConstants.intakeLifterDvalue);
		//declaress a variable to run the speed of the rollers
		private final double speed;
		
		private Direction(double d) {
			//sets the speed to be equal to the direction
			speed = d;
		}
		//gets the speed/direction of the rollers 
		public double getSpeed() {
			return speed;
			
		}
		public String toString(){
			return "Speed: " + speed;
		}
		
	}
	//sets it so that you can set the direction with the word state
	Direction state;
	//declares the victor to control the motor
	Victor intakeRoller = new Victor(RobotMap.IntakeRollersPort);
	//declares the Lightsensor so it can get values
	DigitalInput lightSensor = new DigitalInput(RobotMap.lightSensorIntakePort);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public IntakeRoller() {
		super();
		//sets the first direction to stop
		state = Direction.STOP;
		//sets the motor to do the opposite of the value I give it
		intakeRoller.setInverted(true);
	}																																																																																																																																																														
		
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		// setDefaultCommand(new MoveIntakeRollers(Direction.FORWARD));
	}

	public void setState(Direction d) {
		//System.out.println("Setting Direction: "+d);
		//sets state the equal direction
		state = d;
		intakeRoller.set(state.getSpeed());
	}
	//gives the state of the lightsensor
	public boolean seeBall() {
		//System.out.println("I see ball: "+lightSensor.get());
		return lightSensor.get();
		
	}
	//gets the direction of the rollers
	public Direction getState() {
		return state;
	}
}