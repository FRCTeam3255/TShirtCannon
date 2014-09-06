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
import edu.wpi.first.wpilibj.templates.commands.DriveArcadeDrive;

/**
 *
 * @author Super NURDs
 *
 */
public class Drivetrain extends PIDSubsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    //Doubles and booleans
    public boolean driveHighGearState = true; //true is high gear and false is low gear
    public double driveMoveSpeed = 0.0;
    public double driveRotateSpeed = 0.0;
    public double driveGyroAngle = 0.0;
    public double drivetrainLeftEncoderValue = 0.0;
    public double drivetrainRightEncoderValue = 0.0;
    
    // DriveBoxes
    DriveBox driveBoxLeft = null;
    DriveBox driveBoxRight = null;
    
    //Robot Drive
    RobotDrive drivetrain = null;
    
    //Solenoids
    DoubleSolenoid driveShiftSolenoid = null;
    
    //Encoders
    public Encoder driveEncoderRight = null;
    public Encoder driveEncoderLeft = null;
    
    //Gyro and Accelerometer 
    public Gyro driveGyroscope = null;
    public PIDController gyroscope = null;
    public ADXL345_I2C driveAccelerometer = null;
    
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
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public double getError() {
        return this.getPIDController().getError();
    }
    
    public void setPositionSpeed(double s) {
        try {
            driveFrontLeftTalon.pidWrite(s);
            driveFrontRightTalon.pidWrite(-s);
            driveBackLeftTalon.pidWrite(s);
            driveBackRightTalon.pidWrite(-s);
        } catch (Exception e) {
        }
    }
    
    public void setTurnSpeed(double speed) {
        try {
            driveFrontLeftTalon.pidWrite(speed);
            driveFrontRightTalon.pidWrite(speed);
            driveBackLeftTalon.pidWrite(speed);
            driveBackRightTalon.pidWrite(speed);
        } catch (Exception e) {
        }
    }
    
    public void arcadeDrive(double moveValue, double rotateValue) {
        try {
            drivetrain.arcadeDrive(moveValue, rotateValue, driveHighGearState);
            driveMoveSpeed = moveValue;
            driveRotateSpeed = rotateValue;
        } catch (Exception e) {
        }
    }
    
    public void arcadeDriveAxes() {
        try {
            drivetrain.arcadeDrive(edu.wpi.first.wpilibj.templates.commands.CommandBase.oi.DriverHID, 
                    2, edu.wpi.first.wpilibj.templates.commands.CommandBase.oi.DriverHID, 4, driveHighGearState);
        } catch (Exception e) {
        }
    }
    public void tankDrive(double leftValue, double rightValue){
        try {
            drivetrain.tankDrive(leftValue, rightValue);
            driveMoveSpeed = leftValue;
            driveRotateSpeed = 0.0;
        } catch(Exception e) {
        }
    }
    
     public double driveMoveSpeed() {
        return driveMoveSpeed;
    }

    public double driveRotateSpeed() {
        return driveRotateSpeed;
    }

    public double getRightEncoderValue() {
        if (driveEncoderRight == null) {
            return (0.0);
        }
        double encValue = driveEncoderRight.getDistance();
        drivetrainRightEncoderValue = encValue;
        return (encValue);
    }

    public double getLeftEncoderValue() {
        if (driveEncoderLeft == null) {
            return (0.0);
        }
        double encValue = driveEncoderLeft.getDistance();
        drivetrainLeftEncoderValue = encValue;
        return (encValue);
    }
    
    public double getEncoderAverages() {
        double encAve = ((getRightEncoderValue() + getLeftEncoderValue())/2);
        return encAve;
    }

    public void startEncoders() {
        try {
            driveEncoderLeft.start();
            driveEncoderRight.start();

        } catch (Exception e) {
        }
    }

    public void stopEncoders() {
        try {
            driveEncoderLeft.stop();
            driveEncoderRight.stop();
        } catch (Exception e) {
        }
    }

    public void resetEncoders() {
        try {
            driveEncoderLeft.reset();
            driveEncoderRight.reset();
        } catch (Exception e) {
        }
    }
    
    public void driveEncodersInit() {
        startEncoders();
        resetEncoders();
    }
    
    public double driveEncodersLeftValue() {
        return drivetrainLeftEncoderValue;
    }
    
    public double driveEncodersRightValue() {
        return drivetrainRightEncoderValue;
    }
    
     public void resetGyro() {
        try {
            driveGyroscope.reset();
        } catch (Exception e) {
        }
    }
     
    public void initGyroscope(double Kp, double Ki, double Kd, double setpoint) {
        try {
            gyroscope.setPID(Kp, Ki, Kd);
            gyroscope.setSetpoint(setpoint);
            driveGyroscope.reset();
            gyroscope.enable();
        } catch (Exception e) {  
        }
    }

    public double getGyroAngle() {
        if (driveGyroscope == null) {
            return 0.0;
        }
        return driveGyroscope.getAngle();
    }
    
    public double getAcceleration(String axis) {
        if ("x".equals(axis)) {
            return driveAccelerometer.getAcceleration(ADXL345_I2C.Axes.kX);
        }
        else if ("y".equals(axis)) {
            return driveAccelerometer.getAcceleration(ADXL345_I2C.Axes.kY);
        }
        else if ("z".equals(axis)) {
            return driveAccelerometer.getAcceleration(ADXL345_I2C.Axes.kZ);
        }
        else {
            return 0.0;
        }
    }

    public void shiftLowGear() {
        try {
            driveShiftSolenoid.set(DoubleSolenoid.Value.kReverse);
            driveHighGearState = false;
        } catch (Exception e) {
        }
    }

    public void shiftHighGear() {
        try {
            driveShiftSolenoid.set(DoubleSolenoid.Value.kForward);
            driveHighGearState = true;
        } catch (Exception e) {
        }
    }

    public boolean gearState() {
        return driveHighGearState;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        this.setDefaultCommand(new DriveArcadeDrive());
    }

    protected double returnPIDInput() {
        return getLeftEncoderValue();
    }

    protected void usePIDOutput(double output) {
        setPositionSpeed(output);    
    }
    
}