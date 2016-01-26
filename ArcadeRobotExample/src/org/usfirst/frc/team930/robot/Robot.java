package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends SampleRobot {
	// RobotDrive myRobot;
	// Joystick stick;
	double rotateValue;
	double Speed;
	CANTalon shooterwheels;
	CANTalon shooterwheels2;
	Preferences prefs;
	Counter counter1;

	public Robot() {
		// myRobot = new RobotDrive(0, 1);
		// m//yRobot.setExpiration(0.1);
		// stick = new Joystick(0);
		shooterwheels = new CANTalon(0);
		shooterwheels2 = new CANTalon(2);
		prefs = Preferences.getInstance();
		counter1 = new Counter(new DigitalInput(0));
		counter1.setSemiPeriodMode(true);
	}

	public void autonomous() {
	}

	public void operatorControl() {
		// myRobot.setSafetyEnabled(true);
		while (isOperatorControl() && isEnabled()) {
			// rotateValue = stick.getX();
			// moveValue = stick.getY();

			Speed = prefs.getDouble("Speed", 0);
			shooterwheels.set(Speed);
			shooterwheels2.set(Speed);
			SmartDashboard.putNumber("Motor Output", Speed);
			// rotateValue = prefs.getDouble("Rotatevalue", 4.);

			double highPulse = counter1.getPeriod();
			highPulse = 30.0 / highPulse;
			SmartDashboard.putNumber("RPM Output", highPulse);

			/*
			 * CameraServer server = CameraServer.getInstance();
			 * server.setQuality(100); server.startAutomaticCapture("cam0");
			 */

			// myRobot.arcadeDrive(moveValue, rotateValue); // drive with arcade
			// style (use right stick)
			Timer.delay(0.005); // wait for a motor update time
		}
	}

	public void test() {
	}
}
