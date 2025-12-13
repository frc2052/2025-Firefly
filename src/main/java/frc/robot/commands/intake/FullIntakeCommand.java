package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class FullIntakeCommand extends Command {

  private final IntakeSubsystem intake = IntakeSubsystem.getInstance();
  private final HopperSubsystem hopper = HopperSubsystem.getInstance();
  private final IndexerSubsystem indexer = IndexerSubsystem.getInstance();

  public FullIntakeCommand() {
  }

  // what runs ONCE @ the beginning
  @Override
  public void initialize() {
    intake.armOut(); // lower intake
  }

  // what runs REPEATEDLY when calling the command
  @Override
  public void execute() {
    // case 1: no staged ball (either pre-staged or empty) - run until stageed
    if(!indexer.getCargoStagedDetected()) {
      intake.runIntake();
      hopper.run();
      indexer.runPreload();
      indexer.runFeeder();
    }
    // case 2: ball staged, 2nd ball not detected - keep running
    else if(!indexer.getCargoPreStagedDetected()){
      intake.runIntake();
      hopper.run();
      indexer.stopFeeder();
      indexer.runPreload();
    }
    // case 4: two balls loaded - no more can be intaken
    else {
      intake.stop();
      hopper.stop();
      indexer.stopAll();
    }
  }

  // what happens when the command ENDS
  @Override
  public void end(boolean interrupted) {
    intake.stop();
    hopper.stop();
    indexer.stopAll();

    intake.armIn();
  }

  // how the robot decides the Command is done
  @Override
  public boolean isFinished() {
    // finish if both balls staged & prestaged
    return indexer.getCargoPreStagedDetected() && indexer.getCargoStagedDetected();
  }
}
