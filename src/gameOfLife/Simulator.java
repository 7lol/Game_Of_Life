package gameOfLife;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Simulator {
	final static int maxX = Main._width / Cell._size;
	final static int maxY = Main._height / Cell._size;
	static boolean started = false;
	Graphics graph=Main.mainWindow.getGraphics();
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
		for (int j = 0; j < Simulator.maxY; j++) {
			for (int i = 0; i < Simulator.maxX; i++) {
				Main.simulation.cells.get(i).get(j).draw(graph);
			}
		}
	}

	public  void prepareToDraw(Graphics graph) {
		if (MouseAndKeyboardHandler.go) {
			runnable = new CheckingThread(Simulator.maxY-1, 1);
			sync = maxY + 1;
			for (int j = 0; j < maxY; j++) {
				thread = new Thread(runnable);
				thread.start();
			}
		}
	}

	public synchronized void draw(Graphics graph) {
		drawAll(graph);
		prepareToDraw(graph);
		if (MouseAndKeyboardHandler.go) {
			sync--;
			for (int j = 0; j < maxY; j++) {
				for (int i = 0; i < maxX; i++) {
					cells.get(i).get(j).updateCellState();
				}
			}
		}
	}

	public void draw1(Graphics graph) {
		for (int j = 0; j < maxY; j++) {
			for (int i = 0; i < maxX; i++) {
				cells.get(i).get(j).draw(graph);
			}
		}
	}
}
