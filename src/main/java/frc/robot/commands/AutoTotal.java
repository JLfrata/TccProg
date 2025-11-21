// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevador;
import frc.robot.subsystems.Shooter;


public class AutoTotal extends SequentialCommandGroup {
  public AutoTotal(DriveTrain driveTrain, Elevador elevador, Shooter shooter) {
    addCommands(
      new AutoLinear(driveTrain, -10),
      new AutoRotation(driveTrain, 90),
      new AutoL3(elevador),
      new AutoShoot(shooter, 3));
  }
}
