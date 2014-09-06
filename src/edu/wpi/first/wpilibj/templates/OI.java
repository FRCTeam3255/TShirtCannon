
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.templates.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    public Joystick joystickDriver = new Joystick(RobotMap.JOYSTICK_DRIVER);
    public Joystick joystickShooter = new Joystick(RobotMap.JOYSTICK_SHOOTER);
    
    Button buttonDriver1 = new JoystickButton(joystickDriver, 1);
    Button buttonDriver2 = new JoystickButton(joystickDriver, 2);
    Button buttonDriver3 = new JoystickButton(joystickDriver, 3);
    Button buttonDriver4 = new JoystickButton(joystickDriver, 4);
    Button buttonDriver5 = new JoystickButton(joystickDriver, 5);
    Button buttonDriver6 = new JoystickButton(joystickDriver, 6);
    Button buttonDriver7 = new JoystickButton(joystickDriver, 7);
    Button buttonDriver8 = new JoystickButton(joystickDriver, 8);
    Button buttonDriver9 = new JoystickButton(joystickDriver, 9);
    Button buttonDriver10 = new JoystickButton(joystickDriver, 10);
    Button buttonDriver11 = new JoystickButton(joystickDriver, 11);
    Button buttonDriver12 = new JoystickButton(joystickDriver, 12);
    
    Button buttonShooter1 = new JoystickButton(joystickShooter, 1);
    Button buttonShooter2 = new JoystickButton(joystickShooter, 2);
    Button buttonShooter3 = new JoystickButton(joystickShooter, 3);
    Button buttonShooter4 = new JoystickButton(joystickShooter, 4);
    Button buttonShooter5 = new JoystickButton(joystickShooter, 5);
    Button buttonShooter6 = new JoystickButton(joystickShooter, 6);
    Button buttonShooter7 = new JoystickButton(joystickShooter, 7);
    Button buttonShooter8 = new JoystickButton(joystickShooter, 8);
    Button buttonShooter9 = new JoystickButton(joystickShooter, 9);
    Button buttonShooter10 = new JoystickButton(joystickShooter, 10);
    Button buttonShooter11 = new JoystickButton(joystickShooter, 11);
    Button buttonShooter12 = new JoystickButton(joystickShooter, 12);

    public OI() {

        //Driver dual action controller button allocations
        buttonDriver1.whileHeld(new DriveForward());
        buttonDriver2.whileHeld(new DriveReverse());
        buttonDriver3.whenPressed(new ShiftHighGear());
        buttonDriver4.whenPressed(new ShiftLowGear());
        // D3 free
        // D4 free
        // D5 free
        // D6 free
        // D7 free
        // D8 free
        // D9 free
        // D10 free

        //Custom control interface for the shooter button allocations
        // M1 free
        // M2 free
        // M3 free
        // M4 free
        // M5 free
        // M6 free
        // M7 free
        // M8 free
        // M8 free
        // M8 free
        // M8 free
        // M8 free
    }
}

