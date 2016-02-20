package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Joystick.RumbleType;

import org.usfirst.frc.team930.robot.commands.IntakeLiftHigh;
import org.usfirst.frc.team930.robot.commands.IntakeLiftPickup;
import org.usfirst.frc.team930.robot.commands.IntakeLiftPort;
import org.usfirst.frc.team930.robot.commands.Pickup;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain;
import org.usfirst.frc.team930.robot.subsystems.HangerLifter;
import org.usfirst.frc.team930.robot.subsystems.HangerWinch;
import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;
import org.usfirst.frc.team930.robot.subsystems.Shooter;

public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static IntakeRoller intakeRoller;
	public static Shooter shooter;
	public static IntakeLifter intakeLifter;
	public static OI oi;
	//public static HangerLifter hangerLifter;
	public static HangerWinch hangerWinch;
	
	static {
		try {
			drivetrain = new Drivetrain();
			intakeRoller = new IntakeRoller();
			System.out.println("in static");
			shooter = new Shooter();
			System.out.println("made shooter");

			intakeLifter = new IntakeLifter();
		} catch(Exception e) {
			System.out.println("exception");

			e.printStackTrace();
		}
	}
	
	Command autonomousCommand;
	Command intakeLiftPortTeleop;
	Command intakeLiftHighTeleop;
	Command pickupTeleop;
	SendableChooser chooser;
	public static Preferences prefs;

	public void robotInit() {
		 OI.getInstance();
		
		prefs = Preferences.getInstance();
	}

	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		autonomousCommand = (Command) chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		//Scheduler.getInstance().run();
	}

	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		intakeLiftPortTeleop = new IntakeLiftPort();
		intakeLiftHighTeleop = new IntakeLiftHigh();
		pickupTeleop = new Pickup();
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("Status of Photo eye", Robot.intakeRoller.seeBall());
		System.out.println(Robot.intakeRoller.seeBall());
		System.out.println(Robot.drivetrain.gyro.getAngle());
		
		// When the left trigger is held down, the intake lifter moves to the portcullis angle
		// When the right trigger is held down, the intake lifter moves down and the rollers move inward 
		if (OI.getInstance().getLeftTrigger() >= 0.75 && !intakeLiftPortTeleop.isRunning()){
			intakeLiftPortTeleop.start();
		}else if((OI.getInstance().getRightTrigger() >= 0.75) && !pickupTeleop.isRunning()){
			pickupTeleop.start();
		}else
			intakeLiftHighTeleop.start();
		
		if(oi.getRightTrigger()>0.75 && Robot.intakeRoller.seeBall() == true){
			oi.driverJoystick.setRumble(RumbleType.kLeftRumble, 1);
			oi.driverJoystick.setRumble(RumbleType.kRightRumble, 1);
		}else{
			oi.driverJoystick.setRumble(RumbleType.kLeftRumble,0);
			oi.driverJoystick.setRumble(RumbleType.kRightRumble, 0);
		}
		
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
