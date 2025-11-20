package frc.robot.subsystems;



import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {

    final TalonSRX rodaShooter;

    public Shooter(){
        rodaShooter = new TalonSRX(Constants.ConstantsOutTake.idShooter);
    }

    public void run(double speed){
        rodaShooter.set(TalonSRXControlMode.PercentOutput, speed);
    }
    @Override
    public void periodic(){
    }
}
