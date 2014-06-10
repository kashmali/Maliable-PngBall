import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.*;
import java.io.*;

import Files.Current.physicsEngine.*;

public class HighScorePanel extends JPanel
{
  public HighScorePanel (JFrame parent)
  {
    JLabel[] nameLabels = new JLabel[10];
   JLabel[] scoreLabels = new JLabel[10];
    JLabel name = new JLabel ("Name");
    JLabel score = new JLabel ("Score");
    JButton closeButton = new JButton ("Close");
    closeButton.addActionListener ((ActionListener)parent);
    Font font = new Font (Font.SERIF,Font.PLAIN, 20);
    Font titleFont = new Font (Font.SERIF,Font.BOLD, 30);
    name.setFont (titleFont);
    score.setFont (titleFont);
    ArrayList<HighScore> scores = HighScoreManager.getScores ();
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
                                              .addComponent (name)
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
                                             .addComponent (score)
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
                                            .addComponent (name)
                                            .addComponent (score))
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

}