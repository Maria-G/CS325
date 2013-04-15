package frs.hotgammon.view;

import java.awt.event.MouseEvent;

import frs.hotgammon.framework.Game;

import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.AbstractTool;

public class HotGammonTool extends AbstractTool{

	private Tool dieTool;
	private Tool moveTool;
	private Tool currentTool;
	private Game game;
	
	public HotGammonTool(DrawingEditor editor, Game game) {
		super(editor);
		this.game = game;
		this.dieTool = new DieRollTool(editor,game);
		this.moveTool = new MoveTool(editor,game);
	}

	public void mouseUp(MouseEvent e, int x, int y) { 
		
		setTool(e);
	    
	    this.currentTool.mouseUp(e,x,y);
	    
	    editor.showStatus(game.getPlayerInTurn().toString() + " has " + game.getNumberOfMovesLeft() + " moves left..");
		

	}
	
	public void mouseDown(MouseEvent e, int x, int y) {

		setTool(e);
		
	    this.currentTool.mouseDown(e,x,y);
	}
	
	private void setTool(MouseEvent e){
		Drawing model = editor().drawing();
	    
	    model.lock();
	    
	    Figure clickedFig = model.findFigure(e.getX(), e.getY());
		
	    model.unlock();
	    
	    if (isDieFigure(clickedFig)){
	    	this.currentTool = dieTool;
	    }

	    if (isCheckerFigure(clickedFig)){
	    	this.currentTool = moveTool;
	    }
	}
	
	private boolean isCheckerFigure(Figure f){
		return f != null && (f instanceof CheckerFigure);
	}
	
	
	private boolean isDieFigure(Figure f){
		return f != null && (f instanceof DieFigure);
	}

}
