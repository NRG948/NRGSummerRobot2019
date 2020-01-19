/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.SPI;

public class Drive extends SubsystemBase {
  // The motors on the left side of the drive.
  private final CANSparkMax frontRight = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax backRight = new CANSparkMax(3, MotorType.kBrushless);
  private final CANSparkMax frontLeft = new CANSparkMax(4, MotorType.kBrushless);
  private final CANSparkMax backLeft = new CANSparkMax(5, MotorType.kBrushless);
  private final CANEncoder encoderBackRight = new CANEncoder(backRight);
  private final CANEncoder encoderBackLeft = new CANEncoder(backLeft);

   // The motors on the left side of the drive.
  private final SpeedControllerGroup m_leftMotors =
      new SpeedControllerGroup(frontLeft, backLeft);
   // The motors on the right side of the drive.
   private final SpeedControllerGroup m_rightMotors =
      new SpeedControllerGroup(frontRight, backRight);  
  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
  
  private final AHRS m_gyro = new AHRS(SPI.Port.kMXP);

  // Creating my odometry object. Here,
  // our starting pose is 5 meters along the long end of the field and in the
  // center of the field along the short end, facing forward.
  private final DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(
      Rotation2d.fromDegrees(getHeading()),
      new Pose2d(0.0, 0.0, new Rotation2d()));
  
  // new DifferentialDriveOdometry(
  //   getGyroHeading());

  public Drive() {
    }
  public void tankDrive(double leftPower, double rightPower){
    m_leftMotors.set(leftPower);
    m_rightMotors.set(rightPower);
    m_drive.feed();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Get my gyro angle. We are negating the value because gyros return positive
    // values as the robot turns clockwise. This is not standard convention that is
    // used by the WPILib classes.
    Rotation2d gyroAngle = Rotation2d.fromDegrees(-m_gyro.getAngle());
     
    // Update the pose
    Pose2d m_pose = m_odometry.update(gyroAngle, encoderBackLeft.getPosition(), encoderBackRight.getPosition());
    SmartDashboard.putString("position", m_pose.toString());
    
  }
    /**
   * Zeroes the heading of the robot.
   */
  public void zeroHeading() {
    m_gyro.reset();
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(pose, 
        new Rotation2d());
  }

  public void resetEncoders() {
    // encoderFrontRight.setPosition(0);
    encoderBackRight.setPosition(0);
    // encoderFrontLeft.setPosition(0);
    encoderBackLeft.setPosition(0);
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from 180 to 180
   */
  public double getHeading() {
    return Math.IEEEremainder(m_gyro.getAngle(), 360) * (DriveConstants.kGyroReversed ? -1.0 : 1.0);
  }
}
