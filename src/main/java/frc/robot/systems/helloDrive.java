package frc.robot.systems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.systems.ParadigmSystem;

public class helloDrive extends ParadigmSystem {

    private DifferentialDrive driver;
    private SpeedControllerGroup leftMotors;
    private SpeedControllerGroup rightMotors;

    private final double TURN_SENSE = 0.55;

    public helloDrive(XboxController controller) {
        super("Driver", controller);
    }

    @Override
    public void update() {
        double xSpeed = controller.getY(GenericHID.Hand.kLeft);
        double zRotation = controller.getX(GenericHID.Hand.kRight);

        if (Math.abs(xSpeed) < 0.1){
            driver.tankDrive(-zRotation * TURN_SENSE, zRotation * TURN_SENSE, true);
        } else {
            driver.curvatureDrive(xSpeed, zRotation, false);
        }
    }

@Override
public void enable () {
    VictorSP top_Left = new VictorSP(Constants.DRIVE_TOP_LEFT);
    VictorSP extra_Left = new VictorSP(Constants.DRIVE_EXTRA_LEFT);
    VictorSP bottom_Left = new VictorSP(Constants.DRIVE_BOTTOM_LEFT);
    VictorSP top_Right = new VictorSP(Constants.DRIVE_TOP_RIGHT);
    VictorSP extra_Right = new VictorSP(Constants.DRIVE_EXTRA_RIGHT);

    leftMotors = new SpeedControllerGroup(top_Left, extra_Left, bottom_Left);
    rightMotors = new SpeedControllerGroup(top_Right, extra_Right, bottom_Left);
    leftMotors.setInverted(true);
    rightMotors.setInverted(true);

    driver = new DifferentialDrive(leftMotors, rightMotors);
    super.enable();
}

    @Override
    public void disable (){
        driver.stopMotor();
        super.disable();
    }

    public DifferentialDrive getDrive(){
        return driver;
    }

}