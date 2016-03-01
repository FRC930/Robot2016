package org.usfirst.frc.team930.robot.controller;

import java.util.TimerTask;

import edu.wpi.first.wpilibj.Notifier;
/**
 * Abstract class to control inputs, or in its basic form
 * an enable-able looped calculator. 
 * 
 * @author Nick Janovetz
 * @version 2016.02.10_1 Notifier added
 *
 */
abstract class Controller {

	private static final double DEFAULT_PERIOD = .05;
	private boolean enabled;
	private Notifier loop;
	
	/**
	 * Default Controller constructor that creates a new timed task and runs it.
	 */
	public Controller() {
		enabled = false;
		loop = new Notifier(new ControllerTask(this));
		loop.startPeriodic(DEFAULT_PERIOD);
	}
	
	/**
	 * Method to run at each iteration of the task
	 */
	public abstract void calculate();
	
	/**
	 * Allows the Controller to run
	 */
	public void enable() {
		enabled = true;
		//System.out.println("Enabled Controller");
	}

	/**
	 * Disables the controller
	 */
	public void disable() {
		enabled = false;
		//System.out.println("Disabled Controller");
	}
	
	/**
	 * Controller Task to run
	 * 
	 * @author Nick Janovetz
	 * @version 2016.01.26_1
	 */
	private class ControllerTask implements Runnable {

        private Controller cont;

        public ControllerTask(Controller c) {
            if (c == null) {
                throw new NullPointerException("Given Controller was null");
            }
            cont = c;
        }

        @Override
        public void run() {
            if(enabled) {
            	cont.calculate();
            }
        }
    }
}