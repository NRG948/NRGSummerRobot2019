/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {

  CANSparkMax frontRight = new CANSparkMax(4, MotorType.kBrushless);
  CANSparkMax backRight = new CANSparkMax(5, MotorType.kBrushless);
  CANSparkMax frontLeft = new CANSparkMax(2, MotorType.kBrushless);
  CANSparkMax backLeft = new CANSparkMax(3, MotorType.kBrushless);


  public Drive() {
    }
  public void tankDrive(double leftPower, double rightPower){
    frontLeft.set(leftPower);
    frontRight.set(rightPower);
    backLeft.set(leftPower);
    backRight.set(rightPower);
    frontRight.setInverted(true);
    backRight.setInverted(true);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
