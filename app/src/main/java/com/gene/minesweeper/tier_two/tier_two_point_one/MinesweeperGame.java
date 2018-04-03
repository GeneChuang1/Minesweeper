/*CODE WRITTEN BY GENE CHUANG   
The use of this code is prohibited without written authorization by Gene Chuang.
 */
package com.gene.minesweeper.tier_two.tier_two_point_one;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import com.gene.minesweeper.tier_three.Tile;
import com.gene.minesweeper.tier_two.tier_two_point_two.CheckBombButton;
import com.gene.minesweeper.tier_two.tier_two_point_two.HintButton;
import com.gene.minesweeper.tier_two.tier_two_point_two.TileClicked;
import com.gene.minesweeper.tier_two.Tags.*;
import com.gene.minesweeper.tier_two.tier_two_point_two.TileClicked_Recursive;

import java.util.Random;

public class MinesweeperGame {
	//This is the tile board
	static public Tile[][] gameBoard = new Tile[Tags.TOTAL_ROW][Tags.TOTAL_COLUMNS];
    //static public ArrayList<ArrayList<Tile>> gameBoard = new ArrayList<ArrayList<Tile>>();

	public int numBombsInsertedCounter;
	private CheckBombButton cb;
	private HintButton hint;
    private TileClicked tileClicked;
    private TileClicked_Recursive tileClickedRecursive;

	public MinesweeperGame(){
	}
	
	public void insertBombs() {
		Random random = new Random();
		numBombsInsertedCounter=0;
		
		for (int i = 0; i != (Tags.TOTAL_ROW-1); i++) {
			for (int ii = 0; ii != (Tags.TOTAL_COLUMNS-1); ii++) {
				boolean hasBomb = false;
				int nm = random.nextInt(100); // The range returned is from 0 to 99.
				if (nm < 19) // Gives it a 19% chance of getting a bomb
					hasBomb = true;

				if (numBombsInsertedCounter <= Tags.TOTAL_BOMBS_INSERTED) {
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
		for (int row = 0; row != (Tags.TOTAL_ROW-1); row++) {
			for (int column = 0; column != (Tags.TOTAL_COLUMNS-1); column++) {
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

		for (int row = 0; row != (Tags.TOTAL_ROW-1); row++) {
			for (int col = 0; col != (Tags.TOTAL_COLUMNS-1); col++) {
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
		return Tags.TOTAL_ROW * Tags.TOTAL_COLUMNS;
	}

	public void checkBombsButtonPressed(View checkBombsButtonView, Activity activity){
		cb = new CheckBombButton(activity);
		String result= cb.checkBombs(checkBombsButtonView);

		if (result.equals("lose")){
			CharSequence text = "Sorry, you didn't find all the bombs!";
			Toast mToast = Toast.makeText(activity.getApplicationContext(), text, Toast.LENGTH_SHORT);
			mToast.show();
		} else if (result.equals("win")){
			CharSequence text = "You found all the bombs! YOU WIN!";
			Toast toast = Toast.makeText(activity.getApplicationContext(), text, Toast.LENGTH_SHORT);
			toast.show();
		}
	}

    public void hintButtonPressed(Activity activity, View view){
        hint= new HintButton(activity, this);
        hint.hint(view);
    }

    public void tileClicked(Activity activity, View view){
        tileClickedRecursive= new TileClicked_Recursive(activity);
        tileClickedRecursive.mClickMethod(view);
		//tileClicked = new TileClicked(activity);
        //tileClicked.mClickMethod(view);
    }
}
