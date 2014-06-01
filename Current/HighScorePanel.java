import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.*;

public class HighScorePanel extends JPanel
{
  //static final File HIGHSCORES = new File ("highscores.jp");
  static final String HIGHSCORES = "highscores.txt";
  public static ArrayList<HighScore> scores = new ArrayList<HighScore>(10);
  
  public HighScorePanel (JFrame parent)
  {
    JLabel[] nameLabels = new JLabel[10];;
    JLabel[] scoreLabels = new JLabel[10];
    JButton closeButton = new JButton ("Close");
    closeButton.addActionListener ((ActionListener)parent);
    importFile(HIGHSCORES);
    Font font = new Font (Font.SERIF,Font.PLAIN, 20);
    for (int x = 0;x < 10;x++)
    {
     nameLabels [x] = new JLabel (scores.get(x).getName());
     scoreLabels [x] = new JLabel (String.valueOf (scores.get(x).getScore()));
     nameLabels [x].setFont (font);
     scoreLabels [x].setFont (font);
    }
    GroupLayout layout = new GroupLayout (this);
    layout.setAutoCreateGaps (true);
    layout.setAutoCreateContainerGaps (true);
    layout.setHorizontalGroup (layout.createSequentialGroup ()
                                 .addGroup (layout.createParallelGroup ()
                                              .addComponent (nameLabels [0])
                                              .addComponent (nameLabels [1])
                                              .addComponent (nameLabels [2])
                                              .addComponent (nameLabels [3])
                                              .addComponent (nameLabels [4])
                                              .addComponent (nameLabels [5])
                                              .addComponent (nameLabels [6])
                                              .addComponent (nameLabels [7])
                                              .addComponent (nameLabels [8])
                                              .addComponent (nameLabels [9])
                                              .addComponent (closeButton))
                                .addGroup (layout.createParallelGroup ()
                                             .addComponent (scoreLabels [0])
                                             .addComponent (scoreLabels [1])
                                             .addComponent (scoreLabels [2])
                                             .addComponent (scoreLabels [3])
                                             .addComponent (scoreLabels [4])
                                             .addComponent (scoreLabels [5])
                                             .addComponent (scoreLabels [6])
                                             .addComponent (scoreLabels [7])
                                             .addComponent (scoreLabels [8])
                                             .addComponent (scoreLabels [9]))
                                          );
    layout.setVerticalGroup (layout.createSequentialGroup()
                               .addGroup (layout.createParallelGroup()
                                            .addComponent (nameLabels [0])
                                            .addComponent (scoreLabels [0]))
                               .addGroup (layout.createParallelGroup()
                                            .addComponent (nameLabels [1])
                                            .addComponent (scoreLabels [1]))
                               .addGroup (layout.createParallelGroup()
                                            .addComponent (nameLabels [2])
                                            .addComponent (scoreLabels [2]))
                               .addGroup (layout.createParallelGroup()
                                            .addComponent (nameLabels [3])
                                            .addComponent (scoreLabels [3]))
                               .addGroup (layout.createParallelGroup()
                                            .addComponent (nameLabels [4])
                                            .addComponent (scoreLabels [4]))
                               .addGroup (layout.createParallelGroup()
                                            .addComponent (nameLabels [5])
                                            .addComponent (scoreLabels [5]))
                               .addGroup (layout.createParallelGroup()
                                            .addComponent (nameLabels [6])
                                            .addComponent (scoreLabels [6]))
                               .addGroup (layout.createParallelGroup()
                                            .addComponent (nameLabels [7])
                                            .addComponent (scoreLabels [7]))
                               .addGroup (layout.createParallelGroup()
                                            .addComponent (nameLabels [8])
                                            .addComponent (scoreLabels [8]))
                               .addGroup (layout.createParallelGroup()
                                            .addComponent (nameLabels [9])
                                            .addComponent (scoreLabels [9]))
                               .addComponent (closeButton)
                               );
                              
    setLayout (layout);
    setSize (layout.maximumLayoutSize(this));
    setVisible (true);
  }
  
  public void importFile (String fileName)
  {    
    try
    {   
      BufferedReader in = new BufferedReader (new FileReader (fileName));
      for (int x = 0; x < 10; x++)
      {
        String name = in.readLine();
        int score;
        try
        {
        score = Integer.parseInt (in.readLine());
        }
        catch (NumberFormatException e)
        {
          System.out.println ("Error with highscore file. Score is not an integer");
          score = 0;
        }
        scores.add (new HighScore (name,score));
      }
      in.close ();
    }
    catch (IOException e)
    {
      System.out.println ("Error Loading High Scores");
      writeBlankFile();
      importFile (fileName);
    }
  }
  
  public static void writeFile ()
  {
    try
    {
    PrintWriter out = new PrintWriter (new FileWriter (HIGHSCORES));
    for (int x = 0; x < 10; x++)
    {
      HighScore score = scores.get (x);
      out.println (score.getName());
      out.println (score.getScore());
    }
    out.close();
    }
    catch (IOException e)
    {
     System.out.println ("Error writing highscore file"); 
    }
  }
  
  public static void writeBlankFile ()
  {
    try
    {
    PrintWriter out = new PrintWriter (new FileWriter (HIGHSCORES));
    for (int x = 0; x < 10; x++)
    {
      out.println ("P'ngball Grandmaster");
      out.println (0);
    }
    out.close();
    }
    catch (IOException e)
    {
     System.out.println ("Error writing highscore file"); 
    }
    
  }
  
}