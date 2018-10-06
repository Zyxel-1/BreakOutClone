/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gaem;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import java.util.Random;
/**
 *
 * @author roberto_sanchez
 */
public class Ball extends Sprite 
{
   private int BallSpeed    = 2;
   private int x_dir_speed  = BallSpeed;
   private int y_dir_speed  = BallSpeed;
   private int RightBorder  = 570;
   private int LeftBorder   = 0;
   private int TopBorder    = 0;
   

   protected String ball = "../images/ball.png";

   public Ball()
   {
     ImageIcon ii = new ImageIcon(this.getClass().getResource(ball));
     image = ii.getImage();
     
     width = image.getWidth(null);
     heigth = image.getHeight(null);
     respawn();
    }
    public void move() 
    {
        
      x += x_dir_speed;
      y += y_dir_speed;
      
      if (x <= LeftBorder) 
      {
        sfx_bounce();
        setXDir(5);
      }
      if (x >= RightBorder) 
      {
        sfx_bounce() ;
        setXDir(-5);
      }
      if (y == TopBorder) 
      {
        sfx_bounce() ;
        setYDir(5);
      }
      
        //System.out.println(this.x + "," + this.y);
    }
    public void respawn() 
    {
      x = Default_Position_X;
      y = Default_Position_Y - 10;
    }
    public void setXDir(int x)
    {
      x_dir_speed = -x_dir_speed;
    }
    public void setYDir(int y)
    {
      y_dir_speed = -y_dir_speed;
        
    }
    public int getYDir()
    {
      return y_dir_speed;
    }   
    public void sfx_bounce()
    {
        String url = "sounds/bounce.wav";
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(url)));
            clip.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
