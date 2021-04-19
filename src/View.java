import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.w3c.dom.css.CSSImportRule;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

class View extends JPanel{
	private static final int d = 40; 
	private static final long serialVersionUID = 01111111111L;
	private int width;
	private int height;
	private int cellSize;
	private Point mousePos;
	private Point hoverPos = new Point(-1,-1);
	private int[][] grid;
	Image black_chess;
	Image white_chess;

	View(int width, int height, int cellSize, int[][] grid, Point mousePos){
		this.width = width;
		this.height = height;
		this.cellSize = cellSize;
		this.grid = grid;
		this.mousePos = mousePos;
		
		
		try {
			this.black_chess = ImageIO.read(new File("D:/EclipseWorkspace/Game_Gobang/bin/black.png"));
			this.white_chess = ImageIO.read(new File("D:/EclipseWorkspace/Game_Gobang/bin/white.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setBackground(new Color(0xffdd00));
	}
	
	public int getCellSize(){
		return this.cellSize;
	}

	@Override
	public void paint(Graphics g){
		super.paint(g);
		
		for ( int row = 0; row<height; row++ ) {
			int y = cellSize/2+cellSize*row;
			g.drawLine(cellSize/2, y, (width)*cellSize-cellSize/2, y);
		}
		
		for ( int col = 0; col<width; col++ ) {
			int x = cellSize/2+cellSize*col;
			g.drawLine(x, cellSize/2, x, (height)*cellSize-cellSize/2);
		}
		hoverPos = new Point(-1,-1);
		for ( int row = 0; row<height; row++ ) {
			for ( int col = 0; col<width; col++ ) {
				int x = cellSize/2+cellSize*col;
				int y = cellSize/2+cellSize*row;
				if (Point.distance(x, y, mousePos.getX(), mousePos.getY()) < 20) {
					
					hoverPos.move(row, col);
				}
				if (grid[col][row] != 0){
//					g.setColor(grid[col][row] % 2 == 1 ? Color.black : Color.white);
//					g.fillArc(x-d/2, y-d/2, d, d, 0, 360);
					g.drawImage(grid[col][row] % 2 == 1 ? black_chess:white_chess,
							x-d/2, y-d/2, d, d, null);
				}
					
			}
			
		}
		
		
		
	
		int cir_x = cellSize/2+cellSize*(int)hoverPos.getY()-d/2;
		int cir_y = cellSize/2+cellSize*(int)hoverPos.getX()-d/2;
		g.drawArc(cir_x, cir_y, d, d, 0, 360);
		
		
		
		g.setColor(Color.BLACK);
		g.setFont(new Font(null, Font.BOLD, 24));
		g.drawString(
				String.format("(%d, %d)",(int)hoverPos.getY()+1, height - (int)hoverPos.getX()) 
				, 0, (cellSize+1)*height);
		
		g.drawString("Ö´×Ó·½£º", 600, (cellSize+2)*height);
		
		int max_v = 0;
		for ( int x=0; x<width; x++ ) {
			for ( int y=0; y<height; y++ ) {
				max_v = max_v>grid[x][y] ? max_v : grid[x][y];
			}
		}
		Image zzf = max_v%2==1 ? this.white_chess : this.black_chess;
		g.drawImage(zzf, (cellSize-3)*width, (cellSize)*height, d, d,  null);
	}
	
	public Point getHoverPos(){
		return this.hoverPos;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension((width)*cellSize, (height)*cellSize+50);
	}
}
