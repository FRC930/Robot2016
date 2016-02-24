package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Joystick.RumbleType;

import org.usfirst.frc.team930.robot.commands.AutoDriveForward;
import org.usfirst.frc.team930.robot.commands.GyroDriveBackward;
import org.usfirst.frc.team930.robot.commands.GyroDriveLeft;
import org.usfirst.frc.team930.robot.commands.GyroDriveRight;
import org.usfirst.frc.team930.robot.commands.GyroDriveStraight;
import org.usfirst.frc.team930.robot.commands.IntakeLiftHigh;
import org.usfirst.frc.team930.robot.commands.IntakeLiftPickup;
import org.usfirst.frc.team930.robot.commands.IntakeLiftPort;
import org.usfirst.frc.team930.robot.commands.MoveIntakeRollers;
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
	//public static HangerLifter hangerLifter;
	//public static HangerWinch hangerWinch;
	
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
	Command rollersForwardTeleop;
	Command rollersBackwardTeleop;
	Command rollersStopTeleop;
	Command gyroDriveBackward;
	Command gyroDriveLeft;
	Command gyroDriveRight;
	Command gyroDriveStraight;
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
		//Scheduler.getInstance().run();
	}

	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		gyroDriveBackward = new GyroDriveBackward();
		gyroDriveRight = new GyroDriveRight();
		gyroDriveLeft = new GyroDriveLeft();
		gyroDriveStraight = new GyroDriveStraight();
		intakeLiftPortTeleop = new IntakeLiftPort();
		intakeLiftHighTeleop = new IntakeLiftHigh();
		pickupTeleop = new Pickup();
		rollersForwardTeleop = new MoveIntakeRollers(IntakeRoller.Direction.FORWARD);
		rollersBackwardTeleop = new MoveIntakeRollers(IntakeRoller.Direction.BACKWARD);
		rollersStopTeleop = new MoveIntakeRollers(IntakeRoller.Direction.STOP);
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putBoolean("Is ball in Robot?", Robot.intakeRoller.seeBall());
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
				//System.out.println("starting pick up ");
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
		
		
		
		//!!!!!!!BEGINNING OF RUMBLE FOR INTAKE
		int counter = 0;
		int timer = 0;
		
		if(Robot.intakeRoller.getState() == IntakeRoller.Direction.FORWARD){
			timer++;
		}
		if(timer > 1 && Robot.intakeRoller.getState() == IntakeRoller.Direction.STOP){
			
			
		if(Robot.intakeRoller.seeBall() == true && oi.getRightTrigger() >= 0.75){
			counter++;
			oi.driverJoystick.setRumble(RumbleType.kRightRumble,1);
			oi.driverJoystick.setRumble(RumbleType.kLeftRumble,1);
			timer = 0;
			if(counter >= 30){
				oi.driverJoystick.setRumble(RumbleType.kRightRumble,0);
				oi.driverJoystick.setRumble(RumbleType.kLeftRumble,0);
				counter = 0;
				timer = 0;
				
			}
			else {
				counter = 0;
				timer = 0;
				
			}
		}
		else {
			oi.driverJoystick.setRumble(RumbleType.kRightRumble,0);
			oi.driverJoystick.setRumble(RumbleType.kLeftRumble,0);
			timer = 0;
		}
		}
		//!!!!!!!!!!!!!END OF RUMBLE FOR INTAKE
		
		//!!!!!!!!!!!!!BEGINNING OF GYRO RUMBLE
		int pulse = 0;
		if(gyroDriveBackward.isRunning()&&gyroDriveStraight.isRunning()&&gyroDriveLeft.isRunning()&&gyroDriveLeft.isRunning()){
			pulse++;
			if(pulse <= 17){
			oi.driverJoystick.setRumble(RumbleType.kRightRumble, 1);
			oi.driverJoystick.setRumble(RumbleType.kLeftRumble, 1);
			
			}
			else{
				pulse = 0;
				oi.driverJoystick.setRumble(RumbleType.kRightRumble, 0);
				oi.driverJoystick.setRumble(RumbleType.kLeftRumble, 0);
			}
		}
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}