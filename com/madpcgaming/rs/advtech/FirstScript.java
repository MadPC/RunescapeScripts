package com.madpcgaming.rs.advtech;

import java.awt.Graphics;

import org.osbot.script.Script;
import org.osbot.script.ScriptManifest;
import org.osbot.script.rs2.model.RS2Object;

@ScriptManifest(author = "advtech", info = "First Script", name = "First Script", version = 0)
public class FirstScript extends Script {
	
	private enum State {
		MINE, DROP
	};
	
	public State getState() {
		if(client.getInventory().isFull())
			return State.DROP;
		return State.MINE;
	}

	public void onStart() {
		log("this is a test log");
	}

	public void onPaint(Graphics g) {

	}
	
	public int onLoop() throws InterruptedException{
		switch(getState()) {
		case MINE:
				if(!myPlayer().isAnimating())
				{
					RS2Object vein = closestObjectForName("Rocks");
					if(vein != null)
					{
						vein.interact("Mine");
					}
				}
				break;
		case DROP:
			client.getInventory().dropAll();
			break;
		}
		
		return random(200,300);
	}
	
	public void onExit()
	{
		log("Thanks for using my script");
	}

}
