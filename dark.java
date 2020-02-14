import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class dark implements Behavior {

	private NXTColorSensor col = new NXTColorSensor(SensorPort.S1);
    SensorMode color = col.getAmbientMode();
    float[] level = new float[1];
    MovePilot pilot;
    
    dark(MovePilot p) {
    	pilot = p;
    	
    }
    
	
	
	@Override
	public boolean takeControl() {
		color.fetchSample(level, 0);
		if((pilot.getLinearSpeed() > 70) || level[0] < 0.5) {
			
			return true;
			
		}
		
		return false;
	}
	@Override
	public void action() {
		
		pilot.setLinearSpeed(Behaviors.SLOW_SPEED);
		
	}
	@Override
	public void suppress() {
	} 

}
