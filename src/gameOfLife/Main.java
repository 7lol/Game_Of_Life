package gameOfLife;

public class Main {
	public static int _width = 1000;
	public static int _height = 1000;
	public static double _fps = 60;
	public static Simulator simulation;
	public static Frame mainWindow;
	public static boolean startedThreads = false;

	public static void main(String[] args) {
		mainWindow = new Frame();
		mainWindow.setDefaultCloseOperation(3);
		mainWindow.setVisible(true);
		mainWindow.setResizable(false);
		_width = mainWindow.getWidth();
		_height = mainWindow.getHeight();
		Simulator.started = true;
		mainWindow.startDrawing();
		simulation = new Simulator();
		try {
			Thread.sleep((long) 50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long lastFrame = System.currentTimeMillis();
		long thisFrame = 0;
		double fps = 0 ;
		while (true) {
			if(MouseAndKeyboardHandler.go){
				thisFrame = System.currentTimeMillis();
				fps = 1/((thisFrame - lastFrame) / 1000.0);
			}
			simulation.draw(mainWindow.getGraphics());
			if (MouseAndKeyboardHandler.go)
				try {
					while (Simulator.sync!=0)
					Thread.sleep((long) 1);
					if(_fps<fps)
					do
					{
						Thread.sleep((long) 1);
						thisFrame = System.currentTimeMillis();
						fps = 1000.0/((thisFrame - lastFrame));
					}
					while (_fps<fps);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			else {
				try {
					Thread.sleep((long) 10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			lastFrame = thisFrame;
		}
	}
}
