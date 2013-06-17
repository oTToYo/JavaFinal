package core;

import javax.swing.SwingUtilities;

public class ClipCollector {
	
	public static void main(String []args)
	{
		  SwingUtilities.invokeLater(new Runnable() {

	            @Override
	            public void run() {
	            	FunctionBoard fb= new FunctionBoard("ClipCollector"); //instance 主要功能物件並給定標題
	            }
	        });
		
	}
}
