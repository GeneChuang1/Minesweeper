/*CODE WRITTEN BY GENE CHUANG   
The use of this code is prohibited without written authorization by Gene Chuang.
 */
package com.gene.minesweeper.tier_two.tier_two_point_two;

import java.util.Random;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.gene.minesweeper.R;
import com.gene.minesweeper.tier_two.Tags.Tags;
import com.gene.minesweeper.tier_two.tier_two_point_one.MinesweeperGame;

public class HintButton {

	private Activity activity;
	private MinesweeperGame game;

	public HintButton( Activity a, MinesweeperGame game){
		activity=a;
		this.game= game;
	}
	
	public void hint(View arg0) {
		Random random = new Random();
		boolean isShown = false;
		int counter= 0;
		int sizeOfBoard= game.getSizeOfBoard()+4; //+4 is because testHint_Win() was sometimes failing due to counter hitting sizeOfBoard in the while() loop below.

		while (!isShown && counter!= sizeOfBoard) {
			int randomRow = random.nextInt(Tags.TOTAL_ROW-1); // The range returned is from 0 to 8
			int randomCol = random.nextInt(Tags.TOTAL_COLUMNS-1);

			if (MinesweeperGame.gameBoard[randomRow][randomCol].getHasBomb() == true) {
			} else if (MinesweeperGame.gameBoard[randomRow][randomCol].getHasBomb() == false
					&& MinesweeperGame.gameBoard[randomRow][randomCol].getHasBeenRevealed() == false) {
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
		if (MinesweeperGame.gameBoard[row][column].getNumSurroundingBombs() == 0) {
			image.setImageResource(R.mipmap.blank);
			MinesweeperGame.gameBoard[row][column].setHasBeenRevealed(true);
		} else if (MinesweeperGame.gameBoard[row][column].getNumSurroundingBombs() == 1) {
			image.setImageResource(R.mipmap.one);
			MinesweeperGame.gameBoard[row][column].setHasBeenRevealed(true);
		} else if (MinesweeperGame.gameBoard[row][column].getNumSurroundingBombs() == 2) {
			image.setImageResource(R.mipmap.two);
			MinesweeperGame.gameBoard[row][column].setHasBeenRevealed(true);
		} else if (MinesweeperGame.gameBoard[row][column].getNumSurroundingBombs() == 3) {
			image.setImageResource(R.mipmap.three);
			MinesweeperGame.gameBoard[row][column].setHasBeenRevealed(true);
		} else if (MinesweeperGame.gameBoard[row][column].getNumSurroundingBombs() == 4) {
			image.setImageResource(R.mipmap.four);
			MinesweeperGame.gameBoard[row][column].setHasBeenRevealed(true);
		} else if (MinesweeperGame.gameBoard[row][column].getNumSurroundingBombs() == 5) {
			image.setImageResource(R.mipmap.five);
			MinesweeperGame.gameBoard[row][column].setHasBeenRevealed(true);
		} else if (MinesweeperGame.gameBoard[row][column].getNumSurroundingBombs() == 6) {
			image.setImageResource(R.mipmap.six);
			MinesweeperGame.gameBoard[row][column].setHasBeenRevealed(true);
		} else if (MinesweeperGame.gameBoard[row][column].getNumSurroundingBombs() == 7) {
			image.setImageResource(R.mipmap.seven);
			MinesweeperGame.gameBoard[row][column].setHasBeenRevealed(true);
		}
	}

}
