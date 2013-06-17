package core;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;


class Toolbar extends JToolBar implements ItemListener, ActionListener
	{
		private String[] toolName = {"畫筆", "橡皮擦" , "直線"};//tool name
		private JRadioButton[] toolButtons;//the button user can choose the tool
		private ButtonGroup buttongroup;//just one button of group can be chosen
		private JLabel colorLabel;
		private final Font font = new Font("monspaced", Font.PLAIN, 12);
		private JLabel sizeLabel;
		private JTextField setSize;//set the size that user wants to use
		private JButton colorButton;//choose the color that user wants to use
	//	private JButton backButton;
		private JPanel sizePanel;
		private JPanel colorPanel;
	//	private JPanel backPanel;
	//	private Cursor eraserCursor;
	//	private Cursor penCursor;
	//	private Cursor lineCursor;
		public Toolbar()
		{			
			super("TOOLBAR",JToolBar.VERTICAL);
			
			toolButtons = new JRadioButton[toolName.length];
			buttongroup = new ButtonGroup();
			setBorder(BorderFactory.createLineBorder(Color.gray));
			for(int count = 0; count < toolName.length; count++)//initialize the tool button
			{
				if(count == 0)
					toolButtons[count] = new JRadioButton(toolName[count],true);
				else
					toolButtons[count] = new JRadioButton(toolName[count],false);
				
				//toolButtons[count].setSize(1, 1);
				toolButtons[count].addItemListener(this);//register
				toolButtons[count].setFont(font);
				//toolButtons[count].setBackground(Color.LIGHT_GRAY);
				buttongroup.add(toolButtons[count]);
				add(toolButtons[count]);
			}
			//setBackground(Color.LIGHT_GRAY);
			setLayout(new FlowLayout());
			//this.setSize(0,0);
			sizePanel = new JPanel();
			sizeLabel = new JLabel("大小:");
			sizeLabel.setSize(5,5);
			setSize = new JTextField("8",4);
			setSize.addActionListener(this);//register
			sizePanel.add(sizeLabel);
			sizePanel.add(setSize);
			//sizePanel.setBackground(Color.LIGHT_GRAY);
			colorPanel = new JPanel();
			colorLabel = new JLabel("顏色");
			colorLabel.setFont(new Font("monspaced", Font.PLAIN, 12));
			colorLabel.setHorizontalAlignment(SwingConstants.CENTER);
			colorButton = new JButton();
			colorButton.setBackground(PaintArea.getColor());
			colorButton.setSize(1, 1);
			colorButton.addActionListener(this);//register
			colorPanel.setLayout(new GridLayout(1,2,0,0));
			colorPanel.add(colorLabel);
			colorPanel.add(colorButton);
			//colorPanel.setBackground(Color.g);
		/*	backButton = new JButton("BACK");
			backButton.setBackground(Color.GRAY);
			backButton.addActionListener(this);
			backPanel = new JPanel();
			backPanel.setBackground(Color.LIGHT_GRAY);
			backPanel.add(backButton);*/
			add(colorPanel);
			add(sizePanel);
		//	add(backPanel);
		//	penCursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("pen.png").getImage(),new Point(0,0), "pen");
		//	eraserCursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("eraser.png").getImage(), new Point(0,0), "eraser");
		//	lineCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
		}
		@Override
		public void itemStateChanged(ItemEvent e) 
		{
			/**********     choose mode to use      **********/
			for(int count = 0; count < toolName.length; count++)
			{
				if(e.getSource() == toolButtons[count])
				{
					PaintArea.setMode(count);
					return;
				}
			}			
		}
		@Override
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == colorButton)//choose a color to use
			{
				Color oldColor = PaintArea.getColor();
				PaintArea.setColor(JColorChooser.showDialog(this, "Choose a color", PaintArea.getColor()));
				if(PaintArea.getColor() == null)
					PaintArea.setColor(oldColor);
				colorButton.setBackground(PaintArea.getColor());
			}
			else if(e.getSource() == setSize)//input a PaintArea's size to use
			{
				PaintArea.setPaintSize(Integer.parseInt(setSize.getText()));
			}
		}
	}	
