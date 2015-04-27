package gameOfLife;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Frame extends JFrame {

	private static final long serialVersionUID = -4855592840145135975L;

	public Frame() {
		setUndecorated(true);
		String input= "a";
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		input = JOptionPane.showInputDialog(this,
				"Podaj rozmiar kom�rki\n (Domy�lnie 20)", "Rozmiar", 3);
		System.out.println(input);
		if (input==null) {
			System.exit(0);
		}
		try {
			Cell._size = Integer.parseInt(input);
		} catch (Exception e) {
			Cell._size = 20;
		}
		input = JOptionPane.showInputDialog(this,
				"Podaj ��dany FPS\n (Domy�lnie 20)", "Ilo�� klatek na sekunde",
				3);
		if (input==null) System.exit(0);
		try {
			Main._fps = Float.parseFloat(input);
		} catch (Exception e) {
			Main._fps = 20;
		}
		this.setExtendedState(MAXIMIZED_BOTH);

	}

	public void repaint(Simulator simulation) {
		super.repaint();
		simulation.draw(this.getGraphics());
	}

	public void startDrawing() {
		MouseAndKeyboardHandler handler = new MouseAndKeyboardHandler();
		addKeyListener(handler);
		addMouseListener(handler);
		addMouseMotionListener(handler);
	}

}
