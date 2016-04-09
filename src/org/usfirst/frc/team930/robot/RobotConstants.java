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

public static double hangerLifterextendSpeed = 1; //EXTEND / RETRACT SPEEDS
public static double hangerLifterretractSpeed = -0.75;

//*****************************************************************************\\
					//WINCH

public static int winchUnwindlimit = 800; //LIMIT ON UNWINDING

//*****************************************************************************\\
					//INTAKE LIFTER

public static double intakeLifterPvalue = 0.015; //P.I.D.
public static double intakeLifterIvalue = 0;
public static double intakeLifterDvalue = 0;

public static double intakeLifterPORT = 165; //old 155
public static double intakeLifterDEFAULT = 45; //old 25
public static double intakeLifterPICKUP = 150; //old 125

public static double intakeLifterPotUpperThreshold = 0;
public static double intakeLifterPotLowerThreshold = 2;

//*****************************************************************************\\
					//INTAKE ROLLER

public static double intakeRollerforward = 1;  //ROLLER SPEEDS
public static double intakeRollerbackward = -.5;
public static double intakeRollershooterPull = 0.5;

public static boolean intakeRollerphotoEye = true; //ROLLER PHOTEYE 

//*****************************************************************************\\
					//SHOOT HIGH GOAL
public static double shootRatio = .916;
public static double shootHighGoalRPM = 4000; //old 3500 ; // old was .6 ------ 3500 / shootRatio; //no PID = .6
public static double shooterP = 0.065;
public static double shooterI = 0.0005;
public static double shooterMaxSpeed = 6000;

//*****************************************************************************\\
                    //ENCODER
public static int codesPerRev = 1024;


//*****************************************************************************\\
					//MANUAL INTAKE LIFTER

public static double manualIntakeLifterdown = -0.8; //UP / DOWN SPEEDS
public static double manualIntakeLifterup = 0.4;

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

//*****************************************************************************\\
                    //One Turn

//2nd Position
public static double auto2RockwalldriveTime1 = 4;   //Rockwall
public static double auto2RockwallturnAngle = 60;
public static double auto2RockwallturnTime = 1;
public static double auto2RockwalldriveTime2 = 3;
public static double auto2RockwalldriveSpeed = 1;

public static double auto2MoatdriveTime1 = 4;       //Moat
public static double auto2MoatturnAngle = 60;
public static double auto2MoatturnTime = 1;
public static double auto2MoatdriveTime2 = 3;
public static double auto2MoatdriveSpeed = 1;

public static double auto2RTerraindriveTime1 = 4;    //RTerrain
public static double auto2RTerrainturnAngle = 60;
public static double auto2RTerrainturnTime = 1;
public static double auto2RTerraindriveTime2 = 3;
public static double auto2RTerraindriveSpeed = 1;

public static double auto2RampdriveTime1 = 4;       //Ramparts
public static double auto2RampturnAngle = 60;
public static double auto2RampturnTime = 1;
public static double auto2RampdriveTime2 = 3;
public static double auto2RampdriveSpeed = 1;

public static double auto2ChevaldriveTime1 = 4;       //Cheval
public static double auto2ChevallowerDriveTime = 4;
public static double auto2ChevalraiseTime = 1;
public static double auto2ChevalturnAngle = 60;
public static double auto2ChevalturnTime = 1;
public static double auto2ChevaldriveTime2 = 3;
public static double auto2ChevaldriveSpeed = 1;

//5th Position
public static double auto5RockwalldriveTime1 = 4;    //Rockwall
public static double auto5RockwallturnAngle = -60;
public static double auto5RockwallturnTime = 1;
public static double auto5RockwalldriveTime2 = 3;
public static double auto5RockwalldriveSpeed = 1;

public static double auto5MoatdriveTime1 = 4;      //Moat
public static double auto5MoatturnAngle = -60; 
public static double auto5MoatturnTime = 1;
public static double auto5MoatdriveTime2 = 3;
public static double auto5MoatdriveSpeed = 1;

public static double auto5RTerraindriveTime1 = 4;   //RTerrain
public static double auto5RTerrainturnAngle = -60;
public static double auto5RTerrainturnTime = 1;
public static double auto5RTerraindriveTime2 = 3;
public static double auto5RTerraindriveSpeed = 1;

public static double auto5RampdriveTime1 = 4;    //Ramparts
public static double auto5RampturnAngle = -60;
public static double auto5RampturnTime = 1;
public static double auto5RampdriveTime2 = 3;
public static double auto5RampdriveSpeed = 1;

