package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team930.robot.commands.IntakeLiftHigh;
import org.usfirst.frc.team930.robot.commands.IntakeLiftLow;
import org.usfirst.frc.team930.robot.commands.IntakeLiftPort;
import org.usfirst.frc.team930.robot.commands.MoveIntakeRollers;
import org.usfirst.frc.team930.robot.commands.ShootHighGoal;
import org.usfirst.frc.team930.robot.commands.ShootLowGoal;
import org.usfirst.frc.team930.robot.commands.StopRollers;

public class OI {

	private static OI instance = new OI();

	static Joystick driverJoystick1;

	Button button0;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button button6;

	private OI() {

		driverJoystick1 = new Joystick(1);

		button0 = new JoystickButton(driverJoystick1, 1);
		button1 = new JoystickButton(driverJoystick1, 2);
		button2 = new JoystickButton(driverJoystick1, 3);
		button3 = new JoystickButton(driverJoystick1, 4);
		button4 = new JoystickButton(driverJoystick1, 5);
		button5 = new JoystickButton(driverJoystick1, 6);
		button6 = new JoystickButton(driverJoystick1, 7);

		// CHECK MEEEE!!!!

		button0.whenPressed(new ShootLowGoal());
		button1.whenPressed(new MoveIntakeRollers());
		button2.whenPressed(new IntakeLiftHigh());
		button3.whenPressed(new ShootHighGoal());
		button4.whenPressed(new StopRollers());
		button5.whenPressed(new IntakeLiftLow());
		button6.whenPressed(new IntakeLiftPort());

	}

	public static OI getInstance() {
		if (instance == null)
			instance = new OI();
		return instance;
	}

	public static double getXAxis() {
		// if things go funky change to getAxis
		return driverJoystick1.getRawAxis(0);
	}

	public static double getYAxis() {
		return -driverJoystick1.getRawAxis(1);
	}
}