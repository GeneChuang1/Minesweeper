package com.gene.minesweeper.tier_two.tier_two_point_two;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gene.minesweeper.R;
import com.gene.minesweeper.tier_two.Tags.Tags;
import com.gene.minesweeper.tier_two.tier_two_point_one.MinesweeperGame;

import java.util.LinkedList;
import java.util.Queue;

/*CODE WRITTEN BY GENE CHUANG
The use of this code is prohibited without written authorization by Gene Chuang.
 */
public class TileClicked_Recursive {
    private static final String Tracking = "Tracking";

    Activity activity;
    int[][] alreadyChecked = new int[Tags.TOTAL_ROW][Tags.TOTAL_COLUMNS];
    String[] checkedBlanks = new String[100];
    int counter = 0;
    Queue<String> blanksQueue = new LinkedList<String>();
    public AlertDialog.Builder alert;
    ImageView mImage;

    public TileClicked_Recursive(Activity a) {
        activity = a;
    }

    public void mClickMethod(View arg0) {
        String str = activity.getResources().getResourceEntryName(arg0.getId());
        Log.i(Tracking, "arg0.getId()= " + arg0.getId());
        Log.i(Tracking, "str= " + str);
        char[] charArray = str.toCharArray();
        int row = (Character.getNumericValue(charArray[4]));
        int column = (Character.getNumericValue(charArray[10]));
        mImage = (ImageView) activity.findViewById(arg0.getId());

        if (MinesweeperGame.gameBoard[row][column].getHasBomb() == true) {
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
            recursiveReveal(row, column);
        }
    }


    private void recursiveReveal(int row, int column) {
        Log.i(Tracking, "Inside recursiveReveal(row=" + row + ", col= " + column + ")");

        // Center (The tile the user clicked on)
        boolean hasNotBeenRevealed = !(MinesweeperGame.gameBoard[row][column].getHasBeenRevealed()); //The tile hasn't been revealed= !(false)= true. Thus if(true)
        Log.i(Tracking, "Center");
        int revealedValue = reveal(row, column);
        if (revealedValue == 0) {  //If it's a blank tile.
            // Center Left
            int clRow = row;
            int clColumn = (column - 1);
            if (clColumn >= 0) {  //Boundary check.
                Log.i(Tracking, "Center Left");
                hasNotBeenRevealed = !(MinesweeperGame.gameBoard[clRow][clColumn].getHasBeenRevealed()); //The tile hasn't been revealed= !(false)= true. Thus if(true)
                if (hasNotBeenRevealed) {
                    revealedValue = reveal(clRow, clColumn);
                    if (revealedValue == 0) {
                        recursiveReveal(clRow, clColumn);
                    }
                }
            }
            // Center Right
            int crRow = row;
            int crColumn = (column + 1);
            if (crColumn <= 7) {  //Board's Boundary Check
                Log.i(Tracking, "Center Right");
                hasNotBeenRevealed = !(MinesweeperGame.gameBoard[crRow][crColumn].getHasBeenRevealed());
                if (hasNotBeenRevealed) {
                    revealedValue = reveal(crRow, crColumn);
                    if (revealedValue == 0) {
                        recursiveReveal(crRow, crColumn);
                    }
                }
            }
            // Top Left
            int tlRow = (row - 1);
            int tlColumn = (column - 1);
            if (tlRow >= 0 && tlColumn >= 0) { //Boundary Check
                Log.i(Tracking, "Top Left");
                hasNotBeenRevealed = !(MinesweeperGame.gameBoard[tlRow][tlColumn].getHasBeenRevealed());
                if (hasNotBeenRevealed) {
                    revealedValue = reveal(tlRow, tlColumn);
                    if (revealedValue == 0) {
                        recursiveReveal(tlRow, tlColumn);
                    }
                }
            }
            // Top Center
            int tcRow = (row - 1);
            int tcColumn = column;
            if (tcRow >= 0) {
                Log.i(Tracking, "Top Center");
                hasNotBeenRevealed = !(MinesweeperGame.gameBoard[tcRow][tcColumn].getHasBeenRevealed()); //The tile hasn't been revealed= !(false)= true. Thus if(true)
                if (hasNotBeenRevealed) {
                    revealedValue = reveal(tcRow, tcColumn);
                    if (revealedValue == 0) {
                        recursiveReveal(tcRow, tcColumn);
                    }
                }
            }
            // Top Right
            int trRow = (row - 1);
            int trColumn = (column + 1);
            if (trRow >= 0 && trColumn <= 7) {  //Boundary Check
                Log.i(Tracking, "Top Right");
                hasNotBeenRevealed = !(MinesweeperGame.gameBoard[trRow][trColumn].getHasBeenRevealed()); //The tile hasn't been revealed= !(false)= true. Thus if(true)
                if (hasNotBeenRevealed) {
                    revealedValue = reveal(trRow, trColumn);
                    if (revealedValue == 0) {
                        recursiveReveal(trRow, trColumn);
                    }
                }
            }
            // Bottom Left
            int blRow = (row + 1);
            int blColumn = (column - 1);
            if (blRow <= 7 && blColumn >= 0) {  //Boundary Check
                Log.i(Tracking, "Bottom Left");
                hasNotBeenRevealed = !(MinesweeperGame.gameBoard[blRow][blColumn].getHasBeenRevealed()); //The tile hasn't been revealed= !(false)= true. Thus if(true)
                if (hasNotBeenRevealed) {
                    revealedValue = reveal(blRow, blColumn);
                    if (revealedValue == 0) {
                        recursiveReveal(blRow, blColumn);
                    }
                }
            }
            // Bottom Center
            int bcRow = (row + 1);
            int bcColumn = column;
            if (bcRow <= 7) { //Boundary Check
                Log.i(Tracking, "Bottom Center");
                hasNotBeenRevealed = !(MinesweeperGame.gameBoard[bcRow][bcColumn].getHasBeenRevealed()); //The tile hasn't been revealed= !(false)= true. Thus if(true)
                if (hasNotBeenRevealed) {
                    revealedValue = reveal(bcRow, bcColumn);
                    if (revealedValue == 0) {
                        recursiveReveal(bcRow, bcColumn);
                    }
                }
            }
            // Bottom Right
            int brRow = (row + 1);
            int brColumn = (column + 1);
            if (brRow <= 7 && brColumn <= 7) {  //Board's boundary check
                Log.i(Tracking, "Bottom Right");
                hasNotBeenRevealed = !(MinesweeperGame.gameBoard[brRow][brColumn].getHasBeenRevealed()); //The tile hasn't been revealed= !(false)= true. Thus if(true)
                if (hasNotBeenRevealed) {
                    revealedValue = reveal(brRow, brColumn);
                    if (revealedValue == 0) {
                        recursiveReveal(brRow, brColumn);
                    }
                }
            }
        }
    }

    private int reveal(int row, int column) {
        String tile = "row_" + (row) + "_col_" + (column);
        ImageView image = (ImageView) activity.findViewById(activity.getResources()
                .getIdentifier(tile, "id", activity.getPackageName()));

        if (MinesweeperGame.gameBoard[row][column].getNumSurroundingBombs() == 0) {
            image.setImageResource(R.mipmap.blank);
            MinesweeperGame.gameBoard[row][column].setHasBeenRevealed(true);
            return 0;
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
        return -1;
    }

}
