package org.usfirst.frc.team930.robot;

public class RobotConstants {
	
//!!!!!!!!!!!!!!!!!!!!Tele-Operated!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//*****************************************************************************\\
					//DRIVETRAIN
	
public static double drivetrainPvalue = 0.035; //P.I.D.
public static double drivetrainIvalue = 0.000;
public static double drivetrainDvalue = 0.0;
 
public static double drivetrainGoalangleBackward = 180; //GYRO ANGLES
public static double drivetrainGoalangleLeft = -55;
public static double drivetrainGoalangleRight = 55;

public static double driveThrottledeadBand = 0.1; //DEADBAND VALUES
public static double driveWheeldeadBand = 0.085;

//*****************************************************************************\\
					//HANGER 

public static double hangerLifterextendSpeed = 0.75; //EXTEND / RETRACT SPEEDS
public static double hangerLifterretractSpeed = -0.75;

//*****************************************************************************\\
					//WINCH

public static int winchUnwindlimit = 800; //LIMIT ON UNWINDING

//*****************************************************************************\\
					//INTAKE LIFTER

public static double intakeLifterPvalue = 0.015; //P.I.D.
public static double intakeLifterIvalue = 0;
public static double intakeLifterDvalue = 0;

public static double intakeLifterPORT = 175; //old 100
public static double intakeLifterDEFAULT = 72; //old 187
public static double intakeLifterPICKUP = 155; //old 115

public static double intakeLifterPotUpperThreshold = 0;
public static double intakeLifterPotLowerThreshold = -2;

//*****************************************************************************\\
					//INTAKE ROLLER

public static double intakeRollerforward = 1;  //ROLLER SPEEDS
public static double intakeRollerbackward = -0.75;
public static double intakeRollershooterPull = 0.75;

public static boolean intakeRollerphotoEye = true; //ROLLER PHOTEYE 

//*****************************************************************************\\
					//SHOOT HIGH GOAL
public static double shootRatio = .916;
public static double shootHighGoalRPM = 3500 / shootRatio; //R.P.M.
public static double shooterP = 10;
public static double shooterMaxSpeed = 6000;
//*****************************************************************************\\
					//MANUAL INTAKE LIFTER

public static double manualIntakeLifterdown = -0.6; //UP / DOWN SPEEDS
public static double manualIntakeLifterup = 0.6;

//!!!!!!!!!!!!!!!!!!!!!!!AUTONOMOUS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

//*****************************************************************************\\
					//PORT

public static double autoDrivePORTdriveSpeed = 0.5; //DRIVE SPEED

//*****************************************************************************\\
					//LOW BAR

public static double autoLowBardriveSpeed = 0.5; //DRIVE SPEED

//*****************************************************************************\\
					//LOW BAR SHOOT

public static double autoLowBarshootDrivespeed = 0.5;
public static double autoLowBarshootDrivespeed2 = 0.6;//DRIVE SPEED

public static double autoLowBarShootdistance1 = 48; //DISTANCE 1
public static double autoLowBarShootdistance2 = 70; //DISTANCE 2
public static double autoLowBarShootdistance3 = 5; //DISTANCE 3

//*****************************************************************************\\
					//DRIVE FORWARD / BACKWARD

public static double autoDriveForwardSpeed = 1; //DRIVE FORWARD SPEED
public static double autoDriveBackwardSpeed = -1; //DRIVE BACKWARD SPEED

//*****************************************************************************\\
					//SPY BOX SHOOTER

public static double spyBoxDriveSpeed = 1.0; //DRIVE SPEED

//*****************************************************************************\\
                    //2nd Position

public static double auto2ndPositionShootDrivespeed = 0.75;

//*****************************************************************************\\
                    //3rd Position

public static double auto3rdPositionShootDrivespeed = 0.75;

//*****************************************************************************\\
                    //4th Position

public static double auto4thPositionShootDrivespeed = 0.5;

}
