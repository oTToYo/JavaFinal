package core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

public class Drawing implements Serializable {

int x1,x2,y1,y2;   	    //定義座標

void draw(Graphics2D g2d ){}//定義繪圖函數
}

class Rect extends Drawing
{//矩形
	void draw(Graphics2D g2d ){
		g2d.setPaint(Color.red);
		g2d.setStroke(new BasicStroke(2.0f));//定義筆刷大小
		g2d.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2), Math.abs(y1-y2));
	}
}