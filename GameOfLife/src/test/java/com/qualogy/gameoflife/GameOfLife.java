package com.qualogy.gameoflife;

import java.util.Arrays;

public class GameOfLife {

	boolean[][] grid;
	int numberOfColumns;
	int numberOfRows;
	
	public GameOfLife() {
	}

	public GameOfLife(int numberOfColumns, int numberOfRows) {
		this.numberOfColumns = numberOfColumns;
		this.numberOfRows = numberOfRows;
		grid = new boolean[numberOfColumns][numberOfRows];
	}
	
	public GameOfLife play(int cyclesOfLife) {
		int counter = countLiveCells();
		GameOfLife newGame = new GameOfLife(numberOfColumns, numberOfRows);
		for(int column = 0; column < numberOfColumns; column++){
			for(int row = 0; row < numberOfRows; row++) {
				if(isAlive(column, row)) {
					if(!(liveNeighbourCount(column, row) < 2)){
						newGame.makeAlive(column, row) ;
					}
				}
			}
		}
		return newGame;
	}

	public int countLiveCells() {
		int counter = 0;
		for (int j = 0; j < grid.length; j++ ) {
			for (int k = 0; k < grid[0].length; k++ ) {	
				if (grid[j][k] ) {
					counter++;
				}
			}
		}
		return counter;
	}

	public int liveNeighbourCount(int columnPosition, int rowPosition) {
		int neighbourCount =0;
		int colStartIndex = Math.max(columnPosition-1, 0);
		int colEndIndex = Math.min(columnPosition+1, numberOfColumns-1);
		int rowStartIndex = Math.max(rowPosition-1, 0);
		int rowEndIndex = Math.min(rowPosition+1, numberOfRows-1);
		for (int column = colStartIndex; column <= colEndIndex; column++) {
			for (int row = rowStartIndex; row <= rowEndIndex; row++) {
				if (grid[column][row]) {
					neighbourCount++;
				}
			}
		}
		
		return neighbourCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(grid);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameOfLife other = (GameOfLife) obj;
		if(this.grid.length != other.grid.length) {
			return false;
		}
		for (int  i = 0;  i < this.grid.length; i++) {
			if (!Arrays.equals(grid[i], other.grid[i]))
				return false;
	
		}
		return true;
	}

	public void makeAlive(int column, int row) {
		grid[column][row] = true;
		
	}

	public boolean isAlive(int column, int row) {
		return grid[column][row];
	}

}
