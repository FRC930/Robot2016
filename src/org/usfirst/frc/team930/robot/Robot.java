package org.usfirst.frc.team930.robot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.usfirst.frc.team930.robot.shooter.BBSController;
import org.usfirst.frc.team930.robot.shooter.ControllerSource;
import org.usfirst.frc.team930.robot.shooter.CounterRPMSource;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

		cont1 = new BBSController((ControllerSource) counter, shooter1, 500.0);
		cont2 = new BBSController((ControllerSource) counter, shooter2, 500.0);
	}

	public void autonomous() {
	}

	public void operatorControl() {

		Timer.delay(5);

		cont1.enable();
		cont2.enable();

		Timer.delay(5);

		cont1.disable();
		cont2.disable();
	}

	public void test() {
	}
}
