package seamcarve;

import javafx.scene.layout.BorderPane;
import support.seamcarve.*;

import java.util.Arrays;


/**
 * This class is your seam carving picture pane.  It is a subclass of PicturePane,
 * an abstract class that takes care of all the drawing, displaying, carving, and
 * updating of seams and images for you.  Your job is to override the abstract
 * method of PicturePane that actually finds the lowest cost seam through
 * the image.
 *
 * See method comments and handouts for specifics on the steps of the seam carving algorithm.
 *
 *
 * @version 01/17/2019
 */

public class MyPicturePane extends PicturePane {



	/**
	 * The constructor accepts an image filename as a String and passes
	 * it to the superclass for displaying and manipulation.
	 *
	 * @param pane
	 * @param filename
	 */
	public MyPicturePane(BorderPane pane, String filename) {
		super(pane, filename);


	}


	/**
	 * In this method, you'll implement the dynamic programming algorithm
	 * that you learned on the first day of class to find the lowest cost seam from the top
	 * of the image to the bottom. BEFORE YOU START make sure you fully understand how the algorithm works
	 * and what it's doing.
	 * See the handout for some helpful resources and use hours/piazza to clarify conceptual blocks
	 * before you attempt to write code.
	 *
	 * This method returns an array of ints that represents a seam.  This size of this array
	 * is the height of the image.  Each entry of the seam array corresponds to one row of the
	 * image.  The data in each entry should be the x coordinate of the seam in this row.
	 * For example, given the below "image" where s is a seam pixel and - is a non-seam pixel
	 *
	 * - s - -
	 * s - - -
	 * - s - -
	 * - - s -
	 *
	 *
	 * the following code will properly return a seam:
	 *
	 * int[] currSeam = new int[4];
	 * currSeam[0] = 1;
	 * currSeam[1] = 0;
	 * currSeam[2] = 1;
	 * currSeam[3] = 2;
	 * return currSeam;
	 *
	 *
	 * This method is protected so it is accessible to the class MyPicturePane and is not
	 * accessible to other classes. PLEASE DO NOT CHANGE THIS!
	 *
	 * @return the lowest cost seam of the current image
 	 */

	//Returns the lowest cost seam
	protected int[] findLowestCostSeam() {
		//2-D array of importance values
		int[][] importance = importance();
		//Costs and directions arrays
		int[][] costs = new int[getPicHeight()][getPicWidth()];
		int[][] dirs = new int[getPicHeight()][getPicWidth()];
		//Seam array
		int[] seam = new int[getPicHeight()];
		//Adds the bottom of the importance array to the bottom of the costs array
		costs[getPicHeight()-1] = importance[getPicHeight()-1];

		//Fills up the costs and directions array
		for(int row = getPicHeight()-2; row >= 0; row--) {
			for(int col = 0; col < getPicWidth(); col++) {

				//Edge cases
				if(col == 0) {
					costs[row][col] = importance[row][col] + Math.min(costs[row+1][col], costs[row+1][col+1]);
				} else if(col == getPicWidth()-1) {
					costs[row][col] = importance[row][col] + Math.min(costs[row+1][col], costs[row+1][col-1]);
				} else {
					costs[row][col] = importance[row][col] + Math.min(Math.min(costs[row+1][col], costs[row+1][col+1]),
							costs[row+1][col-1]);
				}

				//Edge cases
				if(col != 0 && costs[row][col] == importance[row][col] + costs[row+1][col-1]){
					dirs[row][col] = -1;
				} else if(col !=getPicWidth()-1 && costs[row][col] == importance[row][col] + costs[row+1][col+1]) {
					dirs[row][col] = 1;
				} else {
					dirs[row][col] = 0;
				}
			}
		}

		//Get the starting index for the seam
		int min_col = argMin(costs[0]);
		seam[0] = min_col;

		//Fill up the seam array
		for(int i = 0; i < getPicHeight()-1; i++) {

			seam[i+1] = seam[i] + dirs[i][seam[i]];
		}
		return seam;
	}

	//Pixel values
	int[][] _pixelValue = new int[getPicHeight()][getPicWidth()];

	//Fills up my pixel values
	public int[][] getPixelValue() {
		_pixelValue = new int[getPicHeight()][getPicWidth()];
		for(int row = 0; row < getPicHeight(); row++) {
			for(int col = 0; col < getPicWidth(); col++) {
				_pixelValue[row][col] = getColorRed(getPixelColor(row, col)) + getColorBlue(getPixelColor(row, col)) +
						getColorGreen(getPixelColor(row, col));
			}
		}
		return _pixelValue;
	}

	//If the pixel is an edge case (corner of the image, or side of the image) return 0, otherwise return the desired
	// pixel value
	public int edgeCase(int row, int col) {
		if(row > 0 && col > 0 && row < getPicHeight()-1 && col < getPicWidth() -1) {
			return _pixelValue[row][col];
		} else {
			return 0;
		}
	}

	//Fills up my importance array
	public int[][] importance() {
		getPixelValue();
		int[][] importance = new int[getPicHeight()][getPicWidth()];
		for(int row = 0; row < getPicHeight(); row++) {
			for(int col = 0; col < getPicWidth(); col++) {
				int currentColorVal = _pixelValue[row][col];
				//Adds the absolute value of the difference of the surrounding pixels into the importance array
				//If the pixel is an edge case only the currentColorValue is added
				importance[row][col] = Math.abs(edgeCase(row + 1, col) - currentColorVal) +
						Math.abs(edgeCase(row - 1, col) - currentColorVal) +
						Math.abs(edgeCase(row, col + 1) - currentColorVal) +
						Math.abs(edgeCase(row, col - 1) - currentColorVal);
			}
		}
		return importance;
	}

	//Returns the index of the lowest value of the top of the cost array
	public int argMin(int[] cost) {
		int solution = 0;
		int start = cost[0];
		for(int i =0; i < cost.length; i++) {
			if(start > cost[i]) {
				start = cost[i];
				solution = i;
			}
		}
		return solution;
	}
}
