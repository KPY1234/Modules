package modules.utilities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class RobotHandler {

private volatile static Robot robot;
	
	public static Robot getRobot(){
		if(robot == null){
			synchronized (RobotHandler.class){
				if(robot ==null) {
					try {
						robot = createRobot();
					} catch (AWTException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return robot;

	}
	
	private static Robot createRobot() throws AWTException{
//		System.out.println("create");
		Robot r = null;
		try {
			r = new Robot();
			r.setAutoWaitForIdle(true);
		 	r.setAutoDelay(300);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return r;
	}
	
	public static void pressAndRelease(){
		
		Robot r = getRobot();
		
		r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	
	
	
	
	public static void main(String[] args) {
		
	}
	
}
