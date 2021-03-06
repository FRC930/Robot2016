package org.usfirst.frc.team930.robot.controller;

/**
 * Interface to interact with controllers
 * 
 * @author D531
 * @version 2016.02.03_1 Documentated
 */
interface ControllerSource {
	
	/**
	 * @return A value the Controller will adjust for
	 */
	public double getValue();
}
