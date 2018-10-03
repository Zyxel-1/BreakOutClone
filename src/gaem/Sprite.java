/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gaem;

/**
 *
 * @author roberto_sanchez
 */
import java.awt.Image;
import java.awt.Rectangle;
public class Sprite
{
    protected int x;
    protected int y;
    protected int width;
    protected int heigth;
    protected Image image;
    protected int Default_Position_Y = 800;
    protected int Default_Position_X = 280;
    public void setX(int x) 
    {
        this.x = x;
    }
    public int getX()
    {
        return x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public int getY() 
    {
        return y;
    }
    public int getWidth()
    {
        return width;
    }
    public int getHeight() 
    {
        return heigth;
    }
    Image getImage()
    {
      return image;
    }

    Rectangle getRect()
    {
      return new Rectangle(x, y, 
      image.getWidth(null), image.getHeight(null));
    }
}
