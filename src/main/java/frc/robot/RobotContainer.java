package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.intake.BasicIntakeCommand;
import frc.robot.commands.intake.IntakeArmToggleCommand;
import frc.robot.commands.intake.OuttakeCommand;
import frc.robot.commands.shooter.BasicThrottleShootCommand;
import frc.robot.commands.shooter.FullShootCommand;
import frc.robot.commands.shooter.IndexCommand;
import frc.robot.commands.shooter.IndexOutCommand;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ShooterSubsystem.FiringAngle;

public class RobotContainer {

  Joystick joystick = new Joystick(1);

  Drivetrain drivetrain = Drivetrain.getInstance();
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

  JoystickButton basicShootButton;
  JoystickButton lowShootButton;
  JoystickButton highShootButton;

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
    basicShootButton = new JoystickButton(joystick, 9); // manual angle toggle
    lowShootButton = new JoystickButton(joystick, 7); 
    highShootButton = new JoystickButton(joystick, 8); 

    indexButton = new JoystickButton(joystick, 2);
    indexOutButton = new JoystickButton(joystick, 11);
      
    angle1Button = new JoystickButton(joystick, 7);
    angle2Button = new JoystickButton(joystick, 8);
  // ------------------------- BUTTON BINDINGS ------------------------- //
    // intake button
      // level 1
        intakeButton.whileTrue(new BasicIntakeCommand());
      // level 2
        // intakeButton.whileTrue(new FullIntakeCommand());
    outtakeButton.whileTrue(new OuttakeCommand());
    intakeToggleButton.whileTrue(new IntakeArmToggleCommand());

    // shooter button - need to adust throttle speeds
    basicShootButton.whileTrue(new BasicThrottleShootCommand(joystick::getThrottle));
    lowShootButton.whileTrue(new FullShootCommand(joystick::getThrottle, FiringAngle.ANGLE_1));
    highShootButton.whileTrue(new FullShootCommand(joystick::getThrottle, FiringAngle.ANGLE_2));

    indexButton.whileTrue(new IndexCommand());
    indexOutButton.whileTrue(new IndexOutCommand());

    angle1Button.onTrue(new InstantCommand(() -> shooter.setShootAngle1()));
    angle2Button.onTrue(new InstantCommand(() -> shooter.setShootAngle2()));

    zeroGyroButton.onTrue(new InstantCommand(() -> drivetrain.zeroGyro()));
  }

  public Command getAutonomousCommand() {
    System.out.println("RAN AUTONOMOUS COMMAND - INSTANT COMMAND");
    return new InstantCommand();
  }
}
