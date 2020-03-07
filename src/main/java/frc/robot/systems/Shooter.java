package frc.robot.systems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.Constants;
import frc.robot.systems.ParadigmSystem;

public class Shooter extends ParadigmSystem {

    private final double RISER_DOWN_SPEED = -0.5;
    private final double RISER_UP_SPEED = 0.50;

    private final double CONVEYOR_SPEED = -0.25;
    private final double SHOOTER_SPEED = -0.25;

    VictorSP intake, riser_left, riser_right;
    VictorSP shooter, conveyor;

    public Shooter(XboxController controller){
        super("Shooting Mechanism", controller);
    }

    @Override
    public void update() {
        updateShooter();
        updateIntake();
        updateConveyor();
        updateRiser();
    }
    
    public void updateShooter() {
        if (controller.getBButtonPressed()) {
            shooter.set(SHOOTER_SPEED);
        } else if (controller.getBButtonReleased()) {
            shooter.set(0.00);
        }
    }

    public void updateIntake(){
        intake.setSpeed(-controller.getTriggerAxis(Hand.kRight));
    }

    public void updateConveyor(){
        if (controller.getYButtonPressed()) {
            conveyor.set(CONVEYOR_SPEED);
        } else if (controller.getYButtonReleased()) {
            conveyor.set(0.00);
        }
    }

    public void updateRiser(){
        // Riser Down Control
        if (controller.getXButtonPressed()) {
            log("X PRESSED");
            riser_left.set(RISER_DOWN_SPEED);
            riser_right.set(RISER_DOWN_SPEED);
        } else if (controller.getXButtonReleased()) {
            riser_left.set(0.00);
            riser_right.set(0.00);
        }

        // Riser Up Control
        if (controller.getAButtonPressed()) {
            log("A PRESSED");
            riser_left.set(RISER_UP_SPEED);
            riser_right.set(RISER_UP_SPEED);
        } else if (controller.getAButtonReleased()) {
            riser_left.set(0.00);
            riser_right.set(0.00);
        }
    }

    @Override
    public void enable(){
        intake = new VictorSP(Constants.INTAKE);
        intake.setInverted(true);
        riser_left = new VictorSP(Constants.RISER_LEFT);
        riser_left.setInverted(true);
        riser_right = new VictorSP(Constants.RISER_RIGHT);
        shooter = new VictorSP(Constants.SHOOTER);
        conveyor = new VictorSP(Constants.CONVEYOR);
        super.enable();
    }
    
    @Override
    public void disable(){
        intake.disable();
        riser_left.disable();
        riser_right.disable();
        shooter.disable();
        conveyor.disable();
        super.disable();
    }

}