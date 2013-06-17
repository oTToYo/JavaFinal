package core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class DragArea extends JPanel{
	Drawing D ;
	JWindow window;
	FunctionBoard control;
	DragArea(JWindow J,FunctionBoard f) {
		window = J;
		control = f;
		setBackground(Color.white);//  背景設為白色
		addMouseListener(new MouseModel());// 添加滑鼠事件
		addMouseMotionListener(new MouseMotionModel());
		createNewitem();
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		D.draw(g2d);
	}
	void createNewitem()
	{
		D = new Rect();
	}
	

	class MouseModel extends MouseAdapter
	{
		@Override
		public void mouseEntered(MouseEvent me) 
		{
			// TODO 鼠標進入
		}

		@Override
		public void mouseExited(MouseEvent me) 
		{
			// TODO 鼠標退出
		}

		@Override
		public void mousePressed(MouseEvent me) 
		{
			// TODO 鼠標按下
			createNewitem();//創建圖形單元
			System.out.println("鼠标按下在：["+me.getX()+" ,"+me.getY()+"]");
				D.x1 = D.x2 = me.getX();
				D.y1 = D.y2 = me.getY();
				repaint();
				
		}

		@Override
		public void mouseReleased(MouseEvent me) 
		{
			// TODO 鼠標鬆開
			D.x2 = me.getX();
			D.y2 = me.getY();
			System.out.println("鼠標鬆開在：["+D.x2+" ,"+D.y2+"]");
			int x1=D.x1;
			int x2=D.x2;
			int y1 = D.y1;
			int y2 = D.y2;
		    repaint();
		    if(x1!=x2&&y1!=y2)
		    {
			createNewitem();//創建圖形單元
			window.dispose();
			GuiCamera cam = new GuiCamera("tempImg", "png");
			BufferedImage snapShotImage = cam.snapShot(x1,y1,x2,y2);
			
			//JFrame showImage = new JFrame("是否儲存?");
			//String path = System.getProperty("user.dir")+"/tempImg.png";//儲存在使用者執行程式擋下目錄
			ImagePanelJFrame showImage = new ImagePanelJFrame("是否儲存?",snapShotImage,control);	
		    }
		}
	}
	
	class MouseMotionModel extends MouseMotionAdapter {
	      public void mouseDragged(MouseEvent me)//鼠標拖動
	      {
	    	  D.x2 = me.getX();
	    	  D.y2 = me.getY();
	    	  repaint();
	      }
	      public void mouseMoved(MouseEvent me)//鼠標移動
	      {
	    	 
	      }
		}
}
