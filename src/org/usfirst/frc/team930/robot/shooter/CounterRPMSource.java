package org.usfirst.frc.team930.robot.shooter;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

public class CounterRPMSource extends Counter implements ControllerSource {

	public CounterRPMSource(DigitalInput s) {
		super(s);
	}
	
	@Override
	public double getValue() {
		return 30.0 / getPeriod();
	}
	
}