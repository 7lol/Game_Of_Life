package gameOfLife;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class MouseAndKeyboardHandler implements KeyListener,
		MouseMotionListener, MouseListener {
	static boolean go;
	private int lastButton;

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (!go)
			if (e.getKeyCode() == KeyEvent.VK_C) {
				for (int x = 0; x < Simulator.maxX; x++) {
					for (int y = 0; y < Simulator.maxY; y++) {
						Main.getSimulation().cells.get(x).get(y).willBeAlive(false);
						Main.getSimulation().cells.get(x).get(y).setAlive(false);
					}
				}
			}
		if (!go)
			if (e.getKeyCode() == KeyEvent.VK_G) {
				if (Cell._grid)
					Cell._grid = false;
				else
					Cell._grid = true;
				for (int x = 0; x < Simulator.maxX; x++) {
					for (int y = 0; y < Simulator.maxY; y++) {
						Main.getSimulation().cells.get(x).get(y).stateChanged(true);
					}
				}
			}
		if (!go)
			if (e.getKeyCode() == KeyEvent.VK_R) {
				Random random = new Random();
				for (int x = 0; x < Simulator.maxX; x++) {
					for (int y = 0; y < Simulator.maxY; y++) {
						Main.getSimulation().cells.get(x).get(y)
								.setAlive(random.nextBoolean());
						Main.getSimulation().cells.get(x).get(y)
								.willBeAlive(random.nextBoolean());
					}
				}
			}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (go) {
				while (Simulator.sync>0){
					Main.getSimulation().drawAll(Main.getMainWindow().getGraphics());
					try {
						Thread.sleep((long) 5);
					} catch (InterruptedException e2) {
						e2.printStackTrace();
					}
				}
				go = false;
				Main.setStartedThreads(false);

			} else {
				Main.set_startime(System.currentTimeMillis());
				Main.set_frames(0);
				Main.setStartedThreads(true);
				go = true;
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getButton() != 0)
			lastButton = e.getButton();
		if (!go) {
			int mx = e.getX() / Cell._size;
			int my = e.getY() / Cell._size;
			if (lastButton == 1)
				Main.getSimulation().cells.get(mx).get(my).willBeAlive(true);
			else
				Main.getSimulation().cells.get(mx).get(my).willBeAlive(false);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		lastButton = e.getButton();
		if (!go) {
			int mx = e.getX() / Cell._size;
			int my = e.getY() / Cell._size;
			if (lastButton == 1)
				Main.getSimulation().cells.get(mx).get(my).willBeAlive(true);
			else
				Main.getSimulation().cells.get(mx).get(my).willBeAlive(false);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}
}
