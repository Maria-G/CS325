package frs.hotgammon.view;

import java.awt.Point;
import minidraw.standard.ImageFigure;

public class DieFigure extends ImageFigure{

	public DieFigure(int val, Point p){
		super("die" + val, p);
	}
}
