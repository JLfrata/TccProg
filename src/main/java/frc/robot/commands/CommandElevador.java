package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevador;

public class CommandElevador extends Command {
    private Elevador elevador;
    private Joystick controller;
    double target = 0;

    public CommandElevador(Elevador elevador, Joystick controller){
        this.elevador = elevador;
        this.controller = controller;
        addRequirements(elevador);
    }

    @Override
    public void initialize(){
        elevador.ResetEncoder();
    }

    @Override
    public void execute(){
        if(controller.getPOV() == 0){
            target = 152;
        }
        else if(controller.getPOV() == 180){
            target = 0;
        }
        else if (controller.getPOV() == 90){
            target = 100;
        }
        else if (controller.getPOV() == 270){
            target = 50;
        }
        elevador.runPID(target);
    }
    
    @Override
    public void end(boolean interrupted){
        elevador.run(0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }

}
