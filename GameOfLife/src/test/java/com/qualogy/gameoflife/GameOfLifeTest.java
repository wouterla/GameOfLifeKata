package com.qualogy.gameoflife;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameOfLifeTest {

	
	@Test
	public void testBlock() {
		GameOfLife game = new GameOfLife(4, 4);
		game.makeAlive(1, 1);
		game.makeAlive(1, 2);
		game.makeAlive(2, 1);
		game.makeAlive(2, 2);
		GameOfLife newGame = game.play(1);
		assertEquals(game, newGame);
	}

	@Test
	public void testEmptyEquals() {
		GameOfLife game1 = new GameOfLife(4, 4);
		GameOfLife game2 = new GameOfLife(4, 4);
		assertEquals(game1, game2);
	}

	@Test
	public void testMakeAlive() {
		GameOfLife game = new GameOfLife(4, 4);
		game.makeAlive(2, 2);
		assertTrue(game.isAlive(2, 2));
	}

	@Test
	public void testBlock5Times() {
		GameOfLife game = new GameOfLife(4, 4);
		game.makeAlive(1, 1);
		game.makeAlive(1, 2);
		game.makeAlive(2, 1);
		game.makeAlive(2, 2);
		GameOfLife newGame = game.play(5);
		assertEquals(game, newGame);
	}
	
	@Test
	public void testCellDiesWithLessNeighbours() {
		GameOfLife game = new GameOfLife(4, 4);
		game.makeAlive(1, 1);
		game.makeAlive(1, 2);
		GameOfLife newGame = game.play(1);
		assertFalse(newGame.isAlive(1,1));
		assertFalse(newGame.isAlive(1,2));
	}
	
	@Test
	public void testSingleCellWithoutNeighboursDies() {
		GameOfLife game = new GameOfLife(4, 4);
		game.makeAlive(0, 1);
		GameOfLife alldead = new GameOfLife(4, 4);
		assertEquals(alldead, game.play(1));
	}
	
	@Test
	public void testCountLiveCellsForEmptyGrid() {
		GameOfLife game = new GameOfLife(4, 4);
		assertEquals(0, game.countLiveCells());
	}
	
	@Test
	public void testCountLiveCellsForNonEmptyGrid(){
		GameOfLife game = new GameOfLife(4, 4);
		game.makeAlive(0,  1);
		game.makeAlive(0, 2);
		assertEquals(2, game.countLiveCells());
	}
	
	@Test
	public void testLiveNeighboursForDeadCellWithoutEdges() {
		GameOfLife game = new GameOfLife(4, 4);
		game.makeAlive(1, 2);
		int rowPosition = 1;
		int columnPosition = 1;
		assertEquals(1, game.liveNeighbourCount(columnPosition, rowPosition));
	}
	
	@Test
	public void testLiveNeighboursForDeadCellAtNearEdge() {
		GameOfLife game = new GameOfLife(4, 4);
		game.makeAlive(1, 2);
		assertEquals(1, game.liveNeighbourCount(0, 2));
	}
	
	@Test
	public void testLiveNeighboursForDeadCellAtFarEdge() {
		GameOfLife game = new GameOfLife(4, 4);
		game.makeAlive(1, 2);
		assertEquals(1, game.liveNeighbourCount(1, 3));
	}
}
