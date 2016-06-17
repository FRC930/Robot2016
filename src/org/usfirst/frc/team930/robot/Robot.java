package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Joystick.RumbleType;


import org.usfirst.frc.team930.robot.commands.A2ChevalS;
import org.usfirst.frc.team930.robot.commands.A2MoatS;
import org.usfirst.frc.team930.robot.commands.A2PortS;
import org.usfirst.frc.team930.robot.commands.A2RTerrainS;
import org.usfirst.frc.team930.robot.commands.A2RampS;
import org.usfirst.frc.team930.robot.commands.A2RockwallS;
import org.usfirst.frc.team930.robot.commands.A3ChevalS;
import org.usfirst.frc.team930.robot.commands.A3MoatS;
import org.usfirst.frc.team930.robot.commands.A3PortS;
import org.usfirst.frc.team930.robot.commands.A3RTerrainS;
import org.usfirst.frc.team930.robot.commands.A3RampS;
import org.usfirst.frc.team930.robot.commands.A3RockwallS;
import org.usfirst.frc.team930.robot.commands.A4ChevalS;
import org.usfirst.frc.team930.robot.commands.A4MoatS;
import org.usfirst.frc.team930.robot.commands.A4PortS;
import org.usfirst.frc.team930.robot.commands.A4RTerrainS;
import org.usfirst.frc.team930.robot.commands.A4RampS;
import org.usfirst.frc.team930.robot.commands.A4RockwallS;
import org.usfirst.frc.team930.robot.commands.A5ChevalS;
import org.usfirst.frc.team930.robot.commands.A5MoatS;
import org.usfirst.frc.team930.robot.commands.A5PortS;
import org.usfirst.frc.team930.robot.commands.A5RTerrainS;
import org.usfirst.frc.team930.robot.commands.A5RampS;
import org.usfirst.frc.team930.robot.commands.A5RockwallS;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.usfirst.frc.team930.robot.commands.Auto2ndPositionCommandGroupShoot;
import org.usfirst.frc.team930.robot.commands.Auto3rdPositionShootCommandGroup;
import org.usfirst.frc.team930.robot.commands.Auto4thCommandGroup;
import org.usfirst.frc.team930.robot.commands.AutoDriveForward;
import org.usfirst.frc.team930.robot.commands.AutoDrivePort;
import org.usfirst.frc.team930.robot.commands.AutoLowBar;
import org.usfirst.frc.team930.robot.commands.AutoLowBarCommandGroupShoot;
import org.usfirst.frc.team930.robot.commands.AutoLowBarShoot;
import org.usfirst.frc.team930.robot.commands.IntakeLiftHigh;
import org.usfirst.frc.team930.robot.commands.LiftHanger;
import org.usfirst.frc.team930.robot.commands.RetractHanger;
import org.usfirst.frc.team930.robot.commands.SpyBoxShooter;
import org.usfirst.frc.team930.robot.commands.WinchHanger;
import org.usfirst.frc.team930.robot.commands.IntakeLiftPort;
import org.usfirst.frc.team930.robot.commands.MoveIntakeRollers;
import org.usfirst.frc.team930.robot.commands.Pickup;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain;
import org.usfirst.frc.team930.robot.subsystems.IntakeLifter;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller;
import org.usfirst.frc.team930.robot.subsystems.Shooter;
import org.usfirst.frc.team930.robot.subsystems.HangerLifter;
import org.usfirst.frc.team930.robot.subsystems.HangerWinch;
import org.usfirst.frc.team930.robot.commands.UnwindWinch;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static IntakeRoller intakeRoller;
	public static Shooter shooter;
	public static IntakeLifter intakeLifter;
	public static OI oi;
	public static HangerLifter hangerLifter;
	public static HangerWinch hangerWinch;
	
	

	static {
		try {
			drivetrain = new Drivetrain();
			intakeRoller = new IntakeRoller();
			System.out.println("in static");
			shooter = new Shooter();
			System.out.println("made shooter");
			hangerLifter = new HangerLifter();
			hangerWinch = new HangerWinch();
			intakeLifter = new IntakeLifter();
		} catch(Exception e) {
			System.out.println("exception");

			e.printStackTrace();
		}
	}
	Command autonmousCommand;
	SendableChooser autoChooser;
	Command autonomousCommand;
	Command intakeLiftPortTeleop;
	Command intakeLiftHighTeleop;
	Command pickupTeleop;
	Command rollersForwardTeleop;
	Command rollersBackwardTeleop;
	Command rollersStopTeleop;
	Command liftHanger;
	Command winchHanger;
	Command retractHanger;
	Command unwinchHanger;

	// CAMERA --------------------------------
	int session;
	Image frame;
	// CAMERA --------------------------------

	SendableChooser chooser;
	public static Preferences prefs;
	//	Timer timer = new Timer();
	//	double startTime;
	//	double currentTime;

	public void robotInit() {
		OI.getInstance();
		prefs = Preferences.getInstance();
		
		
    

		// CAMERA --------------------------------
		try{
			frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
			session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			NIVision.IMAQdxConfigureGrab(session);
		}catch(Exception e){
		}
		// CAMERA --------------------------------
		
		autoChooser = new SendableChooser();
		autoChooser.addDefault ("Auto Drive Forward", new AutoDriveForward());
		//autoChooser.addObject ("SpyBotShooter", new SpyBoxShooter());
		//autoChooser.addObject ("Low Bar", new AutoLowBar());
		autoChooser.addObject ("Low Bar Shooter", new AutoLowBarCommandGroupShoot());
		//autoChooser.addObject ("Portcullis", new AutoDrivePort());
		//autoChooser.addObject ("2nd Position Shoot", new Auto2ndPositionCommandGroupShoot());
		//autoChooser.addObject ("3rd Position Shoot", new Auto3rdPositionShootCommandGroup());
		//autoChooser.addObject ("4th Position Shoot", new Auto4thCommandGroup());
		autoChooser.addObject("2 Cheval Shoot", new A2ChevalS());
		autoChooser.addObject("2 Port Shoot", new A2PortS());
		autoChooser.addObject("2 Moat Shoot", new A2MoatS());
		autoChooser.addObject("2 Ramparts Shoot", new A2RampS());
		autoChooser.addObject("2 Rockwall Shoot", new A2RockwallS());
		autoChooser.addObject("2 RTerrain Shoot", new A2RTerrainS());
		autoChooser.addObject("3 Cheval Shoot", new A3ChevalS());
		autoChooser.addObject("3 Port Shoot", new A3PortS());
		autoChooser.addObject("3 Moat Shoot", new A3MoatS());
		autoChooser.addObject("3 Ramparts Shoot", new A3RampS());
		autoChooser.addObject("3 Rockwall Shoot", new A3RockwallS());
		autoChooser.addObject("3 RTerrain Shoot", new A3RTerrainS());
		autoChooser.addObject("4 Cheval Shoot", new A4ChevalS());
		autoChooser.addObject("4 Port Shoot", new A4PortS());
		autoChooser.addObject("4 Moat Shoot", new A4MoatS());
		autoChooser.addObject("4 Ramparts Shoot", new A4RampS());
		autoChooser.addObject("4 Rockwall Shoot", new A4RockwallS());
		autoChooser.addObject("4 RTerrain Shoot", new A4RTerrainS());
		autoChooser.addObject("5 Cheval Shoot", new A5ChevalS());
		autoChooser.addObject("5 Port Shoot", new A5PortS());
		autoChooser.addObject("5 Moat Shoot", new A5MoatS());
		autoChooser.addObject("5 Ramparts Shoot", new A5RampS());
		autoChooser.addObject("5 Rockwall Shoot", new A5RockwallS());
		autoChooser.addObject("5 RTerrain Shoot", new A5RTerrainS());
		
		SmartDashboard.putData("Autonomous mode chooser",autoChooser);

	}

	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)

		autonomousCommand = (Command) autoChooser.getSelected();
		autonomousCommand.start();
