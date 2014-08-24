package experiment;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {

    public Window(int w, int h, String title, Experiment experiment){
        experiment.setPreferredSize(new Dimension(w,h));
        experiment.setMaximumSize(new Dimension(w,h));
        experiment.setMinimumSize(new Dimension(w,h));

        JFrame frame = new JFrame(title);
        frame.add(experiment);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        experiment.start();
    }

}