package org.usfirst.frc.team930.robot.shooter;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * Concrete class for the implementation of the Bang Bang Shooter Controller,
 * which will set the motor speed to max speed if the motor runs below
 * the target RPM and will let it run freely otherwise (ie, speed of zero).
 * <p>
 * Based on the implementation on 
 * <a href="http://www.chiefdelphi.com/media/papers/2663">Chief Delphi</a>.

 * @see Controller
 * @author Nick Janovetz
 * @version 2016.01.26_1
 */
public class BBSController extends Controller {
	
	PIDSource m_source;
	SpeedController m_sc;
	double m_targetRPM;
	
	/**
	 * Constructs a Controller given an RPM input, target, and speedcontroller
	 * @param source The PID input for the motor that returns RPM
	 * @param sc The SpeedController that will be controlled
	 * @param targetRPM The target RPM for the SpeedController to spin at
	 */
	public BBSController(PIDSource source, SpeedController sc, double targetRPM) {
		m_source = source;
		m_sc = sc;
		m_targetRPM = targetRPM;
	}
	
	public void calculate() {
		
	}
	
}