package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotMap;
import org.usfirst.frc.team930.robot.subsystems.IntakeRoller.Direction;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class IntakeLifter extends Subsystem {
	
	public static enum Positions
	{
		PORT(123),
		DEFAULT(245),
		PICKUP(150);
private final double angle;
		
		private Positions(double p) {
			angle = p;
		}
		
		public double getAngle() {
			return angle;
			
		}
		public String toString(){
			return "Angle: " + angle;
		}
		
	}
	Positions position;
	//Preferences will allow remotely inputted proportional, integrational, and derivative values
	//static int P = Robot.prefs.getInt("P", 0);
	//static int I = Robot.prefs.getInt("I", 0);
	//static int D = Robot.prefs.getInt("D", 0);
	static double P = 0.02;
	static double I = 0.0001;
	static double D = 0;

	
	static AnalogPotentiometer potentiometer = new AnalogPotentiometer(RobotMap.potPort,270,0);
	static Spark intakeLifter = new Spark(RobotMap.ILiftPort);
	public static PIDController PID = new PIDController(P,I,D,potentiometer,intakeLifter);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public IntakeLifter() {
		super();
		position = Positions.DEFAULT;
//		PID.setInputRange(-135, 135);
		intakeLifter.setInverted(true);
		PID.setAbsoluteTolerance(5);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());

	}
	public void setAngle(Positions p) {
		//System.out.println("Setting Direction: "+d);
		position = p;
		PID.setSetpoint(position.getAngle());
		PID.enable();
	}
	/**
	 * Updates PID Values in accordance to Preferences inputs and returns the new angle that will be set.
	 * 
	 * @author GuyAcrossTheStreet
	 */
	public static Sendable Update() {
		/*P = Robot.prefs.getInt("P", 0);
		I = Robot.prefs.getInt("I", 0);
		D = Robot.prefs.getInt("D", 0);*/
		//PID = new PIDController(P,I,D,potentiometer,intakeLifter);
		
		return null;
		//return null;
		
	}
	public Positions getAngle() {
		return position;
	}
	public double getPOT(){
		return potentiometer.get();
	}
	
}
