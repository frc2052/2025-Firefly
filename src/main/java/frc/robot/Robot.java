package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private final RobotContainer m_robotContainer;

  public Robot() {
    // New instance of RobotContainer! contains code for button functionality & auto setup
    m_robotContainer = new RobotContainer();
  }

  /**
   * Remember, a periodic loop is called every 20ms during a specific period -
   * This periodic loop is called regardless of which mode the robot is in.
   */
  @Override
  public void robotPeriodic() {
    // We ALWAYS want our "ToDo List" to be up and running to add Commands to,
    // so we run it here

    CommandScheduler.getInstance().run();
  }

  // This method is called ONCE when the robot is initially disabled.
  // Example use: competition where we want to switch climber modes
  // on disable so the climber doesn't give out & drop the robot
  // after a match ends.
  @Override
  public void disabledInit() {}

  // This method is called every 20ms throughout the time the robot is disabled.
  @Override
  public void disabledPeriodic() {}

  // This method is called ONCE when the robot enters auto mode.
  // There is a separate level to explore autos, but just know that here
  // is where we set it up so whichever auto we have set up in RobotContainer
  // is what runs once a match starts.
    // NOTE: think of an Auto as just another type of Command. They need to be scheduled
    // and added / removed to the CommandScheduler.
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  // This method is called every 20ms throughout auto (15 seconds)
  @Override
  public void autonomousPeriodic() {}

  // This method is called ONCE when the robot enters teleoperated mode.
  // .cancel() removes the selected auto mode from the CommandScheduler,
  // so auto stops & we can move to 
  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  // This method is called every 20ms throughout teleop (15 seconds)
  @Override
  public void teleopPeriodic() {}
  @Override

  // "Test mode" is a mode option on the DriverStation. We don't really 
  // use this mode as a team, but the init() and periodic() methods 
  // work similar to those above.
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }
  @Override
  public void testPeriodic() {}
}
