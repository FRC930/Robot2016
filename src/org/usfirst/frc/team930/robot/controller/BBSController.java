package org.usfirst.frc.team930.robot.controller;

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
 * @version 2016.02.10_1 removed Hungarian notation
 */
public class BBSController extends Controller {

	private static int CONTROLLER_COUNT = 0;

	private ControllerSource source;
	private final SpeedController speedcontroller;
	private double targetRPM;
	private double accel;
	private int count;

	/**
	 * Constructs a Controller given an RPM input, target, speedcontroller, and
	 * acceleration
	 * <p>
	 * The speedcontroller must be in coast mode - check your NI Dashboard
	 * 
	 * @param s
	 *            The PID input for the motor that returns RPM
	 * @param sc
	 *            The SpeedController that will be controlled
	 * @param tr
	 *            The target RPM for the SpeedController to spin at
	 * @param a
	 *            The amount the speedcontroller impulses to when below target,
	 *            0 <= accel <= 1
	 */
	public BBSController(ControllerSource s, SpeedController sc,
			double tr, double a) {
		source = s;
		speedcontroller = sc;
		targetRPM = tr;
		if (a < 0) {
			accel = 0;
		} else if (a > 1) {
			accel = 1;
		}

		CONTROLLER_COUNT++;
		count = CONTROLLER_COUNT;
		System.out.println("Controller " + count + " made");
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
		double t = targetRPM;
		double a = accel;

		// Calculations
		if (source.getValue() < t) {
			System.out.println("" + count + " Increase! for " + t + " " + a);
			speedcontroller.set(a);
		} else {
			System.out.println("" + count + " OK! for " + t + " " + a);
			speedcontroller.set(0);
		}
	}

	/**
	 * Overriden disable from the Controller that also sets the motor controlled
	 * to 0
	 */
	@Override
	public void disable() {
		super.disable();
		speedcontroller.set(0);
	}

	/**
	 * Sets the target the controller aims for in the calculation of the RPM
	 * 
	 * @param tr
	 *            New set RPM for the controller to set the speedcontroller to
	 */
	public void setRPM(double tr) {
		System.out.println("Set to tr" + tr);
		targetRPM = tr;
	}

	/**
	 * Sets the acceleration values of the speedcontroller
	 * 
	 * @param a
	 *            New acceleration value for the speedcontroller
	 */
	public void setAccel(double a) {
		System.out.println("Set accel " + a);
		accel = a;
	}
}