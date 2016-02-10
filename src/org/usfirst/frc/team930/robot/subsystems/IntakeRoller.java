package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeRoller extends Subsystem {

	Victor intakeRoller = new Victor(RobotMap.I1Port);

	DigitalInput lightSensor = new DigitalInput(RobotMap.lightSensorPort);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void setintakeRoller(double speed/*, boolean sensor*/) {
		/*if(sensor == false || sensor == true && lightSensor.get() == false) intakeRoller.set(speed);
		else */ intakeRoller.set(speed);
	}

}