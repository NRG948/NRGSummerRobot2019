/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  public static CANSparkMax frontLeftSpark = new CANSparkMax(2, MotorType.kBrushless);
  public static CANSparkMax frontRightSpark = new CANSparkMax(3, MotorType.kBrushless);
  public static CANSparkMax rearLeftSpark = new CANSparkMax(4, MotorType.kBrushless);
  public static CANSparkMax rearRightSpark = new CANSparkMax(5, MotorType.kBrushless);

}
