package core;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JWindow;
import javax.swing.KeyStroke;





public class FunctionBoard extends JFrame implements ActionListener 
{

	private static final long serialVersionUID = 1L;
	private JToolBar toolBar ; //定義工具列
	private JMenuBar menuBar; //定義菜單列
	private JMenu file,edit,window; //定義菜單內選項
	private JMenuItem add,save,cut,mark,pen,next,last,setOnBoard,refresh,remove;//定義菜單
	private ImageIcon logoIcon,cutIcon,saveIcon,addIcon,nextIcon,lastIcon,markIcon,penIcon,setOnBoardIcon,refreshIcon,removeIcon;//File菜單內圖案
	private String names[] = {"New24","Save24","Cut24","Paint24","postIt","Last24","Next24","Import24","Refresh24","Remove24"};//定義工具欄圖示名稱
	private Icon icons[];//定義工具列圖示
	private String iconsTip[] = {"新增畫布","存成Pdf","擷取畫面","繪圖工具","便利貼","上一頁","下一頁","定位圖片","刷新頁面","移除本頁"};//定義圖示游標提示
	private JScrollPane jsPane;
	private static int panelIndex, nowPanelIndex;
	private static boolean isNew ;
	private JLabel pageLabel;
	private Toolbar markToolBar;//
	JButton button[];//定義工具列內的按鈕
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	double screenW = d.getWidth();
	double screenH = d.getHeight();
	private ArrayList<PaintArea> panels;
	FunctionBoard(String title)
	{	
		super(title);//設定應用程式標題
	
		 
		//初始化menu
		file = new JMenu("文件");
		edit = new JMenu("編輯");
		window = new JMenu("視窗");
		
		menuBar = new JMenuBar();//菜單列初始化
		
		//將菜單加入菜單列
		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(window);
		//介面中加入菜單列
		setJMenuBar(menuBar);
		//菜單中添加快捷鍵
	    file.setMnemonic('F');//Alt+F
	    //panelIndex = 0;
	   // nowPanelIndex = 0;
	    panels= new ArrayList<PaintArea>();
	    //file菜單初始化
	    /*try 
	    {
			Reader reader = new InputStreamReader(getClass().getResourceAsStream("/icons"));//讀取此專案下目錄
		} 
	    catch (Exception e) 
	    {
			JOptionPane.showMessageDialog(this,"圖片讀取錯誤","錯誤",JOptionPane.ERROR_MESSAGE);
		}*/
	    
	    //設定LOGO 
	    logoIcon = new ImageIcon(getClass().getResource("/icons/Icon.png"));
	    setIconImage(logoIcon.getImage());
	    //初始化應用程式位置與大小
	    setBounds((int)(screenW-800)/2, (int)(screenH-600)/2, 800, 600);
	    
	    addIcon = new ImageIcon(getClass().getResource("/icons/New24.gif"));
	    add = new JMenuItem("新增",addIcon);
	    
	    saveIcon = new ImageIcon(getClass().getResource("/icons/Save24.gif"));
	    save = new JMenuItem("儲存",saveIcon);
	    
	    cutIcon = new ImageIcon(getClass().getResource("/icons/Cut24.gif"));
	    cut = new JMenuItem("擷取",cutIcon);
	    
	    penIcon = new ImageIcon(getClass().getResource("/icons/Paint24.gif"));
	    pen = new JMenuItem("繪圖",penIcon);
	    
	    markIcon = new ImageIcon(getClass().getResource("/icons/postIt.gif"));
	    mark = new JMenuItem("便利貼",markIcon);
	    
	    
	    lastIcon = new ImageIcon(getClass().getResource("/icons/Last24.gif"));
	    last = new JMenuItem("上一頁",lastIcon);
	    
	    nextIcon = new ImageIcon(getClass().getResource("/icons/Next24.gif"));
	    next = new JMenuItem("下一頁",nextIcon);
	    
	    setOnBoardIcon = new ImageIcon(getClass().getResource("/icons/Import24.gif"));
	    setOnBoard = new JMenuItem("定位圖片",setOnBoardIcon);
	    
	    refreshIcon = new ImageIcon(getClass().getResource("/icons/Refresh24.gif"));
	    refresh = new JMenuItem("刷新頁面",refreshIcon);
	    
	    removeIcon = new ImageIcon(getClass().getResource("/icons/Remove24.gif"));
	    remove = new JMenuItem("移除本頁",removeIcon);
	    //File菜單內添加選項
	    file.add(add);
	    file.add(save);
	    file.add(last);
	    file.add(next);
	    //Edit菜單內添加選項
	    edit.add(cut);
	    edit.add(pen);
	    edit.add(mark);
	    edit.add(setOnBoard);
	    edit.add(refresh);
	    edit.add(remove);
	    //Window菜單內添加選項
	    JCheckBoxMenuItem checkToolbar = new JCheckBoxMenuItem("工具列");
		checkToolbar.setSelected(true);
		checkToolbar.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				int state = e.getStateChange();
				if(state == ItemEvent.SELECTED)
					toolBar.setVisible(true);
				else
					toolBar.setVisible(false);
			}
		});
		
		JCheckBoxMenuItem checkMarkToolBar = new JCheckBoxMenuItem("繪圖板");
		checkMarkToolBar.setSelected(false);
		checkMarkToolBar.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e)
			{
				int state = e.getStateChange();
				if(state == ItemEvent.SELECTED)
					markToolBar.setVisible(true);
				else
					markToolBar.setVisible(false);
			}
		});
	
		window.add(checkToolbar);
		window.add(checkMarkToolBar);
	 
	    
	    //File菜單選項添加快捷鍵
	    add.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK));
	    save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
	    cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK));
	    //File菜單選項添加監聽器
	    cut.addActionListener(this);
	    add.addActionListener(this);
	    save.addActionListener(this);
	    mark.addActionListener(this);
	    pen.addActionListener(this);
	    next.addActionListener(this);
	    last.addActionListener(this);
	    setOnBoard.addActionListener(this);
	    refresh.addActionListener(this);
	    remove.addActionListener(this);
	    
	    //工具列初始化
	    toolBar = new JToolBar( JToolBar.HORIZONTAL);//instance &設定為橫向
	    //toolBar.setRollover(true);
	    toolBar.setFloatable(false);
	    icons = new ImageIcon[names.length];
	    button = new JButton[names.length];
	    for(int i = 0 ;i<names.length;i++)
	    {
	        icons[i] = new ImageIcon(getClass().getResource("/icons/"+names[i]+".gif"));//取得圖片
	    	button[i] = new JButton("",icons[i]);//instance工具列中的按鈕
	    	button[i].setToolTipText(iconsTip[i]);//設定滑鼠對應到圖片的提示
	    	toolBar.add(button[i]);
	    	button[i].addActionListener(this);
	    }
	    //初始化顯示頁面label
	    pageLabel = new JLabel();
	    
	   // paint.setLayout(null);
	    //paint.setBounds(0, 0, 600, 300);
	    //jsPane  = new JScrollPane(paint);
	    //jsPane.setLayout(null);
	    //jsPane.setPreferredSize(new Dimension(200,200));
	  
	    //定義繪圖功能版
	    markToolBar = new Toolbar();
		markToolBar.setFloatable(false);
		markToolBar.setVisible(false);
	    //panels.add(paint);
	    Container con = getContentPane();
	    con.add(toolBar, BorderLayout.NORTH);
	   // con.add(paint,BorderLayout.CENTER);
	   
	    toolBar.add(markToolBar);
	    //con.add(markToolBar,BorderLayout.WEST);
	    setVisible(true);
	   
	    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //con.remove(jsPane);
	    //con.add(jsPane);
	    addWindowListener(new WindowAdapter(){ //加入視窗linstener
			public void windowClosing(WindowEvent event)
			{
			
				if(isNew == true) //判對是否有頁面
				{
					int input = JOptionPane.showConfirmDialog(FunctionBoard.this, "是否儲存檔案?","關閉程式",JOptionPane.YES_NO_CANCEL_OPTION);
					if (input == JOptionPane.YES_OPTION)
					{
		
						save.doClick();
						FunctionBoard.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						revalidate();
						repaint();
					}
					else if (input == JOptionPane.NO_OPTION)
					{
						FunctionBoard.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
					else if(input == JOptionPane.CANCEL_OPTION)
					{
						FunctionBoard.this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					}
				}
				else
					FunctionBoard.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			});
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//setCursor(Cursor.DEFAULT_CURSOR);
		if(e.getSource() == add||e.getSource() == button[0])//新增事件
		{
			
			if(panelIndex>0)
			{
				
				int input = JOptionPane.showConfirmDialog(null, "是否新增分頁?","新增",JOptionPane.YES_NO_OPTION);
				if (input == JOptionPane.YES_OPTION)
				{
					setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					getContentPane().remove(panels.get(panelIndex-1));
					panels.add(new PaintArea(pen));
					getContentPane().add(panels.get(panelIndex),BorderLayout.CENTER);
					getContentPane().repaint();
					nowPanelIndex = panelIndex;
					pageLabel.setText((nowPanelIndex+1)+"/"+(panelIndex+1));
					panelIndex++;
					isNew = true;
					
				}
				else if (input == JOptionPane.NO_OPTION)
				{
					//do nothing and new a new one.
					
				}
			}
			else
			{
			
			panels.add(new PaintArea(pen));
			getContentPane().add(panels.get(panelIndex),BorderLayout.CENTER);
			getContentPane().repaint();
			nowPanelIndex = panelIndex;
			pageLabel.setText((nowPanelIndex+1)+"/"+(panelIndex+1));
			panelIndex++;
			isNew = true;
			getContentPane().add(pageLabel,BorderLayout.SOUTH);
			
			}
			revalidate();
			repaint();
			
			
		}
		if(panelIndex>0)//沒有新增以下動作無法進行
		{
			
			if(e.getSource() == save||e.getSource() == button[1])//儲存事件
			{	
				
			 JFileChooser c = new JFileChooser();
		      // Demonstrate "Save" dialog:
		      int rVal = c.showSaveDialog(this);
		     // c.setFocusable(true);
		      String name = null;
		      String path = null;
		      if (rVal == JFileChooser.APPROVE_OPTION) {
		       name = "\\" +c.getSelectedFile().getName()+".pdf";
		       path = c.getCurrentDirectory().toString();
		      }
		      if (rVal == JFileChooser.CANCEL_OPTION) ;
		      
		      
	
		      TranslateToPdf.Pdf (panels,path+name,getContentPane().getWidth(),getContentPane().getHeight());
			
			}
			if(e.getSource() == cut||e.getSource() == button[2])//擷取事件
			{
			
				//取得螢幕元件
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				panels.get(nowPanelIndex).setPaintLock(false);
				JWindow subFrame = new JWindow(); //instance 遮罩於屏幕上的視窗
				subFrame.setBounds(0, 0, (int)screenW, (int)screenH);//設定大小為螢幕大小
				subFrame.setAlwaysOnTop(true);//讓遮罩視窗位於最上層
				subFrame.setOpacity(0.50f);//設定透明度為半透明
				subFrame.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.05f));
				DragArea DArea = new DragArea(subFrame,this); 
				DArea.createNewitem();
	        	DArea.repaint();
	        	DArea.setSize( (int) screenW, (int) screenH);
	        	DArea.setBackground(Color.white);
	        	subFrame.getContentPane().setLayout(null);
	        	subFrame.getContentPane().add(DArea);
	        	subFrame.setVisible(true);
	        	setVisible(false);
			}
			if(e.getSource() == pen||e.getSource() == button[3])//繪圖事件
			{
				Toolkit toolkit = Toolkit.getDefaultToolkit();  
				
				if(panels.get(nowPanelIndex).getMode()==0)
				{
					Image img= toolkit.getImage(getClass().getResource("/icons/pen.gif"));
					Cursor cursor = toolkit.createCustomCursor((Image)img, new Point(0,0), "Pen");  
					setCursor(cursor);  
			    }
			    else if(panels.get(nowPanelIndex).getMode()==1)
			    {
			    	Image img= toolkit.getImage(getClass().getResource("/icons/eraser.png"));
					Cursor cursor = toolkit.createCustomCursor((Image)img, new Point(0,0), "eraser");  
					setCursor(cursor); 
			    }
			    else
			    	setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				panels.get(nowPanelIndex).setPaintLock(true);
			}
			if(e.getSource() == mark||e.getSource() == button[4])//便利貼事件
			{
				Posit ps = new  Posit("posit");
				ps.setLocation(0, 0);
				panels.get(nowPanelIndex).add(ps);
				repaint();
				
			}
			if(e.getSource() == last||e.getSource() == button[5])//上一頁事件
			{
				if(nowPanelIndex>0)
				{
				getContentPane().remove(panels.get(nowPanelIndex--));
				getContentPane().add(panels.get(nowPanelIndex),BorderLayout.CENTER);
				getContentPane().repaint();
				pageLabel.setText((nowPanelIndex+1)+"/"+panelIndex);
				}
			}
		        
		
		
			if(e.getSource() == next||e.getSource() == button[6])//下一頁事件
			{
				if(nowPanelIndex<panelIndex-1)
				{
					getContentPane().remove(panels.get(nowPanelIndex++));
					getContentPane().add(panels.get(nowPanelIndex),BorderLayout.CENTER);
					getContentPane().repaint();
					pageLabel.setText((nowPanelIndex+1)+"/"+panelIndex);
				}
			}
			if(e.getSource() == setOnBoard||e.getSource() == button[7])//貼上圖片事件
			{
				panels.get(nowPanelIndex).lockImage();
			}
			if(e.getSource() == refresh||e.getSource() == button[8])//刷新頁面事件
			{
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				getContentPane().remove(panels.get(nowPanelIndex));
				PaintArea newPaint = new PaintArea(pen);
				getContentPane().add(newPaint);
				panels.set(nowPanelIndex, newPaint);
				
				revalidate();
				repaint();
			}
			if(e.getSource() == remove||e.getSource() == button[9])//刪除頁面事件
			{
				
				getContentPane().remove(panels.get(nowPanelIndex)); 
				panels.remove(nowPanelIndex);
			
				if(nowPanelIndex!=0)
				{
					nowPanelIndex--;
					getContentPane().add(panels.get(nowPanelIndex));
					pageLabel.setText((nowPanelIndex+1)+"/"+panelIndex);
				}
				else
				{
					remove(pageLabel);
					isNew =false;
				}
				panelIndex--;
				revalidate();
				repaint();
			}
			
		}
	}
	public Component getMyComponent(String name) //取得paintArea JLabel
	{
		if(name =="paint")
			return panels.get(nowPanelIndex);
		return null;
	}
}
