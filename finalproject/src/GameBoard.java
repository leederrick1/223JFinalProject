import java.nio.file.*;
import java.io.*;
import static java.nio.file.StandardOpenOption.*;
import java.util.Scanner;
public class GameBoard {
	// logic behind the board game
	private int [] [] gameBoard = new int [8][8];
	private int count=1;
	private String curPlayer = "1";
	private boolean didSomeoneWinAlready = false;
	//file writing object
	Path file = Paths.get("/Users/derickleee/eclipse-workspace/finalproject/src/Save.txt");
	String s;
	String delimiter = ",";
	public GameBoard() {
		for(int i = 0; i < 8; ++i) {
			for(int j = 0; j < 8; ++j) {
				gameBoard[i][j]=0;
			}
		}
	}
	public int gameWinCheck(int rows, int cols) {
		if(verticalWin(rows,cols) == true || horizontalWin(rows,cols) == true || posDiagonalWin(rows,cols) == true || negDiagonalWin(rows,cols) == true) {
			if(getPlayer()=="2") {
				return 2;
			}
			else { 
				return 1;
			}
		}
		else {
			return 0;
		}
	}
	public void setAlreadyWin() {
		didSomeoneWinAlready = true;
	}
	public boolean getAlreadyWin() {
		return didSomeoneWinAlready;
	}
	public boolean verticalWin(int row, int col) {
		boolean top3 = false;
		boolean top2 = false;
		boolean top1 = false;
		boolean bot3 = false;
		boolean bot2 = false;
		boolean bot1 = false;
		int runUp = 0;
		int runDown = 0;
		//checks verically for 4 in a row
		for(int i = 1; i < 4; i++) {
			if(row-i>=0) {
				runUp++;
			}
			if(row+i<=7) {
				runDown++;
			}
		}
		if(runUp != 0) {
			if(runUp >= 1 &&gameBoard[row][col] == gameBoard[row-1][col]) {
				top1=true;
				if(runUp >= 2 &&gameBoard[row][col] == gameBoard[row-2][col]) {
					top2 = true;
					if(runUp >= 3 && gameBoard[row][col] == gameBoard[row-3][col]) {
						top3=true;
						return true;
					}
				}
			}
		}
		if(runDown != 0) {
			if(runDown >= 1 && gameBoard[row][col] == gameBoard[row+1][col]) {
				bot1=true;
				if(top2 == true) {
					return true;
				}
				if(runDown >= 2 && gameBoard[row][col] == gameBoard[row+2][col]) {
					bot2 = true;
					if(top1 == true) {
						return true;
					}
					if(runDown >= 3 && gameBoard[row][col] == gameBoard[row+3][col]) {
						bot3=true;
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean horizontalWin(int row, int col) {
		boolean left3 = false;
		boolean left2 = false;
		boolean left1 = false;
		boolean right3 = false;
		boolean right2 = false;
		boolean right1 = false;
		int runLeft = 0;
		int runRight = 0;
		for(int i = 1; i < 4; i++) {
			if(col-i>=0) {
				runLeft++;
			}
			if(col+i<=7) {
				runRight++;
			}
		}
		if(runLeft != 0) {
			if(runLeft >= 1 &&gameBoard[row][col] == gameBoard[row][col-1]) {
				left1=true;
				if(runLeft >= 2 &&gameBoard[row][col] == gameBoard[row][col-2]) {
					left2 = true;
					if(runLeft >= 3 && gameBoard[row][col] == gameBoard[row][col-3]) {
						left3=true;
						return true;
					}
				}
			}
		}
		if(runRight != 0) {
			if(runRight >= 1 && gameBoard[row][col] == gameBoard[row][col+1]) {
				right1=true;
				if(left2 == true) {
					return true;
				}
				if(runRight >= 2 && gameBoard[row][col] == gameBoard[row][col+2]) {
					right2 = true;
					if(left1 == true) {
						return true;
					}
					if(runRight >= 3 && gameBoard[row][col] == gameBoard[row][col+3]) {
						right3=true;
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean posDiagonalWin(int row, int col) {
		boolean upRight3 = false;
		boolean upRight2 = false;
		boolean upRight1 = false;
		boolean downLeft3 = false;
		boolean downLeft2 = false;
		boolean downLeft1 = false;
		int runUpRight = 0;
		int runDownLeft = 0;
		for(int i = 0; i<4; i++) {
			if(col+i<=6 && row-i>=1) {
				runUpRight++;
			}
			if(col-i>=1 && row+i<=6) {
				runDownLeft++;
			}
		}
		if(runUpRight!=0) {
			if(runUpRight >= 1 && gameBoard[row][col] == gameBoard[row-1][col+1]) {
				upRight1=true;
				if(runUpRight >= 2 && gameBoard[row][col] == gameBoard[row-2][col+2]) {
					upRight2 = true;
					if(runUpRight >= 3 && gameBoard[row][col] == gameBoard[row-3][col+3]) {
						upRight3=true;
						return true;
					}
				}
			}
		}
		if(runDownLeft!=0) {
			if(runDownLeft >= 1 && gameBoard[row][col] == gameBoard[row+1][col-1]) {
				downLeft1=true;
				if(upRight2 == true) {
					return true;
				}
				if(runDownLeft >= 2 && gameBoard[row][col] == gameBoard[row+2][col-2]) {
					downLeft2 = true;
					if(upRight1 == true) {
						return true;
					}
					if(runDownLeft >= 3 && gameBoard[row][col] == gameBoard[row+3][col-3]) {
						downLeft3=true;
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean negDiagonalWin(int row, int col) {
		boolean downRight3 = false;
		boolean downRight2 = false;
		boolean downRight1 = false;
		boolean upLeft3 = false;
		boolean upLeft2 = false;
		boolean upLeft1 = false;
		int runDownRight = 0;
		int runUpLeft = 0;
		for(int i = 0; i<4; i++) {
			if(col-i>=1 && row-i>=1) {
				runUpLeft++;
			}
			if(col+i<=6 && row+i<=6) {
				runDownRight++;
			}
		}
		if(runDownRight!=0) {
			if(runDownRight >= 1 && gameBoard[row][col] == gameBoard[row+1][col+1]) {
				downRight1=true;
				if(runDownRight >= 2 && gameBoard[row][col] == gameBoard[row+2][col+2]) {
					downRight2 = true;
					if(runDownRight >= 3 && gameBoard[row][col] == gameBoard[row+3][col+3]) {
						downRight3=true;
						return true;
					}
				}
			}
		}
		if(runUpLeft!=0) {
			if(runUpLeft >= 1 && gameBoard[row][col] == gameBoard[row-1][col-1]) {
				downRight1=true;
				if(downRight2 == true) {
					return true;
				}
				if(runUpLeft >= 2 && gameBoard[row][col] == gameBoard[row-2][col-2]) {
					downRight2 = true;
					if(downRight1 == true) {
						return true;
					}
					if(runUpLeft >= 3 && gameBoard[row][col] == gameBoard[row-3][col-3]) {
						downRight3=true;
						return true;
					}
				}
			}
		}
		
		return false;
	}
	public boolean isColFull(int num) {
		for(int i = 7; i>=0; --i) {
			if(gameBoard[i][num]== 0) {
				return false;
			}
		}
		return true;
	}
	public void dropSpot(int rows, int cols) {
		if(getPlayer()=="1") {
			gameBoard[rows][cols]=1;
		}
		else if(getPlayer()=="2") {
			gameBoard[rows][cols]=2;
		}
		if(getAlreadyWin()!=true) {
			count++;
		}
	}
	public int findSpot(int col) {
		int spot=0;
		for(int i = 7; i>=0; --i) {
			if(gameBoard[i][col]== 0) {
				spot = i;
				dropSpot(i,col);
				break;
			}
		}
		return spot;
	}
	public int getCount() {
		return count;
	}
	public String getPlayer() {
		if(count%2==0) {
			curPlayer = "2";
			return curPlayer;
		}
		else {
			curPlayer = "1";
			return curPlayer;
		}
	}
	//game save
	public void gameSave() {
		int place;
		try
		{
		OutputStream output = new
		BufferedOutputStream(Files.newOutputStream(file, CREATE));
		BufferedWriter writer = new
		BufferedWriter(new OutputStreamWriter(output));
		s="Player: " + getPlayer() + " Won";
		writer.write(s, 0, s.length());
		writer.newLine();
		s="";
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				place =gameBoard[i][j];
				s += String.valueOf(place);
				if(j!=7) {
					s+=delimiter;
				}
			}
			writer.write(s, 0, s.length());
			writer.newLine();
			s="";
		}
		writer.close();
		}
		catch(Exception e)
		{
		System.out.println("Message: " + e);
		}
	}
}