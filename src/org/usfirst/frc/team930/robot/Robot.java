package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Joystick.RumbleType;

import org.usfirst.frc.team930.robot.commands.AutoDriveForward;
import org.usfirst.frc.team930.robot.commands.GyroDriveBackward;
import org.usfirst.frc.team930.robot.commands.GyroDriveLeft;
import org.usfirst.frc.team930.robot.commands.GyroDriveRight;
import org.usfirst.frc.team930.robot.commands.GyroDriveStraight;
import org.usfirst.frc.team930.robot.commands.IntakeLiftHigh;
import org.usfirst.frc.team930.robot.commands.LiftHanger;
import org.usfirst.frc.team930.robot.commands.RetractHanger;
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
	
	SendableChooser chooser;
	public static Preferences prefs;
//	Timer timer = new Timer();
//	double startTime;
//	double currentTime;

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
		autonomousCommand = new AutoDriveForward();

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
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("Is ball in Robot?", Robot.intakeRoller.seeBall());
		System.out.println("             POT--------------------------- " + Robot.intakeLifter.getPOT());
		//System.out.println(Robot.intakeRoller.seeBall());
		//System.out.println(Robot.drivetrain.gyro.getAngle());
		//System.out.println("Pot value: " + Robot.intakeLifter.getPOT());
		
		// When the left trigger is held down, the intake lifter moves to the portcullis angle
		// When the right trigger is held down, the intake lifter moves down and the rollers move inward 
		
		// Moving the position of the arm with driver triggers
		//System.out.println("See ball: " + Robot.intakeRoller.seeBall());
		if (OI.getInstance().getLeftTrigger() >= 0.75){
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
			if(!intakeLiftHighTeleop.isRunning()) {
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
		if(Robot.intakeRoller.seeBall() == true && (OI.getInstance().getRightTrigger() > .75)){
	OI.getInstance().driverJoystick.setRumble(RumbleType.kLeftRumble, 1);
				OI.getInstance().driverJoystick.setRumble(RumbleType.kLeftRumble, 1);
			}else{
		OI.getInstance().driverJoystick.setRumble(RumbleType.kLeftRumble, 0);
				OI.getInstance().driverJoystick.setRumble(RumbleType.kLeftRumble, 0);
	}
	}
//			
//		}
//		OI.getInstance().driverJoystick.setRumble(RumbleType.kLeftRumble, 0);
//		OI.getInstance().driverJoystick.setRumble(RumbleType.kLeftRumble, 0);
		//!!!!!!!BEGINNING OF RUMBLE FOR INTAKE
//		boolean ballVibes = false;
//		if(Robot.intakeRoller.seeBall() == false){
//			ballVibes = true;
//		}
//		if(Robot.intakeRoller.seeBall() == true && ballVibes == true){
//			int count= 0;
//			
//			while(count<50){
//				count++;
//			
//				OI.getInstance().driverJoystick.setRumble(RumbleType.kLeftRumble, 1);
//				OI.getInstance().driverJoystick.setRumble(RumbleType.kLeftRumble, 1);
//				
//			}
//			if(count>50){
//				OI.getInstance().driverJoystick.setRumble(RumbleType.kLeftRumble, 0);
//				OI.getInstance().driverJoystick.setRumble(RumbleType.kLeftRumble, 0);
//				ballVibes = false;
//			}
//			else{
//				OI.getInstance().driverJoystick.setRumble(RumbleType.kLeftRumble, 0);
//				OI.getInstance().driverJoystick.setRumble(RumbleType.kLeftRumble, 0);
//				ballVibes = false;
//			}
//			
//		}
		
		 
		//!!!!!!!!!!!!!END OF RUMBLE FOR INTAKE
		
		//!!!!!!!!!!!!!BEGINNING OF GYRO RUMBLE
		



	public void testPeriodic() {
		LiveWindow.run();
	}
}