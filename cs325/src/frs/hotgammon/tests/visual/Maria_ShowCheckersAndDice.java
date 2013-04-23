package frs.hotgammon.tests.visual;

import java.util.HashMap;

import minidraw.standard.*;
import minidraw.framework.*;

import javax.swing.*;

import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.Game;
import frs.hotgammon.variants.factories.AlphaMonFactory;
import frs.hotgammon.variants.factories.BetaMonFactory;
import frs.hotgammon.variants.factories.DeltaMonFactory;
import frs.hotgammon.view.DieRollTool;
import frs.hotgammon.view.HotGammonDrawing;
import frs.hotgammon.view.HotGammonTool;
import frs.hotgammon.view.MoveTool;

/** Show the dice and some checkers on the
 * backgammon board.  
 * 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
 */
public class Maria_ShowCheckersAndDice {
  
  public static void main(String[] args) {
    DrawingEditor editor = 
      new MiniDrawApplication( "Show HotGammon figures...",  
                               new HotGammonFactory() );
    editor.open();
        
    //Game creation && AddObserver
    Game game = new GameImpl(new BetaMonFactory());//new AlphaMonFactory());//new StubGame1();//new DeltaMonFactory()
    game.addObserver((HotGammonDrawing)editor.drawing());
    ((HotGammonDrawing) editor.drawing()).setGame(game);
    //
    
    //HotGammonTool Setup
    final Tool dieRollTool = new DieRollTool(editor,game);
    final Tool moveTool = new MoveTool(editor,game);
    HashMap<String, Tool> states = new HashMap<String, Tool>(){{
		put( HotGammonTool.DIE_ROLL_TOOL, dieRollTool );
		put( HotGammonTool.MOVE_TOOL, moveTool );
		}};
	//
    
	//Add tool to Editor
    editor.setTool( 
    		new HotGammonTool(editor,game, HotGammonTool.DIE_ROLL_TOOL, states) );
    //
    
    // Start Game : 
    game.newGame();
    game.nextTurn();
    //
    
  }
}

class HotGammonFactory implements Factory {
  public DrawingView createDrawingView( DrawingEditor editor ) {
    DrawingView view = 
      new StdViewWithBackground(editor, "board");
    return view;
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    return new HotGammonDrawing(editor);
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    JTextField statusField = new JTextField( "Hello HotGammon..." );
    statusField.setEditable(false);
    return statusField;
  }
}


