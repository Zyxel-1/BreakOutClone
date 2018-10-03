/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gaem;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
/**
 *
 * @author roberto_sanchez
 */
public class Brick extends Sprite {
    String whiteBrick = "../images/brick.png";
    String greenBrick = "../images/greenBrick.png";
    String goldBrick = "../images/goldBrick.png";
    String purpleBrick = "../images/purpleBrick.png";
    String blueBrick = "../images/blueBrick.png";
    boolean destroyed;
    ImageIcon brickImg;
    public Brick(int x, int y, String color) 
    {
      this.x = x;
      this.y = y;
      if(color == "white")
      {
        brickImg = new ImageIcon(this.getClass().getResource(whiteBrick));
      }
      else if(color == "green")
      {
        brickImg = new ImageIcon(this.getClass().getResource(greenBrick));
      }
      else if(color == "blue")
      {
         brickImg = new ImageIcon(this.getClass().getResource(blueBrick));
      }
      else if(color =="purple")
      {
        brickImg = new ImageIcon(this.getClass().getResource(purpleBrick));
      }
      else if(color =="gold")
      {
        brickImg = new ImageIcon(this.getClass().getResource(goldBrick));
      }
      image = brickImg.getImage();

      width = image.getWidth(null);
      heigth = image.getHeight(null);

      destroyed = false;
    }

    public boolean isDestroyed()
    {
      return destroyed;
    }
    public void setDestroyed(boolean destroyed)
    {
      this.destroyed = destroyed;
      sfx_BrickBounce();
    }
    public void sfx_BrickBounce()
    {
        String url = "sounds/brick.wav";
        try {
            Clip clip = AudioSystem.getClip();
            try {
                try {
                    clip.open(AudioSystem.getAudioInputStream(new File(url)));
                } catch (IOException ex) {
                    Logger.getLogger(Brick.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
            clip.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Ball.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

}
