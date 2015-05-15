package gameOfLife;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {
	private boolean _alive;
	private boolean _nextAliveState;
	private boolean _stateChanged = true;
	private int _y;
	private int _x;
	static int _size = 20;
	static boolean _grid = true;

	public Cell(int x, int y) {
		_alive = false;
		_x = x;
		_y = y;
	}

	public int checkForFriends() {
		int friends = 0;
		int x1 = _x - 1, x2 = _x + 1, y1 = _y - 1, y2 = _y + 1;
		if (x1 < 0)
			x1 = Simulator.maxX - 1;
		if (x2 >= Simulator.maxX)
			x2 = 0;
		if (y1 < 0)
			y1 = Simulator.maxY - 1;
		if (y2 >= Simulator.maxY)
			y2 = 0;
		if (Main.getSimulation().cells.get(_x).get(y1).isAlive())
			friends++;
		if (Main.getSimulation().cells.get(_x).get(y2).isAlive())
			friends++;
		if (Main.getSimulation().cells.get(x1).get(y1).isAlive())
			friends++;
		if (Main.getSimulation().cells.get(x1).get(_y).isAlive())
			friends++;
		if (Main.getSimulation().cells.get(x1).get(y2).isAlive())
			friends++;
		if (Main.getSimulation().cells.get(x2).get(y1).isAlive())
			friends++;
		if (Main.getSimulation().cells.get(x2).get(_y).isAlive())
			friends++;
		if (Main.getSimulation().cells.get(x2).get(y2).isAlive())
			friends++;
		return friends;
	}

	public void updateCellNextState() {
		int friends = checkForFriends();
		if ((friends < 2) || (friends >= 4)) {
			willBeAlive(false);
		} else if (friends == 3)
			willBeAlive(true);
	}

	public void updateCellState() {
		setAlive(_nextAliveState);
	}

	public void stateChanged(boolean in){
		_stateChanged=in;
	}

	public boolean isAlive() {
		return _alive;
	}

	public boolean willBeAlive() {
		return _nextAliveState;
	}

	public void setAlive(boolean alive) {
		_alive = alive;
	}

	public void willBeAlive(boolean alive) {
		if (alive != _nextAliveState)
			_stateChanged = true;
		_nextAliveState = alive;
	}

	public void draw(Graphics graph) {
		if (_stateChanged) {
			if (_grid) {
				if (!_nextAliveState) {
					graph.setColor(Color.WHITE);
				}
				graph.fillRect(_x * _size, _y * _size, _size, _size);
				graph.setColor(Color.BLACK);
				graph.drawRect(_x * _size, _y * _size, _size, _size);
			} else {
				if (_nextAliveState) {
					graph.setColor(Color.BLACK);
				} else {
					graph.setColor(Color.WHITE);
				}
				graph.fillRect(_x * _size, _y * _size, _size, _size);
			}
		}
		_stateChanged=false;
	}

}
