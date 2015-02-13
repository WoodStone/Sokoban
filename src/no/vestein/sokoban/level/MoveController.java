package no.vestein.sokoban.level;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import no.vestein.sokoban.SokobanScene;
import no.vestein.sokoban.blocks.Block;
import no.vestein.sokoban.blocks.BlockGoal;
import no.vestein.sokoban.blocks.BlockBox;
import no.vestein.sokoban.blocks.BlockPlayer;
import no.vestein.sokoban.blocks.BlockWall;

public class MoveController {

	public static void checkKeyPressed(KeyEvent keyevent) {
		KeyCode key = keyevent.getCode();
		BlockPlayer player = SokobanScene.gameLevel.getMap().getPlayer();
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
	
	private static boolean canBlockBeMoved(int playerX, int playerY, int dirX, int dirY) {
		Block block = SokobanScene.gameLevel.getMap().getBlock(playerX + (dirX * 2), playerY + (dirY * 2));
		if (block instanceof BlockBox || block instanceof BlockWall) return false;
		return true;
	}
	
	private static void moveBlock(int playerX, int playerY, int dirX, int dirY) {
		BlockBox box = (BlockBox) SokobanScene.gameLevel.getMap().getBlock(playerX + dirX, playerY + dirY);
		BlockGoal goal = SokobanScene.gameLevel.getMap().getGoal(playerX + (dirX * 2), playerY + (dirY * 2));

		if (goal != null) {
			box.goDir(dirX, dirY);
			
			SokobanScene.gameLevel.getMap().getObjectMap().put((playerX + (dirX * 2)) + "." + (playerY + (dirY * 2)), box);
			SokobanScene.gameLevel.getMap().getObjectMap().remove((playerX + dirX) + "." + (playerY + dirY));
		} else {
			box.goDir(dirX, dirY);
			
			SokobanScene.gameLevel.getMap().getObjectMap().put((playerX + (dirX * 2)) + "." + (playerY + (dirY * 2)), box);
			SokobanScene.gameLevel.getMap().getObjectMap().remove((playerX + dirX) + "." + (playerY + dirY));
		}
	}

	public static void movePlayer(int dirX, int dirY) {
		BlockPlayer player = SokobanScene.gameLevel.getMap().getPlayer();
		int playerX = player.getXPosition();
		int playerY = player.getYPosition();
		
		Block block = SokobanScene.gameLevel.getMap().getBlock(playerX + dirX, playerY + dirY);
		if (block == null || block instanceof BlockGoal) {
			player.goDir(dirX, dirY);
			player.setMoving(true);
			SokobanScene.gameLevel.getMap().getObjectMap().put("player", SokobanScene.gameLevel.getMap().getPlayer());
		} else if (! (block instanceof BlockWall)) {
			if (canBlockBeMoved(playerX, playerY, dirX, dirY)) {
				player.goDir(dirX, dirY);
				player.setMoving(true);
				moveBlock(playerX, playerY, dirX, dirY);
				SokobanScene.gameLevel.getMap().getObjectMap().put("player", SokobanScene.gameLevel.getMap().getPlayer());
			}
		}
	}
	
}
