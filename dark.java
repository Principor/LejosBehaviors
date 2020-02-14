import lejos.hardware.sensor.SensorMode;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class dark implements Behavior {

    SensorMode color;
    float[] level = new float[1];
    MovePilot pilot;
    
    dark(MovePilot p, SensorMode color) {
    	pilot = p;
    	this.color = color;
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
