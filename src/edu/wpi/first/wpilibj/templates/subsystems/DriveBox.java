package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 * @author Super NURDs
 *
 */
public class DriveBox extends PIDSubsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    //Variables to hold current state of drive box
    public boolean highGearState = true; //true is high gear and false is low gear
    public double speed = 0.0;
    public double encoderValue = 0.0;
    
    //Motor controllers
    Talon talonTopMotor = null;
    Talon talonMiddleMotor = null;
    Talon talonBottomMotor = null;
    
    //Solenoids
    DoubleSolenoid solenoidShifter = null;
    
    //Encoders
    public Encoder encoder = null;
    
    //PID Controller to control distance that drive box should travel
    //public PIDController distanceController = null;
    
    public DriveBox(String name,
            int topTalonPort, int middleTalonPort, int bottomTalonPort,
            int solenoidSlot, int solenoidOpenPort, int solenoidClosePort,
            int encoderChannelAPort, int encoderChannelBPort) {
        super(name, 7.0, 3.0, 4.0);
        setAbsoluteTolerance(0.1);
        PIDController distanceController = this.getPIDController();
        distanceController.setContinuous(true);
        
        try {
            // create talons for motors based on the port they are connected to
            talonTopMotor = new Talon(topTalonPort);
            talonMiddleMotor = new Talon(middleTalonPort);
            talonBottomMotor = new Talon(bottomTalonPort);
            
            // turn the safety off on each talon
            talonTopMotor.setSafetyEnabled(false);
            talonMiddleMotor.setSafetyEnabled(false);
            talonBottomMotor.setSafetyEnabled(false);

            solenoidShifter = new DoubleSolenoid(solenoidSlot, solenoidOpenPort, solenoidClosePort);

            encoder = new Encoder(encoderChannelAPort, encoderChannelBPort, true, CounterBase.EncodingType.k2X);

            encoder.setDistancePerPulse(RobotMap.DRIVEBOX_ENCODER_FEET_PER_PULSE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public double getError() {
        return this.getPIDController().getError();
    }
    
    public void setSpeed(double s) {
        try {
            speed = s;
            talonTopMotor.pidWrite(s);
            talonMiddleMotor.pidWrite(s);
            talonBottomMotor.pidWrite(s);
        } catch (Exception e) {
        }
    }
    
     public double getSpeed() {
        return speed;
    }

    public double getEncoderValue() {
        if (encoder == null) {
            return (0.0);
        }
        encoderValue = encoder.getDistance();
        return (encoderValue);
    }

    public void startEncoder() {
        try {
            encoder.start();

        } catch (Exception e) {
        }
    }

    public void stopEncoder() {
        try {
            encoder.stop();
        } catch (Exception e) {
        }
    }

    public void resetEncoder() {
        try {
            encoder.reset();
        } catch (Exception e) {
        }
    }
    
    public void initEncoder() {
        startEncoder();
        resetEncoder();
    }

    public void shiftLowGear() {
        try {
            solenoidShifter.set(DoubleSolenoid.Value.kReverse);
            highGearState = false;
        } catch (Exception e) {
        }
    }

    public void shiftHighGear() {
        try {
            solenoidShifter.set(DoubleSolenoid.Value.kForward);
            highGearState = true;
        } catch (Exception e) {
        }
    }

    public boolean isHighGear() {
        return highGearState;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }

    protected double returnPIDInput() {
        return getEncoderValue();
    }

    protected void usePIDOutput(double output) {
        setSpeed(output);    
    }
    
}