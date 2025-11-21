// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.AutoTotal;
import frc.robot.commands.CommandDrive;
import frc.robot.commands.CommandElevador;
import frc.robot.commands.CommandShooterFalse;
import frc.robot.commands.CommandShooterTrue;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevador;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


public class RobotContainer {

  private final Joystick controller = new Joystick(0);
//  private final Joystick controller1 = new Joystick(1);

  private final DriveTrain driveTrain = new DriveTrain();
  private final Elevador elevador = new Elevador();
  private final Shooter shooter = new Shooter();
  
  public RobotContainer() {
    configureBindings(controller);
  }
  

  private void configureBindings(Joystick controller) {
    driveTrain.setDefaultCommand(new CommandDrive(driveTrain, controller));
    elevador.setDefaultCommand(new CommandElevador(elevador, controller));
    new JoystickButton(controller, 1).whileTrue(new CommandShooterTrue(shooter));
    new JoystickButton(controller, 2).whileTrue(new CommandShooterFalse(shooter));
  }

  public Command getAutonomousCommand() {
    return new AutoTotal(driveTrain, elevador, shooter);
  }
}
