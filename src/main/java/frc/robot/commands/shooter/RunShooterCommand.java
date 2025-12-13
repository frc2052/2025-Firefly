package frc.robot.commands.shooter;

import java.util.function.DoubleSupplier;

import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.ShooterSubsystem.FiringAngle;

public class RunShooterCommand extends BasicThrottleShootCommand {
  private final ShooterSubsystem shooter = ShooterSubsystem.getInstance();
  private FiringAngle firingAngle;

  public RunShooterCommand(DoubleSupplier throttle, FiringAngle angle) {
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
}
