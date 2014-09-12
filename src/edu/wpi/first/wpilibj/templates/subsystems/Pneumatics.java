/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.*;

/**
 *
 * @author SuperNURD
 */
public class Pneumatics extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private Compressor compressor = null;

    public Pneumatics(String name) {
        super(name);
        
        compressor = new Compressor(RobotMap.COMPRESSOR_SWITCH_CHANNEL,
                RobotMap.COMPRESSOR_RELAY_CHANNEL);
    }
    
    public void start() {
        compressor.start();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        this.setDefaultCommand(new RunCompressor());
    }
}
