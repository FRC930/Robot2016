package org.usfirst.frc.team930.robot.Shooter;

import java.util.TimerTask;

abstract public class Controller {

	private static double DEFAULT_PERIOD = .05;
	private java.util.Timer loop;
	
	public Controller() {
		loop = new java.util.Timer();
		loop.schedule(new ControllerTask(this), 0L, (long) (1000 * DEFAULT_PERIOD));
	}
	
	public void calculate() {
		
	}
	
	public void enable() {
		
	}

	public void disable() {
		
	}
	
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
            cont.calculate();
        }
    }
}