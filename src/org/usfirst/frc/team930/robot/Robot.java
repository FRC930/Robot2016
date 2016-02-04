package org.usfirst.frc.team930.robot;

import org.usfirst.frc.team930.robot.shooter.BBSController;
import org.usfirst.frc.team930.robot.shooter.ControllerSource;
import org.usfirst.frc.team930.robot.shooter.CounterRPMSource;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends SampleRobot {

	CANTalon shooter1;
	CANTalon shooter2;
	Counter counter;
	BBSController cont1;
	BBSController cont2;

	public Robot() {
		shooter1 = new CANTalon(0);
		shooter2 = new CANTalon(2);

		counter = new CounterRPMSource(new DigitalInput(0));
		counter.setSemiPeriodMode(true);

		cont1 = new BBSController((ControllerSource) counter, shooter1, 3000.0, .8);
		cont2 = new BBSController((ControllerSource) counter, shooter2, 3000.0, .8);
	}

	public void autonomous() {
	}

	public void operatorControl() {

		Timer.delay(3);

		cont1.enable();
		cont2.enable();

		while(isEnabled()){ }
		
//		Timer.delay(2);

		cont1.disable();
		cont2.disable();
	}

	public void test() {
	}
}
