/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gaem;

/**
 *
 * @author roberto_sanchez
 */
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
public class Gaem extends JFrame
{
    JMenu gameMenu;
    JMenuItem restart, stop, pause;
    JMenuBar bar;
    static Gaem BreakOut ;
    public Gaem()
    {
        add(new Board());
        setTitle("BreakOut");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,870);
        setLocationRelativeTo(null);
        setIgnoreRepaint(true);
        setResizable(false);
        //-----------------------------------------------------
        MenuListener event = new MenuListener();
        bar = new JMenuBar( );
    
        gameMenu = new JMenu("Game Options");
        gameMenu.setVisible(true);
        
        restart = new JMenuItem("New Game");
        restart.addActionListener(event);
        gameMenu.add(restart);
        
        stop = new JMenuItem("Quit");
        stop.addActionListener(event);
        gameMenu.add(stop);
        
        pause = new JMenuItem("Pause");
        pause.addActionListener(event);
        gameMenu.add(pause);
        
        bar.add(gameMenu);
        
        setJMenuBar(bar);
        //----------------------------------------------------
        setVisible(true);
    }
    public static void main(String[] args)
    {
       StartGame(1);
    }
    public static void StartGame(int i)
    {
        if(i==1)
        BreakOut = new Gaem();
        else
        {
            BreakOut.dispose();
            BreakOut = new Gaem();
        }
    }
    public static void Restart()
    {
        StartGame(0);
    }
    public static void Pause()
    {
        //Pause Code   
    }
    public static void Stop()
    {
        //Stop Code
    }
    private class MenuListener implements ActionListener
    {
      public void Input(){};
      public void actionPerformed (ActionEvent event)
      {
         String source = event.getActionCommand();
         //---------------------------------------------
         //               Game Options
         //---------------------------------------------
         if (source.equals("New Game"))
            Restart();
         else if(source.equals("Pause"))
             Pause();
         else if(source.equals("Quit"))
             Stop();
         /*---------------------------------------------
         //          Banking Program Options
         //---------------------------------------------
          if (source.equals("Load From File"))
            BankProgram.Read();
         else if (source.equals("Save To File"))
            BankProgram.Write();
         else if (source.equals("Add New Account"))
            BankProgram.NewAccount();
         else if (source.equals("Sort Accounts"))
            BankProgram.SortVector(BankProgram.bank);
         else if (source.equals("List Transactions"))
            BankProgram.ListAllTransactions();
         else if (source.equals("List Checks"))
            BankProgram.ListAllChecks();
         else if (source.equals("List Deposits"))
            BankProgram.ListAllDeposits();
         else if (source.equals("Find Account"))
            BankProgram.SearchAccount();
         else if (source.equals("Add Transaction"))
            BankProgram.getTransCode();
         *///-------------------------------------------
     
      }
   }
}
