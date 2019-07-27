package tank;

import core_logic.Tank;

public class TankMap
{
	public static final int TILE_NONE = 0;

	public int num_rows = 0;
	public int num_columns = 0;

	private int map_width = 0;
	private int map_height = 0;

	TankPlayer players[] = null;
	public MapTiles.Tile map_tiles[][] = null;


	public TankMap(TankPlayer tank_players[])
	{
		players = tank_players;
		createDefaultMap();
	}

	public void computeMapSize()
	{
		map_width = num_columns * MapTiles.TILE_SIZE;
		map_height = num_rows * MapTiles.TILE_SIZE;
	}

	public int getWidth() {
		return map_width;
	}

	public int getHeight() {
		return map_height;
	}

	public void createDefaultMap()
	{
		num_rows = 50;
		num_columns = 50;

		computeMapSize();

		map_tiles = new MapTiles.Tile[num_rows][num_columns];

		int p;
		int tile = TILE_NONE;

		for(int y = 0; y < num_rows; y++) {
			for(int x = 0; x < num_columns; x++) {
				MapTiles.constructTile(map_tiles, y, x, tile);
			}
		}
		players[Tank.IDX_P1].setTankPosition(5, 5);
	}

}