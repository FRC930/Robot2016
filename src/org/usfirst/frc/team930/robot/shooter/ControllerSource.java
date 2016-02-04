package org.usfirst.frc.team930.robot.shooter;

/**
 * Interface to interact with controllers
 * 
 * @author D531
 * @version 2016.02.03_1 Documentated
 */
public interface ControllerSource {
	
	/**
	 * @return A value the Controller will adjust for
	 */
	public double getValue();
}
