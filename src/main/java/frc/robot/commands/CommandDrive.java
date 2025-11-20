package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

public class CommandDrive extends Command{
    private DriveTrain driveTrain;
    private Joystick controller;

    public CommandDrive(DriveTrain driveTrain, Joystick controller){
        this.driveTrain = driveTrain;
        this.controller = controller;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        driveTrain.Drive(-controller.getY(), controller.getZ());
    }
    
    @Override
    public void end(boolean interrupted){
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}