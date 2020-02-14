import lejos.hardware.Battery;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class LowBattery implements Behavior{

	MovePilot pilot;
	
	LowBattery(MovePilot p){
		pilot = p;
	}
	
	public boolean takeControl() {

		return Battery.getVoltage() < 1;
	}
	
	public void action() {
		pilot.stop();
		LCD.clear();
		LCD.drawString("LOW BATTERY", 1, 1);
		Sound.beep();
		Delay.msDelay(1000);
	}

	public void suppress() {
	}

}
