package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevador;

public class AutoL0 extends Command {
    private Elevador elevador;
    private double target = 0;

    public AutoL0(Elevador elevador){
        this.elevador = elevador;
        addRequirements(elevador);
    }

    @Override
    public void initialize(){
    }
    @Override
    public void execute(){
        elevador.runPID(target);
    }
    @Override
    public void end(boolean interrupted){
        elevador.run(0);
    }
    @Override
    public boolean isFinished(){
        return elevador.isAtSetPoint();
    }
    
}