import java.util.Random;

import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class BackUp implements Behavior{
	
	private MovePilot turner;
	private EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S1);
	private SampleProvider sp = (SampleProvider) us.getDistanceMode();
	private Random rgen = new Random();
	private float[] samples = new float[1];
	
	BackUp(MovePilot p) {
		turner = p;
	}
	
	public void action() {
		turner.travel(-50);
		turner.rotate((2 * rgen.nextInt(2) - 10) * 30);
	}
	
	public void suppress() {
	}
	
	public boolean takeControl() {
		sp.fetchSample(samples, 0);
		return (samples[0] < 0.2f);
	}
	
	

}
