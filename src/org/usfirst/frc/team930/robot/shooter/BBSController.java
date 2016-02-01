package org.usfirst.frc.team930.robot.shooter;

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
	
	private static int count = 0;
	
	private ControllerSource m_source;
	private SpeedController m_sc;
	private double m_targetRPM;
	private int m_count; 
	
	/**
	 * Constructs a Controller given an RPM input, target, and speedcontroller
	 * @param source The PID input for the motor that returns RPM
	 * @param sc The SpeedController that will be controlled
	 * @param targetRPM The target RPM for the SpeedController to spin at
	 */
	public BBSController(ControllerSource source, SpeedController sc, double targetRPM) {
		m_source = source;
		m_sc = sc;
		m_targetRPM = targetRPM;
		
		count++;
		m_count = count;
	}
	
	public void calculate() {
		if(m_source.getValue() < m_targetRPM) {
			System.out.println("\t" + count + " Increase!");
			m_sc.set(1);
		} else {
			m_sc.set(0);
			System.out.println("\t" + count + " OK!");
		}
	}
	
}