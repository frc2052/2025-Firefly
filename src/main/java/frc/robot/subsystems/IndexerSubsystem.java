// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.MotorIDs;

public class IndexerSubsystem extends SubsystemBase {
  
  private static IndexerSubsystem INSTANCE;
    public static IndexerSubsystem getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new IndexerSubsystem();
        }
        return INSTANCE;
    }

  // two motors: preload and feeder
  private static TalonSRX preloadIndexerMotor;
  private static TalonSRX feederIndexerMotor;

  // limit switches
  private final DigitalInput preStagedCargoDetector = new DigitalInput(Constants.LimitSwitch.INDEXER_PRELOAD);
  private final DigitalInput stagedCargoDetector = new DigitalInput(Constants.LimitSwitch.INDEXER_FEEDER);

  public IndexerSubsystem() {
    preloadIndexerMotor = new TalonSRX(MotorIDs.PRELOAD_INDEXER_MOTOR);
    preloadIndexerMotor.configFactoryDefault();
    preloadIndexerMotor.setNeutralMode(NeutralMode.Brake);

    feederIndexerMotor = new TalonSRX(MotorIDs.FEEDER_INDEXER_MOTOR);
    feederIndexerMotor.configFactoryDefault();
    feederIndexerMotor.setNeutralMode(NeutralMode.Brake);
  }

  // PRELOAD METHODS
  public void runPreload() {
    preloadIndexerMotor.set(ControlMode.PercentOutput, Constants.Shooter.PRELOAD_WHEEL_SPEED);
  }

  public void runPreloadReverse() {
    preloadIndexerMotor.set(ControlMode.PercentOutput, -Constants.Shooter.PRELOAD_WHEEL_SPEED);
  }

  public void stopPreload() {
    preloadIndexerMotor.set(ControlMode.PercentOutput, 0);
  }

  // FEEDER METHODS
  public void runFeeder() {
    feederIndexerMotor.set(ControlMode.PercentOutput, -Constants.Shooter.INDEXER_WHEEL_SPEED);
  }

  public void runFeederReverse() {
    feederIndexerMotor.set(ControlMode.PercentOutput, Constants.Shooter.INDEXER_WHEEL_SPEED);
  }

  public void stopFeeder() {
    feederIndexerMotor.set(ControlMode.PercentOutput, 0);
  }

  public void stopAll(){
    feederIndexerMotor.set(ControlMode.PercentOutput, 0);
    preloadIndexerMotor.set(ControlMode.PercentOutput, 0);
  }

  // GETTERS
  public double getLargeIndexerSpeed() {
    double largeIndexerRunning = preloadIndexerMotor.getSelectedSensorVelocity();
    return largeIndexerRunning;
  }

  public double getFeederIndexerSpeed() {
    double feederIndexerRunning = feederIndexerMotor.getSelectedSensorVelocity();
    return feederIndexerRunning;
  }

  // TODO: double check accuracy
  public boolean getCargoPreStagedDetected() {
    //returns true if beam is not broken, no ball
    return !preStagedCargoDetector.get();
  }

  public boolean getCargoStagedDetected() {
    //returns true if beam is not broken, no ball
    return !stagedCargoDetector.get();
  }

  @Override
  public void periodic() { 
    boolean stagedDetected = getCargoStagedDetected();
    boolean preStagedDetected = getCargoPreStagedDetected();

    SmartDashboard.putBoolean("Staged Cargo Detected", stagedDetected);
    SmartDashboard.putBoolean("PreStaged Cargo Detected", preStagedDetected);
  }
}
