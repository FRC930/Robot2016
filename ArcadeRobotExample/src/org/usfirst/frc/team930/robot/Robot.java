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
	double speed;
	CANTalon shooter1;
	CANTalon shooter2;
	Preferences prefs;
	Counter counter;

	public Robot() {
		prefs = Preferences.getInstance();
		
		shooter1 = new CANTalon(0);
		shooter2 = new CANTalon(2);
		
		counter = new Counter(new DigitalInput(0));
		counter.setSemiPeriodMode(true);
	}

	public void autonomous() {
	}

	public void operatorControl() {
		while (isOperatorControl() && isEnabled()) {

			speed = prefs.getDouble("Speed", 0);
			shooter1.set(speed);
			shooter2.set(speed);
			SmartDashboard.putNumber("Motor Output", speed);
			
			double highPulse = 30f / counter.getPeriod();
			SmartDashboard.putNumber("RPM Output", highPulse);

			Timer.delay(0.005); // wait for a motor update time
		}
	}

	public void test() {
	}
}
