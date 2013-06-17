
package core;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Posit extends JPanel
{

	private Image image;
	private JPanel innerPanel = new JPanel();
	private JTextArea txtArea = new JTextArea(8,12);;

	private int screenX = 0;
	private int screenY = 0;
	private int myX = 0;
	private int myY = 0;
	private Color col = new Color(246,238,130);
	private Color textAreaColor=new Color(255,237,151);
	public Posit(String text)
	{
		setVisible(true);
		setLayout(null);
		setBounds(0, 0, 160, 160);
		setBackground(new Color(0,0,0,0));
		addMouseListener(new panelMouseListener());
	    addMouseMotionListener(new PositDragHandler());
	    
		txtArea.setBackground(textAreaColor);
		Font f =new Font("", Font.HANGING_BASELINE, 14);
		txtArea.setFont(f);
		txtArea.setWrapStyleWord(true);
		txtArea.setLineWrap(true);
		txtArea.addMouseListener(new textAreaMouseListener());
		txtArea.addMouseMotionListener(new PositDragHandler());
		
		innerPanel.setBounds(11, 8, 135, 135);
		innerPanel.setBackground(col);
		innerPanel.add(txtArea);	
		innerPanel.addMouseListener(new panelMouseListener());
		innerPanel.addMouseMotionListener(new PositDragHandler());
	    
	    add(innerPanel);
	}


	@Override public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setColor(new Color(255,255,0,150));
        try {
            image = ImageIO.read(getClass().getResource("/icons/postit2.png"));
        }
        catch (Exception ex) {
            //System.out.println("No postit.png");
        }
        g.drawImage(image, 0, 0, 160, 160, null);
        
        repaint();
    }
	 private class panelMouseListener extends MouseAdapter
	 {
	        @Override
	        public void mouseClicked(MouseEvent e)
	        {
	        	
	        	txtArea.setVisible(false);
	    		txtArea.setBackground(col);
	        	txtArea.setEditable(false);
	        	txtArea.setVisible(true);
	    		if(e.getClickCount()==2)
				{
		        	txtArea.setEditable(true);
		    		txtArea.setBackground(textAreaColor);
		        	
				}
	        }
	        @Override
	        public void mousePressed(MouseEvent e) {
	          screenX = e.getXOnScreen();
	          screenY = e.getYOnScreen();

	          myX = getX();
	          myY = getY();
	        }
	 }
	 private class PositDragHandler extends MouseAdapter
	 {
	        @Override
	        public void mouseDragged(MouseEvent e)
	        {
	        
	          int deltaX = e.getXOnScreen() - screenX;
	          int deltaY = e.getYOnScreen() - screenY;

	          setLocation(myX + deltaX, myY + deltaY);
	        }
	 }
    private class textAreaMouseListener extends MouseAdapter
    {
    	
		@Override
		public void mouseClicked(MouseEvent e) 
		{
			if(e.getClickCount()==2)
			{
				txtArea.setBackground(textAreaColor);
				txtArea.setEditable(true);
	        	
	        	txtArea.setVisible(true);
			}
		}
    }
    

}