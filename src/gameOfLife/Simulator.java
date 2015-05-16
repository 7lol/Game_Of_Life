package gameOfLife;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
	final static int maxX = Main.get_width() / Cell._size;
	final static int maxY = Main.get_height() / Cell._size;
	static boolean started = false;
	Graphics graph = Main.getMainWindow().getGraphics();
	static int sync = 0;
	static int numberOfThreads = 0;
	Thread thread;
	CheckingThread runnable;
	final List<List<Cell>> cells = new ArrayList<List<Cell>>();

	public Simulator() {
		List<Cell> lane;
		for (int j = 0; j < maxX; j++) {
			lane = new ArrayList<Cell>();
			for (int i = 0; i < maxY; i++) {
				Cell x = new Cell(j, i);
				lane.add(x);
			}
			cells.add(lane);
		}
	}

	public void drawAll(Graphics graph) {
		for (List<Cell> cellList: cells) {
			for (Cell singleCell: cellList) {
				singleCell.draw(graph);
			}
		}
	}

	public void oneCycleSimulation(Graphics graph) {
		if (MouseAndKeyboardHandler.go) {
			runnable = new CheckingThread(cells.size() - 1, 1);
			sync = cells.size() + 1;
			for (int i = 0; i < cells.size(); i++) {
				thread = new Thread(runnable);
				thread.start();
			}
		}
	}

	public synchronized void draw(Graphics graph) {
		drawAll(graph);
		oneCycleSimulation(graph);
		if (MouseAndKeyboardHandler.go) {
			sync--;
			Main.set_frames(Main.get_frames()+1);
			for (List<Cell> cellList: cells) {
				for (Cell singleCell: cellList) {
					singleCell.updateCellState();
				}
			}
		}
	}
}
