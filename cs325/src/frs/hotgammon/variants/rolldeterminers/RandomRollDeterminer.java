package frs.hotgammon.variants.rolldeterminers;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import frs.hotgammon.Game;
import frs.hotgammon.RollDeterminer;

public class RandomRollDeterminer implements RollDeterminer {

	//private List<Integer> diceRoll;
	private int[] diceRoll = new int[2];
	private final int[] POSSIBLE_DICE_VALUES = {1, 2, 3, 4, 5, 6};
		
	@Override
	public void rollDice() {
		Random r = new Random();
		//diceRoll = Arrays.asList(POSSIBLE_DICE_VALUES[r.nextInt(POSSIBLE_DICE_VALUES.length - 1)],
		//						 POSSIBLE_DICE_VALUES[r.nextInt(POSSIBLE_DICE_VALUES.length - 1)]); 
		diceRoll = new int[]{POSSIBLE_DICE_VALUES[r.nextInt(POSSIBLE_DICE_VALUES.length - 1)],
				 POSSIBLE_DICE_VALUES[r.nextInt(POSSIBLE_DICE_VALUES.length - 1)]}; 			
	}

	@Override
	public int[] getDiceRoll() {
		return diceRoll;
	}

	@Override
	public void reset() {
		diceRoll = new int[2];
	}

}
