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
	static Joystick codriverJoystick1;

	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button button6;
	Button button7;

	private OI() {

		driverJoystick1 = new Joystick(1);
		codriverJoystick1 = new Joystick(2);

		button1 = new JoystickButton(codriverJoystick1, 1);
		button2 = new JoystickButton(codriverJoystick1, 2);
		button3 = new JoystickButton(codriverJoystick1, 3);
		button4 = new JoystickButton(codriverJoystick1, 4);
		button5 = new JoystickButton(codriverJoystick1, 5);
		button6 = new JoystickButton(codriverJoystick1, 6);
		button7 = new JoystickButton(codriverJoystick1, 7);

		// CHECK MEEEE!!!!
		
		//button1.whenPressed(new StopRollers());
		button2.whileHeld(new MoveIntakeRollers());
		button3.whileHeld(new ShootLowGoal());
		button4.whileHeld(new ShootHighGoal());
		button5.whileHeld(new IntakeLiftLow());
		button6.whileHeld(new IntakeLiftHigh());
		//button6.whenPressed(new IntakeLiftPort());

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