//		if (autonomousCommand != null)
//			autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		

		intakeLiftPortTeleop = new IntakeLiftPort();
		intakeLiftHighTeleop = new IntakeLiftHigh();
		pickupTeleop = new Pickup();
		rollersForwardTeleop = new MoveIntakeRollers(IntakeRoller.Direction.FORWARD);
		rollersBackwardTeleop = new MoveIntakeRollers(IntakeRoller.Direction.BACKWARD);
		rollersStopTeleop = new MoveIntakeRollers(IntakeRoller.Direction.STOP);
		liftHanger = new LiftHanger();
		winchHanger = new WinchHanger();
		retractHanger = new RetractHanger();
		unwinchHanger = new UnwindWinch();

		// CAMERA --------------------------------
		try{
			NIVision.IMAQdxStartAcquisition(session);
		}catch(Exception e){
		}
		// CAMERA --------------------------------
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//UNCOMMENT
		//SmartDashboard.putNumber("DEVIN LOOK HERE YOU BUM!!!!!!", Robot.shooter.encoderPulses());
		
		// CAMERA --------------------------------
		try{
			NIVision.IMAQdxGrab(session, frame, 1);
			CameraServer.getInstance().setImage(frame);
		}catch(Exception e){
		}
		// CAMERA --------------------------------

		SmartDashboard.putNumber("R2 Output", Robot.drivetrain.R2.get());
		
		if(Robot.shooter.seeBall())
			SmartDashboard.putNumber("Shooter Photo Eye GRAPH", 1 + Math.random() *.001);
		else if(!Robot.shooter.seeBall())
			SmartDashboard.putNumber("Shooter Photo Eye GRAPH", 0 + Math.random() *.001);
		SmartDashboard.putBoolean("Is ball in intake?", Robot.intakeRoller.seeBall());
		//System.out.println("             POT--------------------------- " + Robot.intakeLifter.getPOT());
		SmartDashboard.putBoolean("Shooter Photo Eye", Robot.shooter.seeBall());
		SmartDashboard.putNumber("pot", Robot.intakeLifter.getPOT());

		//System.out.println(Robot.intakeRoller.seeBall());
		SmartDashboard.putNumber("gyro",Robot.drivetrain.gyro.getAngle());
		//System.out.println("Pot value: " + Robot.intakeLifter.getPOT());
		
		try {
			SmartDashboard.putNumber("voltage 1",Robot.shooter.shooter1.get());
			SmartDashboard.putNumber("voltage 2",Robot.shooter.shooter2.get());
		} catch(Exception e) {
			e.printStackTrace();
		}
		

		// When the left trigger is held down, the intake lifter moves to the portcullis angle
		// When the right trigger is held down, the intake lifter moves down and the rollers move inward 

		// Moving the position of the arm with driver triggers
		//System.out.println("See ball: " + Robot.intakeRoller.seeBall());
		
		if (OI.getInstance().getLeftTrigger() >= 0.75){ // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! POT
			System.out.println("port ");
			if(!intakeLiftPortTeleop.isRunning()){
				intakeLiftPortTeleop.start();
				//System.out.println("starting port ");
			}	
		}else if((OI.getInstance().getRightTrigger() >= 0.75)){
			System.out.println(" pick up ");
			if(!pickupTeleop.isRunning()){
				pickupTeleop.start();
				System.out.println("starting pick up ");
			}
		}else{
			if(!intakeLiftHighTeleop.isRunning() && Robot.intakeLifter.PID.isEnabled()) {
				intakeLiftHighTeleop.start();
			}
			//System.out.println("Default");
		}

		// Moving the rollers with codriver triggers
		//		if(OI.getInstance().getCoLeftTrigger() >= 0.75){
		//			if( !rollersForwardTeleop.isRunning()){
		//				rollersForwardTeleop.start();
		//			}
		//		}else if (OI.getInstance().getCoRightTrigger() >= 0.75){
		//			if(!rollersBackwardTeleop.isRunning())
		//			{
		//				rollersBackwardTeleop.start();
		//			}
		//		}else{
		//			if(!pickupTeleop.isRunning() && !rollersStopTeleop.isRunning()){
		//				rollersStopTeleop.start();
		//			}
		//		}

		//		startTime = timer.get();
		//		currentTime = timer.get();
		//		
		//		while(startTime - currentTime < 5){
		//			currentTime = timer.get();
		if(Robot.intakeRoller.seeBall() == true && (OI.getInstance().getRightTrigger() > .75) && isEnabled()){
			OI.getInstance().driverJoystick.setRumble(RumbleType.kLeftRumble, 1);
			OI.getInstance().driverJoystick.setRumble(RumbleType.kRightRumble, 1);
		}else{
			OI.getInstance(). driverJoystick.setRumble(RumbleType.kLeftRumble, 0);
			OI.getInstance().driverJoystick.setRumble(RumbleType.kRightRumble, 0);
		}

	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}