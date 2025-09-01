package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeArmToggleCommand extends Command {

  private final IntakeSubsystem intake = IntakeSubsystem.getInstance();

  public IntakeArmToggleCommand() {
  }
  
  // what runs ONCE @ the beginning
  @Override
  public void initialize() {
    if (intake.isArmOut()) {
        intake.armIn();
    } else {
      intake.armOut();
    }
  }

  // what runs REPEATEDLY when calling the command
  @Override
  public void execute() {
  }

  // what happens when the command ENDS
  @Override
  public void end(boolean interrupted) {}

  // how the robot decides the Command is done
  @Override
  public boolean isFinished() {
    // change to TRUE
    return true; // command ends immediately because initialize() happens before isFinished()
  }
}
