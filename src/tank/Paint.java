package tank;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import core_logic.Tank;

public class Paint extends Component implements Runnable
{
	private Tank tank = null;

	public class ReturnValues
	{
		Graphics2D g2d;
		BufferedImage bi_obj;
	}
	ReturnValues return_values = new ReturnValues();

	public Graphics2D g2d_off_screen;
	public BufferedImage bi_map, bi_final;

	public Paint(Tank this_game) {
		tank = this_game;
	}
	public Thread paint_thread = new Thread(this);

	@Override
	public void run()
	{
		while(true)
		{
			try {
				tank.repaint();
				paint_thread.sleep(16);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void getReady() {
		paint_thread.start();
	}

	private void createMap(int w, int h, Graphics2D g2d, BufferedImage bi_obj) // Width, height
	{
		if(bi_obj == null || (bi_obj.getWidth() != w || bi_obj.getWidth() != h)) {
			bi_obj = (BufferedImage) tank.createImage(w, h);
		}

		g2d = bi_obj.createGraphics();
		g2d.setColor(tank.getBackground());

		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.clearRect(0, 0, w, h);

		return_values.g2d = g2d;
		return_values.bi_obj = bi_obj;
	}

	private void renderEntireMap(BufferedImage map_obj)
	{
		TankMap map = tank.property.map;

		int x, y;
		for(y = 0; y < map.num_rows; y++)
		{
			for(x = 0; x < map.num_columns; x++)
			{
				map.map_tiles[y][x].update(tank);
				map.map_tiles[y][x].draw(g2d_off_screen, this);
			}
		}

	}

	public void startPainting(Graphics g_painter) // The graphics object of the main window.
	{
		TankMap map = tank.property.map;
		Dimension d = tank.getSize();

		createMap(map.getWidth(), map.getHeight(), g2d_off_screen, bi_map); //white screen without

		// Function called, return values are retrieved.
		g2d_off_screen = return_values.g2d;
		bi_map = return_values.bi_obj;

		g2d_off_screen.rotate(0.0, 0, 0);

		renderEntireMap(bi_map);

		g2d_off_screen.dispose();

		createMap(d.width, d.height, g2d_off_screen, bi_final);
		g2d_off_screen = return_values.g2d;
		bi_final = return_values.bi_obj;

		g2d_off_screen.drawImage(bi_map, 0, 0, map.getWidth(), map.getHeight(), this); // Player 1's view


		if(tank.property.players[Tank.IDX_P1].isDestroyed()) {
			g2d_off_screen.drawString("P1 Remaining lives: " + tank.property.players[Tank.IDX_P1].num_lives, 90, 180);
		}

		g2d_off_screen.dispose();
		g_painter.drawImage(bi_final, 0, 0, this);
	}

}