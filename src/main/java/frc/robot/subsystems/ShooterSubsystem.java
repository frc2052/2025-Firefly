// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.MotorIDs;

public class ShooterSubsystem extends SubsystemBase {

  private static ShooterSubsystem INSTANCE;
    public static ShooterSubsystem getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new ShooterSubsystem();
        }
        return INSTANCE;
  }
  
  private final TalonSRX topMotor;
  private final TalonSRX bottomMotor;

  private final DoubleSolenoid angleSolenoid;

  private FiringAngle currentAngle;

  public ShooterSubsystem() {

  // top motor
    topMotor = new TalonSRX(MotorIDs.TOP_SHOOTER_MOTOR);
    topMotor.configFactoryDefault();
    topMotor.setNeutralMode(NeutralMode.Coast);
    // pid
    topMotor.config_kP(0, 0.11, 10);
    topMotor.config_kI(0, 0.001, 10); 
    topMotor.config_kD(0, 0.7, 10);

  // bottom motor
    bottomMotor = new TalonSRX(MotorIDs.BOTTOM_SHOOTER_MOTOR);
    bottomMotor.configFactoryDefault();
    bottomMotor.setNeutralMode(NeutralMode.Coast);
    // pid
    bottomMotor.config_kP(0, 0.11, 10);
    bottomMotor.config_kI(0, 0.001, 10);
    bottomMotor.config_kD(0, 0.7, 10);

    angleSolenoid = new DoubleSolenoid(
      Constants.SolenoidIDs.COMPRESSOR_MODULE_ID,
      PneumaticsModuleType.REVPH,
      Constants.SolenoidIDs.SHOOTER_IN,
      Constants.SolenoidIDs.SHOOTER_OUT
    );

    if(angleSolenoid.get().equals(Value.kReverse)){
      currentAngle = FiringAngle.ANGLE_1;
    } else {
      currentAngle = FiringAngle.ANGLE_2;
    }

    stop();
  }

  // wheels-related

  public void stop() {
    System.err.println("***************************** STOPPING SHOOTER");
      topMotor.set(ControlMode.PercentOutput, 0);
      bottomMotor.set(ControlMode.PercentOutput, 0);
  }

  public void shootAtPercentage(double topWheelPercent, double bottomWheelPercent) {
    topMotor.set(ControlMode.PercentOutput, topWheelPercent/100.0);
    bottomMotor.set(ControlMode.PercentOutput, bottomWheelPercent/100.0);
  }

  public double getTopWheelVelocity() {
    return topMotor.getSelectedSensorVelocity();
  }

  public double getBottomWheelVelocity() {
    return bottomMotor.getSelectedSensorVelocity();
  }

  // solenoid related


  public enum FiringAngle { // remember to create a "Firing Angle"
    ANGLE_1(Constants.Shooter.FIRING_ANGLE_1_DEGREES),
    ANGLE_2(Constants.Shooter.FIRING_ANGLE_2_DEGREES);

    private double angleDegrees;

    FiringAngle(double angleDegrees) {
      this.angleDegrees = angleDegrees;
    }

    public double getAngleDegrees() {
        return angleDegrees;
    }
  }

  public void setShootAngle1() {
      angleSolenoid.set(Value.kReverse);
      currentAngle = FiringAngle.ANGLE_1;
  }

  public void setShootAngle2() {
      angleSolenoid.set(Value.kForward);
      currentAngle = FiringAngle.ANGLE_2;
  }

  public double getShootAngleDegrees() {
    return currentAngle.getAngleDegrees();
  }

  public FiringAngle getShootAngle() {
    return currentAngle;
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Shooter Top Wheel Speed", topMotor.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Shooter Bottom Wheel Speed", bottomMotor.getSelectedSensorVelocity());
  }
}
