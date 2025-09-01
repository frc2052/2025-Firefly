// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;

public class OuttakeCommand extends Command {
  private final IntakeSubsystem intake = IntakeSubsystem.getInstance();
  private final IndexerSubsystem indexer = IndexerSubsystem.getInstance();
  private final HopperSubsystem hopper = HopperSubsystem.getInstance();

  public OuttakeCommand() {
  }

  // what runs ONCE @ the beginning
  @Override
  public void initialize() {}

  // what runs REPEATEDLY when calling the command
  @Override
  public void execute() {
    intake.reverse();
    hopper.reverse();
    indexer.runPreloadReverse();
    indexer.runFeederReverse();
  }

  // what happens when the command ENDS
  @Override
  public void end(boolean interrupted) {
    intake.stop();
    hopper.stop();
    indexer.stopPreload();
    indexer.stopFeeder();
  }

  // how the robot decides the Command is done
  @Override
  public boolean isFinished() { 
    return false; // runs until interrupted (ex. when button is released)
  }
}
