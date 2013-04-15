package frs.hotgammon.view;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.Location;

import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.AbstractTool;

public class MoveTool extends AbstractTool{

	private Game game;
	private DrawingEditor editor;
	private Point originalPt;
	private Figure clickedFig;
	
	public MoveTool(DrawingEditor editor, Game game) {
		super(editor);
		this.editor = editor;
		this.game = game;
	}

	public void mouseDown(MouseEvent e, int x, int y) {
	    super.mouseDown(e, x, y);	    
	    
	    Drawing model = editor.drawing();
	    model.lock();

	    clickedFig = model.findFigure(e.getX(), e.getY());

	    model.unlock();
	    
	    if (isCheckerFigure(clickedFig)){
	    	originalPt = new Point(e.getX(), e.getY());//x,y);
	    }
	  }
	  
	public void mouseUp(MouseEvent e, int x, int y) { 
		
		if ( isCheckerFigure(clickedFig) ){
			Location from = Convert.xy2Location(originalPt.x, originalPt.y);
			Location to = Convert.xy2Location(x, y);
			System.out.println("IS CHECKER FIGURE ::::::::::::..............");
			System.out.println(game.getPlayerInTurn().toString());
			System.out.println(((CheckerFigure) clickedFig).getColor().toString());
			System.out.println("From: " + from.toString() + " to: " + to.toString());
			System.out.println("Move method begin ---------------------------");
			game.move(from, to);
			System.out.println("Move method over-----------------------------");
		}
		
		

	}
	
	private boolean isCheckerFigure(Figure f){
		return f != null && (f instanceof CheckerFigure);
	}
	
	

}
