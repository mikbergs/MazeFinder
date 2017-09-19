package view;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;
import javax.swing.border.*;

import controller.BoardDisplay;
import controller.Maze;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class Gui
{
	private static String VERSION = "1.1";
    // fields:
    private JFrame frame;
    private JTextField widthField = new JTextField();
    private JTextField heightField = new JTextField();
    private JButton createButton;
    private JButton searchButton;
    private JButton quitButton;    
    private int width = 0, height = 0;
    private Canvas canvas;
    private Maze maze;
	private BoardDisplay display;
    
    /**
     * Create an Maze explorer and display its GUI on screen.
     */
    public Gui()
    {
        makeFrame();
    }

    /**
     * Quit function: quit the application.
     */
    private void quit()
    {
        System.exit(0);
    }
    
    // Some dialogues for demo and testing
    
    /**
     * 'About' function: show the 'about' box.
     */
    private void showAbout()
    {
        JOptionPane.showMessageDialog(frame, 
                    "Maze\n" + VERSION,
                    "About Maze", 
                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showMessage(String message)
    {
        JOptionPane.showMessageDialog(frame, 
                    message,
                    "GUI-demo", 
                    JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * 'About' function: show the 'about' box.
     */
    private void showValues( int w, int h )
    {
        JOptionPane.showMessageDialog(frame, 
                    "Width: " + w + ", height: " + h,
                    "GUI-demo (REMOVE WHEN FINISHED!)", 
                    JOptionPane.INFORMATION_MESSAGE);
    }
    

    /**
     * Create a new maze in the graphics pane.
     */
    private void createMaze()
    {
//    	Develop this method!
		canvas.erase();
		maze = new Maze(width, height);
		display = new BoardDisplay(canvas, width, height);
		maze.addObserver(display);
		maze.create();
    	searchButton.setEnabled(true);
    	
    }

    /**
     * Display a graphical search through the maze.
     */
    private void searchMaze()
    {
    	maze.search();
    }
      
    // ---- Swing stuff to build the frame and all its components and menus ----
    
    /**
     * Create the Swing frame and its content.
     */
    private void makeFrame()
    {
        frame = new JFrame("Maze explorer");
        JPanel contentPane = (JPanel)frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
       
        // Specify the layout manager with nice spacing
        contentPane.setLayout(new BorderLayout(6, 6));
        
        // Create the maze canvas to the right
        canvas = new Canvas(frame,Color.lightGray);
        contentPane.add(canvas);
       
        // Create the toolbar with the buttons
        JPanel toolbar = new JPanel();
        toolbar.setLayout(new GridLayout(7, 2, 10, 10));
        
        widthField.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		createButton.setEnabled(false);
        		searchButton.setEnabled(false);
        		width = 0;
        		String text = widthField.getText();
        		if ( text != null && text.length() > 0 ) {
        			try {
        				width = Integer.parseInt(text);	
        			}
        			catch (NumberFormatException x) {}
        			if ( width > 0 && height > 0 )
        				createButton.setEnabled(true);
        		} 
        	}
        });
        toolbar.add(new JLabel("width"));
        toolbar.add(widthField);      
        
        heightField.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { 
        		createButton.setEnabled(false);
        		searchButton.setEnabled(false);
        		height = 0;
        		String text = heightField.getText();
        		if ( text != null && text.length() > 0 ) {
        			try {
        				height = Integer.parseInt(text);
        			}
        			catch (NumberFormatException x) {}
        			if ( width > 0 && height > 0 )
        				createButton.setEnabled(true);
        		} 
        	}
        });
        toolbar.add(new JLabel("height"));
        toolbar.add(heightField);      
        
        createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { createMaze(); }
        });
        createButton.setEnabled(false);
        toolbar.add(createButton);
        
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { searchMaze(); }
        });
        searchButton.setEnabled(false);
        toolbar.add(searchButton);
        
        quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) { quit(); }
        });
        quitButton.setEnabled(true);
        toolbar.add(quitButton);

        // Add toolbar into panel with flow layout for spacing
        JPanel flow = new JPanel();
        flow.add(toolbar);
        
        contentPane.add(flow, BorderLayout.WEST);
        
        // building is done - arrange the components      
        frame.pack();
        
        // place the frame at the center of the screen and show
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(d.width,d.height - 40);
        frame.setVisible(true);
        canvas.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
    }
        
	public static void main(String[] args) {
		new Gui();
	}
}
