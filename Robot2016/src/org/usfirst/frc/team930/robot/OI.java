package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team930.robot.commands.Drive;
import org.usfirst.frc.team930.robot.commands.MoveIntakeRollers;
import org.usfirst.frc.team930.robot.commands.ShootLowGoal;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);
	private static OI instance = new OI();
	Button button0 = new JoystickButton(driverJoystick, 0);
	Button button1 = new JoystickButton(driverJoystick, 1);

	private OI() {
		try { // CHECK MEEEE!!!!
			button0.whenPressed(new ShootLowGoal());
			button1.whenPressed(new MoveIntakeRollers());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static OI getInstance() {
		if (instance == null)
			instance = new OI();
		return instance;
	}

	static Joystick driverJoystick = new Joystick(0);

	public static double getXAxis() {
		return driverJoystick.getRawAxis(0);
	}

	public static double getYAxis() {
		return driverJoystick.getRawAxis(1);
	}

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
