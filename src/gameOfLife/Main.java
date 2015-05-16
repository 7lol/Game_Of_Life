package gameOfLife;

import java.awt.Font;
import java.util.List;

public class Main {
	private static double _startime = 0;
	private static int _frames=0;
	private static int _width = 1000;
	private static int _height = 1000;
	private static double _fps = 60;
	private static Simulator simulation;
	private static Frame mainWindow;
	private static boolean startedThreads = false;

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
			Thread.sleep((long) 100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long lastFrame = System.currentTimeMillis();
		long thisFrame = 0;
		double fps = 0 ;
		mainWindow.setFont(new Font("SansSerif", Font.BOLD, 25));
		simulation.draw(mainWindow.getGraphics());
		for (List<Cell> cellList: simulation.cells) {
			for (Cell singleCell: cellList) {
				singleCell.stateChanged();
			}
		}
		while (true) {
			if(MouseAndKeyboardHandler.go){
				thisFrame = System.currentTimeMillis();
				fps = 1/((thisFrame - lastFrame) / 1000.0);
			}
			simulation.draw(mainWindow.getGraphics());
			if (MouseAndKeyboardHandler.go)
				try {
					if(_fps<fps)
					do
					{
						Thread.sleep((long) 1);
						thisFrame = System.currentTimeMillis();
						fps = 1000.0/((int)(thisFrame - lastFrame));
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
	public static double get_startime() {
		return _startime;
	}

	public static void set_startime(double _startime) {
		Main._startime = _startime;
	}

	public static int get_frames() {
		return _frames;
	}

	public static void set_frames(int _frames) {
		Main._frames = _frames;
	}

	public static int get_width() {
		return _width;
	}

	public static void set_width(int _width) {
		Main._width = _width;
	}

	public static int get_height() {
		return _height;
	}

	public static void set_height(int _height) {
		Main._height = _height;
	}

	public static double get_fps() {
		return _fps;
	}

	public static void set_fps(double _fps) {
		Main._fps = _fps;
	}

	public static Simulator getSimulation() {
		return simulation;
	}

	public static void setSimulation(Simulator simulation) {
		Main.simulation = simulation;
	}

	public static Frame getMainWindow() {
		return mainWindow;
	}

	public static void setMainWindow(Frame mainWindow) {
		Main.mainWindow = mainWindow;
	}

	public static boolean isStartedThreads() {
		return startedThreads;
	}

	public static void setStartedThreads(boolean startedThreads) {
		Main.startedThreads = startedThreads;
	}

}
