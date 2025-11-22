// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.Elevador;
import frc.robot.subsystems.Shooter;

public class AutoL3Shooter extends ParallelCommandGroup {

  public AutoL3Shooter(Elevador elevador, Shooter shooter) {
    addCommands(
      new AutoL3(elevador, 4),
      new AutoWait(shooter));
  }
}
