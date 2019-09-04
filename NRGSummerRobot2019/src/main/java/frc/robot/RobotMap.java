/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
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

  public static void init() {
  frontLeftSpark = new CANSparkMax(2, MotorType.kBrushless);
  frontRightSpark  = new CANSparkMax(3, MotorType.kBrushless);
  rearLeftSpark  = new CANSparkMax(4, MotorType.kBrushless);
  rearRightSpark  = new CANSparkMax(5, MotorType.kBrushless);

  System.out.println("FrontLeftSpark: " + frontLeftSpark.getDeviceId() + "; firmware = " + frontLeftSpark.getFirmwareString());
  }
}
