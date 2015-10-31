/*CODE WRITTEN BY GENE CHUANG   
The use of this code is prohibited without written authorization by Gene Chuang.
 */
package com.gene.minesweeper.tier_one;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gene.minesweeper.R;
import com.gene.minesweeper.tier_two.tier_two_point_one.MinesweeperGame;

public class StartOfGame extends Activity {
	private MinesweeperGame game;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		game= new MinesweeperGame();
		game.insertBombs();
	}

	//NewGame Button is Pressed
	public void newGame(View arg0) {
		Intent intent = getIntent();
		finish();
		startActivity(intent);
	}
	
	//Check Bombs Button is Pressed
	public void checkBombs(View checkBombsButtonView) {
		game.checkBombsButtonPressed(checkBombsButtonView, this);
	}
	
	//A Tile is Pressed
	public void mClickMethod(View view) {
        game.tileClicked(this, view);
	}

	//Hint Button is Pressed
	public void hint(View view) {
		game.hintButtonPressed(this, view);
    }

	public MinesweeperGame getBoard(){
		return game;
	}
	
}
