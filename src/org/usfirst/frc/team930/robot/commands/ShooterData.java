package org.usfirst.frc.team930.robot.commands;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.RobotConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterData extends Command {
	File f;
	BufferedWriter bw;
	FileWriter fw;
	Calendar cal = Calendar.getInstance();
	Timer timer = new Timer();
	double startTime = 0;
	double currentTime = 0;
	boolean END = false;

    public ShooterData() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	END = false;
    	Robot.shooter.commandRunning=true;
		try {
    		//f = new File("/home/lvuser/Output"+cal.get(Calendar.DAY_OF_MONTH)+cal.get(Calendar.MONTH)+cal.get(Calendar.YEAR)+cal.get(Calendar.HOUR_OF_DAY)+cal.get(Calendar.MINUTE)+cal.get(Calendar.SECOND)+".csv");
    	    f = new File("/home/lvuser/Output"+ Robot.shooter.shooter1.getP() + " " + Robot.shooter.shooter1.getI() + " " + RobotConstants.shootHighGoalRPM + " " + System.currentTimeMillis()+".csv");
			//f = new File("/media/sda1"+System.currentTimeMillis()+".csv");

			if(!f.exists()){
    			f.createNewFile();
    		}
			fw = new FileWriter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	bw = new BufferedWriter(fw);
    	timer.start();
    	startTime = timer.get();
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
			bw.write(System.currentTimeMillis() + ", "+(int)Robot.shooter.shooter1.get()+"\n");
    		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	currentTime = timer.get();
    	if(currentTime >= 3.5){
    		END = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return END;
    }

    // Called once after isFinished returns true
    protected void end() {
    	try {
    		fw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
		try {
			fw.flush();
			fw.close();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
