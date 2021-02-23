package addNewQuestionAndAnswer;

import home.MainFrame;
import standards.StandardButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddQuestionAndAnswerMainPanel extends JPanel {
    private SpringLayout layout;
    private JLabel informationLabel,informationLabel2;
    private StandardButton addNewQuestion,startQuestionAndAnswer,backToHome;

    public AddQuestionAndAnswerMainPanel(){

        layout = new SpringLayout();
        setLayout(layout);

        informationLabel = new JLabel("Start adding your question and answer");
        informationLabel.setFont(new Font("arial",Font.BOLD,17));
        add(informationLabel);

        informationLabel2 = new JLabel("<html>You can add your question and answers from external file E.g txt,json and xml files. <br/>and also you can just add using created system for this purpose</html>");
        informationLabel2.setFont(new Font("arial",Font.BOLD,12));
        add(informationLabel2);

        layout.putConstraint(SpringLayout.WEST,informationLabel,100,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,informationLabel,100,SpringLayout.NORTH,this);

        layout.putConstraint(SpringLayout.WEST,informationLabel2,10,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,informationLabel2,30,SpringLayout.NORTH,informationLabel);

        addNewQuestion = new StandardButton();
        addNewQuestion.setText("Add question from external file");
        add(addNewQuestion);
        layout.putConstraint(SpringLayout.WEST,addNewQuestion,100,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,addNewQuestion,100,SpringLayout.NORTH,informationLabel2);

        startQuestionAndAnswer = new StandardButton();
        startQuestionAndAnswer.setText("Add question using the system");
        add(startQuestionAndAnswer);
        layout.putConstraint(SpringLayout.WEST,startQuestionAndAnswer,100,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,startQuestionAndAnswer,75,SpringLayout.NORTH,addNewQuestion);

        addNewQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComponents(new AddQuestionAndAnswerFromFilePanel());
            }
        });

        startQuestionAndAnswer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addComponents(new AddQuestionAndAnswerUsingSystem());
            }
        });

        backToHome = new StandardButton();
        backToHome.setText("Back to home");
        add(backToHome);

        layout.putConstraint(SpringLayout.WEST,backToHome,250,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,backToHome,200,SpringLayout.NORTH,startQuestionAndAnswer);
        backToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.getWindowAncestor(AddQuestionAndAnswerMainPanel.this).dispose();
                MainFrame mainFrame = new MainFrame();
                mainFrame.showBack();
            }
        });


    }

    public void  addComponents(JComponent component){
        removeAll();
        add(component);
        repaint();
        revalidate();
    }
}
