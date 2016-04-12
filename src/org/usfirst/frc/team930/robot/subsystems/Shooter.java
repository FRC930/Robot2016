package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotConstants;
import org.usfirst.frc.team930.robot.RobotMap;
import org.usfirst.frc.team930.robot.commands.IntakeLiftHigh;
import org.usfirst.frc.team930.robot.commands.ShootHighGoal;
import org.usfirst.frc.team930.robot.controller.BBSController;
import org.usfirst.frc.team930.robot.controller.CounterRPMSource;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.Victor;

public class Shooter extends Subsystem {

	double Error = 0;
	double tempSpeed = 0;

//	public Victor shooter1 = new Victor(RobotMap.Shooter1Port);
//	public Victor shooter2 = new Victor(RobotMap.Shooter2Port);
	
	public CANTalon shooter1 = new CANTalon(RobotMap.Shooter1Port);
	public CANTalon shooter2 = new CANTalon(RobotMap.Shooter2Port);

	DigitalInput lightSensorShooter = new DigitalInput(RobotMap.lightSensorShooterPort); 

	//CounterRPMSource rpmSource = new CounterRPMSource(shooter1);
	
	//Encoder rpmSource = new Encoder(RobotMap.Shooter1Port);
	
	public boolean commandRunning = false;
	//BBSController cont1, cont2;

	public Shooter() {
		super();
		
		shooter1.setInverted(true);

		shooter1.changeControlMode(TalonControlMode.Speed);
		//shooter2.changeControlMode(TalonControlMode.Follower);
		
		shooter2.changeControlMode(TalonControlMode.Follower);

		shooter2.reverseOutput(true);
		
		shooter1.enableBrakeMode(false);
		shooter2.enableBrakeMode(false);

		shooter1.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		//shooter1.configEncoderCodesPerRev(RobotConstants.codesPerRev);
		
		
		shooter1.setPID(RobotConstants.shooterP,RobotConstants.shooterI,0);
		shooter1.enableControl();
		
		
		
		// bang bang controllers
	//	cont1 = new BBSController(rpmSource, shooter1, 0, 1);
		//cont2 = new BBSController(rpmSource, shooter2, 0, 1);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		//setDefaultCommand(new IntakeLiftHigh());
	}

	public void setShooter(double rpm) {
		shooter1.set(-rpm);
		shooter2.set(6);
		
		//shooter1.set(-1);
		//shooter2.set(-1);
	
		try {
			SmartDashboard.putNumber("shooter speed", shooter1.getSpeed());
			System.out.println("SHOOTER 1 " + shooter1.get());
			System.out.println("SHOOTER 2 " + shooter2.get());
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		

	//	shooter1.getEncVelocity();
	//	cont1.setRPM(rpm);
		
		
	//	cont2.setRPM(rpm);
//		Error = (rpm-30.0/rpmSource.getPeriod());
//		System.out.print("ERROR:      ");
//		System.out.println(Error);
//
//		tempSpeed = ((rpm - 30.0/rpmSource.getPeriod()) * RobotConstants.shooterP) / RobotConstants.shooterMaxSpeed;
//		if(tempSpeed <0){
//			shooter1.set(0);
//			shooter2.set(0);
//		}
//		else{
//			
//				 shooter1.set(tempSpeed);
//				 shooter2.set(tempSpeed);
//				 
//		 
//		}
		 	
	}
	
	public double encoderPulses()
	{
		return shooter1.getEncPosition();
		
	}
	
	public void enableTalons()
	{
		shooter1.enable();
		shooter2.enable();
	}
	
	public void disableTalons()
	{
		shooter1.disable();
		shooter2.disable();
	}
	
	public void setShooterPlain(double percent)
	{
		shooter1.set(percent);
		shooter2.set(percent);
	}
	
	public void print(){
		 //System.out.println("Shooter1Speed    "+ 30.0/rpmSource.getPeriod());
//		 SmartDashboard.putNumber("Shooter1Speed", 30.0/rpmSource.getPeriod());
		 SmartDashboard.putNumber("ShooterError", Error);
		 SmartDashboard.putNumber("ShooterTempSpeed",tempSpeed );

		 
	}
	
	public boolean seeBall(){
		return lightSensorShooter.get();
	}

	public void setAccel(double accel) {
	//	cont1.setAccel(accel);
	//	cont2.setAccel(accel);
	}
	
	public void enable() {
	//	cont1.enable();
	//	cont2.enable();
	}
	
	public void disable() {
		//cont1.disable();
		//cont2.disable();
	}
	
}