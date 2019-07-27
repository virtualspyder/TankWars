package general_logic;

import java.util.Observable;

public class GameEvents extends Observable {
	
	public String event;
	public int which_player;
	
	public void makeEvent(String msg, int player_idx) 
	{	
		event = msg;
		which_player = player_idx;
		
		setChanged();
		
		notifyObservers(this);	
	}
}
