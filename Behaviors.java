import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Behaviors {

	final static float WHEEL_DIAMETER = 56;
	final static float AXLE_LENGTH = 190;
	final static float ANGULAR_SPEED = 100;
	final static float FAST_SPEED = 70;
	final static float SLOW_SPEED = FAST_SPEED / 2;
	
	public static void main(String[] args) {
		BaseRegulatedMotor mLeft = new EV3LargeRegulatedMotor(MotorPort.A);
		Wheel wLeft = WheeledChassis.modelWheel(mLeft, WHEEL_DIAMETER).offset(-AXLE_LENGTH / 2);
		BaseRegulatedMotor mRight = new EV3LargeRegulatedMotor(MotorPort.B);
		Wheel wRight = WheeledChassis.modelWheel(mRight, WHEEL_DIAMETER).offset(AXLE_LENGTH / 2);
		
		Chassis chassis = new WheeledChassis(new Wheel[] { wRight, wLeft}, WheeledChassis.TYPE_DIFFERENTIAL);
		
		MovePilot pilot = new MovePilot(chassis);
		
		EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S1);
		SampleProvider distance = (SampleProvider) us.getDistanceMode();

		EV3TouchSensor ts = new EV3TouchSensor(SensorPort.S1);
		SampleProvider touched = ts.getTouchMode();
		
		NXTColorSensor cs = new NXTColorSensor(SensorPort.S1);
		SensorMode color = cs.getAmbientMode(); 
		
		Trundle trundle = new Trundle(pilot);
		BackUp backup = new BackUp(pilot, distance);
		LowBattery lowBattery = new LowBattery(pilot);
		Light light = new Light(pilot, color);
		dark d = new dark(pilot, color);
		Emergency emergency = new Emergency(pilot, touched);
		
		Arbitrator ab = new Arbitrator (new Behavior[]{trundle, backup, light, d, emergency, lowBattery});
		ab.go();
		
		us.close();
		ts.close();
		cs.close();
	}
	
}
