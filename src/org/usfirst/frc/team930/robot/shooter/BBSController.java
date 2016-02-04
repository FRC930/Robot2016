package org.usfirst.frc.team930.robot.shooter;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Concrete class for the implementation of the Bang Bang Shooter Controller,
 * which will set the motor speed to max speed if the motor runs below the
 * target RPM and will let it run freely otherwise (ie, speed of zero).
 * <p>
 * For best results, tune your acceleration values of the speed controller so
 * that it doesn't jump around the set point - note, it will still control to
 * your set points, but it will consistently overaccelerate in the calculation
 * timeframe. As a reference, accel = 1 will produce ~3800 RPM
 * <p>
 * Based on the implementation on <a
 * href="http://www.chiefdelphi.com/media/papers/2663">Chief Delphi</a>.
 * 
 * @see Controller.java
 * @author Nick Janovetz
 * @version 2016.02.03_2 Added set methods
 */
public class BBSController extends Controller {

	private static int count = 0;

	private ControllerSource m_source;
	private SpeedController m_sc;
	private double m_targetRPM;
	private double m_accel;
	private int m_count;

	/**
	 * Constructs a Controller given an RPM input, target, speedcontroller, and
	 * acceleration
	 * <p>
	 * The speedcontroller must be in coast mode - check your NI Dashboard
	 * 
	 * @param source
	 *            The PID input for the motor that returns RPM
	 * @param sc
	 *            The SpeedController that will be controlled
	 * @param targetRPM
	 *            The target RPM for the SpeedController to spin at
	 * @param accel
	 *            The amount the speedcontroller impulses to when below target,
	 *            0 <= accel <= 1
	 */
	public BBSController(ControllerSource source, SpeedController sc,
			double targetRPM, double accel) {
		m_source = source;
		m_sc = sc;
		m_targetRPM = targetRPM;
		m_accel = accel < 0f ? 0f : (accel > 1f ? 1 : accel);

		count++;
		m_count = count;
	}

	/**
	 * Constructs a controller without a given acceleration, defaulted to 1.0
	 * <p>
	 * The speedcontroller must be in coast mode - check your NI Dashboard
	 * 
	 * @param source
	 *            The PID input for the motor that returns RPM
	 * @param sc
	 *            The SpeedController that will be controlled
	 * @param targetRPM
	 *            The target RPM for the SpeedController to spin at
	 */
	public BBSController(ControllerSource source, SpeedController sc,
			double targetRPM) {
		this(source, sc, targetRPM, 1f);
	}

	/**
	 * If less than target RPM, accelerate at given value, otherwise coast
	 */
	public void calculate() {
		// Snapshot these values
		double targetRPM = m_targetRPM;
		double accel = m_accel;

		// Calculations
		if (m_source.getValue() < targetRPM) {
			System.out.println("\t" + m_count + " Increase!");
			m_sc.set(accel);
		} else {
			System.out.println("\t" + m_count + " OK!");
			m_sc.set(0);
		}
	}

	/**
	 * Overriden disable from the Controller that also sets the motor controlled
	 * to 0
	 */
	@Override
	public void disable() {
		super.disable();
		m_sc.set(0);
	}

	/**
	 * Sets the target the controller aims for in the calculation of the RPM
	 * 
	 * @param targetRPM
	 *            New set RPM for the controller to set the speedcontroller to
	 */
	public void setRPM(double targetRPM) {
		m_targetRPM = targetRPM;
	}

	/**
	 * Sets the acceleration values of the speedcontroller
	 * 
	 * @param accel
	 *            New acceleration value for the speedcontroller
	 */
	public void setAccel(double accel) {
		m_accel = accel;
	}
}