package com.schneide.abas.ccd.blue.tf;

public class Game {
	
	private Frame[] frames;
	private int currentFrame;

	public Game(String playerName) {
		this.frames = new Frame[10];
		for (int i = 0; i < frames.length; i++) {
			this.frames[i] = new Frame((i == 9) ? 3 : 2);
		}
	}

	public void roll(int pins) {
		frames[currentFrame].rolled(pins);
		if (frames[currentFrame].isComplete()) {
			currentFrame++;
		}
	}

	public int score() {
		int totalScore = 0;
		int currentIndex = 0;
		for (Frame each : frames) {
			totalScore += each.pins();
			if (each.isStrike()) {
				totalScore += frames[currentIndex + 1].pins();
				totalScore += frames[currentIndex + 2].pins();
			}
			currentIndex++;
		}
		return totalScore;
	}
	
	private static class Frame {
		private int[] pins;
		private int index;
		
		public Frame(int rollCount) {
			this.pins = new int[rollCount];
		}

		public void rolled(int pins) {
			this.pins[index] = pins;
			index++;
		}
		
		public boolean isComplete() {
			if (pins.length == 2) {
				for (int each : pins) {
					if (each == 10) {
						return true;
					}
				}
			}
			return (index >= pins.length);
		}
		
		public int pins() {
			int result = 0;
			for (int each : pins) {
				result += each;
			}
			return result;
		}
		
		public boolean isStrike() {
			return (pins[0] == 10);
		}
		
		public boolean isSpare() {
			return (!isStrike() && (10 == pins()));
		}
	}
}
