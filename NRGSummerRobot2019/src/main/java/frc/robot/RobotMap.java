/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.kauailabs.navx.frc.AHRS;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static CANSparkMax frontLeftSpark;
  public static CANSparkMax frontRightSpark;
  public static CANSparkMax rearLeftSpark;
  public static CANSparkMax rearRightSpark;

  public static AHRS navx;

  public static Encoder driveLeftEncoder;
  public static Encoder driveRightEncoder;


  public static void init() {
  frontLeftSpark = new CANSparkMax(2, MotorType.kBrushless);
  frontRightSpark  = new CANSparkMax(3, MotorType.kBrushless);
  rearLeftSpark  = new CANSparkMax(4, MotorType.kBrushless);
  rearRightSpark  = new CANSparkMax(5, MotorType.kBrushless);

  
  driveLeftEncoder = new Encoder(DRIVE_LEFT_ENCODER_PORT1, DRIVE_LEFT_ENCODER_PORT2);
  driveRightEncoder = new Encoder(DRIVE_RIGHT_ENCODER_PORT1, DRIVE_RIGHT_ENCODER_PORT2, true);
  driveLeftEncoder.setDistancePerPulse(DRIVE_LEFT_ENCODER_DIST_PER_PULSE);
  driveRightEncoder.setDistancePerPulse(DRIVE_RIGHT_ENCODER_DIST_PER_PULSE);

  navx = new AHRS(SPI.Port.kMXP);
  System.out.println("FrontLeftSpark: " + frontLeftSpark.getDeviceId() + "; firmware = " + frontLeftSpark.getFirmwareString());
  }
  public static void resetSensors() {
    navx.reset();
  }

}
