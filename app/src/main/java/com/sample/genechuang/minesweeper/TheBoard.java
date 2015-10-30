/*CODE WRITTEN BY GENE CHUANG   
The use of this code is prohibited without written authorization by Gene Chuang.
 */
package com.sample.genechuang.minesweeper;

import java.util.Random;

public class TheBoard {
	
	final static public int TOTAL_ROW=9;
	final static public int TOTAL_COLUMNS=9;
	//Change this if you want to change how many bombs there are.
	final int TOTAL_BOMBS_INSERTED=9;
		
	//This is the tile board
	static public Tile[][] gameBoard = new Tile[TOTAL_ROW][TOTAL_COLUMNS];

	public int numBombsInsertedCounter;
	
	public TheBoard(){
	}
	
	public void insertBombs() {
		Random random = new Random();
		numBombsInsertedCounter=0;
		
		for (int i = 0; i != (TOTAL_ROW-1); i++) {
			for (int ii = 0; ii != (TOTAL_COLUMNS-1); ii++) {
				boolean hasBomb = false;
				int nm = random.nextInt(100); // The range returned is from 0 to 99.
				if (nm < 19) // Gives it a 19% chance of getting a bomb
					hasBomb = true;

				if (numBombsInsertedCounter <= TOTAL_BOMBS_INSERTED) {
					gameBoard[i][ii] = new Tile(i, ii, hasBomb, 0);
					if (hasBomb == true) {
						numBombsInsertedCounter++;
					}
				} else {
					gameBoard[i][ii] = new Tile(i, ii, false, 0);
				}
			}
		}

		//Tells each of the Tiles to figure out their surroundings. 
		for (int row = 0; row != (TOTAL_ROW-1); row++) {
			for (int column = 0; column != (TOTAL_COLUMNS-1); column++) {
				gameBoard[row][column].setNumSurroundingBombs();
			}
		}
		printTable(gameBoard, "Mine Locations", 1);
		printTable(gameBoard, "Surrouding Bomb Locations", 2);
	}

	// The printTable method prints the table to the console
	private void printTable(Tile[][] table, String nameOfTable, int printFuction) {
		// printFunction: 1= getHasBomb(), 2=getNumSurroundingBombs()
		System.out.println(nameOfTable + " Map");
		String output = "";

		for (int row = 0; row != (TOTAL_ROW-1); row++) {
			for (int col = 0; col != (TOTAL_COLUMNS-1); col++) {
				int booleanToInt = 0;
				switch (printFuction) {
				case 1:
					if (table[row][col].getHasBomb() == true) {
						booleanToInt = 1;
					}
					output += " " + booleanToInt;
					break;
				case 2:
					output += " " + table[row][col].getNumSurroundingBombs();
					break;
				}
			}
			output += "\n";
		}
		System.out.println(output);
	}

	public int getSizeOfBoard(){
		return TOTAL_ROW * TOTAL_COLUMNS;
	}
}
