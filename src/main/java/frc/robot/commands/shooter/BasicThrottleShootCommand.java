package frc.robot.commands.shooter;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterSubsystem;

public class BasicThrottleShootCommand extends Command {

  private final ShooterSubsystem shooter = ShooterSubsystem.getInstance();
  private final DoubleSupplier throttle;

  public BasicThrottleShootCommand(DoubleSupplier throttle) {
    this.throttle = throttle;
      addRequirements(shooter);
  }

  // what runs ONCE @ the beginning
  @Override
  public void initialize() {}

  // what runs REPEATEDLY when calling the command
  @Override
  public void execute() {
    // convert throttle to percent
    double throttlePercent = throttle.getAsDouble() * 100;

    // prevent speeds < 25
    double percentRun = throttlePercent < 25? 50: throttlePercent;
    System.out.println("==== THROTTLE AS SHOOTER PERCENT: " + percentRun);

    shooter.shootAtPercentage(percentRun, percentRun);
  }

  // what happens when the command ENDS
  @Override
  public void end(boolean interrupted) {
    shooter.stop();
  }

  // how the robot decides the Command is done
  @Override
  public boolean isFinished() {
    return false;
  }
}
