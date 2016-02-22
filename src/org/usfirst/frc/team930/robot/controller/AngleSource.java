package org.usfirst.frc.team930.robot.controller;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;


public class AngleSource implements PIDSource {
	PIDSourceType pidSource;
	
	public double pidGet() {
		double angle = Robot.drivetrain.gyro.getAngle();
		System.out.println("ANGLE GET: " + angle);
		return angle;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		this.pidSource = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return PIDSourceType.kDisplacement;
	}
}
