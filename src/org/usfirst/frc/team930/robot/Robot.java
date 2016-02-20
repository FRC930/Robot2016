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
import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;
import org.usfirst.frc.team930.robot.subsystems.Shooter;

public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static IntakeRoller intakeRoller;
	public static Shooter shooter;
	public static IntakeLifter intakeLifter;
	public static OI oi;
	
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
	SendableChooser chooser;
	public static Preferences prefs;

	public void robotInit() {
		 OI.getInstance();
		// chooser = new SendableChooser();
		// chooser.addDefault("Default Auto", new Drive());
		// chooser.addObject("My Auto", new MyAutoCommand());
		// SmartDashboard.putData("Auto mode", chooser);
		
		prefs = Preferences.getInstance();
		//SmartDashboard.putData("Update", IntakeLifter.Update()); //Creates a SmartDashboard button to call Update()
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
		
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("Status of Photo eye", Robot.intakeRoller.seeBall());
		System.out.println(Robot.intakeRoller.seeBall());
		System.out.println(Robot.drivetrain.gyro.getAngle());
		
		if (OI.getInstance().getLeftTrigger() >= 0.75){
			new IntakeLiftPort();
		}else{
			new IntakeLiftHigh();
		}
		
		if (OI.getInstance().getRightTrigger() >= 0.75){
			new Pickup();
		}else{
			new IntakeLiftHigh();
		}
		if(oi.getRightTrigger()>0.75 && Robot.intakeRoller.seeBall() == true){
			oi.driverJoystick.setRumble(RumbleType.kLeftRumble, 1);
		}
		
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
