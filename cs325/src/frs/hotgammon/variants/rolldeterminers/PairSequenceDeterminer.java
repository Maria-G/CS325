package frs.hotgammon.variants.rolldeterminers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import frs.hotgammon.RollDeterminer;
import frs.hotgammon.framework.Game;

public class PairSequenceDeterminer implements RollDeterminer{

	//private List<Integer> diceRoll;
//	private int[] diceRoll = new int[2];
	
	private final int[][] DICE_ROLLS = {  {1,2}, {3,4}, {5,6} };
	private int diceRollIdx = 2;
	
	@Override
	public void rollDice() {
		diceRollIdx = ((diceRollIdx < 2) ? diceRollIdx + 1 : 0);
		//diceRoll = Arrays.asList(DICE_ROLLS[diceRollIdx][0],DICE_ROLLS[diceRollIdx][1]); 
//		diceRoll = DICE_ROLLS[diceRollIdx]; 		
	}

	@Override
	public int[] getDiceRoll() {
		return DICE_ROLLS[diceRollIdx];//diceRoll;
	}

	@Override
	public void reset() {
		diceRollIdx = 2;
//		diceRoll = new int[2];
	}

}
