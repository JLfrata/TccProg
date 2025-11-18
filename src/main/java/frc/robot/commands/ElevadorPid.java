package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevador;

public class ElevadorPid extends Command {
    
    Elevador elevador;
    Joystick controller;
    double target;

    public ElevadorPid(Elevador elevador, Joystick controller, double target){
        this.elevador = elevador;
        this.controller = controller;
        this.target = target;
        addRequirements(elevador);
    }

    @Override
    public void initialize(){
    
    }
    
    @Override
    public void execute(){
    }


    public void end(boolean interrupted){
    }

    public boolean isFinished(){
        return false;
    }
    
}
