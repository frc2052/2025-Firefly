// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ShootCommandFactory;
import frc.robot.commands.ShootCommandFactory.ShootMode;
import frc.robot.commands.intake.BasicIntakeCommand;
import frc.robot.commands.intake.IntakeArmToggleCommand;
import frc.robot.commands.intake.OuttakeCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ShooterSubsystem.FiringAngle;

public class RobotContainer {

  Joystick joystick = new Joystick(1);

  Drivetrain drivetrain = Drivetrain.getInstance();
  RobotState robotPose = RobotState.getInstance();
  ShooterSubsystem shooter = ShooterSubsystem.getInstance();
 

  public RobotContainer() {
    Drivetrain.getInstance().setDefaultCommand(
      new DriveCommand(
        joystick::getX, 
        joystick::getY, 
        joystick::getX, 
        () -> true)
    );

    configureBindings();
  }

  JoystickButton intakeButton;
  JoystickButton outtakeButton;
  JoystickButton intakeToggleButton;

  JoystickButton indexButton;
  JoystickButton indexOutButton;

  JoystickButton lowShootButton;
  JoystickButton highShootButton;

  JoystickButton adjustableShootButton;
  JoystickButton basicShootCommandButton;
  JoystickButton angle1Button;
  JoystickButton angle2Button;

  JoystickButton zeroGyroButton;
 
  private void configureBindings() {
  // ------------------------- BUTTON CREATION ------------------------- //
    // intake buttons
    intakeButton = new JoystickButton(joystick, 5); 
    outtakeButton = new JoystickButton(joystick,4); 
    intakeToggleButton = new JoystickButton(joystick, 3);
  
    // shooter buttons
    lowShootButton = new JoystickButton(joystick, 7); 
    highShootButton = new JoystickButton(joystick, 8); 
    adjustableShootButton = new JoystickButton(joystick, 10); 

    indexButton = new JoystickButton(joystick, 2);
    indexOutButton = new JoystickButton(joystick, 11);
    
    basicShootCommandButton = new JoystickButton(joystick, 1); 
  
    angle1Button = new JoystickButton(joystick, 7);
    angle2Button = new JoystickButton(joystick, 8);
  
  // ------------------------- BUTTON'S COMMANDS ------------------------- //
    // shooter commands
    Command lowShootCommand = ShootCommandFactory.shootPercentage(
      ShootMode.SHOOT_ALL, 
      Constants.Shooter.SHOOTER_TOP_PULLDOWN_PCT, 
      Constants.Shooter.SHOOTER_BOTTOM_PULLDOWN_PCT, 
      FiringAngle.ANGLE_1);
    
    Command highShootCommand = ShootCommandFactory.shootPercentage(
      ShootMode.SHOOT_ALL, 
      Constants.Shooter.SHOOTER_TOP_PULLDOWN_PCT, 
      Constants.Shooter.SHOOTER_BOTTOM_PULLDOWN_PCT, 
      FiringAngle.ANGLE_2);

    Command adjustableShootCommand = 
      ShootCommandFactory.throttleShootCommand(
        ShootMode.SHOOT_ALL, 
        joystick::getThrottle);

    Command indexCommand = 
      ShootCommandFactory.manualRunIndexer();

    Command indexOutCommand = 
      ShootCommandFactory.manualIndexOut();

    Command basicShootCommand = // change percentage in the command per angle
      ShootCommandFactory.basicThrottleShootCommand(() -> joystick.getThrottle());
      // ShootCommandFactory.shootPercentage(ShootMode.SHOOT_ALL, 75, 75, FiringAngle.ANGLE_1 );

  // ------------------------- BUTTON BINDINGS ------------------------- //
    // intake button
      // level 1
        intakeButton.whileTrue(new BasicIntakeCommand());
      // level 2
        // intakeButton.whileTrue(new FullIntakeCommand());
    outtakeButton.whileTrue(new OuttakeCommand());
    intakeToggleButton.whileTrue(new IntakeArmToggleCommand());

    // shooter button - need to adust throttle speeds
    lowShootButton.whileTrue(lowShootCommand);
    highShootButton.whileTrue(highShootCommand);
    adjustableShootButton.whileTrue(adjustableShootCommand);

    indexButton.whileTrue(indexCommand);
    indexOutButton.whileTrue(indexOutCommand);
    basicShootCommandButton.whileTrue(basicShootCommand);

    angle1Button.onTrue(new InstantCommand(() -> shooter.setShootAngle1()));
    angle2Button.onTrue(new InstantCommand(() -> shooter.setShootAngle2()));

    zeroGyroButton.onTrue(new InstantCommand(() -> drivetrain.zeroGyro()));
  }

 
  public Command getAutonomousCommand() {
    System.out.println("RAN AUTONOMOUS COMMAND - INSTANT COMMAND");
    return new InstantCommand();
  }
}
