package no.vestein.sokoban.map;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import no.vestein.sokoban.SokobanScene;
import no.vestein.sokoban.blocks.Block;
import no.vestein.sokoban.blocks.BlockGoal;
import no.vestein.sokoban.blocks.BlockGreenBox;
import no.vestein.sokoban.blocks.BlockPlayer;
import no.vestein.sokoban.blocks.BlockRedBox;
import no.vestein.sokoban.blocks.BlockWall;

public class Movement {

	public static void checkKeyPressed(KeyEvent keyevent) {
		KeyCode key = keyevent.getCode();
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
	
//	public static void checkCommand(String command) {
//		if (command.toLowerCase().equals("s")) {
//			movePlayer(0, 1);
//		} else if (command.toLowerCase().equals("w")) {
//			movePlayer(0, -1);
//		} else if (command.toLowerCase().equals("a")) {
//			movePlayer(-1, 0);
//		} else if (command.toLowerCase().equals("d")) {
//			movePlayer(1, 0);
//		}
//	}
	
	private static boolean moveBlock(int playerX, int playerY, int dirX, int dirY) {
		Block block = Level.getMap().getBlock(playerX + dirX, playerY + dirY);
		if (block instanceof BlockRedBox) {
			if (moveBlockRed(playerX, playerY, dirX, dirY)) return true;
		} else if (block instanceof BlockGreenBox) {
			if (moveBlockGreen(playerX, playerY, dirX, dirY)) return true;;
		}
		return false;
	} 
	
	private static boolean moveBlockRed(int playerX, int playerY, int dirX, int dirY) {
		BlockRedBox redbox = (BlockRedBox) Level.getMap().getBlock(playerX + dirX, playerY + dirY);
		Block block = Level.getMap().getBlock(playerX + (dirX * 2), playerY + (dirY * 2));
		if (block instanceof BlockRedBox || block instanceof BlockGreenBox || block instanceof BlockWall) {
			return false;
		} else if (block instanceof BlockGoal) {
			SokobanScene.gameView.getChildren().remove(redbox.getObject());
			BlockGreenBox greenbox = new BlockGreenBox((playerX + (dirX * 2)) * 20 + 50, (playerY + (dirY * 2)) * 20 + 50);
			SokobanScene.gameView.getChildren().add(greenbox.getObject());
			
			Level.getMap().getMap().put((playerX + (dirX * 2)) + "." + (playerY + (dirY * 2)), greenbox);
			Level.getMap().getMap().remove((playerX + dirX) + "." + (playerY + dirY));
		} else {
			Level.getMap().getMap().put((playerX + (dirX * 2)) + "." + (playerY + (dirY * 2)), redbox);
			Level.getMap().getMap().remove((playerX + dirX) + "." + (playerY + dirY));
			
			redbox.setXPosition(playerX + (dirX * 2));
			redbox.setYPosition(playerY + (dirY * 2));
		}
		return true;
	}
	
	private static boolean moveBlockGreen(int playerX, int playerY, int dirX, int dirY) {
		BlockGreenBox greenbox = (BlockGreenBox) Level.getMap().getBlock(playerX + dirX, playerY + dirY);
		Block block = Level.getMap().getBlock(playerX + (dirX * 2), playerY + (dirY * 2));
		if (block instanceof BlockRedBox || block instanceof BlockGreenBox || block instanceof BlockWall) {
			return false;
		} else  {
			SokobanScene.gameView.getChildren().remove(greenbox.getObject());
			SokobanScene.gameView.getChildren().remove(Level.getMap().getPlayer().getObject());
			
			BlockRedBox redbox = new BlockRedBox((playerX + (dirX * 2)) * 20 + 50, (playerY + (dirY * 2)) * 20 + 50);
			BlockGoal goal = new BlockGoal((playerX + dirX) * 20 + 50, (playerY + dirY) * 20 + 50);
			Level.getMap().setPlayer(new BlockPlayer((playerX + dirX) * 20 + 50, (playerY + dirY) * 20 + 50));
			
			SokobanScene.gameView.getChildren().add(redbox.getObject());
			SokobanScene.gameView.getChildren().add(goal.getObject());
			SokobanScene.gameView.getChildren().add(Level.getMap().getPlayer().getObject());
			
			Level.getMap().getMap().put((playerX + (dirX * 2)) + "." + (playerY + (dirY * 2)), redbox);
			Level.getMap().getMap().put((playerX + dirX) + "." + (playerY + dirY), goal);
		}
		return true;
	}
	
	public static void movePlayer(int dirX, int dirY) {
		int playerX = Level.getMap().getPlayer().getXPosition();
		int playerY = Level.getMap().getPlayer().getYPosition();
		try {
			Block block = Level.getMap().getBlock(playerX + dirX, playerY + dirY);
			if (block == null || block instanceof BlockGoal) {
				Level.getMap().getPlayer().setXPosition(playerX + dirX);
				Level.getMap().getPlayer().setYPosition(playerY + dirY);
			
				Level.getMap().getMap().remove(playerX + "." + playerY + "player");
				Level.getMap().getMap().put("player", Level.getMap().getPlayer());
			} else if (! (block instanceof BlockWall)) {
				if (moveBlock(playerX, playerY, dirX, dirY)) {
					Level.getMap().getPlayer().setXPosition(playerX + dirX);
					Level.getMap().getPlayer().setYPosition(playerY + dirY);
				
					Level.getMap().getMap().remove(playerX + "." + playerY + "player");
					Level.getMap().getMap().put("player", Level.getMap().getPlayer());
				}
			}
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		
	}
	
}
