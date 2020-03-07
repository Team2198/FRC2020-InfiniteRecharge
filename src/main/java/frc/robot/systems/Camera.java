package frc.robot.systems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.VideoSource.ConnectionStrategy;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.XboxController;

public class Camera extends ParadigmSystem {
    
    private UsbCamera camera1;
    private UsbCamera camera2;
    private VideoSink server;

	public Camera(XboxController controller) {
		super("Cameras", controller);
    }
    
    @Override
    public void enable(){
        camera1 = CameraServer.getInstance().startAutomaticCapture(0);
        camera2 = CameraServer.getInstance().startAutomaticCapture(1);
        server = CameraServer.getInstance().getServer();

        camera1.setFPS(60);
        camera2.setFPS(60);
        
        camera1.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
        camera2.setConnectionStrategy(ConnectionStrategy.kKeepOpen);
        
    }

	@Override
	public void update() {
		
	}
    
}