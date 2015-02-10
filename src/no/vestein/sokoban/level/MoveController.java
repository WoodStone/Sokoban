package no.vestein.sokoban.level;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import no.vestein.sokoban.animation.AnimationPlayer;
import no.vestein.sokoban.blocks.Block;
import no.vestein.sokoban.blocks.BlockGoal;
import no.vestein.sokoban.blocks.BlockBox;
import no.vestein.sokoban.blocks.BlockWall;

public class MoveController {

	public static void checkKeyPressed(KeyEvent keyevent) {
		KeyCode key = keyevent.getCode();
		if (key == KeyCode.DOWN) {
//			movePlayer(0, 1);
			if (movePlayer(0, 1)) AnimationPlayer.goDown();
		} else if (key == KeyCode.UP) {
//			movePlayer(0, -1);
			if (movePlayer(0, -1)) AnimationPlayer.goUp();
		} else if (key == KeyCode.LEFT) {
			movePlayer(-1, 0);
//			Level.getMap().getPlayer().playLeft();
			if (movePlayer(-1, 0)) AnimationPlayer.goLeft();
		} else if (key == KeyCode.RIGHT) {
//			movePlayer(1, 0);
//			Level.getMap().getPlayer().playRight();
			if (movePlayer(1, 0)) AnimationPlayer.goRight();
		}
	}
	
	private static boolean moveBlock(int playerX, int playerY, int dirX, int dirY) {
		BlockBox box = (BlockBox) Level.getMap().getBlock(playerX + dirX, playerY + dirY);
		Block block = Level.getMap().getBlock(playerX + (dirX * 2), playerY + (dirY * 2));
		BlockGoal goal = Level.getMap().getGoal(playerX + (dirX * 2), playerY + (dirY * 2));
		
		if (block instanceof BlockBox || block instanceof BlockWall) {
			return false;
		} else if (goal != null) {
			box.getObject().setFill(Color.GREEN);
			box.setXPosition(playerX + (dirX * 2));
			box.setYPosition(playerY + (dirY * 2));
			
			Level.getMap().getObjectMap().put((playerX + (dirX * 2)) + "." + (playerY + (dirY * 2)), box);
			Level.getMap().getObjectMap().remove((playerX + dirX) + "." + (playerY + dirY));
		} else {
			box.getObject().setFill(Color.RED);
			box.setXPosition(playerX + (dirX * 2));
			box.setYPosition(playerY + (dirY * 2));
			
			Level.getMap().getObjectMap().put((playerX + (dirX * 2)) + "." + (playerY + (dirY * 2)), box);
			Level.getMap().getObjectMap().remove((playerX + dirX) + "." + (playerY + dirY));
		}
		return true;
	}

	public static boolean movePlayer(int dirX, int dirY) {
		int playerX = Level.getMap().getPlayer().getXPosition();
		int playerY = Level.getMap().getPlayer().getYPosition();
		System.out.println(playerX + ":" + playerY);
		
		Block block = Level.getMap().getBlock(playerX + dirX, playerY + dirY);
		if (block == null || block instanceof BlockGoal) {
//			Level.getMap().getPlayer().setXPosition(playerX + dirX);
//			Level.getMap().getPlayer().setYPosition(playerY + dirY);
			System.out.println("p + dir: " + (playerX + dirX));
			System.out.println(Level.getMap().getPlayer().getXPosition());
			System.out.println(Level.getMap().getPlayer().getYPosition());
			
			
			Level.getMap().getObjectMap().remove(playerX + "." + playerY + "player");
			Level.getMap().getObjectMap().put("player", Level.getMap().getPlayer());
			return true;
		} else if (! (block instanceof BlockWall)) {
			if (moveBlock(playerX, playerY, dirX, dirY)) {
//				Level.getMap().getPlayer().setXPosition(playerX + dirX);
//				Level.getMap().getPlayer().setYPosition(playerY + dirY);
				
				Level.getMap().getObjectMap().remove(playerX + "." + playerY + "player");
				Level.getMap().getObjectMap().put("player", Level.getMap().getPlayer());
				return true;
			}
		}
		return false;
	}
	
}
