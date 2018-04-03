/*CODE WRITTEN BY GENE CHUANG   
The use of this code is prohibited without written authorization by Gene Chuang.
 */

package com.gene.minesweeper;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;

import android.test.suitebuilder.annotation.MediumTest;
import android.view.View;
import android.widget.ImageView;

import com.gene.minesweeper.tier_one.StartOfGame;
import com.gene.minesweeper.tier_three.Tile;
import com.gene.minesweeper.tier_two.Tags.Tags;
import com.gene.minesweeper.tier_two.tier_two_point_two.CheckBombButton;
import com.gene.minesweeper.tier_two.tier_two_point_two.HintButton;
import com.gene.minesweeper.tier_two.tier_two_point_one.MinesweeperGame;
import com.gene.minesweeper.tier_two.tier_two_point_two.TileClicked;

public class StartOfGameTest extends ActivityInstrumentationTestCase2<StartOfGame> {
	
	private StartOfGame startOfGame;
	TileClicked tileClickedTest;
	CheckBombButton mCheckBombButton;
	Tile mTileTest;
    private ImageView checkBombsTest;
    private int TOTAL_ROW;
    private int TOTAL_COLUMNS;
	private MinesweeperGame game;
    
    public StartOfGameTest() {
        super(StartOfGame.class);
        System.out.println("Inside MainActivityTest()");
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.out.println("Inside setUp()");
        //Setting the touch mode to true prevents the UI control from taking focus 
        //when you click it programmatically in the test method later 
        //(for example, a button UI will just fire its on-click listener). 
        //Make sure that you call setActivityInitialTouchMode() before calling getActivity().
        setActivityInitialTouchMode(true);
        
        startOfGame = getActivity();
        game= startOfGame.getBoard();

        tileClickedTest= new TileClicked(startOfGame);
        checkBombsTest = (ImageView) startOfGame.findViewById(R.id.check_bombs);
        TOTAL_ROW= Tags.TOTAL_ROW;
        TOTAL_COLUMNS= Tags.TOTAL_COLUMNS;
    }
    
    public void testPreconditions() {
        System.out.println("Inside testPreconditions()");
    	assertNotNull("Setup Error, MainActivity is null", startOfGame);
        assertNotNull("Setup Error, check_bombs Button is null", checkBombsTest);
      }

    @MediumTest
    public void testClick_XTiles() {
    	int totalNumTilesClicked=3;
        System.out.println("Inside testClick_"+totalNumTilesClicked+"Tiles() method");
        int numTilesClicked=0;

        breakLoops: for (int row = 0; row != (TOTAL_ROW-1); row++) {
			for (int column = 0; column != (TOTAL_COLUMNS-1); column++) {
				String tile = "row_" + (row) + "_col_" + (column);
				ImageView Bombs = (ImageView) startOfGame.findViewById( startOfGame.getResources().getIdentifier(tile, "id", startOfGame.getPackageName()));
				if (numTilesClicked >= totalNumTilesClicked){
					break breakLoops;
				}
				if(game.gameBoard[row][column].getHasBomb()==false){
					TouchUtils.clickView(this, Bombs);
					numTilesClicked++;
				}
			}
		}
        TouchUtils.clickView(this, checkBombsTest);
        mCheckBombButton= new CheckBombButton(startOfGame);
        assertEquals("lose", mCheckBombButton.checkBombs((startOfGame.findViewById(R.id.check_bombs))));
    }

    @MediumTest
    public void testClick_AllTiles() {
        System.out.println("Inside testClick_AllTiles() method");
    	final View decorView = startOfGame.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView, checkBombsTest);

        for (int row = 0; row != (TOTAL_ROW-1); row++) {
			for (int column = 0; column != (TOTAL_COLUMNS-1); column++) {
				String tile = "row_" + (row) + "_col_" + (column);
				ImageView Bombs = (ImageView) startOfGame.findViewById( startOfGame.getResources().getIdentifier(tile, "id",
								startOfGame.getPackageName()));
				if(game.gameBoard[row][column].getHasBomb()==false){
					TouchUtils.clickView(this, Bombs);
				}
			}
		}
        TouchUtils.clickView(this, checkBombsTest);
        mCheckBombButton= new CheckBombButton(startOfGame);
        assertEquals("win", mCheckBombButton.checkBombs((startOfGame.findViewById(R.id.check_bombs))));
    }

    @MediumTest
    public void testHint_Win() {
    	System.out.println("Inside testHint_Win() method");
    	HintButton hb= new HintButton(startOfGame, game);
    	int pressCounter=0;

    	int totalTiles= (TOTAL_ROW-1)* (TOTAL_COLUMNS-1);
    	int numNonBombTiles= totalTiles - (game.numBombsInsertedCounter);
    	for (int i=0; i!= numNonBombTiles;i++){
    		TouchUtils.clickView(this, startOfGame.findViewById(R.id.hint));
    		pressCounter++;
    	}
    	TouchUtils.clickView(this, checkBombsTest);
        mCheckBombButton= new CheckBombButton(startOfGame);
        assertEquals("win", mCheckBombButton.checkBombs((startOfGame.findViewById(R.id.check_bombs))));
    }

    @MediumTest
    public void testHint_LoseByXTiles() {
    	int numTilesOffBy= 1;
    	System.out.println("Inside testHint_LoseBy"+numTilesOffBy+"Tiles() method");
    	HintButton hb= new HintButton(startOfGame, game);
    	int pressCounter=0;

    	int totalTiles= (TOTAL_ROW-1)* (TOTAL_COLUMNS-1);
    	int numNonBombTiles= totalTiles - (game.numBombsInsertedCounter+numTilesOffBy);
    	for (int i=0; i!= numNonBombTiles;i++){
    		TouchUtils.clickView(this, startOfGame.findViewById(R.id.hint));
    		pressCounter++;
    	}
    	TouchUtils.clickView(this, checkBombsTest);
        mCheckBombButton= new CheckBombButton(startOfGame);
        assertEquals("lose", mCheckBombButton.checkBombs((startOfGame.findViewById(R.id.check_bombs))));
    }

    @Override
	protected void tearDown() throws Exception {
		System.out.println("Inside tearDown()");
		super.tearDown();
	}

}
