/*CODE WRITTEN BY GENE CHUANG   
The use of this code is prohibited without written authorization by Gene Chuang.
 */
package com.sample.genechuang.minesweeper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class StartOfGame extends Activity {
	private TheBoard game;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		game= new TheBoard();
		game.insertBombs();
	}

	//NewGame Button is Pressed
	public void newGame(View arg0) {
		Intent intent = getIntent();
		finish();
		startActivity(intent);
	}
	
	//Check Bombs Button is Pressed
	public void checkBombs(View CheckButton) {
		CheckBombButton cb = new CheckBombButton(this);
		String result= cb.checkBombs(CheckButton);

		if (result.equals("lose")){
			CharSequence text = "Sorry, you didn't find all the bombs!";
			Toast mToast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
			mToast.show();
		} else if (result.equals("win")){
			CharSequence text = "You found all the bombs! YOU WIN!";
			Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	//A Tile is Pressed
	public void mClickMethod(View arg0) {
		TileClicked tc= new TileClicked(this);
		tc.mClickMethod(arg0);
	}

	//Hint Button is Pressed
	public void hint(View arg0) {
		HintButton hb= new HintButton(this, game);
		hb.hint(arg0);
	}

	public TheBoard getBoard(){
		return game;
	}
	
}
