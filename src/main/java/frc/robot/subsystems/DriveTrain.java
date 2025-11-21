package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

    private Field2d field2d = new Field2d();

    private final WPI_TalonSRX leftMaster = new WPI_TalonSRX(Constants.ConstantsDriveTrain.kLeftMaster);
    private final WPI_TalonSRX leftSlave = new WPI_TalonSRX(Constants.ConstantsDriveTrain.kLeftSlave);
    private final WPI_TalonSRX rightMaster = new WPI_TalonSRX(Constants.ConstantsDriveTrain.kRightMaster);
    private final WPI_TalonSRX rightSlave = new WPI_TalonSRX(Constants.ConstantsDriveTrain.kRightSlave);

    private static AHRS gyro = new AHRS(NavXComType.kMXP_SPI);
    public PIDController PIDControllerDriveTrain = new PIDController(Constants.ConstantsDriveTrain.kp, Constants.ConstantsDriveTrain.ki, Constants.ConstantsDriveTrain.kd);
    public PIDController PIDControllerRotate = new PIDController(Constants.ConstantsDriveTrain.kp, Constants.ConstantsDriveTrain.ki, Constants.ConstantsDriveTrain.kd);


    private DifferentialDrive diffDrive = new DifferentialDrive(leftMaster, rightMaster);

    private DifferentialDrivetrainSim diffDriveSim = new DifferentialDrivetrainSim(
            DCMotor.getNEO(2),
            10.71,
            5.708124999999999,
            60,
            Units.inchesToMeters(6),
            0.685,
            VecBuilder.fill(0.001, 0.001, 0.001, 0.1, 0.1, 0.005, 0.005));

    public DriveTrain() {
        leftSlave.follow(leftMaster);
        rightSlave.follow(rightMaster);

        leftMaster.setInverted(true);
        leftSlave.setInverted(true);
        rightMaster.setInverted(true);
        rightSlave.setInverted(false);

        rightMaster.configSelectedFeedbackSensor(
        FeedbackDevice.CTRE_MagEncoder_Relative, 
        1, 
        20
        );
        leftMaster.configSelectedFeedbackSensor(
        FeedbackDevice.CTRE_MagEncoder_Relative, 
        1, 
        20
        );
        gyro.reset();
        ResetEncoder();

        SmartDashboard.putData("field", field2d);
    }


    public void ResetEncoder(){
        leftMaster.setSelectedSensorPosition(0);
        rightMaster.setSelectedSensorPosition(0);
    
    }

    public double ticksToMeters(double ticks){
        return ticks * Constants.ConstantsDriveTrain.calculo_Ticks_DriveTrain;
    }

    public double getAvarage(){
        return(ticksToMeters(rightMaster.getSelectedSensorPosition()) - ticksToMeters(leftMaster.getSelectedSensorPosition()))/2; 
    }

    public void AutoMove(double setDistance){
        PIDControllerDriveTrain.setSetpoint(setDistance);
        double speed = MathUtil.clamp(PIDControllerDriveTrain.calculate(getAvarage()), -0.7, 0.7);
        Drive(speed, 0);

    }

    public void PIDRodar(double rotation){
        PIDControllerRotate.setSetpoint(rotation);
        double speedRotation = PIDControllerRotate.calculate(gyro.getAngle());
        Drive(0, speedRotation * 0.5);
    }

    public boolean isAtSetPoint(){
        return PIDControllerDriveTrain.atSetpoint();
    }
    public boolean isAtSetPointRotation(){
        return PIDControllerRotate.atSetpoint();
    }

    public void Drive(double speed, double rotation) {
        diffDrive.arcadeDrive(speed, rotation);
        Constants.ConstantsDriveTrain.leftVoltage = (speed - rotation) * 5;
        Constants.ConstantsDriveTrain.rightVoltage = (speed + rotation) * 5;

    }

    public void stop() {
        diffDrive.stopMotor();
        Constants.ConstantsDriveTrain.leftVoltage = 0;
        Constants.ConstantsDriveTrain.rightVoltage = 0;
    }

    @Override
    public void periodic(){
        SmartDashboard.putData("PID do DriveTrain", PIDControllerDriveTrain);
        SmartDashboard.putNumber("Encoder DriveTrain", getAvarage());
        SmartDashboard.putBoolean("Set Point", isAtSetPoint());
    }

    @Override
    public void simulationPeriodic() {
        diffDriveSim.setInputs(
                -Constants.ConstantsDriveTrain.leftVoltage,
                -Constants.ConstantsDriveTrain.rightVoltage);

        diffDriveSim.update(0.02);
        field2d.setRobotPose(diffDriveSim.getPose());
    }

}

