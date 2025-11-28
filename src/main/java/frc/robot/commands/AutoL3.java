package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevador;

public class AutoL3 extends Command {
    private Elevador elevador;
    private double target = 93;
    Timer timer = new Timer();
    double maxTime;

    public AutoL3(Elevador elevador, double maxTime){
        this.elevador = elevador;
        this.maxTime = maxTime;
        addRequirements(elevador);
    }

    @Override
    public void initialize(){
        elevador.ResetEncoder();
        timer.restart();
    }
    @Override
    public void execute(){
        elevador.runPID(target);
    }
    @Override
    public void end(boolean interrupted){
        elevador.run(0);
        timer.stop();
    }
    @Override
    public boolean isFinished(){
        return timer.get() >= maxTime;
    }
    
}