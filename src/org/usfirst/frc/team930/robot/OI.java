package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team930.robot.commands.IntakeLiftHigh;
import org.usfirst.frc.team930.robot.commands.IntakeLiftLow;
import org.usfirst.frc.team930.robot.commands.IntakeLiftPort;
import org.usfirst.frc.team930.robot.commands.MoveIntakeRollers;
import org.usfirst.frc.team930.robot.commands.ShootHighGoal;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;


public class OI {

	private static OI instance = new OI();

	static Joystick driverJoystick1;

	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button button6;
	Button button7;

	private OI() {

		driverJoystick1 = new Joystick(1);

		button1 = new JoystickButton(driverJoystick1, 1);
		button2 = new JoystickButton(driverJoystick1, 2);
		button3 = new JoystickButton(driverJoystick1, 3);
		button4 = new JoystickButton(driverJoystick1, 4);
		button5 = new JoystickButton(driverJoystick1, 5);
		button6 = new JoystickButton(driverJoystick1, 6);
		button7 = new JoystickButton(driverJoystick1, 7);

		// CHECK MEEEE!!!! change button 2 and 1 to toggle

		button2.whileHeld(new MoveIntakeRollers(IntakeRoller.Direction.BACKWARD));
		button1.whileHeld(new MoveIntakeRollers(IntakeRoller.Direction.FORWARD));
		button4.whenPressed(new IntakeLiftHigh());
		button3.toggleWhenPressed(new ShootHighGoal());
		
		button6.whenPressed(new IntakeLiftLow());
		button7.whenPressed(new IntakeLiftPort());

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