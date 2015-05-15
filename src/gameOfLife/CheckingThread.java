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
			Main.getSimulation().cells.get(i).get(j).updateCellNextState();
		}
	}

	public void run() {
		synchronized (Main.getSimulation()) {
			if (_start >= 0)
				if (MouseAndKeyboardHandler.go) {
					for (int j = _start; j > _start - _jump; j--) {
						for (int i = 0; i < Simulator.maxY; i++) {
							Main.getSimulation().cells.get(j).get(i).updateCellNextState();
						}
					}
					Simulator.sync = Simulator.sync - 1;
					_start -= _jump;
				}
		}
	}
}
