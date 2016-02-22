package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team930.robot.commands.IntakeLiftPort;
import org.usfirst.frc.team930.robot.commands.ManualIntakeDown;
import org.usfirst.frc.team930.robot.commands.ManualIntakeUp;
import org.usfirst.frc.team930.robot.commands.MoveIntakeRollers;
import org.usfirst.frc.team930.robot.commands.Pickup;
import org.usfirst.frc.team930.robot.commands.ShootHighGoal;
import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;

public class OI {

	private static OI instance = new OI();

	Joystick driverJoystick;
	Joystick coJoystick;

	Button driverButton1;
	Button driverButton2;
	Button driverButton3;
	Button driverButton4;
	Button driverButton5;
	Button driverButton6;
	Button driverButton7;
	
	Button coDriverButton1;
	Button coDriverButton2;
	Button coDriverButton3;
	Button coDriverButton4;
	Button coDriverButton5;
	Button coDriverButton6;
	Button coDriverButton7;
	Button coDriverButton8;

	double leftTrigger;
	double rightTrigger;
	double coLeftTrigger;
	double coRightTrigger;

	private OI() {
		// init
		driverJoystick = new Joystick(1);
		coJoystick = new Joystick(2);

		driverButton1 = new JoystickButton(driverJoystick, 1);
		driverButton2 = new JoystickButton(driverJoystick, 2);
		driverButton3 = new JoystickButton(driverJoystick, 3);
		driverButton4 = new JoystickButton(driverJoystick, 4);
		driverButton5 = new JoystickButton(driverJoystick, 5);
		driverButton6 = new JoystickButton(driverJoystick, 6);
		driverButton7 = new JoystickButton(driverJoystick, 7);
		
		coDriverButton1 = new JoystickButton(coJoystick, 1);
		coDriverButton2 = new JoystickButton(coJoystick, 2);
		coDriverButton3 = new JoystickButton(coJoystick, 3);
		coDriverButton4 = new JoystickButton(coJoystick, 4);
		coDriverButton5 = new JoystickButton(coJoystick, 5);
		coDriverButton6 = new JoystickButton(coJoystick, 6);
		coDriverButton7 = new JoystickButton(coJoystick, 7);
		coDriverButton8 = new JoystickButton(coJoystick, 8);

		leftTrigger = driverJoystick.getRawAxis(2);
		rightTrigger = driverJoystick.getRawAxis(3);
		coLeftTrigger = coJoystick.getRawAxis(2);
		coRightTrigger = coJoystick.getRawAxis(3);

		//Driver Buttons (May end up being removed)
		driverButton2.toggleWhenPressed(new MoveIntakeRollers(
				IntakeRoller.Direction.BACKWARD));
		driverButton1.whenPressed(new Pickup());
		driverButton4.whenPressed(new ShootHighGoal());
		coDriverButton3.whenPressed(new ManualIntakeDown());
		coDriverButton4.whenPressed(new ManualIntakeUp());
		
		//CoDriver Buttons
		//A Button runs Shooter
		coDriverButton1.whenPressed(new ShootHighGoal());
		//Start/Select Buttons run a Hanger command that doesn't exist yet
		//coDriverButton7.whenPressed(new <LiftHanger>);
		//coDriverButton8.whenPressed(new <LowerHanger>);
		
		//Right Joystick runs Intake
		
		// test me later
//		if (coJoystick.getRawAxis(5) >= 0.75) new LiftIntake(IntakeLifter.Position.HIGH);
//		else new LiftIntake(IntakeLifter.Position.LOW);
//		if (coJoystick.getRawAxis(5) >= 0.75) new LiftIntake(IntakeLifter.Position.PORT);
//		else new LiftIntake(IntakeLifter.Position.LOW);
	}

	public static OI getInstance() {
		if (instance == null)
			instance = new OI();
		return instance;
	}
	
	public double getLeftTrigger(){
		return leftTrigger;	
	}
	
	public double getRightTrigger(){
		return rightTrigger;	
	}
	
	public double getCoLeftTrigger(){
		return coLeftTrigger;	
	}
	
	public double getCoRightTrigger(){
		return coRightTrigger;	
	}

	public double getXAxis() {
		// if things go funky change to getAxis
		return driverJoystick.getRawAxis(0);
	}

	public double getYAxis() {
		return -driverJoystick.getRawAxis(1);
	}
}