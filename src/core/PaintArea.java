package core;

import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;



public class PaintArea extends JPanel{
	class Data //a data structure that contain the color and size
	{
		private Color color;
		private int size;
		private String type;
		private int x,y;
		public Data()
		{
			this.color = Color.BLACK;
			this.size = 5;
			this.type = "PEN";
		}
		public Data(int x,int y ,String type)
		{
			this.x =x;
			this.y =y;
			this.type =  type;
		}
		public Data(Color color, int size,String type)
		{
			this.color = color;
			this.size = size;
			this.type = type;
		}
		public Color getColor()
		{
			return this.color;
		}
		public int getSize()
		{
			return this.size;
		}
		public String getType()
		{
			return this.type;
		}
		public Point getPoint()
		{
			return new Point(x,y);
		}
	}
	//paint used var
	private static int mode;
	private static Color color = Color.black; //the color now
	private static int paintSize; //the size now
	private Vector<Object> node = new Vector<Object>();//store every point
	private Vector<Data> data = new Vector<Data>();//store every point's data
	private int x1,x2,y1,y2;
	private boolean lineDrag;
	private boolean IsPaintUnlocked;
	private static JMenuItem pen;
	//image used var
	
	int moveX;
	int moveY;
	boolean checkDrag;
	ArrayList<JLabel> JLabelArray;
	ArrayList<BufferedImage> imageArray;
	JLabel currentLabel;
	public PaintArea(JMenuItem pen)
	{
		this.pen = pen;
		setLayout(null);
		setBackground(Color.white);
		paintSize = 8;//初始化畫筆大小為10
		
		
		setName("繪圖板");
		this.addMouseListener(new MouseProcess());
		this.addMouseMotionListener(new MouseMotionProcess());
		this.addKeyListener(new deletekey());
		 JLabelArray = new ArrayList<JLabel>();
		 imageArray = new ArrayList<BufferedImage>();
	}
	//**********************************************************
	//paint used function
	public int getMode()
	{
		return mode;
	}
	public static void setMode(int newMode)
	{
		mode = newMode;	
		pen.doClick();
	
	}
	public static void setColor(Color newColor)
	{
		color = newColor;
	}
	public static void setPaintSize(int newSize)
	{
		paintSize = newSize;
	}
	public static Color getColor()
	{
		return color;
	}
	public void removeAll()
	{
		data.removeAllElements();
		node.removeAllElements();
		this.repaint();
	}
	public void setPaintLock(boolean isUnlocked)
	{
		IsPaintUnlocked = isUnlocked;
	}
	
	//*********************************************************
	//image used function
	public void setJLabel(JLabel label)
	{
		JLabelArray.add(label);
	}
	public void setImage(BufferedImage img)
	{
		imageArray.add(img);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		draw(g);

	}
	private class MouseProcess extends MouseAdapter  
    {  
        public void mouseClicked(MouseEvent e)  
        { 
        
        	 
        }  
        public void  mousePressed(MouseEvent e)  
        {  
        	
        	Component c =e.getComponent();
        	currentLabel =null; //設定當前沒有點擊JLabel
        	for(JLabel obj:JLabelArray)//讓每個jlabe邊框變為白色
        	{
        		obj.setBorder(BorderFactory.createLineBorder(Color.white));
        	}
        	if(IsPaintUnlocked)
        	{
	        	if(mode == 0)//mode is pen
	    		{
	    			data.addElement(new Data(color,paintSize,"PEN"));
	    			node.addElement(new Rectangle(e.getX(),e.getY(),e.getX(),e.getY()));
	    			x1 = e.getX();
	    			y1 = e.getY();
	    			repaint();
	    		}
	    		else if(mode == 1)//mode is eraser
	    		{
	    			data.addElement(new Data(Color.WHITE,paintSize,"ERASER"));
	    			node.addElement(new Rectangle(e.getX(),e.getY(),e.getX(),e.getY()));
	    			x1 = e.getX();
	    			y1 = e.getY();
	    			repaint();
	    		}
	    		else if(mode == 2)//mode is line
	    		{
	    			lineDrag = true;
	    			x1 = e.getX();
	    			y1 = e.getY();
	    		}
        	}
        	
        	
        } 
        public void mouseReleased(MouseEvent e)
    	{
        	if(IsPaintUnlocked)
        	{
	    		if(mode == 2)
	    		{
	    			data.addElement(new Data(color,paintSize,"LINE"));
	    			node.addElement(new Rectangle(x1, y1, e.getX(), e.getY()));
	    			lineDrag = false;
	    		}
	    		repaint();
        	}
    	}
    }
	private class MouseMotionProcess extends MouseMotionAdapter  
    {
       public void mouseDragged(MouseEvent e)  
       {  
    	   if(IsPaintUnlocked)
       		{
	    	   if(mode == 0)
	   			{
	   				data.addElement(new Data(color,paintSize,"PEN"));
	   				node.addElement(new Rectangle(x1, y1, e.getX(), e.getY()));
	   				x1 = e.getX();
	   				y1 = e.getY();
	   				repaint();
	   			}
		   		else if(mode == 1)
		   		{
		   			data.addElement(new Data(Color.WHITE,paintSize,"ERASER"));
		   			node.addElement(new Rectangle(x1,y1,e.getX(),e.getY()));
		   			x1 = e.getX();
		   			y1 = e.getY();
		   			repaint();
		   		}
		   		else if(mode == 2)
		   		{
		   			x2 = e.getX();
		   			y2 = e.getY();
		   			repaint();
		   		}		
       		}
	     }  
    }
	public void draw(Graphics g)
	{		
		//
		Graphics2D g2d = (Graphics2D)g;
		int i;
		Rectangle p;
		for(i = 0; i<node.size(); i++)//draw every point that user has draw
		{
			if(data.elementAt(i).getType().equals("IMAGE"))
			{
				Point imgPosition = data.elementAt(i).getPoint();
				g.drawImage((Image)node.elementAt(i),imgPosition.x, imgPosition.y, this);
			}
			else
			{
				if(data.elementAt(i).getType().equals("PEN")||data.elementAt(i).getType().equals("ERASER"))
					g2d.setStroke(new BasicStroke((float)data.elementAt(i).getSize(),BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER));
				else if(data.elementAt(i).getType().equals("LINE"))
					g2d.setStroke(new BasicStroke((float)data.elementAt(i).getSize()));
				p = (Rectangle)node.elementAt(i);
				g2d.setColor(data.elementAt(i).getColor());
				g2d.drawLine(p.x, p.y, p.width, p.height);
			}
		}
		if(lineDrag == true)//the line can be move until the mouse is released.
		{
			g2d.setStroke(new BasicStroke((float)paintSize));
			g2d.setColor(color);
			g2d.drawLine(x1, y1, x2, y2);
		}
	
	}
	
