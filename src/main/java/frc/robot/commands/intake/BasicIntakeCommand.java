package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class BasicIntakeCommand extends Command {
  private final IntakeSubsystem intake = IntakeSubsystem.getInstance();
  private final HopperSubsystem hopper = HopperSubsystem.getInstance();

  public BasicIntakeCommand() {
    addRequirements(intake);
  }

  // what runs ONCE @ the beginning
  @Override
  public void initialize() {
    intake.armOut();
  }

  // what runs REPEATEDLY when calling the command
  @Override
  public void execute() {
    intake.runIntake();
    hopper.run();
  }

  // what happens when the command ENDS
  @Override
  public void end(boolean interrupted) {
    intake.stop();
    hopper.stop();
    intake.armIn();
  }

  // how the robot decides the Command is done
  @Override
  public boolean isFinished() {
    return false; // runs until interrupted (ex. when button is released)
  }
}
