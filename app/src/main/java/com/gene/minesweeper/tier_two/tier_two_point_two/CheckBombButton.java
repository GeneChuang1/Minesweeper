/*CODE WRITTEN BY GENE CHUANG   
The use of this code is prohibited without written authorization by Gene Chuang.
 */
package com.gene.minesweeper.tier_two.tier_two_point_two;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.gene.minesweeper.R;
import com.gene.minesweeper.tier_two.Tags.Tags;
import com.gene.minesweeper.tier_two.tier_two_point_one.MinesweeperGame;

public class CheckBombButton {

	private Activity activity;

	public CheckBombButton(Activity a) {
		activity = a;
	}

	public String checkBombs(View CheckButton) {
		for (int row = 0; row != (Tags.TOTAL_ROW-1); row++) {
			for (int col = 0; col != (Tags.TOTAL_COLUMNS-1); col++) {
				if (MinesweeperGame.gameBoard[row][col].getHasBeenRevealed() == false
						&& MinesweeperGame.gameBoard[row][col].getHasBomb() == false) {
					revealAllBombs(CheckButton);
					return "lose";
				}
			}
		}
		return "win";
	}

	private void revealAllBombs(View CheckButton) {
		for (int row = 0; row != (Tags.TOTAL_ROW - 1); row++) {
			for (int col = 0; col != (Tags.TOTAL_COLUMNS - 1); col++) {
				if (MinesweeperGame.gameBoard[row][col].getHasBomb() == true) {
					String tile = "row_" + (row) + "_col_" + (col);
					ImageView bombs = (ImageView) activity.findViewById(activity.getResources().getIdentifier(tile, "id",
							activity.getPackageName()));
					bombs.setImageResource(R.mipmap.bomb);
				}
			}
		}
	}
}
