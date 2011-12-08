package com.qualogy.gameoflife;

import java.util.Arrays;

public class GameOfLife {

	boolean[][] grid;
	int numberOfColumns;
	int numberOfRows;

	public GameOfLife(int numberOfColumns, int numberOfRows) {
		this.numberOfColumns = numberOfColumns;
		this.numberOfRows = numberOfRows;
		grid = new boolean[numberOfColumns][numberOfRows];
	}

	public void play(int cyclesOfLife) {
		boolean[][] newGrid = new boolean[numberOfColumns][numberOfRows];
		
		for (int column = 0; column < numberOfColumns; column++) {
			for (int row = 0; row < numberOfRows; row++) {
				int neighbours = liveNeighbourCount(column, row);
				if (isAlive(column, row) && (neighbours == 2)) {
					newGrid[column][row] = true;
				} 
				if (neighbours == 3) {
					newGrid[column][row] = true;
				}
			}
		}
		grid = newGrid;
	}

	public int countLiveCells() {
		int counter = 0;
		for (int j = 0; j < grid.length; j++) {
			for (int k = 0; k < grid[0].length; k++) {
				if (grid[j][k]) {
					counter++;
				}
			}
		}
		return counter;
	}

	public int liveNeighbourCount(int columnPosition, int rowPosition) {
		int neighbourCount = 0;
		int colStartIndex = Math.max(columnPosition - 1, 0);
		int colEndIndex = Math.min(columnPosition + 1, numberOfColumns - 1);
		int rowStartIndex = Math.max(rowPosition - 1, 0);
		int rowEndIndex = Math.min(rowPosition + 1, numberOfRows - 1);

		for (int column = colStartIndex; column <= colEndIndex; column++) {
			for (int row = rowStartIndex; row <= rowEndIndex; row++) {
				if (grid[column][row] 
						&& !((columnPosition == column) && (rowPosition == row))) {
					neighbourCount++;
				}
			}
		}

		return neighbourCount;
	}

	public void makeAlive(int column, int row) {
		grid[column][row] = true;
	}

	public boolean isAlive(int column, int row) {
		return grid[column][row];
	}

	@Override
	public String toString() {
		String str = new String();
		for (int row = numberOfRows - 1; row >= 0; row--) {
			for (int col = 0; col < numberOfColumns; col++) {
				if (grid[col][row]) {
					str = str + 'X';
				} else {
					str = str + '.';
				}
			}
			str = str + "\n";
		}
		return str;
	}
	
	public static GameOfLife fromString(String gridString) {
		String[] rows = gridString.split("\n");
		int nrOfRows = rows.length;
		int nrOfColumns = rows[0].length();

		GameOfLife game = new GameOfLife(nrOfColumns, nrOfRows);
		int rowNr = nrOfRows - 1;
		for (String row : rows) {
			char[] cells = row.toCharArray();
			for (int colNr = 0; colNr < nrOfColumns; colNr++) {
				if ('X' == cells[colNr]) {
					game.makeAlive(colNr, rowNr);
				}
			}
			rowNr--;
		}
		return game;
	}
}
