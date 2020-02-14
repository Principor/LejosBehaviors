import lejos.hardware.Button;
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
		sp.fetchSample(sample, 1);
		return Button.ESCAPE.isDown() || sample[0] == 1;
	}

	@Override
	public void action() {
		pilot.stop();
		System.exit(0);
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}
	
}
