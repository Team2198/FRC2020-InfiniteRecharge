package frc.robot.systems;
//grbfhvjsdk
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.systems.ParadigmSystem;

public class Drive extends ParadigmSystem {

    private DifferentialDrive driver;
    private SpeedControllerGroup leftMotors;
    private SpeedControllerGroup rightMotors;
    private final double TURN_SENSE =0.675;

    public Drive (XboxController controller){
        super("Drive", controller);
    }

    @Override
    public void update() {
        double xSpeed = controller.getY(GenericHID.Hand.kLeft);
        double zRotation = controller.getX(GenericHID.Hand.kRight);

        if (Math.abs(xSpeed) < 0.1){
            driver.tankDrive(zRotation * TURN_SENSE, -zRotation * TURN_SENSE, true);
        } else {
            if (xSpeed < 0){
                driver.curvatureDrive(xSpeed, zRotation, false);
            } else {
                driver.curvatureDrive(xSpeed, -zRotation, false);
            }
            
        }
    }

    @Override
    public void enable(){
        WPI_TalonSRX top_Left = new WPI_TalonSRX(Constants.DRIVE_TOP_LEFT);
        WPI_TalonSRX extra_Left = new WPI_TalonSRX(Constants.DRIVE_EXTRA_LEFT);
        WPI_TalonSRX bottom_Left = new WPI_TalonSRX(Constants.DRIVE_BOTTOM_LEFT);
        WPI_VictorSPX top_Right = new WPI_VictorSPX(Constants.DRIVE_TOP_RIGHT);
        WPI_VictorSPX extra_Right = new WPI_VictorSPX(Constants.DRIVE_EXTRA_RIGHT);
        WPI_VictorSPX bottom_Right = new WPI_VictorSPX(Constants.DRIVE_BOTTOM_RIGHT);
    
        leftMotors = new SpeedControllerGroup(top_Left, extra_Left, bottom_Left);
        rightMotors = new SpeedControllerGroup(top_Right, extra_Right, bottom_Right);

        driver = new DifferentialDrive(rightMotors, leftMotors);
        super.enable();
    }
    
    @Override
    public void disable(){
        driver.stopMotor();
        super.disable();
    }

    public DifferentialDrive getDrive(){
        return driver;
    }

}