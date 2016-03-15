package org.usfirst.frc.team930.robot.controller;

import org.usfirst.frc.team930.robot.OI;

public class ThrottleInt {

	boolean joystick;
	double throttle;
	
	public void useJoystick(boolean stick)
	{
		joystick = stick;
	}
	
	public double getThrottle()
	{
		if (joystick == true)
		{
			return OI.getInstance().getYAxis();
		}
		else 
		{
			return throttle;
		}
	}
	
	public void setThrottle(double t)
	{
		throttle = t;
	}
}
