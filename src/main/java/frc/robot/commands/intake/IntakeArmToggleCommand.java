package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;

public class IntakeArmToggleCommand extends Command {

  public IntakeArmToggleCommand() {
  }
  
  // what runs ONCE @ the beginning
  @Override
  public void initialize() {

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
