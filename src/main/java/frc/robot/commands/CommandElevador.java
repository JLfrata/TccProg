package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevador;

public class CommandElevador extends Command {
    private Elevador elevador;
    private Joystick controller;

    public CommandElevador(Elevador elevador, Joystick controller){
        this.elevador = elevador;
        this.controller = controller;
        addRequirements(elevador);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        elevador.ControleElevador(-controller.getY(), controller);
    }
    
    @Override
    public void end(boolean interrupted){
        elevador.ControleElevador(0, controller);
    }

    @Override
    public boolean isFinished(){
        return false;
    }

}
