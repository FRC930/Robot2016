package org.usfirst.frc.team930.robot.controller;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class AngleSource implements PIDSource {
	public double pidGet() {
		return Robot.drivetrain.gyro.getAngle();
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return null;
	}
}