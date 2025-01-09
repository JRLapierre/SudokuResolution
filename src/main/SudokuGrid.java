package main;

import java.util.Arrays;

import exception.InvalidGridDimentionsException;
import exception.InvalidNumberException;
import exception.RepeatingNumberException;

public class SudokuGrid {

	private short[][] grid;
		
	public SudokuGrid(short[][] grid) {
		validGrid(grid);
		this.grid = grid;

	}
	
	public void validGrid(short[][] grid) {
		//check the dimentions
		checkValidGridDimentions(grid);
		//check the numbers
		for (int i = 0; i < 9; i++) {
			checkValidRow(grid, i);
			checkValidColumn(grid, i);
		}
	    for (int row = 0; row < 9; row += 3) {
	        for (int column = 0; column < 9; column += 3) {
	            checkValidBox(grid, row, column);
	        }
	    }
	}
	
	void checkValidGridDimentions(short[][] grid) {
		if (grid.length != 9 || Arrays.stream(grid).anyMatch(row -> row.length != 9)) {
		    throw new InvalidGridDimentionsException();
		}
	}
	
	private void checkValidRow(short[][] grid, int row) {
		this.valid9(grid[row]);
	}
	
	private void checkValidColumn(short[][] grid, int column) {
		short[] tempArray = new short[9];
		for (int i = 0; i < grid.length; i++) {
			tempArray[i] = grid[i][column];
		}
		this.valid9(tempArray);
	}
	
	private void checkValidBox(short[][] grid, int row, int column) {
		short[] tempArray = new short[9];
		int startRow = row - (row % 3);
		int startColumn = column - (column % 3);
		for (int i = startRow; i < startRow + 3; i++) {
			for (int j = startColumn; j < startColumn + 3; j++) {
				tempArray[3*(i - startRow) + (j - startColumn)] = grid[i][j];
			}
		}
		this.valid9(tempArray);
	}
	
	void valid9(short[] data) {
		boolean[] verificationArray = new boolean[10];
		for (short number : data) {
			if (number < 0 || number > 9) {
				throw new InvalidNumberException(number);
			}
			if (number == 0 || !verificationArray[number]) {
				verificationArray[number] = true;
			} else {
				throw new RepeatingNumberException(number);
			}
			
		}
	}
	
	/**
	 * Fuction to display the grid in the terminal
	 */
	public void displayGrid() {
        for (int i = 0; i < this.grid.length; i++) {
            if (i % 3 == 0) {
                System.out.println("+-------+-------+-------+");
            }
            for (int j = 0; j < this.grid[i].length; j++) {
                if (j % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(this.grid[i][j] == 0 ? "  " : this.grid[i][j] + " ");
            }
            System.out.print("| ");
            System.out.println();
        }
        System.out.println("+-------+-------+-------+");
	}
	
}
