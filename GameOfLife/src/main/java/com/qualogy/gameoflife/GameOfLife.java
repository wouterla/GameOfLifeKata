package com.qualogy.gameoflife;

public class GameOfLife {

	private static final String SEPARATOR = "\n";
	private static final char DEAD_CHARACTER = '.';
	private static final char ALIVE_CHARACTER = 'X';
	
	private boolean[][] grid;
	private int numberOfColumns;
	private int numberOfRows;

	public GameOfLife(int numberOfColumns, int numberOfRows) {
		this.numberOfColumns = numberOfColumns;
		this.numberOfRows = numberOfRows;
		grid = new boolean[numberOfColumns][numberOfRows];
	}

	public void play(int cyclesOfLife) {
		boolean[][] newGrid = new boolean[numberOfColumns][numberOfRows];
		
		for (int column = 0; column < numberOfColumns; column++) {
			for (int row = 0; row < numberOfRows; row++) {				
				newGrid[column][row] = getNewStateForCell(column, row);
			}
		}
		grid = newGrid;
	}

	public boolean getNewStateForCell(int column, int row) {
		int liveNeighbours = liveNeighbourCount(column, row);
		if (isAlive(column, row)) {
			return calculateStateForLiveCells(liveNeighbours);
		} else {
			return calculateStateForDeadCells(liveNeighbours);
		}
	}
	
	public boolean calculateStateForLiveCells(int liveNeighbours) {
		return (liveNeighbours == 2) || (liveNeighbours == 3);
	}
	
	public boolean calculateStateForDeadCells(int liveNeighbours) {
		return (liveNeighbours == 3);
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
		for (int column = columnPosition - 1; column <= columnPosition + 1; column++) {
			for (int row = rowPosition - 1; row <= rowPosition + 1; row++) {
				if (isOnGrid(column, row) 
						&& isAlive(column, row)
						&& !isSamePosition(columnPosition, rowPosition, column, row)) {
					neighbourCount++;
				}
			}
		}
		return neighbourCount;
	}

	public boolean isSamePosition(int columnPosition, int rowPosition,
			int column, int row) {
		return (columnPosition == column) && (rowPosition == row);
	}
	
	public boolean isOnGrid(int column, int row) {
		return ((column > 0) && (column < numberOfColumns)
				&& (row > 0) && (row < numberOfRows));
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
					str = str + ALIVE_CHARACTER;
				} else {
					str = str + DEAD_CHARACTER;
				}
			}
			str = str + SEPARATOR;
		}
		return str;
	}
	
	public static GameOfLife fromString(String gridString) {
		String[] rows = gridString.split(SEPARATOR);
		int nrOfRows = rows.length;
		int nrOfColumns = rows[0].length();

		GameOfLife game = new GameOfLife(nrOfColumns, nrOfRows);
		int rowNr = nrOfRows - 1;
		for (String row : rows) {
			char[] cells = row.toCharArray();
			for (int colNr = 0; colNr < nrOfColumns; colNr++) {
				if (ALIVE_CHARACTER == cells[colNr]) {
					game.makeAlive(colNr, rowNr);
				}
			}
			rowNr--;
		}
		return game;
	}
}
