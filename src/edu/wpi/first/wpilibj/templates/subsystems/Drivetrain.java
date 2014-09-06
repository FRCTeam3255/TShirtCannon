package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 * @author Super NURDs
 *
 */
public class Drivetrain extends PIDSubsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    //Variables to hold current state of drive box
    public boolean highGearState = true; //true is high gear and false is low gear
    public double speed = 0.0;   
        
    // DriveBoxes
    DriveBox driveBoxLeft = null;
    DriveBox driveBoxRight = null;
    
    //Robot Drive
    RobotDrive robotDrive = null;
            
    public Drivetrain() {
        super("Drivetrain Encoder PID", 7.0, 3.0, 4.0);
        setAbsoluteTolerance(0.1);
        PIDController controller = this.getPIDController();
        controller.setContinuous(true);
        
        try {
            driveBoxLeft = new DriveBox("Left DriveBox",
                    RobotMap.DRIVEBOX_LEFT_TALON_TOP,
                    RobotMap.DRIVEBOX_LEFT_TALON_MIDDLE,
                    RobotMap.DRIVEBOX_LEFT_TALON_BOTTOM,
                    RobotMap.SOLENOID_SLOT_1,
                    RobotMap.DRIVEBOX_LEFT_SOLENOID_OPEN,
                    RobotMap.DRIVEBOX_LEFT_SOLENOID_CLOSE,
                    RobotMap.DRIVEBOX_LEFT_ENCODER_CH_A,
                    RobotMap.DRIVEBOX_LEFT_ENCODER_CH_B);
            
            driveBoxRight = new DriveBox("Right DriveBox",
                    RobotMap.DRIVEBOX_RIGHT_TALON_TOP,
                    RobotMap.DRIVEBOX_RIGHT_TALON_MIDDLE,
                    RobotMap.DRIVEBOX_RIGHT_TALON_BOTTOM,
                    RobotMap.SOLENOID_SLOT_1,
                    RobotMap.DRIVEBOX_RIGHT_SOLENOID_OPEN,
                    RobotMap.DRIVEBOX_RIGHT_SOLENOID_CLOSE,
                    RobotMap.DRIVEBOX_RIGHT_ENCODER_CH_A,
                    RobotMap.DRIVEBOX_RIGHT_ENCODER_CH_B);
            
            setSpeed(0.0);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setSpeed(double s) {
        try {
            speed = s;
            driveBoxLeft.setSpeed(s);
            driveBoxRight.setSpeed(-s);
        } catch (Exception e) {
        }
    }
    
    public double getSpeed() {
        return speed;
    }
            
    public void shiftLowGear() {
        driveBoxLeft.shiftLowGear();
        driveBoxRight.shiftLowGear();
        highGearState = false;
    }

    public void shiftHighGear() {
        driveBoxLeft.shiftHighGear();
        driveBoxRight.shiftHighGear();
        highGearState = true;
    }

    public boolean isHighGear() {
        return highGearState;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    }

    public double getError() {
        return this.getPIDController().getError();
    }
    
    protected double returnPIDInput() {
        return driveBoxLeft.getEncoderValue();
    }

    protected void usePIDOutput(double output) {
        setSpeed(output);    
    }
    
}