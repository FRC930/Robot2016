package org.usfirst.frc.team930.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
	
	public static int LFrontPort = 0;
	public static int LTopPort = 1;
	public static int LBackPort = 2;
	public static int RFrontPort = 3;
	public static int RTopPort = 4;
	public static int RBackPort = 5;
	public static int S1Port = 0;
	public static int S2Port = 1;
	public static int I1Port = 3;
	public static int I2Port = 4;
	public static int ILiftPort = 5;
	public static int lightSensorPort = 0;
	public static int potPort = 1;
	public static int shooterSensorPort = 2;
	public static int distanceSensorPort = 3;
	
	
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
