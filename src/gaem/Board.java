/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gaem;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author roberto_sanchez
 */
public class Board extends JPanel 
{
    Timer timer;                             //Controls Game Sequence
    String message = "Game Over :(";         //Prints Message at Game loss or won
    Ball ball;                               //Creates Ball Object for Game
    Paddle paddle;                           //Creates Paddle Object for Game
    Brick bricks[];                          //Greates an array of Brick Objects for game
    ImageIcon win = new ImageIcon("../images/levelcomplete.gif");//Does not work yet...
    //JLabel gif = new JLabel (win);           // Look at line 35
    boolean ingame = true;                   //Tells whether to continue(true) or end game (false)
    int y_numBrick = 10;                     // Number of Bricks in the X axis
    int x_numBrick = 24;                     // Number of Bricks in the Y axis
    int BottomBorder = 820;                  // Bottom border bounderies.
    //-------------------------------------------------------------------------
    //      Constructor
    public Board()
    {
       addKeyListener(new TAdapter());
       setFocusable(true);
       setBackground (Color.black);
       bricks = new Brick[x_numBrick*y_numBrick];
       setDoubleBuffered(true);
       timer = new Timer();
       timer.scheduleAtFixedRate(new ScheduleTask(), 1000, 10); 
    }
    //------------------------------------------------------------------------
    //      Sets up the game with ball, paddle, and bricks
    public void setUpGame() 
    {
        ball = new Ball();
        paddle = new Paddle();
        int k = 0;
        for (int y = 3; y < y_numBrick+3; y++) //Starts at 3 to skip the first three
        {
            for (int x = 0; x < x_numBrick; x++)
            {
                if(y == 3 || y == 4)
                {
                    bricks[k] = new Brick(x *30, y*20,"gold");//Controls Brick Layout, DO NOT MESS!!!
                }
                else if(y == 5||y == 6)
                {
                    bricks[k] = new Brick(x *30, y*20,"purple" );//Controls Brick Layout, DO NOT MESS!!!
                }
                else if(y== 7 || y ==8)
                {
                    bricks[k] = new Brick(x *30, y*20,"blue" );//Controls Brick Layout, DO NOT MESS!!!
                }
                else if(y==9 ||y==10)
                {
                    bricks[k] = new Brick(x *30, y*20,"green" );//Controls Brick Layout, DO NOT MESS!!!
                }
                else     
                    bricks[k] = new Brick(x *30, y*20,"white");//Controls Brick Layout, DO NOT MESS!!!
                k++;
            }
        }
    }
    //-------------------------------------------------------------------------
    //      Repaints the entire screen with bricks, paddle, and ball
    public void paint(Graphics g)
    {
        super.paint(g);
        if (ingame)
        {
            g.drawImage(ball.getImage(), ball.getX(), ball.getY(),
                        ball.getWidth(), ball.getHeight(), this);
            g.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                        paddle.getWidth(), paddle.getHeight(), this);

            for (int i = 0; i < (x_numBrick*y_numBrick); i++) 
            {
                if (!bricks[i].isDestroyed())
                    g.drawImage(bricks[i].getImage(), bricks[i].getX(),
                                bricks[i].getY(), bricks[i].getWidth(),
                                bricks[i].getHeight(), this);
            }
        }else 
        {

            Font font = new Font("arial", Font.BOLD, 27);
            FontMetrics metr = this.getFontMetrics(font);

            g.setColor(Color.white);
            g.setFont(font);
            g.drawString(message,(600 - metr.stringWidth(message)) / 2,870 / 2);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    public void stop()
    {
        ingame = false;
        timer.cancel();
    }    
    //-------------------------------------------------------------
    //      Determinds if ball collided with paddle or brick or if ball goes beyond paddle.
    public void checkCollision()
    {
        if (ball.getRect().getMaxY() > BottomBorder) 
        {
            sfx_died();
            stop();
        
        }
        for (int i = 0, j = 0; i < (x_numBrick*y_numBrick); i++)
        {
            if (bricks[i].isDestroyed())
            {
                 //sfx_BrickBounce();
                j++;
            }
            if (j == (x_numBrick*y_numBrick))
            {
                message = "Level Complete!";
                //add(gif);//Adds ,does not work...
                stop();
            }
        }
        if ((ball.getRect()).intersects(paddle.getRect()))
        {

            int paddleLPos = (int)paddle.getRect().getMinX();
            int ballLPos = (int)ball.getRect().getMinX();
            int paddlewidth = (int)paddle.width;
            int LeftPaddle = paddleLPos + paddlewidth/3;
            int MiddlePaddle = paddleLPos + 2*paddlewidth/3;
            if(ballLPos < LeftPaddle) {
            	System.out.println("Left Side");
            	ball.setXDir(-10);
            	ball.setYDir(10);
            }else if(ballLPos >LeftPaddle) {
            	ball.setXDir(10);
            	ball.setYDir(-10);
            }
        }
        for (int i = 0; i < (x_numBrick*y_numBrick); i++)
        {
            if ((ball.getRect()).intersects(bricks[i].getRect()))
            {
                int ballLeft = (int)ball.getRect().getMinX();
                int ballHeight = (int)ball.getRect().getHeight();
                int ballWidth = (int)ball.getRect().getWidth();
                int ballTop = (int)ball.getRect().getMinY();

                Point pointRight =
                    new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom =
                    new Point(ballLeft, ballTop + ballHeight + 1);

                if (!bricks[i].isDestroyed()) 
                {
                    if (bricks[i].getRect().contains(pointRight)) 
                        ball.setXDir(-1);
                    else if (bricks[i].getRect().contains(pointLeft)) 
                        ball.setXDir(1);
                    if (bricks[i].getRect().contains(pointTop)) 
                        ball.setYDir(1);
                    else if (bricks[i].getRect().contains(pointBottom)) 
                        ball.setYDir(-1);
                    bricks[i].setDestroyed(true);
                }
            }
        }
        //End of Check Collision Method
    }  
    //-------------------------------------------------------------
    //      Other Methods
    //-------------------------------------------------------------
    class ScheduleTask extends TimerTask
    {
        public void run() 
        {
            ball.move();
            paddle.move();
            checkCollision();
            repaint();
        }
    }
    private class TAdapter extends KeyAdapter
    {
        public void keyReleased(KeyEvent e)
        {
            paddle.keyReleased(e);
        }
        public void keyPressed(KeyEvent e)
        {
            paddle.keyPressed(e);
        }
    }
    public void addNotify()
    {
       super.addNotify();
       setUpGame();
    }
    
    public void sfx_died()
    {
        String url = "sounds/died.wav";
        try {
            Clip clip = AudioSystem.getClip();
            try {
                clip.open(AudioSystem.getAudioInputStream(new File(url)));
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
            clip.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
  
    //End of Class
}
