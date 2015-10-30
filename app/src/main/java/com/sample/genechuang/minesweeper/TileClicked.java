/*CODE WRITTEN BY GENE CHUANG   
The use of this code is prohibited without written authorization by Gene Chuang.
 */
package com.sample.genechuang.minesweeper;

import java.util.LinkedList;
import java.util.Queue;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

public class TileClicked {

	Activity activity;
	int[][] alreadyChecked = new int[TheBoard.TOTAL_ROW][TheBoard.TOTAL_COLUMNS];
	String[] checkedBlanks = new String[100];
	int counter = 0;
	Queue<String> blanksQueue = new LinkedList<String>();
	public AlertDialog.Builder alert;
	
	public TileClicked(Activity a) {
		activity = a;
	}

	public void mClickMethod(View arg0) {
		String str = activity.getResources().getResourceEntryName(arg0.getId());
		char[] charArray = str.toCharArray();

		int row = (Character.getNumericValue(charArray[4]));
		int column = (Character.getNumericValue(charArray[10]));
		ImageView mImage = (ImageView) activity.findViewById(arg0.getId());

		if (TheBoard.gameBoard[row][column].getHasBomb() == true) {
			mImage.setImageResource(R.mipmap.bomb);

			alert = new AlertDialog.Builder(activity);
			alert.setTitle("BOOM!");
			alert.setMessage("I'm sorry, you lost. Do you want to restart the game?");
			alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog,
	                    int which) {
	        		Intent intent = activity.getIntent();
	        		activity.finish();
	        		activity.startActivity(intent);
	           }
	        });
			alert.setNegativeButton("Cancel", null);
			alert.show();  
			
		} else {
			if (TheBoard.gameBoard[row][column]
					.getNumSurroundingBombs() == 0) {
				mImage.setImageResource(R.mipmap.blank);
				TheBoard.gameBoard[row][column]
						.setHasBeenRevealed(true);

				initializeBlanks(alreadyChecked);
				alreadyChecked[row][column] = 1;
				surroundingBlanks(row, column);
				counter = 0;
				checkedBlanks = null;

				while (!blanksQueue.isEmpty()) {
					String empty_tile = blanksQueue.remove();
					char[] charArray1 = empty_tile.toCharArray();
					int mRow = Character.getNumericValue(charArray1[0]);
					int mColumn = Character.getNumericValue(charArray1[1]);

					if (alreadyChecked[mRow][mColumn] != 1) {
						alreadyChecked[mRow][mColumn] = 1;
						surroundingBlanks(mRow, mColumn);
					}
				}
			} else {
				reveal(row, column);
			}
		}
	} // end of mClickMethod

	private void initializeBlanks(int[][] arrayName) {
		// initialize all the arrayName table elements to zero.
		for (int row = 0; row != (TheBoard.TOTAL_ROW-1); row++) {
			for (int column = 0; column != (TheBoard.TOTAL_COLUMNS-1); column++) {
				arrayName[row][column] = 0;
			}
		}
	}

	private void surroundingBlanks(int actualRow, int actualColumn) {
		// Top Left
		if ((actualRow - 1) >= 0 && (actualColumn - 1) >= 0) {
			showTile(actualRow, actualColumn, -1, -1);
		}
		// Top Center
		if ((actualRow - 1) >= 0) {
			showTile(actualRow, actualColumn, -1, 0);
		}
		// Top Right
		if ((actualRow - 1) >= 0 && (actualColumn + 1) <= 7) {
			showTile(actualRow, actualColumn, -1, +1);
		}
		// Center Left
		if ((actualColumn - 1) >= 0) {
			showTile(actualRow, actualColumn, 0, -1);
		}
		// Center Right
		if ((actualColumn + 1) <= 7) {
			showTile(actualRow, actualColumn, 0, +1);
		}
		// Bottom Left
		if ((actualRow + 1) <= 7 && (actualColumn - 1) >= 0) {
			showTile(actualRow, actualColumn, +1, -1);
		}
		// Bottom Center
		if ((actualRow + 1) <= 7) {
			showTile(actualRow, actualColumn, +1, 0);
		}
		// Bottom Right
		if ((actualRow + 1) <= 7 && (actualColumn + 1) <= 7) {
			showTile(actualRow, actualColumn, +1, +1);
		}
	}

	private void showTile(int actualRow, int actualColumn, int shiftRow,
			int shiftColumn) {
		int rowCheck = actualRow + shiftRow;
		int columnCheck = actualColumn + shiftColumn;
		String tile = "row_" + rowCheck + "_col_" + columnCheck;
		ImageView blank = (ImageView) activity.findViewById(activity
				.getResources().getIdentifier(tile, "id",
						activity.getPackageName()));

		if (TheBoard.gameBoard[rowCheck][columnCheck]
				.getNumSurroundingBombs() == 0) {
			blank.setImageResource(R.mipmap.blank);
			TheBoard.gameBoard[actualRow][actualColumn]
					.setHasBeenRevealed(true);

			String also_blanks = Integer.toString(rowCheck)
					+ Integer.toString(columnCheck);
			blanksQueue.add(also_blanks);
		} else {
			reveal((rowCheck), (columnCheck));
		}
	}

	private void reveal(int row, int column) {
		String tile = "row_" + (row) + "_col_" + (column);
		ImageView image = (ImageView) activity.findViewById(activity.getResources()
				.getIdentifier(tile, "id", activity.getPackageName()));

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
