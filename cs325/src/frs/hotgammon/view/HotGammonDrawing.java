package frs.hotgammon.view;

import java.awt.Point;

import minidraw.framework.Drawing;
import minidraw.framework.Figure;
import minidraw.standard.StandardDrawing;

import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.GameObserver;
import frs.hotgammon.framework.Location;

public class HotGammonDrawing extends StandardDrawing implements GameObserver {

	private DieFigure[] dice = new DieFigure[2];
	private int diceIdx = 0;
	Point[] diePoints = {new Point(216, 202), new Point(306, 202)};
	private Game subject;
		
	public void setGame(Game subject){
		this.subject = subject;
		
	}
	
	@Override
	public void checkerMove(Location from, Location to) {

		//Remove Checker from current location
		Point pFrom = Convert.locationAndCount2xy(from, subject.getCount(from) + 1);
	    Figure clickedFig = findFigure(pFrom.x, pFrom.y);
	    if(isChecker(clickedFig)){
	    	remove(clickedFig);
	    	invalidate();
	    }

	    //Add Checker to new Location
		Point pTo = Convert.locationAndCount2xy(to, subject.getCount(to));
		Color color = subject.getColor(to);
		//Color color = Color.RED;
		addChecker(color, pTo);	
		

	}
	
	private boolean isChecker(Figure fig){
		return (fig != null && fig instanceof CheckerFigure);
	}

	@Override
	public void diceRolled(int[] values) {
		for(int i = 0; i < values.length; i++){
			addDie(values[i]);
		}
	}

	public void addDie(int value) {
		int diceIdxVal = diceIdx % 2;
		
		if ( dice[diceIdxVal] == null ){
			dice[diceIdxVal] = new DieFigure(value, diePoints[diceIdxVal]);
			add(dice[diceIdxVal]);
		}
		else{
			dice[diceIdxVal].set("die" + value, diePoints[diceIdxVal]);
			dice[diceIdxVal].changed();
		}
		diceIdx++;
	}
	
	public void addChecker(Color color, Point pt){
		CheckerFigure cF = new CheckerFigure(color, pt);
		add(cF);
		cF.changed();
	}
	

}
