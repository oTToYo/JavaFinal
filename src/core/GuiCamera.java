package core;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class GuiCamera
{
    private String fileName; // 檔案名稱

    private String defaultName = "GuiCamera";
    private String imageFormat; // 圖片格式

    private String defaultImageFormat = "png";
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();//取得螢幕物件
    
    public GuiCamera()
    {
    fileName = defaultName;
    imageFormat = defaultImageFormat;
    }
    public GuiCamera(String s, String format)
    {
    fileName = s;
    imageFormat = format;
    }

    public BufferedImage snapShot(int x1,int y1,int x2,int y2)
    {
 
    try
    {
    
        BufferedImage screenshot = (new Robot()) //取得目前螢幕上的快照
            .createScreenCapture(new Rectangle(x1, y1,
            		Math.abs(x1-x2), Math.abs(y1-y2)));
      
        return screenshot; //回傳圖片
    } catch (Exception ex)
    {
        System.out.println(ex);
        return null;
    }

    }

}