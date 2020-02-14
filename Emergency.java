import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Emergency implements Behavior {
	private MovePilot pilot;
	private float[] sample = new float[1]; 
	SampleProvider sp;
	
	Emergency(MovePilot p, SampleProvider touched) {
		pilot = p;
		sp = touched;
	}
	
	public boolean takeControl() {
		sp.fetchSample(sample, 0);
		return Button.ESCAPE.isDown() || sample[0] == 1;
	}

	@Override
	public void action() {
		LCD.clear();
		LCD.drawString("Emergency", 0, 0);
		pilot.stop();
		System.exit(0);
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}
	
}
