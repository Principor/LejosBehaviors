import java.util.Random;

import lejos.hardware.lcd.LCD;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class BackUp implements Behavior{
	
	private MovePilot turner;
	private Random rgen = new Random();
	private float[] samples = new float[1];
	SampleProvider sp;
	
	BackUp(MovePilot p, SampleProvider distance) {
		turner = p;
		sp = distance;
	}
	
	public void action() {
		LCD.clear();
		LCD.drawString("back up", 0, 0);
		turner.travel(-50);
		turner.rotate((2 * rgen.nextInt(2) - 10) * 30);
	}
	
	public void suppress() {
	}
	
	public boolean takeControl() {
		sp.fetchSample(samples, 0);
		return (samples[0] < 0.5f);
	}
	
	

}
