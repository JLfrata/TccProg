// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class AutoShoot extends Command {
  Shooter shooter;
  Timer timer = new Timer();
  double maxTime;
  public AutoShoot(Shooter shooter, double maxTime) {
    this.shooter = shooter;
    this.maxTime = maxTime;
    addRequirements(shooter);
  }
  @Override
  public void initialize() {
    timer.restart();
  }

  @Override
  public void execute(){
    shooter.run(-0.5);
  }

  @Override
  public void end(boolean interrupted) {
    shooter.run(0);
    timer.stop();
  }

  @Override
  public boolean isFinished() {
    return timer.get() >= maxTime;
  }
}