	public void  lockImage()
	{
		//imageLock =true;
		for(int i=0;i<imageArray.size();i++)
		{
			data.addElement(new Data(JLabelArray.get(i).getX(),JLabelArray.get(i).getY(),"IMAGE"));
			node.addElement(imageArray.get(i));
		}
		
		for(JLabel label:JLabelArray)
			this.remove(label);
		JLabelArray = null;
		JLabelArray = new ArrayList<JLabel>();
		imageArray = null;
		imageArray = new ArrayList<BufferedImage>();
		repaint();
	}
	public void removeImage(PaintArea p)
	{
		
	}
	
	//**************************************
	//image used lintener
		private class IconProcess extends MouseAdapter  
	    {  
	        public void mouseClicked(MouseEvent e)  
	        { 
	        	
	        }  
	        public void  mousePressed(MouseEvent e)  
	        {  


	        	
	        	for(JLabel obj:JLabelArray)//讓每個jlabe邊框變為白色
	        	{
	        		obj.setBorder(BorderFactory.createLineBorder(Color.white));
	        	}
	        	JLabel c = (JLabel)e.getComponent();
	        	c.setBorder(BorderFactory.createLineBorder(Color.black));//設定邊框為黑色
	        	setComponentZOrder(c,0);
	        	currentLabel = c;
	        	moveX = c.getX();//jlabe的初始位置
	        	moveY =c.getY();
	        	checkDrag = true; //設定第一次拖拉旗標
	        	
	        	PaintArea.this.setFocusable(true);
	        	PaintArea.this.requestFocusInWindow();//取得foucus

	        } 
	    }
		
	public moveIconHandler getIconHandler()
	 {
		return  new moveIconHandler();
	 }
	public IconProcess getIconProcess()
	 {
		return  new IconProcess();
	 }
	public deletekey getKeyProcess()
	 {
		return  new deletekey();
	 }
	 private class moveIconHandler extends MouseMotionAdapter {
		 public void mouseDragged(MouseEvent e) {
		
		 Component c = e.getComponent();
		 
		 //System.out.println("!x = " +c.getX() + ", y = " +c.getY()+ "\n");
     	//System.out.println("!dx = " +e.getX() + ", dy = " +e.getY()+ "\n"); 
		
     	if(checkDrag==true)
     	{
     		c.setLocation( c.getX(),c.getY());
     		checkDrag = false;
     		moveX = e.getX();
     		moveY = e.getY();
     	}
     	
		c.setLocation(e.getX()+c.getX()-moveX, e.getY()+c.getY()-moveY);
	
		repaint();
		 } 
	 }
	 private class deletekey extends KeyAdapter
	    {
	        public void keyPressed(KeyEvent e)
	        {
	        	
	            if (e.getKeyCode()==KeyEvent.VK_DELETE) //delete
	            {
	            
	            	if(currentLabel != null)
	            	{
	            	PaintArea.this.remove(currentLabel);
	            	int imageInd = JLabelArray.indexOf(currentLabel);
	            	imageArray.remove(imageArray.get(imageInd));
		        	PaintArea.this.validate();
		        	PaintArea.this.repaint();
	            	}
		        
	            }
	            repaint();
	        }
	    }
}