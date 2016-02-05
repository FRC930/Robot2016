package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team930.robot.commands.Drive;
import org.usfirst.frc.team930.robot.commands.IntakeLiftCMD;
import org.usfirst.frc.team930.robot.commands.MoveIntakeRollers;
import org.usfirst.frc.team930.robot.commands.ShootHighGoal;
import org.usfirst.frc.team930.robot.commands.ShootLowGoal;
import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;

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
	
	static Joystick driverJoystick1;
	Button button0;
	Button button1;
	Button button2;
    Button button3;
	private OI() {
		driverJoystick1 = new Joystick(1);
		button0 = new JoystickButton(driverJoystick1,1);
		button1 = new JoystickButton(driverJoystick1,2);
		button2 = new JoystickButton(driverJoystick1,3);
		button3 = new JoystickButton(driverJoystick1,4);
		
		// CHECK MEEEE!!!!
		
		
		
			
			button0.whenPressed(new ShootLowGoal());
			button1.whenPressed(new MoveIntakeRollers());
			button2.whenPressed(new IntakeLiftCMD());
			button3.whenPressed(new ShootHighGoal());
		
		
		}
	

	public static OI getInstance() {
		if (instance == null)
			instance = new OI();
		return instance;
	}

	public static double getXAxis() {
		//if things go funky change to getAxis
		return driverJoystick1.getRawAxis(0);
	}

	public static double getYAxis() {
		return driverJoystick1.getRawAxis(1);
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
