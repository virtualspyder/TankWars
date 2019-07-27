package general_logic;

import java.awt.Component;
import java.awt.Image;
import java.util.HashMap;
import java.net.URL;

import core_logic.Main;
import tank.TankPlayer;

// This class holds the collection of all loaded game sprites. Other classes will need these sprites.
public class GameSprites extends Component  
{
	private HashMap<String, Image> sprite_collection = new HashMap<String, Image>();
	public static Image grass_tile;

	private void loadSprite(String resource, String name) 
	{		
		final String home_dir = "Resources/";
		String resource_file = home_dir + resource;
		
		URL url = TankPlayer.class.getResource(resource_file);

		if(url == null) {
			Main.ErrorLog("Fatal error: A resource file could not be loaded during program initialization. '" + resource_file + "'");						
		}
		
		Image img = getToolkit().getImage(url);
		sprite_collection.put(name, img);		
	}
	
	public Image get(String sprite_name)
	{
		Image img = sprite_collection.get(sprite_name);		
	
		if(img == null)
		{
			Main.ErrorLog("Fatal error: Attempting to retrieve a loaded sprite which does not exist. '" + sprite_name + "'");
			System.exit(100);						
		}	
		
		return img;
	}
		
	public GameSprites()
	{

		 loadSprite("ImgTank/Tank_1.png", "Tank 1");
		 loadSprite("ImgTank/Tank_2.png", "Tank 2");
		 loadSprite("ImgTerran/Grass.png", "Grass");
		 loadSprite("ImgWall/Unbreakable.png", "Unbreakable Wall");
		 loadSprite("ImgWall/Brick.png", "Brick Wall");
		 loadSprite("ImgWall/Brick_Destroyed_1.png", "Brick Wall Destroyed 1");
		 loadSprite("ImgWall/Brick_Destroyed_2.png", "Brick Wall Destroyed 2");
		 loadSprite("ImgWall/Brick_Destroyed_3.png", "Brick Wall Destroyed 3");
		 loadSprite("ImgWall/Fortified_Wall.png", "Fortified Wall");
		 loadSprite("ImgWall/Fortified_Wall_Damaged.png", "Fortified Wall Damaged");
		 loadSprite("ImgWall/Fortified_Wall_Destroyed_1.png", "Fortified Wall Destroyed 1");
		 loadSprite("ImgWall/Fortified_Wall_Destroyed_2.png", "Fortified Wall Destroyed 2");
		 loadSprite("ImgWall/Fortified_Wall_Destroyed_3.png", "Fortified Wall Destroyed 3");
		 loadSprite("ImgIcon/Good_Health.png", "Good Health");
		 loadSprite("ImgIcon/Lost_Health.png", "Lost Health");
		 
		 GameSprites.grass_tile = this.get("Grass");
	}
}
