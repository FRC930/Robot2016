package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotMap;
import org.usfirst.frc.team930.robot.commands.Drive;
import org.usfirst.frc.team930.robot.controller.AlignOutput;
import org.usfirst.frc.team930.robot.controller.AngleSource;
import org.usfirst.frc.team930.robot.subsystems.IntakeLifter.Positions;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public CANTalon L1 = new CANTalon(RobotMap.LDriveFrontPort);
	public CANTalon L2 = new CANTalon(RobotMap.LDriveTopPort);
	public CANTalon L3 = new CANTalon(RobotMap.LDriveBackPort);
	public CANTalon R1 = new CANTalon(RobotMap.RDriveFrontPort);
	public CANTalon R2 = new CANTalon(RobotMap.RDriveTopPort);
	public CANTalon R3 = new CANTalon(RobotMap.RDriveBackPort);
	
	final double P = 0.02;
	final double I = 0.0001;
	final double D = 0;
	
	public double goalAngleStraight = 0.0;
	public double goalAngleBackward = 180.0;
	public double goalAngleLeft = -55.0;
	public double goalAngleRight = 55.0;

	//AnalogInput distanceSensor = new AnalogInput(0);
	
	//public Ultrasonic ultra = new Ultrasonic(0, 0);
	
	public AnalogGyro gyro = new AnalogGyro(0);
	public Ultrasonic distance = new Ultrasonic(RobotMap.distanceSensorOutPort,RobotMap.distanceSensorInPort);
	AngleSource source = new AngleSource();
	AlignOutput alignOutput = new AlignOutput(L1, L2, L3, R1, R2, R3);
	public PIDController drivePID = new PIDController(P, I, D, source, alignOutput, 0.01);
	
	public static enum Positions
	{
		STRAIGHT, 
		BACKWARD,
		LEFT,
		RIGHT;
	}

	public Drivetrain() {
		super();
		R1.setInverted(true);
		R2.setInverted(true);//http://first.wpi.edu/FRC/roborio/release/docs/java/edu/wpi/first/wpilibj/ADXRS450_Gyro.html#ADXRS450_Gyro--
		R3.setInverted(true);
		
		gyro.reset();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Drive());
	}

	public void setL(double speed) {
		L1.set(speed);
		L2.set(speed);
		L3.set(speed);
	}

	public void setR(double speed) {
		R1.set(speed);
		R2.set(speed);
		R3.set(speed);
	}

	public double getAngle() {
		return gyro.getAngle()%360.0;
	}
	
	public void setGoalAngle() {
		goalAngleStraight -= 90.0;
		goalAngleBackward -= 90.0;
		goalAngleLeft -= 90.0;
		goalAngleRight -= 90.0;
	}
	
	public double getGoalAngle(Positions angle) {
		switch (angle) {
		case STRAIGHT:
			return goalAngleStraight;
		case BACKWARD:
			return goalAngleBackward;
		case LEFT:
			return goalAngleLeft;
		case RIGHT:
			return goalAngleRight;
		default:
			return 0;
		}
	}
}