package org.usfirst.frc.team930.robot.shooter;

import java.util.TimerTask;
/**
 * Abstract class to control inputs, or in its basic form
 * an enable-able looped calculator. 
 * 
 * @author Nick Janovetz
 * @version 2016.02.03_1 Formatting changes
 *
 */
abstract public class Controller {

	private static final double DEFAULT_PERIOD = .05;
	private boolean enabled;
	private java.util.Timer loop;
	
	/**
	 * Default Controller constructor that creates a new timed task and runs it.
	 */
	public Controller() {
		enabled = false;
		loop = new java.util.Timer();
		loop.schedule(new ControllerTask(this), 0L, (long) (1000 * DEFAULT_PERIOD));
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
		System.out.println("Enabled Controller");
	}

	/**
	 * Disables the controller
	 */
	public void disable() {
		enabled = false;
		System.out.println("Disabled Controller");
	}
	
	/**
	 * Controller Task to run
	 * 
	 * @author Nick Janovetz
	 * @version 2016.01.26_1
	 */
	private class ControllerTask extends TimerTask {

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