public static double auto5ChevaldriveTime1 = 4;       //Cheval
public static double auto5ChevallowerDriveTime = 4;
public static double auto5ChevalraiseTime = 1;
public static double auto5ChevalturnAngle = 60;
public static double auto5ChevalturnTime = 1;
public static double auto5ChevaldriveTime2 = 3;
public static double auto5ChevaldriveSpeed = 1;

//*****************************************************************************\\
                    //Two Turn

//3rd Position
public static double auto3RockwalldriveTime1 = 4;    //Rockwall
public static double auto3RockwallturnAngle1 = -60;
public static double auto3RockwallturnTime1 = 1;
public static double auto3RockwalldriveTime2 = 3;
public static double auto3RockwallturnTime2 = 1;
public static double auto3RockwalldriveTime3 = 3;
public static double auto3RockwalldriveSpeed = 1;

public static double auto3MoatdriveTime1 = 4;      //Moat
public static double auto3MoatturnAngle1 = -60;
public static double auto3MoatturnTime1 = 1;
public static double auto3MoatdriveTime2 = 3;
public static double auto3MoatturnTime2 = 1;
public static double auto3MoatdriveTime3 = 3;
public static double auto3MoatdriveSpeed = 1;

public static double auto3RTerraindriveTime1 = 4;      //RTerrain
public static double auto3RTerrainturnAngle1 = -60;
public static double auto3RTerrainturnTime1 = 1;
public static double auto3RTerraindriveTime2 = 3;
public static double auto3RTerrainturnTime2 = 1;
public static double auto3RTerraindriveTime3 = 3;
public static double auto3RTerraindriveSpeed = 1;

public static double auto3RampdriveTime1 = 4;       //Ramparts
public static double auto3RampturnAngle1 = -60;
public static double auto3RampturnTime1 = 1;
public static double auto3RampdriveTime2 = 3;
public static double auto3RampturnTime2 = 1;
public static double auto3RampdriveTime3 = 3;
public static double auto3RampdriveSpeed = 1;

public static double auto3ChevaldriveTime1 = 4;       //Cheval
public static double auto3ChevallowerDriveTime = 4;
public static double auto3ChevalraiseTime = 1;
public static double auto3ChevalturnAngle1 = -60;
public static double auto3ChevalturnTime1 = 1;
public static double auto3ChevaldriveTime2 = 3;
public static double auto3ChevalturnTime2 = 1;
public static double auto3ChevaldriveTime3 = 3;
public static double auto3ChevaldriveSpeed = 1;

//4th Position
public static double auto4RockwalldriveTime1 = 4;    //Rockwall
public static double auto4RockwallturnAngle1 = -60;
public static double auto4RockwallturnTime1 = 1;
public static double auto4RockwalldriveTime2 = 3;
public static double auto4RockwallturnTime2 = 1;
public static double auto4RockwalldriveTime3 = 3;
public static double auto4RockwalldriveSpeed = 1;

public static double auto4MoatdriveTime1 = 4;      //Moat
public static double auto4MoatturnAngle1 = -60;
public static double auto4MoatturnTime1 = 1;
public static double auto4MoatdriveTime2 = 3;
public static double auto4MoatturnTime2 = 1;
public static double auto4MoatdriveTime3 = 3;
public static double auto4MoatdriveSpeed = 1;

public static double auto4RTerraindriveTime1 = 4;      //RTerrain
public static double auto4RTerrainturnAngle1 = -60;
public static double auto4RTerrainturnTime1 = 1;
public static double auto4RTerraindriveTime2 = 3;
public static double auto4RTerrainturnTime2 = 1;
public static double auto4RTerraindriveTime3 = 3;
public static double auto4RTerraindriveSpeed = 1;

public static double auto4RampdriveTime1 = 4;       //Ramparts
public static double auto4RampturnAngle1 = -60;
public static double auto4RampturnTime1 = 1;
public static double auto4RampdriveTime2 = 3;
public static double auto4RampturnTime2 = 1;
public static double auto4RampdriveTime3 = 3;
public static double auto4RampdriveSpeed = 1;

public static double auto4ChevaldriveTime1 = 4;       //Cheval
public static double auto4ChevallowerDriveTime = 4;
public static double auto4ChevalraiseTime = 1;
public static double auto4ChevalturnAngle1 = -60;
public static double auto4ChevalturnTime1 = 1;
public static double auto4ChevaldriveTime2 = 3;
public static double auto4ChevalturnTime2 = 1;
public static double auto4ChevaldriveTime3 = 3;
public static double auto4ChevaldriveSpeed = 1;

}
