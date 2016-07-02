package no.vestein.sokoban.board;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import no.vestein.sokoban.blocks.BlockBox;
import no.vestein.sokoban.blocks.BlockPlayer;

public class History {

	private Stack<List<Object>> history = new Stack<List<Object>>();
	private Stack<List<Object>> future = new Stack<List<Object>>();
	
	public void push(int playerX, int playerY, BlockBox box, int boxX, int boxY) {
		List<Object> tempList = Arrays.asList(playerX, playerY, box, boxX, boxY);
		future.clear();
		history.push(tempList);
	}
	
	public void push(int playerX, int playerY) {
		List<Object> tempList = Arrays.asList(playerX, playerY);
		future.clear();
		history.push(tempList);
	}
	
	public List<Object> pop(Board board) {
		List<Object> tempHist = history.pop();
		List<Object> tempFutu;
		try {
			BlockPlayer player = board.getPlayer();
			BlockBox box = (BlockBox) tempHist.get(2);
			tempFutu = Arrays.asList(player.getXPosition(), player.getYPosition(), box, box.getXPosition(), box.getYPosition());
		} catch (ArrayIndexOutOfBoundsException e) {
			BlockPlayer player = board.getPlayer();
			tempFutu = Arrays.asList(player.getXPosition(), player.getYPosition());
		}
		future.push(tempFutu);
		
		return tempHist;
	}
	
	public List<Object> popFuture(Board board) {
		List<Object> tempFutu = future.pop();
		List<Object> tempHist;
		try {
			BlockPlayer player = board.getPlayer();
			BlockBox box = (BlockBox) tempFutu.get(2);
			tempHist = Arrays.asList(player.getXPosition(), player.getYPosition(), box, box.getXPosition(), box.getYPosition());
		} catch (ArrayIndexOutOfBoundsException e) {
			BlockPlayer player = board.getPlayer();
			tempHist = Arrays.asList(player.getXPosition(), player.getYPosition());
		}
		history.push(tempHist);
		
		return tempFutu;
	}
	
	public int size() {
		return history.size();
	}
	
	public int nPushes() {
		int n = 0;
		for (List<Object> temp : history) {
			try {
				temp.get(2);
				n++;
			} catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}
		}
		return n;
	}
	
}
