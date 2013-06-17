package core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ImagePanelJFrame extends JFrame implements ActionListener{  
	private String Path;
    private JButton save,cancel;
    private JToolBar bar ;
    private FunctionBoard control;
    private BufferedImage image;
	public ImagePanelJFrame(String title,BufferedImage image,FunctionBoard f) {  
		
        super(title);
        control = f;
        this.image = image;
		init();  
      
    }  
      
    public void init() { 
    	bar = new JToolBar ();
    	save = new JButton("儲存");
    	cancel = new JButton("取消");
    	bar.add(save);
    	bar.add(cancel);
    	bar.setFloatable(false);
    	save.addActionListener(this);
    	cancel.addActionListener(this);
    	 setSize(image.getWidth(),image.getHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        ImagePanel imagePanel = new ImagePanel();  
        imagePanel.setOpaque(true); //設定imagePanel组件是透明的  
        imagePanel.setBackground(Color.white); //設置背景 
      
        Container con= getContentPane();
        con.add(imagePanel,BorderLayout.CENTER);
        con.add(bar,BorderLayout.NORTH);
        
        setVisible(true);  
    }  
      
    class ImagePanel extends JPanel {  

        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public ImagePanel() {  
            super(new BorderLayout());
        }  
        public void paintComponent(Graphics g) {  
            super.paintComponent(g);  
            g.drawImage(image, 0, 0, null); //在ImagePanel進行畫圖，位置從(0,0)開始 
            
        }  
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() ==save)
		{
		
					Component component = control.getMyComponent("paint");
					PaintArea  j = (PaintArea)component;
					JLabel thumb = new JLabel();
					ImageIcon icon = new ImageIcon(image); 
					thumb.setIcon(icon);
					thumb.setLocation(0, 0);
					thumb.setSize(image.getWidth(),image.getHeight());
					//thumb.setBorder(BorderFactory.createLineBorder(Color.black));
					thumb.addMouseMotionListener(j.getIconHandler());
					thumb.addMouseListener(j.getIconProcess());
					j.setImage(image);
					j.setJLabel(thumb);
					j.add(thumb);
					
			
			
		}
			control.setVisible(true);
			dispose();

	} 
      
}  

 