package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;

public class IndexOutCommand extends Command {
  private final IndexerSubsystem indexer = IndexerSubsystem.getInstance();

  public IndexOutCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // what runs ONCE @ the beginning
  @Override
  public void initialize() {}

  // what runs REPEATEDLY when calling the command
  @Override
  public void execute() {
    indexer.runFeederReverse();
  }

  // what happens when the command ENDS
  @Override
  public void end(boolean interrupted) {
    indexer.stopFeeder();
  }

  // how the robot decides the Command is done
  @Override
  public boolean isFinished() {
    return false;
  }
}
