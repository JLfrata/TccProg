package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

public class AutoRotation extends Command {
    private DriveTrain driveTrain;
    private double rotation;

    public AutoRotation(DriveTrain driveTrain, double rotation){
        this.driveTrain = driveTrain;
        this.rotation = rotation;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize(){
        driveTrain.ResetEncoder();
        SmartDashboard.putString("Girou?", "Girou");
    }
    @Override
    public void execute(){
        driveTrain.PIDRodar(rotation);
        SmartDashboard.putNumber("Rodada Louca", rotation);
    }
    @Override
    public void end(boolean interrupted){
        SmartDashboard.putString("Parou de girar?", "Parou");
        driveTrain.Drive(0, 0);
    }
    @Override
    public boolean isFinished(){
        return driveTrain.isAtSetPoint();
    }
    
}