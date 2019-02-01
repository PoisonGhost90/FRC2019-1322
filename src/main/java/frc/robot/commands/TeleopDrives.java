/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class TeleopDrives extends Command {

  public TeleopDrives() {
    requires(Robot.DRIVES);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    SmartDashboard.putNumberArray("Velocity", Robot.DRIVES.rawVelocities());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.DRIVES.DriveInVoltage(Robot.m_oi.DriverStick.getY(Hand.kLeft), Robot.m_oi.DriverStick.getX(Hand.kLeft),
        Robot.m_oi.DriverStick.getX(Hand.kRight));
    
    SmartDashboard.putNumber("Joystick", Robot.m_oi.DriverStick.getY(Hand.kLeft));
    SmartDashboard.putNumber("Velocity", Robot.DRIVES.rawVelocities()[0]);
    SmartDashboard.putNumber("Position", Robot.DRIVES.rawPosition()[0]);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.DRIVES.DriveInVoltage(0, 0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

}