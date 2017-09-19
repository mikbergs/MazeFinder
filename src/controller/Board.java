package controller;
import java.util.Observable;

import model.Point;

// Maze should inherit Observable, but Java does not allow multiple inheritance, 
// so i goes here instead. 

public abstract class Board extends Observable {		
	protected int maxRow, maxCol, maxCell;
	
	public Board( int maxRow, int maxCol ) {
		this.maxRow = maxRow;
		this.maxCol = maxCol;
		this.maxCell = maxRow*maxCol; 
	}
	
	int getCellId( Point p ) { 
		return p.row*maxCol + p.col; 
	}
	
	int getRow( int cellId ) { 
		return cellId / maxCol; 
	}
	
	int getCol( int cellId ) { 
		return cellId % maxCol; 
	}
	
	boolean isValid( Point p ) {
		return p.row >= 0 && p.row < maxRow && p.col >= 0 && p.col < maxCol;
	}
}
