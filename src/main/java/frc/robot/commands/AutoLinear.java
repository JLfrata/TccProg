package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

public class AutoLinear extends Command {
    private DriveTrain driveTrain;
    private double distance;

    public AutoLinear(DriveTrain driveTrain, double distance){
        this.driveTrain = driveTrain;
        this.distance = distance;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize(){
        driveTrain.ResetEncoder();
        SmartDashboard.putString("Começou?", "Começou");
    }
    @Override
    public void execute(){
        driveTrain.AutoMove(distance);
        SmartDashboard.putNumber("Distancia Louca", distance);
    }
    @Override
    public void end(boolean interrupted){
        SmartDashboard.putString("Terminou?", "Terminou");
        driveTrain.Drive(0, 0);
    }
    @Override
    public boolean isFinished(){
        return driveTrain.isAtSetPoint();
    }
    
}