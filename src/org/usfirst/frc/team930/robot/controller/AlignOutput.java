package org.usfirst.frc.team930.robot.controller;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

public class AlignOutput implements PIDOutput {
	
	SpeedController Left1;
	SpeedController Left2;
	SpeedController Left3;
	SpeedController Right1;
	SpeedController Right2;
	SpeedController Right3;
	
	public AlignOutput(SpeedController L1, SpeedController L2, SpeedController L3, SpeedController R1, SpeedController R2, SpeedController R3) {
		Left1 = L1;
		Left2 = L2;
		Left3 = L3;
		Right1 = R1;
		Right2 = R2;
		Right3 = R3;
		
	}
	
	public void pidWrite(double output) {
		// TODO Auto-generated method stub
		Left1.set(output);
		Left2.set(output);
		Left3.set( output);
		Right1.set(output);
		Right2.set( output);
		Right3.set(output);
		
	}
}
