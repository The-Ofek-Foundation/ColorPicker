/*	Ofek Gila
	February 3rd, 2014
	ColorPicker.java
	This program will attempt to let the user get RGB values for a color
*/
import java.awt.*;			// Imports
import java.awt.event.*;
import javax.swing.*;
import java.util.Scanner;

public class ColorPicker	extends JApplet{			// I'm pretty sure I copied down one of your online codes for key and focus listeners for their methods
	public static void main(String[] args) {	// when I made snake.java, and I copied snake.java to have all the implements for this code, so don't
		JFrame frame = new JFrame("Color Picker");	// ask why I extend JApplet or implement all of those things ^_^
		frame.setContentPane(new ContentPanel());
		frame.setSize(1250, 750);		// Sets size of frame
		frame.setResizable(false);						// Makes it so you can't resize the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public void init()	{
		setContentPane(	new ContentPanel());
	}
}
class ContentPanel extends JPanel implements ActionListener, KeyListener, FocusListener, MouseListener, MouseMotionListener	{

	public int width, height;							// width and height of frame
	public Graphics g;									// Graphics of frame
	public int x, y, saveY;										// x and y values of mouse
	public Color pen;										// the color you draw in
	public Rectangle red, blue, green;
	public boolean initial = true;
	public boolean redSelected, greenSelected, blueSelected;
	public boolean darknessSelected;
	public int B, R, G;
	public Rectangle darkness, reset;
	public int D;
	public Rectangle tangle;	// I blame my brother -_-
	public double increment;
	public boolean scan;
	public Timer t;
	
