
package org.usfirst.frc.team930.robot;


import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the RobotDrive class.
 * The SampleRobot class is the base of a robot application that will automatically call your
 * Autonomous and OperatorControl methods at the right time as controlled by the switches on
 * the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
    //RobotDrive myRobot;
    //Joystick stick;
    double rotateValue;
    double Speed;
    CANTalon shooterwheels;
    CANTalon shooterwheels2;
    Preferences prefs;
    Counter counter1;


    public Robot() {
       // myRobot = new RobotDrive(0, 1);
        //m//yRobot.setExpiration(0.1);
        //stick = new Joystick(0);
    	shooterwheels = new CANTalon(0);
    	shooterwheels2 = new CANTalon(2);
    	prefs = Preferences.getInstance();
    	counter1 = new Counter(new DigitalInput(0));
    	counter1.setSemiPeriodMode(true);
    }

    /**
     * Drive left & right motors for 2 seconds then stop
     */
    public void autonomous() {
       // myRobot.setSafetyEnabled(false);
       // myRobot.drive(-0.5, 0.0);	// drive forwards half speed
       // Timer.delay(2.0);		//    for 2 seconds
        //myRobot.drive(0.0, 0.0);	// stop robot
    }

    /**
     * Runs the motors with arcade steering.
     */
    public void operatorControl() {
       // myRobot.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
        	//rotateValue = stick.getX();
        	//moveValue = stick.getY();
        	
        	
        	
    		Speed = prefs.getDouble("Speed", 0);
    		shooterwheels.set(Speed);
    		shooterwheels2.set(Speed);
    		SmartDashboard.putNumber("Motor Output", Speed);
    		//rotateValue = prefs.getDouble("Rotatevalue", 4.);
    		
    		double highPulse = counter1.getPeriod();
    		highPulse = 30.0/highPulse;
    		SmartDashboard.putNumber("RPM Output", highPulse);
    		
        	/*CameraServer server = CameraServer.getInstance();
        	server.setQuality(100);
        	server.startAutomaticCapture("cam0");
        	*/
    		
        	
        	
        	
            //myRobot.arcadeDrive(moveValue, rotateValue); // drive with arcade style (use right stick)
            Timer.delay(0.005);		// wait for a motor update time
        }
        /*CameraServer server = CameraServer.getInstance();
    	server.setQuality(1);
    	server.startAutomaticCapture("cam0");*/
    }

    /**
     * Runs during test mode
     */
    public void test() {
    }
}