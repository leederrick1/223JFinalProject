import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
public class Interface extends JFrame implements KeyListener, ActionListener {
	//gui interface so displays logic
	private GameBoard board = new GameBoard();
	private boolean winFlag = false ;
	//menu bar
	private JMenuBar menu = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenuItem exit = new JMenuItem("Exit");
	private JMenuItem save = new JMenuItem("Save");
	private JMenu color	= new JMenu("Colors");
	private JMenuItem ry = new JMenuItem("red/yellow");
	private JMenuItem pd = new JMenuItem("penny/dime");
	private JMenuItem emotes = new JMenuItem("happy/sad");
	private JMenu options = new JMenu("Options");
	private JMenuItem back = new JMenuItem("GameBoard");
	private JMenuItem foward = new JMenuItem("Winner");
	//game board variables
	private final int ROWS = 8;
	private final int COLS = 8;
	private final int GAP = 0;
	private final int HEIGHT = 1000;
	private final int WIDTH = 1000;
	private Color paneColor = Color.blue.brighter();
	private JPanel [][] tokenPanel = new JPanel [8][8]; 
	private JLabel [][] token = new JLabel [8][8];
	private JPanel guiBoard = new JPanel(new GridLayout(ROWS,COLS,GAP,GAP));
	// images for the tokens
	private ImageIcon blankIcon = new ImageIcon("/Users/derickleee/eclipse-workspace/finalproject/src/IMAGES/blanktoken.png");
	private Image blank = blankIcon.getImage().getScaledInstance(80,80,java.awt.Image.SCALE_SMOOTH);
	private ImageIcon redTokenIcon = new ImageIcon("/Users/derickleee/eclipse-workspace/finalproject/src/IMAGES/red.png");
	private Image redToken = redTokenIcon.getImage().getScaledInstance(90,90,java.awt.Image.SCALE_SMOOTH);
	private ImageIcon yellowTokenIcon = new ImageIcon("/Users/derickleee/eclipse-workspace/finalproject/src/IMAGES/yellow.png");
	private Image yellowToken = yellowTokenIcon.getImage().getScaledInstance(90,90,java.awt.Image.SCALE_SMOOTH);
	private ImageIcon dimeTokenIcon = new ImageIcon("/Users/derickleee/eclipse-workspace/finalproject/src/IMAGES/dime.png");
	private Image dimeToken = dimeTokenIcon.getImage().getScaledInstance(91,91,java.awt.Image.SCALE_SMOOTH);
	private ImageIcon pennyTokenIcon = new ImageIcon("/Users/derickleee/eclipse-workspace/finalproject/src/IMAGES/penny.png");
	private Image pennyToken = pennyTokenIcon.getImage().getScaledInstance(91,91,java.awt.Image.SCALE_SMOOTH);
	private ImageIcon happyTokenIcon = new ImageIcon("/Users/derickleee/eclipse-workspace/finalproject/src/IMAGES/happy.png");
	private Image happyToken = happyTokenIcon.getImage().getScaledInstance(91,91,java.awt.Image.SCALE_SMOOTH);
	private ImageIcon sadTokenIcon = new ImageIcon("/Users/derickleee/eclipse-workspace/finalproject/src/IMAGES/crying.png");
	private Image sadToken = sadTokenIcon.getImage().getScaledInstance(91,91,java.awt.Image.SCALE_SMOOTH);
	private Image prev;
	private Image currentImage;
	private Image player1Token;
	private Image player2Token;
	private ImageIcon cryIcon = new ImageIcon("/Users/derickleee/eclipse-workspace/finalproject/src/IMAGES/Cry.png");
	private Image winneremote1 = cryIcon.getImage().getScaledInstance(200,200,java.awt.Image.SCALE_SMOOTH);
	private Image winneremote2 = cryIcon.getImage().getScaledInstance(200,200,java.awt.Image.SCALE_SMOOTH);
	private Image winneremote3 = cryIcon.getImage().getScaledInstance(200,200,java.awt.Image.SCALE_SMOOTH);
	//winner panel style
	private JPanel style4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel style5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel style6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JLabel slabel1 = new JLabel();
	private JLabel slabel2 = new JLabel();
	private JLabel slabel3 = new JLabel();
	//white circle
	//6 different color options
	//top array
	private JPanel [] selectArea = new JPanel[8];
	private JLabel [] selectToken = new JLabel [8];
	private JPanel  dropper = new JPanel(new GridLayout(1,8,GAP,GAP));
	private int curRow = 0;
	private int temps;
	//bottom panel
	private JPanel info = new JPanel(new GridLayout(1,3,GAP,GAP));
	private JPanel playerNumPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JLabel playerNum = new JLabel("Player: "+board.getPlayer()+" turn");
	private JLabel playerNum2 = new JLabel("Player: "+board.getPlayer()+" turn");
	private JPanel turnNumbPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JLabel turnNumb = new JLabel("Turn: " + board.getCount());
	private JLabel turnNumb2 = new JLabel("Turn: " + board.getCount());
	private JPanel winnerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JLabel winner= new JLabel("Winner: no one yet");
	private JLabel winner2= new JLabel("Winner: no one yet");
	//winnerPanel's style panels
	private JPanel style1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel style2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JPanel style3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
	//panels to switch from
	private CardLayout myCardLayout = new CardLayout();
	private JPanel gamePanel = new JPanel(new BorderLayout());
	private JPanel winPanelTop = new JPanel(new GridLayout(1,3,GAP,GAP));
	private JPanel winPanelBot= new JPanel(new GridLayout(3,1,GAP,GAP));
	private JPanel win = new JPanel(new GridLayout(2,1,GAP,GAP));
	public Interface()	{
		super("CONNECT 4");
		setSize(HEIGHT,WIDTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(myCardLayout);
		setJMenuBar(menu);
		for(int x=0; x<8; ++x) {
			selectArea[x] = new JPanel(new FlowLayout(FlowLayout.CENTER));
			selectArea[x].setBackground(Color.green.darker());
			selectToken[x] = new JLabel();
			selectToken[x].setIcon(new ImageIcon(blank));
			selectArea[x].add(selectToken[x]);
			dropper.add(selectArea[x]);
			for(int i=0; i<8; ++i) {
				tokenPanel[x][i] = new JPanel();
				tokenPanel[x][i].setBackground(paneColor);
				token[x][i] = new JLabel();
				token[x][i].setIcon(new ImageIcon(blank));
				tokenPanel[x][i].add(token[x][i]);
				guiBoard.add(tokenPanel[x][i]);
			}
		}
		//bottom info panel
		winnerPanel.add(winner);
		turnNumbPanel.add(turnNumb);
		playerNumPanel.add(playerNum);
		info.add(playerNumPanel);
		info.add(turnNumbPanel);
		info.add(winnerPanel);
		//adding items into menubar
		file.add(exit);
		file.add(save);
		menu.add(file);
		color.add(ry);
		color.add(pd);
		color.add(emotes);
		menu.add(color);
		options.add(back);
		options.add(foward);
		menu.add(options);
		//creating starting point
		player1Token = yellowToken;
		player2Token = redToken;
		prev = blank;
		currentImage = player1Token;
		selectToken[curRow].setIcon(new ImageIcon(currentImage));
		playerNum.setText("Player: "+ board.getPlayer()+ " turn");
		//winnerpanel
		playerNum2.setFont(new Font("Arial",Font.BOLD,50));
		turnNumb2.setFont(new Font("Arial",Font.BOLD,50));
		winner2.setFont(new Font("Arial",Font.BOLD,50));
		style1.add(playerNum2);
		style2.add(turnNumb2);
		style3.add(winner2);
		style1.setBackground(Color.orange);
		style2.setBackground(Color.cyan);
		style3.setBackground(Color.orange);
		slabel1.setIcon(new ImageIcon(winneremote1));
		slabel2.setIcon(new ImageIcon(winneremote2));
		slabel3.setIcon(new ImageIcon(winneremote2));
		style4.add(slabel1);
		style4.setBackground(Color.red.brighter());
		style5.add(slabel2);
		style5.setBackground(Color.green.brighter());
		style6.add(slabel3);
		style6.setBackground(Color.red.brighter());
		winPanelTop.add(style4);
		winPanelTop.add(style5);
		winPanelTop.add(style6);
		winPanelBot.add(style1);
		winPanelBot.add(style2);
		winPanelBot.add(style3);
		win.add(winPanelTop);
		win.add(winPanelBot);
		//game pane;
		gamePanel.add(dropper,BorderLayout.NORTH);
		gamePanel.add(guiBoard,BorderLayout.CENTER);
		gamePanel.add(info,BorderLayout.SOUTH);
		add(gamePanel, "GamePanel");
		add(win, "Winner");
		exit.addActionListener(this);
		save.addActionListener(this);
		ry.addActionListener(this);
		pd.addActionListener(this);
		emotes.addActionListener(this);
		back.addActionListener(this);
		foward.addActionListener(this);
		addKeyListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		Object source = ae.getSource();
		Container cont = getContentPane();
		if(source == exit) {
			super.dispose();
		}
		else if(source == save) {
			board.gameSave();
		}
		else if(source == ry) {
			Icon tempIcon;
			ImageIcon tempImageIcon;
			Image tempImage;
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					tempIcon = token[i][j].getIcon();
					tempImageIcon = (ImageIcon)tempIcon;
					tempImage = tempImageIcon.getImage();
					if(pennyToken == tempImage || happyToken == tempImage) {
						token[i][j].setIcon(new ImageIcon(yellowToken));
					}
					else if(dimeToken == tempImage || sadToken == tempImage) {
						token[i][j].setIcon(new ImageIcon(redToken));
					}
				}
			}
			tempIcon = selectToken[curRow].getIcon();
			tempImageIcon = (ImageIcon)tempIcon;
			tempImage = tempImageIcon.getImage();
			if(tempImage == happyToken || tempImage == pennyToken ) {
				currentImage = yellowToken;
				selectToken[curRow].setIcon(new ImageIcon(currentImage));
			}
			else if(tempImage == sadToken || tempImage == dimeToken) {
				currentImage = redToken;
				selectToken[curRow].setIcon(new ImageIcon(currentImage));
			}
			player1Token = yellowToken;
			player2Token = redToken;
		}
		else if(source == pd) {
			Icon tempIcon;
			ImageIcon tempImageIcon;
			Image tempImage;
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					tempIcon = token[i][j].getIcon();
					tempImageIcon = (ImageIcon)tempIcon;
					tempImage = tempImageIcon.getImage();
					if(yellowToken == tempImage || happyToken == tempImage) {
						token[i][j].setIcon(new ImageIcon(pennyToken));
					}
					else if(redToken == tempImage || sadToken == tempImage) {
						token[i][j].setIcon(new ImageIcon(dimeToken));
					}
				}
			}
			tempIcon = selectToken[curRow].getIcon();
			tempImageIcon = (ImageIcon)tempIcon;
			tempImage = tempImageIcon.getImage();
			if(tempImage == happyToken || tempImage == yellowToken ) {
				currentImage = pennyToken;
				selectToken[curRow].setIcon(new ImageIcon(currentImage));
			}
			else if(tempImage == sadToken || tempImage == redToken) {
				currentImage = dimeToken;
				selectToken[curRow].setIcon(new ImageIcon(currentImage));
			}
			player1Token = pennyToken;
			player2Token = dimeToken;
		}
		else if(source == emotes) {
			Icon tempIcon;
			ImageIcon tempImageIcon;
			Image tempImage;
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					tempIcon = token[i][j].getIcon();
					tempImageIcon = (ImageIcon)tempIcon;
					tempImage = tempImageIcon.getImage();
					if(yellowToken == tempImage || pennyToken == tempImage) {
						token[i][j].setIcon(new ImageIcon(happyToken));
					}
					else if(redToken == tempImage || dimeToken == tempImage) {
						token[i][j].setIcon(new ImageIcon(sadToken));
					}
				}
			}
			tempIcon = selectToken[curRow].getIcon();
			tempImageIcon = (ImageIcon)tempIcon;
			tempImage = tempImageIcon.getImage();
			if(tempImage == pennyToken || tempImage == yellowToken ) {
				currentImage = happyToken;
				selectToken[curRow].setIcon(new ImageIcon(currentImage));
			}
			else if(tempImage == dimeToken || tempImage == redToken) {
				currentImage = sadToken;
				selectToken[curRow].setIcon(new ImageIcon(currentImage));
			}
			player1Token = happyToken;
			player2Token = sadToken;
		}
		else if(source == back) {
			myCardLayout.show(getContentPane(), "GamePanel");
		}
		else if(source == foward) {
			myCardLayout.show(getContentPane(), "Winner");
		}
	}
	@Override
	public void keyTyped(KeyEvent ke) {
		
	}
	@Override
	public void keyPressed(KeyEvent ke) {
		int keycode = ke.getKeyCode();
		if(keycode == KeyEvent.VK_SPACE) {
			if(board.getAlreadyWin() != true) {
				if(board.isColFull(curRow) != true) {
					temps=board.findSpot(curRow);
					token[temps][curRow].setIcon(new ImageIcon(currentImage));
					if(board.gameWinCheck(temps,curRow) == 1) {
						if(board.getAlreadyWin()==false) {
							winner.setText("Player: 2 WINS");
							winner2.setText("Player: 2 WINS");
							board.setAlreadyWin();
							board.gameSave();
							myCardLayout.show(getContentPane(), "Winner");
						}
					}
					else if(board.gameWinCheck(temps,curRow)==2) {
						if(board.getAlreadyWin()==false) {
							winner.setText("Player: 1 WINS");
							winner2.setText("Player: 1 WINS");
							board.setAlreadyWin();
							board.gameSave();
							myCardLayout.show(getContentPane(), "Winner");
						}
					}
					playerNum.setText("Player: "+board.getPlayer() +" turn");
					playerNum2.setText("Player: "+board.getPlayer()+" turn");
					turnNumb.setText("Turn: "+ board.getCount());
					turnNumb2.setText("Turn: "+ board.getCount());
					if(board.getPlayer()=="2") {
						currentImage=player2Token;
					}
					else if(board.getPlayer()=="1") {
						currentImage=player1Token;
					}
					selectToken[curRow].setIcon(new ImageIcon(currentImage));
				}
			}
		}
		else if(keycode == KeyEvent.VK_LEFT) {
			if(curRow != 0) {
				if(board.getAlreadyWin() != true) {
					selectToken[curRow].setIcon(new ImageIcon(prev));
					curRow--;
					selectToken[curRow].setIcon(new ImageIcon(currentImage));
				}
			}
		}
		else if (keycode == KeyEvent.VK_RIGHT) {
			if(curRow != 7) {
				if(board.getAlreadyWin() != true) {
					selectToken[curRow].setIcon(new ImageIcon(prev));
					curRow++;
					selectToken[curRow].setIcon(new ImageIcon(currentImage));
				}
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent ke) {
		
	}
}