package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevador extends SubsystemBase {
    
    public PIDController pidElevador = new PIDController(Constants.ConstantsElevador.kp, Constants.ConstantsElevador.ki, Constants.ConstantsElevador.kd);
    
    private RelativeEncoder masterEncoder;
    private RelativeEncoder slaveEncoder;

    private SparkMax motorMaster = new SparkMax(Constants.ConstantsElevador.Master_ID_Elevador, MotorType.kBrushless);
    private SparkMax motorSlave = new SparkMax(Constants.ConstantsElevador.Slave_ID_Elevador, MotorType.kBrushless);

    
    public Elevador(){

        pidElevador.setTolerance(Constants.ConstantsElevador.pidTolerencia);
        
        SparkMaxConfig masterConfig = new SparkMaxConfig();
        
        masterConfig.inverted(false);
        masterConfig.idleMode(IdleMode.kBrake);
        masterConfig.disableFollowerMode();
        
        SparkMaxConfig slaveConfig = new SparkMaxConfig();
        
        motorMaster.configure(masterConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
        motorSlave.configure(slaveConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
        
        masterEncoder = motorMaster.getEncoder();
        slaveEncoder = motorSlave.getEncoder();
    }

    public void ResetEncoder(){
        masterEncoder.setPosition(0);
        slaveEncoder.setPosition(0);
    }
    
    public void runPID(double target){
        pidElevador.setSetpoint(target);
        double speed = MathUtil.clamp(pidElevador.calculate(getHeight()), -0.1, 0.3);
        run(speed);
    }
    
    public void run(double speed){
        motorMaster.set(speed);
        motorSlave.set(-speed);
    }

    public void PIDControllerElevator(double target){
        pidElevador.setSetpoint(target);
        motorMaster.set(pidElevador.calculate(getHeight()));
        motorSlave.set(-pidElevador.calculate(getHeight()));

    }


//    public void ControleElevador(double speed, Joystick controller){
//        motorMaster.set(speed * 0.25);
//        motorSlave.set(-speed * 0.25);
//    }


    public double ticksToMeters(double ticks){
        return ticks * Constants.ConstantsElevador.calculo_Ticks;
    }

    public double getHeight(){
        if (ticksToMeters(masterEncoder.getPosition()) -ticksToMeters(slaveEncoder.getPosition())/2 < 0){
            return 0;
        }
        return(ticksToMeters(masterEncoder.getPosition()) -ticksToMeters(slaveEncoder.getPosition()))/2; 
    }

    public boolean atSetPoint(){
        return pidElevador.atSetpoint();
    }

    public void periodic(){
        SmartDashboard.putData("PID do Elevador", pidElevador);
        SmartDashboard.putNumber("Encoder", getHeight());
    }
}
