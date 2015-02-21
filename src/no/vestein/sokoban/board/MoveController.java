package no.vestein.sokoban.board;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import no.vestein.sokoban.blocks.Block;
import no.vestein.sokoban.blocks.BlockGoal;
import no.vestein.sokoban.blocks.BlockBox;
import no.vestein.sokoban.blocks.BlockPlayer;
import no.vestein.sokoban.blocks.BlockWall;

public class MoveController {
	
	private Board board;
	
	public MoveController(Board board) {
		this.board = board;
	}

	public void checkKeyPressed(KeyEvent keyevent) {
		if (! board.checkIfGameIsDone()) {
			KeyCode key = keyevent.getCode();
			BlockPlayer player = board.getPlayer();
			if (!player.getMoving()) {
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
		} else if (! (block instanceof BlockWall)) {
			if (canBlockBeMoved(playerX, playerY, dirX, dirY)) {
				player.goDir(dirX, dirY);
				player.setMoving(true);
				moveBlock(playerX, playerY, dirX, dirY);
				board.getObjectMap().put("player", board.getPlayer());
			}
		}
	}
	
}
