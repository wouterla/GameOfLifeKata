package com.qualogy.gameoflife;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameOfLifeTest {

	@Test
	public void testBlock() {
		String blockString = 
				"....\n" +
				".XX.\n" +
				".XX.\n" +
				"....\n";
		GameOfLife game = GameOfLife.fromString(blockString);
		game.play(1);
		assertEquals(blockString, game.toString());
	}

	@Test
	public void testMakeAlive() {
		String gridString = 
				"....\n" +
				"..X.\n" +
				"....\n" +
				"....\n";
		GameOfLife game = GameOfLife.fromString(gridString);
		assertTrue(game.isAlive(2, 2));
	}

	@Test
	public void testBlockFiveGenerations() {
		String blockString = 
				"....\n" +
				".XX.\n" +
				".XX.\n" +
				"....\n";
		GameOfLife game = GameOfLife.fromString(blockString);
		game.play(5);
		assertEquals(blockString, game.toString());
	}
	
	@Test
	public void testCellDiesWithLessThanTwoNeighbours() {
		String blockString = 
				"....\n" +
				".X..\n" +
				".X..\n" +
				"....\n";
		GameOfLife game = GameOfLife.fromString(blockString);
		game.play(1);
		assertFalse(game.isAlive(1,1));
		assertFalse(game.isAlive(1,2));
	}
		
	@Test
	public void testCountLiveCellsForEmptyGrid() {
		GameOfLife game = new GameOfLife(4, 4);
		assertEquals(0, game.countLiveCells());
	}
	
	@Test
	public void testCountLiveCellsForNonEmptyGrid(){
		GameOfLife game = new GameOfLife(4, 4);
		game.makeAlive(0, 1);
		game.makeAlive(0, 2);
		assertEquals(2, game.countLiveCells());
	}
	
	@Test
	public void testLiveNeighboursForDeadCellWithoutEdges() {
		GameOfLife game = new GameOfLife(4, 4);
		game.makeAlive(1, 2);
		assertEquals(1, game.liveNeighbourCount(1, 1));
	}
	
	@Test
	public void testLiveNeighboursForLiveCellWithoutEdges() {
		String blockString = 
				"....\n" +
				".X..\n" +
				".X..\n" +
				"....\n";
		GameOfLife game = GameOfLife.fromString(blockString);
		assertEquals(1, game.liveNeighbourCount(1, 1));		
	}
	
	@Test
	public void testLiveNeighboursForDeadCellAtNearEdge() {
		String blockString = 
				"....\n" +
				"....\n" +
				".X..\n" +
				"....\n";
		GameOfLife game = GameOfLife.fromString(blockString);
		assertEquals(1, game.liveNeighbourCount(0, 2));
	}
	
	@Test
	public void testLiveNeighboursForDeadCellAtFarEdge() {
		String blockString = 
				"....\n" +
				".X..\n" +
				"....\n" +
				"....\n";
		GameOfLife game = GameOfLife.fromString(blockString);
		assertEquals(1, game.liveNeighbourCount(1, 3));
	}

	@Test
	public void testSingleCellWithoutNeighboursDies() {
		String expected = 
				"....\n" +
				"....\n" +
				"....\n" +
				"....\n";

		GameOfLife game = new GameOfLife(4, 4);
		game.makeAlive(0, 1);
		game.play(1);		
		
		assertEquals(expected, game.toString());
	}
	
	@Test
	public void testCellDiesWithMoreThanThreeNeighbours() {
		String gridString = 
				".....\n" +
				"..X..\n" +
				".XXX.\n" +
				"..X..\n" +
				".....\n";
		GameOfLife game = GameOfLife.fromString(gridString);
		game.play(1);
		assertFalse(game.isAlive(2, 2));
	}
	
	@Test
	public void testCellStaysAliveWithExactlyThreeNeighbours() {
		String blockString = 
				"....\n" +
				".XX.\n" +
				".XX.\n" +
				"....\n";
		GameOfLife game = GameOfLife.fromString(blockString);
		game.play(1);
		assertTrue(game.isAlive(1, 1));		
	}

	@Test
	public void testCellStaysAliveWithExactlyTwoNeighbours() {
		String gridString = 
				"....\n" +
				"..X.\n" +
				".XX.\n" +
				"....\n";
		GameOfLife game = GameOfLife.fromString(gridString);
		game.play(1);
		assertTrue(game.isAlive(1, 1));		
	}
	
	@Test
	public void testDeadCellBecomesAliveWithExactlyThreeNeighbours() {
		String blockString = 
				"....\n" +
				".XX.\n" +
				"..X.\n" +
				"....\n";
		GameOfLife game = GameOfLife.fromString(blockString);
		game.play(1);
		assertTrue(game.isAlive(1, 1));				
	}

}
