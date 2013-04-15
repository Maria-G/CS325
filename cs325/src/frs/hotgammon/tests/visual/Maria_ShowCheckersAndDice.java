package frs.hotgammon.tests.visual;

import minidraw.standard.*;
import minidraw.framework.*;

import javax.swing.*;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.framework.Game;
import frs.hotgammon.variants.factories.AlphaMonFactory;
import frs.hotgammon.view.HotGammonDrawing;
import frs.hotgammon.view.HotGammonTool;

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
    
    int redDie = 4;
    int blackDie = 2;
    ((HotGammonDrawing) editor.drawing()).addDie(redDie);
    ((HotGammonDrawing) editor.drawing()).addDie(blackDie);
    
    Game game = new GameImpl(new AlphaMonFactory());//new StubGame1();//new DeltaMonFactory()
    game.addObserver((HotGammonDrawing)editor.drawing());
    ((HotGammonDrawing) editor.drawing()).setGame(game);
    
    game.newGame();
    game.nextTurn();
    editor.setTool( 
    		new HotGammonTool(editor,game) );//new MoveTool(editor, game) );//new DieRollTool(editor, game) );

  }
}

class HotGammonFactory implements Factory {
  public DrawingView createDrawingView( DrawingEditor editor ) {
    DrawingView view = 
      new StdViewWithBackground(editor, "board");
    return view;
  }

  public Drawing createDrawing( DrawingEditor editor ) {
    return new HotGammonDrawing();
  }

  public JTextField createStatusField( DrawingEditor editor ) {
    JTextField statusField = new JTextField( "Hello HotGammon..." );
    statusField.setEditable(false);
    return statusField;
  }
}


