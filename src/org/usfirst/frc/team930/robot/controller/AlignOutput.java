package org.usfirst.frc.team930.robot.controller;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

public class AlignOutput implements PIDOutput {
	
	double scAngle;
	
	public AlignOutput(double c1) {
		scAngle = c1;
	}
	
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		
	}
}
