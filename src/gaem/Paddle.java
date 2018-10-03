/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gaem;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

/**
 *
 * @author roberto_sanchez
 */
public class Paddle extends Sprite 
{
    String paddle = "../images/paddle.png";

    int dx;                       // Direction
    int PaddleSpeed = 5;          // Paddle Speed
    int PaddleBorder_Right = 570; // Rightend of Board
    int PaddleBorder_Left  = 600; // Leftend of Board

    public Paddle()
    {
        ImageIcon ii = new ImageIcon(this.getClass().getResource(paddle));
        image = ii.getImage();
        width = image.getWidth(null);
        heigth = image.getHeight(null);
        resetState();
    }
    public void move() 
    {
        if ((x+dx) <= PaddleSpeed || (x+dx) >= PaddleBorder_Right) 
            dx *= -1;
        x+=dx;
    }

    public void keyPressed(KeyEvent e) 
    {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT)
        {
            dx = -PaddleSpeed;
        }
        if (key == KeyEvent.VK_RIGHT)
        {
            dx = PaddleSpeed;
        }
    }
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) 
        {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) 
        {
            dx = 0;
        }
    }
    public void resetState()
    {
        x = Default_Position_X;
        y = Default_Position_Y;
    }
}
