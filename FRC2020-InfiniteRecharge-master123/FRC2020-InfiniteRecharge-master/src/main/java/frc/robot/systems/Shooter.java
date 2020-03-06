package frc.robot.systems;

import edu.wpi.first.wpilibj.*;
import frc.robot.Constants;
import frc.robot.systems.ParadigmSystem;

public class Shooter extends ParadigmSystem {

    private final double RISER_DOWN_SPEED = -0.90;
    private final double RISER_UP_SPEED = 1.00;

    private final double CONVEYOR_SPEED = 0.80;
    private final double SHOOTER_SPEED = 0.80;

    DMC60 intake, riser_left, riser_right;
    DMC60 shooter, conveyor;

    public Shooter(XboxController controller){
        super("Shooting Mechanism", controller);
    }

    @Override
    public void update() {
        updateShooter();
        updateConveyor();
        updateRiser();
    }
    
    public void updateShooter() {
        if (controller.getAButtonPressed()) {
            shooter.set(SHOOTER_SPEED);
        } else if (controller.getAButtonReleased()) {
            shooter.set(0.00);
        }
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
            riser_left.set(RISER_DOWN_SPEED);
            riser_right.set(RISER_DOWN_SPEED);
        } else if (controller.getXButtonReleased()) {
            riser_left.set(0.00);
            riser_right.set(0.00);
        }

        // Riser Up Control
        if (controller.getAButtonPressed()) {
            riser_left.set(RISER_UP_SPEED);
            riser_right.set(RISER_UP_SPEED);
        } else if (controller.getAButtonReleased()) {
            riser_left.set(0.00);
            riser_right.set(0.00);
        }
    }

    @Override
    public void enable(){
        intake = new DMC60(Constants.INTAKE);
        riser_left = new DMC60(Constants.RISER_LEFT);
        riser_right = new DMC60(Constants.RISER_RIGHT);
        shooter = new DMC60(Constants.SHOOTER);
        conveyor = new DMC60(Constants.CONVEYOR);
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