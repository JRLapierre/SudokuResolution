package main;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exception.InvalidGridDimentionsException;
import exception.InvalidNumberException;
import exception.RepeatingNumberException;

class SudokuGridTest {
	
	static short[][] goodGrid1;
	
    static short[][] irregularGrid1 = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };
    
    static short[][] irregularGrid2 = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };
    
    static short[][] irregularGrid3 = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5}
    };
    
    static short[][] irregularGrid4 = {
        {5, 3, 0, 0, 7, 0, 0, 0, 0},
        {6, 0, 0, 1, 9, 5, 0, 0, 0},
        {0, 9, 8, 0, 0, 0, 0, 6, 0},
        {8, 0, 0, 0, 6, 0, 0, 0, 3},
        {4, 0, 0, 8, 0, 3, 0, 0, 1},
        {7, 0, 0, 0, 2, 0, 0, 0, 6},
        {0, 6, 0, 0, 0, 0, 2, 8, 0},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 4, 1, 9, 0, 0, 5},
        {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

	
	@BeforeEach
	void initData() {
		goodGrid1 = new short[][]{
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
	}

	@Test
	void testValid9success() {
		short[] testData1 = {1, 4, 7, 8, 9, 5, 6, 2};
		short[] testData2 = {1, 4, 7, 0, 9, 5, 0, 2};
		SudokuGrid grid = new SudokuGrid(goodGrid1);
		assertDoesNotThrow(() -> grid.valid9(testData1));
		assertDoesNotThrow(() -> grid.valid9(testData2));
	}
	
	@Test
	void testValid9InvalidNumberException() {
		short[] testData1 = {1, 4, 7, 8, 10, 5, 6, 2};
		short[] testData2 = {1, 4,-1, 0, 9, 5, 0, 2};
		SudokuGrid grid = new SudokuGrid(goodGrid1);
		InvalidNumberException exception;
		exception = assertThrows(InvalidNumberException.class, () -> grid.valid9(testData1));
		assertEquals("the number '10' has no place in a sudoku grid", exception.getMessage());
		exception = assertThrows(InvalidNumberException.class, () -> grid.valid9(testData2));
		assertEquals("the number '-1' has no place in a sudoku grid", exception.getMessage());
	}
	
	@Test
	void testValid9RepeatingNumberException() {
		short[] testData1 = {1, 4, 7, 8, 7, 5, 6, 2}; //repeating 7
		short[] testData2 = {1, 4, 0, 0, 9, 2, 0, 2}; //repeating 2 and 0
		SudokuGrid grid = new SudokuGrid(goodGrid1);
		RepeatingNumberException exception;
		exception = assertThrows(RepeatingNumberException.class, () -> grid.valid9(testData1));
		assertEquals("the value '7' is repeated multiple times", exception.getMessage());
		exception = assertThrows(RepeatingNumberException.class, () -> grid.valid9(testData2));
		assertEquals("the value '2' is repeated multiple times", exception.getMessage());
	}
	
	@Test
	void testGridDimentions() {
		InvalidGridDimentionsException exception;
		//create a good object
		SudokuGrid grid = new SudokuGrid(goodGrid1);
		exception = assertThrows(InvalidGridDimentionsException.class, 
				() -> grid.checkValidGridDimentions(irregularGrid1));//miss a case
		assertEquals("The grid must be 9 by 9", exception.getMessage());
		exception = assertThrows(InvalidGridDimentionsException.class, 
				() -> grid.checkValidGridDimentions(irregularGrid2));//one case too much
		assertEquals("The grid must be 9 by 9", exception.getMessage());
		exception = assertThrows(InvalidGridDimentionsException.class, 
				() -> grid.checkValidGridDimentions(irregularGrid3));//not enouth lines
		assertEquals("The grid must be 9 by 9", exception.getMessage());
		exception = assertThrows(InvalidGridDimentionsException.class, 
				() -> grid.checkValidGridDimentions(irregularGrid4));//too much lines
		assertEquals("The grid must be 9 by 9", exception.getMessage());
	}
	
	@Test
	void testTerminalDisplay() {
		SudokuGrid grid = new SudokuGrid(goodGrid1);
	    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        grid.displayGrid();
        assertEquals(
        		"+-------+-------+-------+\r\n"
        		+ "| 5 3   |   7   |       | \r\n"
        		+ "| 6     | 1 9 5 |       | \r\n"
        		+ "|   9 8 |       |   6   | \r\n"
        		+ "+-------+-------+-------+\r\n"
        		+ "| 8     |   6   |     3 | \r\n"
        		+ "| 4     | 8   3 |     1 | \r\n"
        		+ "| 7     |   2   |     6 | \r\n"
        		+ "+-------+-------+-------+\r\n"
        		+ "|   6   |       | 2 8   | \r\n"
        		+ "|       | 4 1 9 |     5 | \r\n"
        		+ "|       |   8   |   7 9 | \r\n"
        		+ "+-------+-------+-------+\r\n"
        		, outContent.toString());
	}

}
