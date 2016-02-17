package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team930.robot.commands.LiftIntake;
import org.usfirst.frc.team930.robot.commands.MoveIntakeRollers;
import org.usfirst.frc.team930.robot.commands.ShootHighGoal;
import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;

public class OI {

	private static OI instance = new OI();

	Joystick driverJoystick1;

	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	Button button6;
	Button button7;

	private OI() {

		try {
			driverJoystick1 = new Joystick(1);
			System.out.println("JOYSTICKKKKK");
			System.out.println(driverJoystick1);

			button1 = new JoystickButton(driverJoystick1, 1);
			button2 = new JoystickButton(driverJoystick1, 2);
			button3 = new JoystickButton(driverJoystick1, 3);
			button4 = new JoystickButton(driverJoystick1, 4);
			button5 = new JoystickButton(driverJoystick1, 5);
			button6 = new JoystickButton(driverJoystick1, 6);
			button7 = new JoystickButton(driverJoystick1, 7);

			// CHECK MEEEE!!!! change button 2 and 1 to toggle

			button2.whileHeld(new MoveIntakeRollers(
					IntakeRoller.Direction.BACKWARD));
			button1.whileHeld(new MoveIntakeRollers(
					IntakeRoller.Direction.FORWARD));
			button3.whenPressed(new LiftIntake(IntakeLifter.Position.HIGH));
			button4.whenPressed(new ShootHighGoal());

			button6.whenPressed(new LiftIntake(IntakeLifter.Position.LOW));
			button7.whenPressed(new LiftIntake(IntakeLifter.Position.PORT));
		} catch (Exception e) {
			System.out.println("LOOK AT ME IM MR EXCEPTION");
			e.printStackTrace();
		}

	}

	public static OI getInstance() {
		if (instance == null)
			instance = new OI();
		return instance;
	}

	public double getXAxis() {
		// if things go funky change to getAxis
		return driverJoystick1.getRawAxis(0);
	}

	public double getYAxis() {
		return -driverJoystick1.getRawAxis(1);
	}
}