package general_logic;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import core_logic.Tank;

// This is a reusable class. List and handle all necessary keyboard codes.
public class KeyControl extends KeyAdapter {
	
	Tank tank = null;
	
	public KeyControl(Tank this_game) {
		tank = this_game;
	}
	
	// Player 1 Tank: WASD to move & rotate, (Z or C) to shoot. X to fire a super bullet.
	// Player 2 Tank: UHJK to move & rotate, (B or M) to shoot. N to fire a super bullet.
	
	// Esc: Exit the game. Enter: Restart the game if a winner is concluded.
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		switch(key)
		{
			case KeyEvent.VK_W:    tank.property.players[Tank.IDX_P1].key_requests.up = true;    break;
			case KeyEvent.VK_A:    tank.property.players[Tank.IDX_P1].key_requests.left = true;    break;
			case KeyEvent.VK_S:    tank.property.players[Tank.IDX_P1].key_requests.down = true;    break;
			case KeyEvent.VK_D:    tank.property.players[Tank.IDX_P1].key_requests.right = true;    break;

			case KeyEvent.VK_Z:    tank.property.players[Tank.IDX_P1].key_requests.shoot = true;    break;
			case KeyEvent.VK_C:    tank.property.players[Tank.IDX_P1].key_requests.shoot = true;    break;
			case KeyEvent.VK_X:    tank.property.players[Tank.IDX_P1].key_requests.super_shoot = true;    break;

			case KeyEvent.VK_U:    tank.property.players[Tank.IDX_P2].key_requests.up = true;    break;
			case KeyEvent.VK_H:    tank.property.players[Tank.IDX_P2].key_requests.left = true;    break;
			case KeyEvent.VK_J:    tank.property.players[Tank.IDX_P2].key_requests.down = true;    break;
			case KeyEvent.VK_K:    tank.property.players[Tank.IDX_P2].key_requests.right = true;    break;

			case KeyEvent.VK_B:    tank.property.players[Tank.IDX_P2].key_requests.shoot = true;    break;
			case KeyEvent.VK_M:    tank.property.players[Tank.IDX_P2].key_requests.shoot = true;    break;
			case KeyEvent.VK_N:    tank.property.players[Tank.IDX_P2].key_requests.super_shoot = true;    break;
			
			case KeyEvent.VK_ESCAPE:    System.exit(0);    break;
		//	case KeyEvent.VK_ENTER:    if(tank.isGameEnded()) { tank.restartGame(); }    break;			
		}
	}

	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		switch(key)
		{
			case KeyEvent.VK_W:    tank.property.players[Tank.IDX_P1].key_requests.up = false;    break;
			case KeyEvent.VK_A:    tank.property.players[Tank.IDX_P1].key_requests.left = false;    break;
			case KeyEvent.VK_S:    tank.property.players[Tank.IDX_P1].key_requests.down = false;    break;
			case KeyEvent.VK_D:    tank.property.players[Tank.IDX_P1].key_requests.right = false;    break;
	
			case KeyEvent.VK_Z:    tank.property.players[Tank.IDX_P1].key_requests.shoot = false;    break;
			case KeyEvent.VK_C:    tank.property.players[Tank.IDX_P1].key_requests.shoot = false;    break;
			case KeyEvent.VK_X:    tank.property.players[Tank.IDX_P1].key_requests.super_shoot = false;    break;

			case KeyEvent.VK_U:    tank.property.players[Tank.IDX_P2].key_requests.up = false;    break;
			case KeyEvent.VK_H:    tank.property.players[Tank.IDX_P2].key_requests.left = false;    break;
			case KeyEvent.VK_J:    tank.property.players[Tank.IDX_P2].key_requests.down = false;    break;
			case KeyEvent.VK_K:    tank.property.players[Tank.IDX_P2].key_requests.right = false;    break;

			case KeyEvent.VK_B:    tank.property.players[Tank.IDX_P2].key_requests.shoot = false;    break;
			case KeyEvent.VK_M:    tank.property.players[Tank.IDX_P2].key_requests.shoot = false;    break;
			case KeyEvent.VK_N:    tank.property.players[Tank.IDX_P2].key_requests.super_shoot = false;    break;
		}
	}
	
}
