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
		//sets the intakelifter values
		PORT(256), //245; old = 50; old = 270
		DEFAULT(137), //130; old = 170; old = 150
		PICKUP(224); //217; old = 80; old = 237
		// declares the variable that will set the position
		private final double angle;
		
		private Positions(double p) {
			//sets angle to equal the position 
			angle = p;
		}
		
		public double getAngle() {
			//allows you the get the position of the lifter
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
	//sets the values that the PID will use
	static double P = -0.02;
	static double I = 0.00;
	static double D = 0;

	//declares the POT and sets the boundry for it
	static AnalogPotentiometer potentiometer = new AnalogPotentiometer(RobotMap.potPort,270,0);
	//declares the SPARK which runs the motor for the intakelifter
	static Spark intakeLifter = new Spark(RobotMap.ILiftPort);
	//declares the PID controller which handles the PID values 
	public PIDController PID = new PIDController(P,I,D,potentiometer,intakeLifter);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public IntakeLifter() {
		super();
		//sets the first position to be at as DEFAULT
		position = Positions.DEFAULT;
//		PID.setInputRange(-135, 135);
		
		intakeLifter.setInverted(true);//sets the motor to go backwards
		//sets the off in degrees that the PID will accept
		PID.setAbsoluteTolerance(5);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());

	}
	public void setAngle(Positions p) {
		//System.out.println("Setting Direction: "+d);
		//sets the position equal to P
		position = p;
		//sets the value that the PID is supposed to get to
		PID.setSetpoint(position.getAngle());
		//enables the PID
		//PID.enable();
	}
	
	public void setSpeed(double speed)
	{
		intakeLifter.set(speed);
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
	//this makes it possible to find the angle of the arm
	public Positions getAngle() {
		return position;
	}
	//finds the raw value of the PID
	public double getPOT(){
		return potentiometer.get();
	}
	
	
	
}
