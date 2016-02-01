package org.usfirst.frc.team930.robot.shooter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;

public class CounterRPMSource extends Counter implements ControllerSource {

	private FileWriter fw;
	
	public CounterRPMSource(DigitalInput s) {
		super(s);
		try {
			fw = new FileWriter(new File("C:\\Outputs.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public double getValue() {
		
		double highPulse = 30.0 / getPeriod(); 
		System.out.printf("%-10s%10.2f%n", "RPM", highPulse);
		try {
			fw.write("" + highPulse + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return highPulse;
	}
	
}