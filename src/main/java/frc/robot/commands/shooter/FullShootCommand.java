package frc.robot.commands.shooter;

import java.util.function.DoubleSupplier;

import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ShooterSubsystem.FiringAngle;

public class FullShootCommand extends BasicThrottleShootCommand {
  private final ShooterSubsystem shooter = ShooterSubsystem.getInstance();
  private FiringAngle firingAngle;

  public FullShootCommand(DoubleSupplier throttle, FiringAngle angle) {
    super(throttle);
    angle = firingAngle;
  }

  // what runs ONCE @ the beginning
  @Override
  public void initialize() {
    if (firingAngle.equals(FiringAngle.ANGLE_1)) {
      shooter.setShootAngle1();
    } else {
      shooter.setShootAngle2();
    }
  }

  // what runs REPEATEDLY when calling the command
  @Override
  public void execute() {
    super.execute();
  }

  // what happens when the command ENDS
  @Override
  public void end(boolean interrupted) {}

  // how the robot decides the Command is done
  @Override
  public boolean isFinished() {
    return false;
  }
}
