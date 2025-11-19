// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.CommandDrive;
import frc.robot.commands.CommandElevador;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevador;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;


public class RobotContainer {

  private final Joystick controller = new Joystick(1);
  private final Joystick controller1 = new Joystick(0);

  private final DriveTrain driveTrain = new DriveTrain();
  private final Elevador elevador = new Elevador();
  
  public RobotContainer() {
    configureBindings(controller);
  }

  private void configureBindings(Joystick controller) {
    driveTrain.setDefaultCommand(new CommandDrive(driveTrain, controller));
    elevador.setDefaultCommand(new CommandElevador(elevador, controller1));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
