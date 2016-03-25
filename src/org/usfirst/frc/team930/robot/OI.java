package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team930.robot.commands.GyroDriveBackward;
import org.usfirst.frc.team930.robot.commands.GyroDriveLeft;
import org.usfirst.frc.team930.robot.commands.GyroDriveRight;
import org.usfirst.frc.team930.robot.commands.GyroDriveStraight;
import org.usfirst.frc.team930.robot.commands.IntakeLiftPort;
import org.usfirst.frc.team930.robot.commands.ManualIntakeDown;
import org.usfirst.frc.team930.robot.commands.ManualIntakeUp;
import org.usfirst.frc.team930.robot.commands.MoveIntakeRollers;
import org.usfirst.frc.team930.robot.commands.Pickup;
import org.usfirst.frc.team930.robot.commands.RetractHanger;
import org.usfirst.frc.team930.robot.commands.ShootHighGoal;
import org.usfirst.frc.team930.robot.commands.LiftHanger;
import org.usfirst.frc.team930.robot.commands.UnwindWinch;
import org.usfirst.frc.team930.robot.commands.WinchHanger;
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
	Button coDriverButton9;
	Button coDriverButton10;

	double leftTrigger;
	double rightTrigger;
	double coLeftTrigger;
	double coRightTrigger;

	private OI() {
		// init
		try
		{
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
		coDriverButton9 = new JoystickButton(coJoystick, 9);
		coDriverButton10 = new JoystickButton(coJoystick, 10);


		//Driver Buttons (May end up being removed)
		//driverButton2.toggleWhenPressed(new MoveIntakeRollers(IntakeRoller.Direction.BACKWARD));
		//driverButton1.whenPressed(new Pickup());
		driverButton5.whenPressed(new ShootHighGoal());
	driverButton6.whenPressed(new ShootHighGoal());
		driverButton4.whileHeld(new GyroDriveStraight());
		driverButton2.whileHeld(new GyroDriveRight());
		driverButton3.whileHeld(new GyroDriveLeft());
		driverButton1.whileHeld(new GyroDriveBackward());
		
		
		//CoDriver Buttons
		//A Button runs Shooter
		//coDriverButton1.whenPressed(new ShootHighGoal());
		//Start/Select Buttons run a Hanger command that doesn't exist yet
		coDriverButton5.whileHeld(new MoveIntakeRollers(IntakeRoller.Direction.BACKWARD));
		coDriverButton4.whileHeld(new ManualIntakeDown());
		coDriverButton1.whileHeld(new ManualIntakeUp());
		coDriverButton6.whileHeld(new MoveIntakeRollers(IntakeRoller.Direction.FORWARD)); // in 
		coDriverButton7.whileHeld(new LiftHanger());
		coDriverButton8.whileHeld(new WinchHanger());
		coDriverButton9.whileHeld(new RetractHanger());
		coDriverButton10.whileHeld(new UnwindWinch());
		
		
		//Right Joystick runs Intake
		
		// test me later
//		if (coJoystick.getRawAxis(5) >= 0.75) new LiftIntake(IntakeLifter.Position.HIGH);
//		else new LiftIntake(IntakeLifter.Position.LOW);
//		if (coJoystick.getRawAxis(5) >= 0.75) new LiftIntake(IntakeLifter.Position.PORT);
//		else new LiftIntake(IntakeLifter.Position.LOW);
	}catch(Exception e){
		System.out.println(e.getLocalizedMessage());
	
	}
	}

	public static OI getInstance() {
		if (instance == null)
			instance = new OI();
		return instance;
	}
	
	public double getLeftTrigger(){
		return driverJoystick.getRawAxis(2);	
	}
	
	public double getRightTrigger(){
		return driverJoystick.getRawAxis(3);	
	}
	
	public double getCoLeftTrigger(){
		return coJoystick.getRawAxis(2);	
	}
	
	public double getCoRightTrigger(){
		return coJoystick.getRawAxis(3);
	}

	public double getXAxis() {
		// if things go funky change to getAxis
		return driverJoystick.getRawAxis(4);
	}

	public double getYAxis() {
		return -driverJoystick.getRawAxis(1);
	}
}