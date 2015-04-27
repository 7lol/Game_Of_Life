package gameOfLife;

import gameOfLife.Simulator;

public class CheckingThread extends Thread implements Runnable {
	private int _start, _jump;

	public CheckingThread(int start, int jump) {
		_start = start;
		_jump = jump;
	}

	public void updateNextState(int i, int j) {
		if (MouseAndKeyboardHandler.go) {
			Main.simulation.cells.get(i).get(j).updateCellNextState();
		}
	}

	public  void run() {
		synchronized (Main.simulation) {
		if (_start >= 0)
			if (MouseAndKeyboardHandler.go) {
				for (int j = _start; j > _start - _jump; j--) {
					for (int i = 0; i < Simulator.maxX; i++) {
						updateNextState(i, j);
					}
				}
					Simulator.sync = Simulator.sync - 1;
					_start -= _jump;
				}
			}
	}
}
