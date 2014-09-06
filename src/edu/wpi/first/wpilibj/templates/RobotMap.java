package edu.wpi.first.wpilibj.templates;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static final int leftMotor = 1;
    // public static final int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static final int rangefinderPort = 1;
    // public static final int rangefinderModule = 1;
    
    // cRIO Slots
    public static final int ANALOG_SLOT = 1;
    public static final int DIGITAL_SIDECAR_SLOT = 2;
    public static final int SOLENOID_SLOT_1 = 1;
    
    // Analog Inputs
    
    // Digital I/O
    public static final int DRIVEBOX_RIGHT_ENCODER_CH_A = 1;
    public static final int DRIVEBOX_RIGHT_ENCODER_CH_B = 2;
    public static final int DRIVEBOX_LEFT_ENCODER_CH_A = 3;
    public static final int DRIVEBOX_LEFT_ENCODER_CH_B = 4;

    // PWM Out
    public static final int DRIVEBOX_RIGHT_TALON_TOP = 1;
    public static final int DRIVEBOX_RIGHT_TALON_MIDDLE = 2;
    public static final int DRIVEBOX_RIGHT_TALON_BOTTOM = 3;
    public static final int DRIVEBOX_LEFT_TALON_TOP = 4;
    public static final int DRIVEBOX_LEFT_TALON_MIDDLE = 5;
    public static final int DRIVEBOX_LEFT_TALON_BOTTOM = 6;
    
     // I2C Slaves
    
    // Relays
    
    // Solenoid Relays on slot 1
    public static final int DRIVEBOX_RIGHT_SOLENOID_OPEN = 1;
    public static final int DRIVEBOX_RIGHT_SOLENOID_CLOSE = 2;
    public static final int DRIVEBOX_LEFT_SOLENOID_OPEN = 3;
    public static final int DRIVEBOX_LEFT_SOLENOID_CLOSE = 4;

    // Controller Inputs
    public static final int CONTROLLER_DRIVER = 1;
    public static final int JOYSTICK_SHOOTER = 2;
    
    
    // Variables
    // The wheel is a 4 in diameter
    // Therefore, distance travelled/wheel rot:
    //      (4 in * PI)
    //      -----------
    //      wheel rot
    // For every one wheel rotation we get one rotation of the 64 tooth gear (A)
    // For every 64 teeth on gear (A) we get 20 teeth on gear (B), for one gear (B) rotation
    
    // For every gear (B) rotation we get one rotation of the 36 tooth gear (C)
    // For every 36 teeth on gear (C) we get 12 teeth on gear (D), for one gear (D) rotation
    
    // For every one rotation of gear (D) we get one encoder rotation
    // For every encoder rotation, we get 250 pulses
    
    
    // To compute ft / encoder pulse:
    
    // (4.125 * PI) in    1 ft     1 A rot     20 teeth    1 B rot     1 C rot    12 teeth     1 encoder rot
    //   ------------- * ------ * ---------- * -------- * --------- * --------- * --------- * --------------
    //     1 wheel rot    12 in    64 teeth    1 B rot     1 C rot    36 teeth     1 D rot      250 pulses
    //
    // Result = 0.0004363323129
    public static final double DRIVEBOX_ENCODER_FEET_PER_PULSE = (57.0 / 48.0) * (4.125 * Math.PI) * (1.0 / 12.0) * (1.0 / 64.0) * (20.0 / 1.0) * (1.0 / 36.0) * (12.0 / 1.0) * (1.0 / 250.0);
}
