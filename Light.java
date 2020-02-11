import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Light implements Behavior {

	private NXTColorSensor col = new NXTColorSensor(SensorPort.S1);
	SensorMode color = col.getAmbientMode();
	float[] level = new float[1]; 
	MovePilot pilot;
	
	Light(MovePilot p) {
		pilot = p;
	}

	
	
	
	
	
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		color.fetchSample(level,0);
		if (pilot.getLinearSpeed() < 70 || level[0] >0.5 ) {
		
		return true;}
		
		return false;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		pilot.setLinearSpeed(70);
		
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
