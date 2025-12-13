package frc.robot.commands.indexer;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IndexerSubsystem;

public class IndexCommand extends Command {

  private final IndexerSubsystem indexer = IndexerSubsystem.getInstance();

  public IndexCommand() {
  }

  // what runs ONCE @ the beginning
  @Override
  public void initialize() {}

  // what runs REPEATEDLY when calling the command
  @Override
  public void execute() {
    indexer.runFeeder();
  }

  // what happens when the command ENDS
  @Override
  public void end(boolean interrupted) {
    indexer.stopFeeder(); // mindful of other commands that may be running
  }

  // how the robot decides the Command is done
  @Override
  public boolean isFinished() {
    return false;
  }
}
