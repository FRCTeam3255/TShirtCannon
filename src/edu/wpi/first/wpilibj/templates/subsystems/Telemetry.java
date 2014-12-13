/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.commands.*;
import edu.wpi.first.wpilibj.templates.commands.ArmReverse;
import edu.wpi.first.wpilibj.templates.commands.CommandBase;
import edu.wpi.first.wpilibj.templates.commands.TelemetryUpdate;

/**
 *
 * @author Tyler
 */
public class Telemetry extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands. 

    public Telemetry() {
        SmartDashboard.putData("Arm Forward", new ArmForward());
        SmartDashboard.putData("Arm Reverse", new ArmReverse());
        SmartDashboard.putData("Arm Stop", new ArmStop());
        
        LiveWindow.addSensor("Arm", "POT", CommandBase.arm.pot);
        LiveWindow.addActuator("Arm", "Motor", CommandBase.arm.motor);
        LiveWindow.addActuator("Arm", "PID Controller", CommandBase.arm.getPIDController());
    }

    public void update() {
        SmartDashboard.putNumber("Arm POT", CommandBase.arm.returnPIDInput());
    }
    
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        this.setDefaultCommand(new TelemetryUpdate());
    }
}
