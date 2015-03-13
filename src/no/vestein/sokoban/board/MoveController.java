package no.vestein.sokoban.board;

import java.util.EmptyStackException;
import java.util.List;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import no.vestein.sokoban.blocks.Block;
import no.vestein.sokoban.blocks.BlockGoal;
import no.vestein.sokoban.blocks.BlockBox;
import no.vestein.sokoban.blocks.BlockPlayer;
import no.vestein.sokoban.blocks.BlockWall;

public class MoveController {
	
	private Board board;
	private History history = new History();
	
	public MoveController(Board board) {
		this.board = board;
	}

	public int nMoves() {
		return history.size();
	}
	
	public int nBlockPushes() {
		return history.nPushes();
	}
	
	public boolean backward() {
		if (! board.isGameIsDone()) {
			try {
				List<Object> lastPositions = history.pop(board);
				BlockPlayer player = board.getPlayer();
				
				int pPosX = (int) lastPositions.get(0);
				int pPosY = (int) lastPositions.get(1);
				
				player.setXPosition(pPosX);
				player.setYPosition(pPosY);
				
				if (lastPositions.size() > 2) {
					BlockBox box = (BlockBox) lastPositions.get(2);
					int bPosX = (int) lastPositions.get(3);
					int bPosY = (int) lastPositions.get(4);
					
					board.getObjectMap().remove(box.getXPosition() + "." + box.getYPosition());
					board.getObjectMap().put(bPosX + "." + bPosY, box);
					
					box.setXPosition(bPosX);
					box.setYPosition(bPosY);
					board.updateBox(box);
				}
			} catch (EmptyStackException e) {
				return false;
			}
		}
		board.updateText();
		return true;
	}
	
	public boolean forward() {
		if (! board.isGameIsDone()) {
			try {
				List<Object> futurePositions = history.popFuture(board);
				BlockPlayer player = board.getPlayer();
				
				int pPosX = (int) futurePositions.get(0);
				int pPosY = (int) futurePositions.get(1);
				
				player.setXPosition(pPosX);
				player.setYPosition(pPosY);
				
				if (futurePositions.size() > 2) {
					BlockBox box = (BlockBox) futurePositions.get(2);
					int bPosX = (int) futurePositions.get(3);
					int bPosY = (int) futurePositions.get(4);
					
					board.getObjectMap().remove(box.getXPosition() + "." + box.getYPosition());
					board.getObjectMap().put(bPosX + "." + bPosY, box);
					
					box.setXPosition(bPosX);
					box.setYPosition(bPosY);
					board.updateBox(box);
				}
			} catch (EmptyStackException e) {
				return false;
			}
		}
		board.updateText();
		return true;
	}
	
	public void checkKeyPressed(KeyEvent keyevent) {
		if (! board.isGameIsDone()) {
			KeyCode key = keyevent.getCode();
			BlockPlayer player = board.getPlayer();
			if (!player.isMoving()) {
				if (key == KeyCode.DOWN) {
					movePlayer(0, 1);
				} else if (key == KeyCode.UP) {
					movePlayer(0, -1);
				} else if (key == KeyCode.LEFT) {
					movePlayer(-1, 0);
				} else if (key == KeyCode.RIGHT) {
					movePlayer(1, 0);
				}
			}
		}
	}
	
	private boolean canBlockBeMoved(int playerX, int playerY, int dirX, int dirY) {
		Block block = board.getBlock(playerX + (dirX * 2), playerY + (dirY * 2));
		if (block instanceof BlockBox || block instanceof BlockWall) return false;
		return true;
	}
	
	private void moveBlock(int playerX, int playerY, int dirX, int dirY) {
		BlockBox box = (BlockBox) board.getBlock(playerX + dirX, playerY + dirY);
		BlockGoal goal = board.getGoal(playerX + (dirX * 2), playerY + (dirY * 2));

		if (goal != null) {
			box.goDir(dirX, dirY);
			
			board.getObjectMap().put((playerX + (dirX * 2)) + "." + (playerY + (dirY * 2)), box);
			board.getObjectMap().remove((playerX + dirX) + "." + (playerY + dirY));
		} else {
			box.goDir(dirX, dirY);
			
			board.getObjectMap().put((playerX + (dirX * 2)) + "." + (playerY + (dirY * 2)), box);
			board.getObjectMap().remove((playerX + dirX) + "." + (playerY + dirY));
		}
	}

	private void movePlayer(int dirX, int dirY) {
		BlockPlayer player = board.getPlayer();
		int playerX = player.getXPosition();
		int playerY = player.getYPosition();
		
		Block block = board.getBlock(playerX + dirX, playerY + dirY);
		if (block == null || block instanceof BlockGoal) {
			player.goDir(dirX, dirY);
			player.setMoving(true);
			board.getObjectMap().put("player", board.getPlayer());
			
			history.push(playerX, playerY);
			
		} else if (! (block instanceof BlockWall)) {
			if (canBlockBeMoved(playerX, playerY, dirX, dirY)) {
				player.goDir(dirX, dirY);
				player.setMoving(true);
				moveBlock(playerX, playerY, dirX, dirY);
				board.getObjectMap().put("player", board.getPlayer());
				
				history.push(playerX, playerY, (BlockBox) block, block.getXPosition(), block.getYPosition());
			}
		}
		board.updateText();
	}
	
}