	public ContentPanel()	{
		pen = new Color(255, 255, 255);	
		scan = false;
		redSelected = greenSelected = blueSelected = false;
		darknessSelected = false;
		addKeyListener(this);							// implements the implements
		addFocusListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	public void paintComponent(Graphics a)	{
		super.paintComponent(a);
		g = a;
		width = getWidth();
		height = getHeight();	
		//pen = new Color(0, 0, 255);
		//System.out.println(pen.getRed() + " " + pen.getGreen() + " " + pen.getBlue());
		if (initial)	{
			red = new Rectangle(width - 40 * 6, height -23, 40, 20);
			blue = new Rectangle(width - 40 * 2, height-23, 40, 20);
			green = new Rectangle(width - 40 * 4,height-23, 40, 20);
			darkness = new Rectangle(width - 40*8, height / 2 - 10, 40, 20);
			tangle = new Rectangle(45, height / 4 * 3 + height / 8 - 50, 350, 100);
			reset = new Rectangle(410, height / 4 * 3 + height / 8 - 50, 350, 100);
			initial = false;
		}
		increment = (height - 30) / 255.0;
		if (scan) {	t.start(); scan = false;	}
		D = 0 - (int)((darkness.getY()+50)/((height - 30) / 510.0)) + 12 + 283;
		R = 255 - (int)((red.getY() + 50) / increment) + 20 + D;
		B = 255 - (int)((blue.getY()+50) / increment) + 20 + D;
		G = 255 - (int)((green.getY()+50)/increment) + 20 + D;
		if (R < 0) R = 0; if (R > 255) R = 255;
		if (B < 0) B = 0; if (B > 255) B = 255;
		if (G < 0) G = 0; if (G > 255) G = 255;
		//System.out.println(D);
		//System.out.println(G + " " + R + " " + B + " " + increment);
		g.setFont(new Font("Arial", Font.BOLD, 60));		
		pen = new Color(R, G, B);
		setBackground(pen);		
		drawLevers();
		pen = new Color(R, G, B);
		g.setColor(pen);
		g.drawString("Flip Colors", 50, height / 4 * 3 + height / 8 + 20);
		g.drawString("Reset", 415, height / 4 * 3 + height / 8 + 20);
		pen = new Color(255 - R, 255 - G, 255 - B);
		g.setColor(pen);
		g.drawString("RED: " + R, 50,height / 4 *1 - 50);
		g.drawString("BLUE: " + B, 50, height / 4 * 3-50);
		g.drawString("GREEN: " + G, 50, height /  4*2-50);
	}
	public void drawLevers()	{
		g.setColor(Color.gray);
		g.fillRect(width - 40 * 6 + 10, 5, 20, height - 10);  
		g.fillRect(width - 40 * 4 + 10, 5, 20, height - 10);
		g.fillRect(width - 40 * 2 + 10, 5, 20, height - 10);
		g.fillRect(width - 40 * 8 + 10, 5, 20, height - 10);
		g.setColor(Color.black);
		g.drawRect(width - 40 * 6 + 10, 5, 20, height - 10);  
		g.drawRect(width - 40 * 4 + 10, 5, 20, height - 10);
		g.drawRect(width - 40 * 2 + 10, 5, 20, height - 10);
		g.drawRect(width - 40 * 8 + 10, 5, 20, height - 10);
		g.setColor(Color.red);
		g.fillRect((int)red.getX(), (int)red.getY(), (int)red.getWidth(), (int)red.getHeight());
		g.setColor(Color.green);
		g.fillRect((int)green.getX(), (int)green.getY(), (int)green.getWidth(), (int)green.getHeight());
		g.setColor(Color.blue);
		g.fillRect((int)blue.getX(), (int)blue.getY(), (int)blue.getWidth(), (int)blue.getHeight());
		g.setColor(Color.white);
		g.fillRect((int)darkness.getX(), (int)darkness.getY(), (int)darkness.getWidth(), (int)darkness.getHeight());
		pen = new Color(255 - R, 255 - G, 255 - B);
		g.setColor(pen);
		g.fillRect((int)tangle.getX(), (int)tangle.getY(), (int)tangle.getWidth(), (int)tangle.getHeight());
		g.fillRect((int)reset.getX(), (int)reset.getY(), (int)reset.getWidth(), (int)reset.getHeight());		

		g.setColor(Color.black);
		g.drawRect((int)red.getX(), (int)red.getY(), (int)red.getWidth(), (int)red.getHeight());
		g.drawRect((int)green.getX(), (int)green.getY(), (int)green.getWidth(), (int)green.getHeight());
		g.drawRect((int)blue.getX(), (int)blue.getY(), (int)blue.getWidth(), (int)blue.getHeight());
		g.drawRect((int)darkness.getX(), (int)darkness.getY(), (int)darkness.getWidth(), (int)darkness.getHeight());	
	}
	public void mouseDragged(MouseEvent evt)	{	
		y = evt.getY();
		int colorNumber = 255 - (int)((y + 40) / increment) + 20;
		//System.out.println(y + " " + colorNumber);

		//System.out.println(R + " " + B + " " + G + " " + increment);
		//System.out.println(y + " " + (255 * increment));
		if (redSelected && colorNumber >= 0 && colorNumber <= 255)
			red = new Rectangle((int)red.getX(), y - 10, (int)red.getWidth(), (int)red.getHeight());
		if (greenSelected && colorNumber >= 0 && colorNumber <= 255)
			green = new Rectangle((int)green.getX(), y - 10, (int)green.getWidth(), (int)green.getHeight());
		if (blueSelected && colorNumber >= 0 && colorNumber <= 255)
			blue = new Rectangle((int)blue.getX(), y - 10, (int)blue.getWidth(), (int)blue.getHeight());
		if (darknessSelected && colorNumber >= 0 && colorNumber <= 255)
			darkness = new Rectangle((int)darkness.getX(), y - 10, (int)darkness.getWidth(), (int)darkness.getHeight());

		repaint();
	}
	public void mouseMoved(MouseEvent evt)	{	}
	public void actionPerformed(ActionEvent e)	{	
		int colorNumber = 255 - (int)((darkness.getY() + 40) / increment) + 20;
		if (colorNumber == 0)	{
			t.stop();
			return;
		}
		darkness = new Rectangle((int)darkness.getX(), (int)darkness.getY() + 1, (int)darkness.getWidth(), (int)darkness.getHeight());
		repaint();
	}
	public void focusGained(FocusEvent evt) {	}
	public void focusLost(FocusEvent evt) {	}
	public void keyTyped(KeyEvent evt) {	
		Scanner Keyboard = new Scanner(System.in);
		char key = evt.getKeyChar();
		if (key == 'P' || key == 'p') t.stop();
		if (key == 'u' || key == 'U') {
			t = new Timer(50, this);
			//darkness = new Rectangle(width - 40*8, 20, 40, 20);
			scan = true;
			repaint();	
		}
		if (key == 'I' || key == 'i')	{
			System.out.print("R:\t");
			red = new Rectangle((int)red.getX(), (int)((257 - Keyboard.nextDouble()) * increment), (int)red.getWidth(), (int)red.getHeight());
			System.out.print("G:\t");
			green = new Rectangle((int)green.getX(), (int)((257 - Keyboard.nextDouble()) * increment), (int)green.getWidth(), (int)green.getHeight());
			System.out.print("B:\t");
			blue = new Rectangle((int)blue.getX(), (int)((257 - Keyboard.nextDouble()) * increment), (int)blue.getWidth(), (int)blue.getHeight());
			System.out.println();
		}
		repaint();
	}
	public void keyPressed(KeyEvent evt) {	}
	public void keyReleased(KeyEvent evt) {	}
	public void mouseEntered(MouseEvent evt) { } 
	public void mousePressed(MouseEvent evt) { 	
		x = evt.getX();
		y = evt.getY();
		if (red.contains(x, y)) redSelected = true;
		if (green.contains(x, y)) greenSelected = true;	
		if (blue.contains(x, y)) blueSelected = true;		
		if (darkness.contains(x, y)) darknessSelected = true;
		if (tangle.contains(x, y)) {
			//System.out.println(height + " " + red.getHeight());
			red = new Rectangle((int)red.getX(), height - (int)red.getY() - 20, (int)red.getWidth(), (int)red.getHeight());
			blue = new Rectangle((int)blue.getX(), height - (int)blue.getY() - 20, (int)blue.getWidth(), (int)blue.getHeight());
			green = new Rectangle((int)green.getX(), height - (int)green.getY() - 20, (int)green.getWidth(), (int)green.getHeight());
			darkness = new Rectangle((int)darkness.getX(), height - (int)darkness.getY() - 20, (int)darkness.getWidth(), (int)darkness.getHeight());
		}
		if (reset.contains(x, y))	{
			//darkness = new Rectangle(width - 40*8, height / 2 - 10, 40, 20);
			initial = true;
		}
		if (redSelected && evt.isControlDown())
			red = new Rectangle(width - 40 * 6, height -23, 40, 20);
		if (blueSelected && evt.isControlDown())
			blue = new Rectangle(width - 40 * 2, height-23, 40, 20);
		if (greenSelected && evt.isControlDown())
			green = new Rectangle(width - 40 * 4,height-23, 40, 20);
		if (darknessSelected && evt.isControlDown())
			darkness = new Rectangle(width - 40*8, height / 2 - 10, 40, 20);			
		if (!(hasFocus())) requestFocus();
		repaint();

	}
    public void mouseExited(MouseEvent evt) { } 
    public void mouseReleased(MouseEvent evt) {   
    	redSelected = greenSelected = blueSelected = false;	
    	darknessSelected = false;
    } 
    public void mouseClicked(MouseEvent evt) { }
}