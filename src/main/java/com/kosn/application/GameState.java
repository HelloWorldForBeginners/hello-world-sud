package com.kosn.application;

public class GameState {

	private static GameState instance = null;
	protected GameState() {
	}
	
	public static GameState getInstance() {
		if(instance == null) {
			instance = new GameState();
		}
		return instance;
	}
	
    private boolean playing = true;

	public boolean getPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
}
