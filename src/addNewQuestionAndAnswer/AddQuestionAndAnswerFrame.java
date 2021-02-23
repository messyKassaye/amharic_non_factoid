package addNewQuestionAndAnswer;

import javax.swing.*;
import java.awt.*;

public class AddQuestionAndAnswerFrame  extends JFrame {
    private AddQuestionAndAnswerMainPanel mainPanel;

    public AddQuestionAndAnswerFrame(){
        mainPanel = new AddQuestionAndAnswerMainPanel();
        setContentPane(mainPanel);
        setSize(new Dimension(550,550));
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
