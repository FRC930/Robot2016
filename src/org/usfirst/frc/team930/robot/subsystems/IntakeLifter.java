package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeLifter extends Subsystem {

	static int P = Robot.prefs.getInt("P", 0);
	static int I = Robot.prefs.getInt("I", 0);
	static int D = Robot.prefs.getInt("D", 0);

	public static enum Position {
		LOW(20), HIGH(60), PORT(50), TO_SHOOTER(0);

		private final double angle;

		private Position(double a) {
			angle = a;
		}

		public double getAngle() {
			return angle;
		}
	}

	AnalogPotentiometer potentiometer = new AnalogPotentiometer(
			RobotMap.ILiftPort);
	Spark intakeLifter = new Spark(RobotMap.I2Port);
	PIDController pidController = new PIDController(0, 0, 0, potentiometer,
			intakeLifter);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());

	}

	/**
	 * Updates PID Values in accordance to Preferences inputs and returns the
	 * new angle that will be set.
	 * 
	 * @author GuyAcrossTheStreet
	 */
	public static Sendable Update() {
		/*
		 * P = Robot.prefs.getInt("P", 0); I = Robot.prefs.getInt("I", 0); D =
		 * Robot.prefs.getInt("D", 0); PID = new
		 * PIDController(P,I,D,potentiometer,intakeLifter);
		 * 
		 * return PID;
		 */
		return null;
	}

	public void setintakeLifter(double angle) {
		// intakeLifter.set(angle);
		// System.out.println(potentiometer.get());

		/*
		 * PID.setSetpoint(angle);
		 * 
		 * SmartDashboard.putData("Set Angle", PID);
		 * SmartDashboard.putData("Actual Angle", potentiometer);
		 */
	}
}
