package tank;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;

import core_logic.Main;
import core_logic.Tank;

public class TankPlayer implements Observer
{
	public int tank_id;
	public String num_lives;

	private double pos_X;
	private double pos_Y;

	private double pos_X_old;
	private double pos_Y_old;

	double PowerUps_TankSpeed = 1;

	public boolean is_destroyed = false;

	private int sprite_width = 0;
	private int sprite_height = 0;

	public Image tank_sprite;

	// Each 30 angle will cover 90 degree.
	// 120 angle will cover 360 degree.
	public double angle = 0;

	// A low angle factor means a tank can rotate smoothly (just enough so the process is not slow).
	static final int ANGLE_FACTOR = 3;

	public double getX() { return pos_X; }
	public double getY() { return pos_Y; }
	public double getSpriteWidth() { return sprite_width; }
	public double getSpriteHeight() { return sprite_height; }

	public boolean isDestroyed() {
		return is_destroyed;
	}

	public static double getCustomRadian(double angle)
	{
		double radian = Math.toRadians(angle * ANGLE_FACTOR);

		return radian;
	}

	public double getTankRadian() {
		return getCustomRadian(angle);
	}

	public void setTankPosition(int tile_Y, int tile_X)
	{
		pos_X = tile_X * MapTiles.TILE_SIZE;
		pos_Y = tile_Y * MapTiles.TILE_SIZE;
	}

	public class KeyRequests
	{
		public boolean up = false, down = false, left = false, right = false;
		public boolean shoot = false;
		public boolean super_shoot = false;
	}

	public KeyRequests key_requests = new KeyRequests();

	public TankPlayer(int player_id, String sprite_name)
	{
		tank_id = player_id - 1;
		tank_sprite = Main.gameSprites.get(sprite_name);

		ImageIcon icon = new ImageIcon(tank_sprite);

		sprite_width = icon.getIconWidth();
		sprite_height = icon.getIconHeight();
	}


	public void draw(Graphics2D g2d, ImageObserver obs)
	{
		if(isDestroyed()) {
			return;
		}

		AffineTransform no_rotation_config = g2d.getTransform(); // Save this config before rotating the viewpoint.

		g2d.rotate(getTankRadian(), pos_X + (sprite_width / 2), pos_Y + (sprite_height / 2));
		g2d.drawImage(tank_sprite, (int) pos_X, (int) pos_Y, obs);
		// Reset the viewpoint - it must not remain rotated.
		g2d.setTransform(no_rotation_config);
	}


	public void update(Tank tank)
	{

		final double Default_SPD = 2.5;

		pos_X_old = pos_X;
		pos_Y_old = pos_Y;

		double radian = getTankRadian();

		// Left & Right keys are handled first.
		if(key_requests.left == true)
		{
			if(--angle < 0) {
				angle = (360 / ANGLE_FACTOR);
			}
		}
		else if (key_requests.right == true)
		{
			if(++angle > (360 / ANGLE_FACTOR)) {
				angle = 0;
			}
		}

		if(key_requests.up == true)
		{
			pos_X += (Default_SPD * PowerUps_TankSpeed) * Math.sin(radian);
			pos_Y -= (Default_SPD * PowerUps_TankSpeed) * Math.cos(radian);
		}
		else if(key_requests.down == true)
		{
			pos_X -= (Default_SPD * PowerUps_TankSpeed) * Math.sin(radian);
			pos_Y += (Default_SPD * PowerUps_TankSpeed) * Math.cos(radian);
		}
	}

	@Override
	public void update(Observable obs, Object arg1)
	{
			return;
	}
}