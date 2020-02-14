import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Light implements Behavior {

	SensorMode color;
	float[] level = new float[1]; 
	MovePilot pilot;
	
	Light(MovePilot p, SensorMode color) {
		pilot = p;
		this.color = color;
	}
	
	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		color.fetchSample(level,0);
		return (pilot.getLinearSpeed() < 70 && level[0] >0.5 );
		
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		LCD.drawString("light", 0, 0);
		pilot.setLinearSpeed(Behaviors.FAST_SPEED);
		
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}

}
