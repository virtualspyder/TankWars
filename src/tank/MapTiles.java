package tank;

import general_logic.CollisionDetector;
import general_logic.GameSprites;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

import core_logic.Tank;

public class MapTiles
{
	public static final int TILE_SIZE = 32;

	static public class Tile
	{
		int pos_X = 0, pos_Y = 0;
		public Image tile_sprite = null;

		public boolean isWalkable = false;

		Tile(int tile_X, int tile_Y)
		{
			pos_X = tile_X * TILE_SIZE;
			pos_Y = tile_Y * TILE_SIZE;
		}

		void update(Tank this_game)
		{
			if(isWalkable == true) { return; }

			Tank tank = this_game;

			for(int tank_id = 0; tank_id < 2; tank_id++)
			{
				TankPlayer player = tank.property.players[tank_id];

				if(CollisionDetector.check(
						this.pos_X, this.pos_Y, TILE_SIZE, TILE_SIZE,
						player.getX(), player.getY(), player.getSpriteWidth(), player.getSpriteHeight()
				))
				{
					tank.property.events_receiver.makeEvent("TankCollision", tank_id);
				}
			}
		}

		void draw(Graphics2D g2d, ImageObserver obs)
		{
			tile_sprite = GameSprites.grass_tile;

			g2d.drawImage(tile_sprite, pos_X, pos_Y, obs);
		}
	}

	static public class EmptyTile extends Tile
	{
		public EmptyTile(int tile_X, int tile_Y)
		{
			super(tile_X, tile_Y);

			isWalkable = true;
		}
	}

	public static Tile constructOneTile(Tile obj, int tile_Y, int tile_X, int which_type)
	{
		if(obj != null)
		{
			return obj;
		}

		Tile tile = null;
		switch(which_type)
		{
			case TankMap.TILE_NONE:
				tile = new EmptyTile(tile_X, tile_Y);
				break;
		}

		obj = tile;
		return obj;
	}

	public static void constructTile(Tile map_tiles[][], int tile_Y, int tile_X, int which_type) {
		map_tiles[tile_Y][tile_X] = constructOneTile(map_tiles[tile_Y][tile_X], tile_Y, tile_X, which_type);
	}

}