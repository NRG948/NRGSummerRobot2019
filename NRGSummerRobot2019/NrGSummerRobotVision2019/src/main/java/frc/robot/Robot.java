/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.utilities.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private static final double visionAngle = 0; //limelight mounting angle
  private static final double h2 = 83.75; //height of high target
  private static final double h1 = 38; //mounting height
  private static Average distanceTshortAverage;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
   distanceTshortAverage = new Average(5);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
          NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
      NetworkTableEntry tx = table.getEntry("tx");
      NetworkTableEntry ty = table.getEntry("ty");
      NetworkTableEntry ta = table.getEntry("ta");
      NetworkTableEntry ts = table.getEntry("ts");
      NetworkTableEntry tshort = table.getEntry("tshort");
      NetworkTableEntry tlong = table.getEntry("tlong");
      //read values periodically
      boolean averageDistancebool = false;
      double x = tx.getDouble(0.0);
      double y = ty.getDouble(0.0);
      double area = ta.getDouble(0.0);
      double skew = ts.getDouble(0.0);
      double z = tshort.getDouble(0.0);
      double tLong = tlong.getDouble(0.0);
      double distanceUsingTshort = (4822/z)-5.0664; distanceTshortAverage.add(distanceUsingTshort);
      double averageTshort = distanceTshortAverage.averaged();
      double distanceTLong = (-0.024+Math.sqrt(-0.0148*tLong+1.2543432))/0.0074;
      double AngledDistance = Math.sqrt(Math.pow(distanceUsingTshort,2)+Math.pow(distanceTLong,2));
      double distance = (h2-h1)/Math.tan(Math.toRadians(visionAngle + y));
      double average = (distanceUsingTshort+ distance)/2;
      double distanceMax = 0;
      //post to smart dashboard periodically
      SmartDashboard.putNumber("LimelightX", x);
      SmartDashboard.putNumber("LimelightY", y);
      SmartDashboard.putNumber("LimelightArea", area);
      SmartDashboard.putNumber("LimeLIghtSkew", skew);
      SmartDashboard.putNumber("Tshort", z);
      SmartDashboard.putNumber("DistanceUsingTshort" , distanceUsingTshort);
      SmartDashboard.putNumber("AverageDistance", average);
      SmartDashboard.putNumber("LimeLightDistance", distance);
      SmartDashboard.putNumber("Tlong", distanceTLong);
      SmartDashboard.putNumber("AngledDistance", AngledDistance);
      SmartDashboard.putNumber("AverageDistanceUsingTshort", averageTshort);
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
