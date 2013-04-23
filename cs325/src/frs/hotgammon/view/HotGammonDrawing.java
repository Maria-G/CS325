package frs.hotgammon.view;

import java.awt.Point;

import minidraw.framework.DrawingEditor;
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
	private DrawingEditor editor;
		
	public HotGammonDrawing(DrawingEditor editor){
		this.editor = editor;
	}
	
	public void setGame(Game subject){
		this.subject = subject;
	}
	
	@Override
	public void checkerMove(Location from, Location to) {

		Point pFrom = Convert.locationAndCount2xy(from, subject.getCount(from));// + 1);
		Point pTo = Convert.locationAndCount2xy(to, subject.getCount(to) - 1);
		
		lock();
		
	    Figure clickedFig = findFigure(pFrom.x, pFrom.y);
	    
	    unlock();
	    
	    if(!isChecker(clickedFig) && (from.equals(Location.R_BEAR_OFF) || from.equals(Location.B_BEAR_OFF))){
	    	Color color = subject.getColor(to);
	    	clickedFig = new CheckerFigure(color, pFrom);
			add(clickedFig);	
	    }

	    if(!from.equals(to)){
	    	clickedFig.moveBy(pTo.x - pFrom.x, pTo.y - pFrom.y);
	    }

		if(!(from.equals(Location.R_BEAR_OFF) || from.equals(Location.B_BEAR_OFF))  && this.subject.getNumberOfMovesLeft() == 0){
			((HotGammonTool) this.editor.tool()).setState(HotGammonTool.DIE_ROLL_TOOL);
		}
	}
	
	private boolean isChecker(Figure fig){
		return (fig != null && fig instanceof CheckerFigure);
	}

	@Override
	public void diceRolled(int[] values) {
		for(int i = 0; i < values.length; i++){
			addDie(values[i]);
		}
		((HotGammonTool) this.editor.tool()).setState(HotGammonTool.MOVE_TOOL);
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

	@Override
	public void setStatus(String status) {
		this.editor.showStatus(status);
	}
	

}
