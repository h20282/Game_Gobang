import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;


public class Main {
	public static void main(String[] args) {
		Point mousePos =  new Point();
		 
		final int size = 50;
		final int width = 15;
		final int height = 15;
		int[][] grid = new int[height][width];
		Model model = new Model(grid, width, height);
		View view = new View(width, height, size, grid, mousePos);
		
		JFrame frame = new JFrame();
//		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
//				System.out.println(e.getX() + ", " + e.getY());
				mousePos.move(e.getX(), e.getY()-32);
				frame.repaint();
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {}
		});
		frame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				model.playOn((int)view.getHoverPos().getX(), (int)view.getHoverPos().getY());
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode()==KeyEvent.VK_Z){
					model.regret();
				}
			}
		});
		frame.add(view);
		frame.pack();
		frame.setVisible(true);
		while(true){
			frame.repaint();
			if ( model.get_winner() != -1 ) {
				System.out.println("winner: " + model.get_winner());
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
		}
		
	}
}
