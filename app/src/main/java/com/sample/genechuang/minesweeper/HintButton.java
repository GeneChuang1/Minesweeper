/*CODE WRITTEN BY GENE CHUANG   
The use of this code is prohibited without written authorization by Gene Chuang.
 */
package com.sample.genechuang.minesweeper;

import java.util.Random;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

public class HintButton {

	private Activity activity;
	private TheBoard game;

	public HintButton( Activity a, TheBoard game){
		activity=a;
		this.game= game;
	}
	
	public void hint(View arg0) {
		Random random = new Random();
		boolean isShown = false;
		int counter= 0;

		while (!isShown && counter!= game.getSizeOfBoard()) {
			int randomRow = random.nextInt(TheBoard.TOTAL_ROW-1); // The range returned is from 0 to 8
			int randomCol = random.nextInt(TheBoard.TOTAL_COLUMNS-1);

			if (TheBoard.gameBoard[randomRow][randomCol].getHasBomb() == true) {
			} else if (TheBoard.gameBoard[randomRow][randomCol].getHasBomb() == false
					&& TheBoard.gameBoard[randomRow][randomCol].getHasBeenRevealed() == false) {
				reveal(randomRow, randomCol);
				isShown = true;
			}
            counter++;
		}
	}
	
	private void reveal(int row, int column) {
		String tile = "row_" + (row) + "_col_" + (column);
		ImageView image = (ImageView) activity.findViewById(activity.getResources()
				.getIdentifier(tile, "id", activity.getPackageName()));

		//Figures out which drawable picture to show
		if (TheBoard.gameBoard[row][column].getNumSurroundingBombs() == 0) {
			image.setImageResource(R.mipmap.blank);
			TheBoard.gameBoard[row][column].setHasBeenRevealed(true);
		} else if (TheBoard.gameBoard[row][column].getNumSurroundingBombs() == 1) {
			image.setImageResource(R.mipmap.one);
			TheBoard.gameBoard[row][column].setHasBeenRevealed(true);
		} else if (TheBoard.gameBoard[row][column].getNumSurroundingBombs() == 2) {
			image.setImageResource(R.mipmap.two);
			TheBoard.gameBoard[row][column].setHasBeenRevealed(true);
		} else if (TheBoard.gameBoard[row][column].getNumSurroundingBombs() == 3) {
			image.setImageResource(R.mipmap.three);
			TheBoard.gameBoard[row][column].setHasBeenRevealed(true);
		} else if (TheBoard.gameBoard[row][column].getNumSurroundingBombs() == 4) {
			image.setImageResource(R.mipmap.four);
			TheBoard.gameBoard[row][column].setHasBeenRevealed(true);
		} else if (TheBoard.gameBoard[row][column].getNumSurroundingBombs() == 5) {
			image.setImageResource(R.mipmap.five);
			TheBoard.gameBoard[row][column].setHasBeenRevealed(true);
		} else if (TheBoard.gameBoard[row][column].getNumSurroundingBombs() == 6) {
			image.setImageResource(R.mipmap.six);
			TheBoard.gameBoard[row][column].setHasBeenRevealed(true);
		} else if (TheBoard.gameBoard[row][column].getNumSurroundingBombs() == 7) {
			image.setImageResource(R.mipmap.seven);
			TheBoard.gameBoard[row][column].setHasBeenRevealed(true);
		}
	}

}
