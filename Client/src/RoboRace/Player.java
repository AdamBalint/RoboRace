package RoboRace;

import COSC3P40.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Player extends JFrame implements Runnable {
	
	private String name;
	private Board board;
	private Port port;
	private BoardCanvas boardCanvas;
	private CardPane cardPane;
	public static GameSoundManager gsm;
  
  
	public Player(String name, Port port) {
    gsm = new GameSoundManager();
    gsm.setSoundPath("./Sounds&Midi/");
    
		this.name = name;
		board = null;
		this.port = port;
		setTitle(name);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		(new Thread(this)).start();    
	}
	
	public void run() {
    String boards = port.recieve();
    System.out.println("Board String: " + boards);
    
		board = Board.read(new StringReader(boards));
		boardCanvas = new BoardCanvas(board);
		cardPane = new CardPane();
		getContentPane().add("North",boardCanvas);
		getContentPane().add("South",cardPane);
		pack();
		setResizable(false);
		setVisible(true);
		boardCanvas.start();
		CardList cards;
		EventList events = new EventList();
		while (!events.containsVictory()) {
			board.revitalize();
			cards = CardList.read(new StringReader(port.recieve()));
			cards = cardPane.selectCards(cards);
			port.send(cards.toXMLString());
			events = EventList.read(new StringReader(port.recieve()));
			events.execute(board);
		};
		GameDialogs.showMessageDialog("End of Game","The winner is " + events.getWinner() + "!!!");	
		boardCanvas.stop();
		setVisible(false);
		dispose();
		try {
			port.close();
		} catch (Exception e) {};
    System.exit(0);
	}
	
}