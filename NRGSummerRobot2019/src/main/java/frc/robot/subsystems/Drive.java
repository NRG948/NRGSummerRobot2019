/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ManualDrive;

/**
 * Add your docs here.
 */
public class Drive extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
         // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        setDefaultCommand(new ManualDrive());
      }
      
      static double[] prevGet = new double[4];

      public void tankDrive(double leftPower, double rightPower) {
        // RobotMap.frontLeftSpark.set(leftPower);
        // RobotMap.rearLeftSpark.set(leftPower);
        RobotMap.frontRightSpark.set(rightPower);
        RobotMap.rearRightSpark.set(rightPower);

        TraceGetChange(0, RobotMap.frontLeftSpark);
        TraceGetChange(1, RobotMap.rearLeftSpark);
        TraceGetChange(2, RobotMap.frontRightSpark);
        TraceGetChange(3, RobotMap.rearRightSpark);
      }

      public static void TraceGetChange(int index, CANSparkMax can)
      {
        double get = can.get();
        if (get != prevGet[index])
        {
          System.out.println("CAN #" + index + " = " + get);
          prevGet[index] = get;
        }
      }

      public void stop() {
        RobotMap.frontLeftSpark.disable();
        RobotMap.rearLeftSpark.disable();
        RobotMap.frontRightSpark.disable();
        RobotMap.rearRightSpark.disable();
      }
  }
  