package core_logic;

import general_logic.GameEvents;
import general_logic.KeyControl;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JApplet;

//import tank.ExplosionEffect;
import tank.Paint;
//import tank.Power_Up;
//import tank.TankBullet;
import tank.TankMap;
import tank.TankPlayer;

public class Tank extends JApplet {

	// Static variables
	public static final int IDX_P1 = 0;
	public static final int IDX_P2 = 1;

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// This class is created to narrow the number of items to find desired things quicker. (code auto-completion will be faster.)
	public class Property
	{
		public TankPlayer players[] = new TankPlayer[2];

		Property()
		{
			players[IDX_P1] = new TankPlayer(1, "Tank 1");
			players[IDX_P2] = new TankPlayer(2, "Tank 2");

			map = new TankMap(players);

			events_receiver.addObserver(players[IDX_P1]);
			events_receiver.addObserver(players[IDX_P2]);
		}

		public TankMap map = null;
		public Paint paint = null;
		public KeyControl keyboard_listener;
		public GameEvents events_receiver = new GameEvents();

		// A place to keep objects alive
		//						public LinkedList<TankBullet> bullets = new LinkedList<TankBullet>();
		//								public LinkedList<ExplosionEffect> effects = new LinkedList<ExplosionEffect>();
		//								public LinkedList<Power_Up> power_ups = new LinkedList<Power_Up>();
	}

	public Property property = new Property();

	public Tank()
	{
		property.paint = new Paint(this);
		property.keyboard_listener = new KeyControl(this);

		setFocusable(true);
		setBackground(Color.gray);

		addKeyListener(property.keyboard_listener);
	}

	public void paint(Graphics g_painter)
	{
		// To make things convenient, game objects are both updated and drawn at the same time.
		property.paint.startPainting(g_painter);
	}

	public void start()
	{
		property.paint.getReady();
	}

}