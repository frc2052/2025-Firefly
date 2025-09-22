package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.commands.DriveCommand;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {

  Joystick joystick = new Joystick(1);

  Drivetrain drivetrain = Drivetrain.getInstance();
 
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

  private void configureBindings() {
  // ------------------------- BUTTON CREATION ------------------------- //
    // intake buttons

  // ------------------------- BUTTON BINDINGS ------------------------- //
    // intake buttons

  }

  public Command getAutonomousCommand() {
    System.out.println("RAN AUTONOMOUS COMMAND - INSTANT COMMAND");
    return new InstantCommand();
  }
}
