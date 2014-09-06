package edu.wpi.first.wpilibj.templates.commands;

/**
 *
 * @author bradmiller
 */
public class DriveForward extends CommandBase {

    public DriveForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        drivetrain.setSpeed(1.0);        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        drivetrain.setSpeed(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
