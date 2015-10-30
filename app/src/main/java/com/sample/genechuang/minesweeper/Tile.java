/*CODE WRITTEN BY GENE CHUANG   
The use of this code is prohibited without written authorization by Gene Chuang.
 */
package com.sample.genechuang.minesweeper;

public class Tile {
	private boolean hasBomb;
	private int numSurroundingBombs=0;
	private int row;
	private int column;
	private boolean hasBeenRevealed= false;
	
	public Tile (int mRow, int mColumn, boolean bomb, int SurroundingBombs) {
		row= mRow;
		column= mColumn;
		hasBomb= bomb;
		numSurroundingBombs= SurroundingBombs;
	}
	
	public boolean getHasBomb (){
		return hasBomb;
	}
	
	public int getNumSurroundingBombs (){
		return numSurroundingBombs;
	}

	public boolean getHasBeenRevealed(){
		return hasBeenRevealed;
	}
	
	public void setNumSurroundingBombs(){
		if ((row - 1) >= 0 && (column - 1) >= 0 && (TheBoard.gameBoard[row-1][column-1].getHasBomb()==true)){
			numSurroundingBombs++;}
		if ((row - 1) >= 0 && TheBoard.gameBoard[row-1][column-0].getHasBomb()==true){
			numSurroundingBombs++;}
		if ((row - 1) >= 0 && (column + 1) <= 7
				&& TheBoard.gameBoard[row-1][column+1].getHasBomb()==true){
			numSurroundingBombs++;}
		if ((column - 1) >= 0 && TheBoard.gameBoard[row-0][column-1].getHasBomb()==true){
			numSurroundingBombs++;}
		if (TheBoard.gameBoard[row-0][column-0].getHasBomb()==true){
			numSurroundingBombs++;}
		if ((column + 1) <= 7 && TheBoard.gameBoard[row-0][column+1].getHasBomb()==true){
			numSurroundingBombs++;}
		if ((row + 1) <= 7 && (column - 1) >= 0
				&& TheBoard.gameBoard[row+1][column-1].getHasBomb()==true){
			numSurroundingBombs++;}
		if ((row + 1) <= 7 && TheBoard.gameBoard[row+1][column-0].getHasBomb()==true){
			numSurroundingBombs++;}
		if ((row + 1) <= 7 && (column + 1) <= 7
				&& TheBoard.gameBoard[row+1][column+1].getHasBomb()==true){
			numSurroundingBombs++;}
	}
	
	public void setHasBeenRevealed(boolean reveal){
		hasBeenRevealed= reveal;
	}
}
