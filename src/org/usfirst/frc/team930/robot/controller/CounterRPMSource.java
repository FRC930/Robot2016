package org.usfirst.frc.team930.robot.controller;

import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Controller Source that also functions as a counter for RPMs
 * 
 * @author Nick Janovetz
 * @version 2016.02.03_1 Documentated
 */
public class CounterRPMSource extends Counter implements ControllerSource {

	private FileWriter fw;
	
	public CounterRPMSource(DigitalInput s) {
		super(s);
		try {
			fw = new FileWriter("Outputs.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	/**
	 * Returns the RPM of the counter using getPeriod()
	 */
	public double getValue() {
		
		Double highPulse = 30.0 / getPeriod(); 
		System.out.printf("%-10s%10.2f%n", "RPM", (highPulse.isInfinite()) || (highPulse > 100000f) ? 0f : highPulse);
		try {
			fw.write("" + highPulse + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return (highPulse.isInfinite()) || (highPulse > 30000f) ? 0f : highPulse;
	}
	
}