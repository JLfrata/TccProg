// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class ConstantsDriveTrain {
    public static final int kDriverControllerPort = 0;
    public static int kLeftMaster = 3;
    public static int kRightMaster = 4;
    public static int kLeftSlave = 1;
    public static int kRightSlave = 2;
    public static double rightVoltage = 0;
    public static double leftVoltage = 0;
    public static double kp = 1.0;
    public static double ki = 0.0;
    public static double kd = 0.0;
    public static double calculo_Ticks_DriveTrain = 0.01150390625 / 100;
  }
  public static class ConstantsElevador{
    public static int Master_ID_Elevador = 6;
    public static int Slave_ID_Elevador = 7;

    public static double voltage_Elevator = 0;
    public static double kp = 0.05;
    public static double ki = 0.0;
    public static double kd = 0.0;
    public static double pidTolerencia = 0.5;
    public static double calculo_Ticks_Elevador = 0.7950125000000001 * 5.14;
  }
  public static class ConstantsOutTake{
    public static int idShooter = 5;
  }
}
