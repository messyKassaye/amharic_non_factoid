package home;

import addNewQuestionAndAnswer.AddQuestionAndAnswerFrame;
import lucene.FilePathService;
import lucene.MainLucene;
import questionAndAnswers.QuestionAndAnswersFrame;
import standards.StandardButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel {
    private SpringLayout layout;
    private JLabel informationLabel;
    private StandardButton addNewQuestion,startQuestionAndAnswer;
    private MainLucene mainLucene;
    private FilePathService filePathService;
    public HomePanel(){
        layout = new SpringLayout();
        setLayout(layout);

        filePathService = new FilePathService();
        mainLucene = new MainLucene(filePathService.getPath());
        informationLabel = new JLabel("Welcome to Afaan Oromo Question Answering System",JLabel.CENTER);
        informationLabel.setFont(new Font("arial",Font.BOLD,15));
        add(informationLabel);

        layout.putConstraint(SpringLayout.WEST,informationLabel,50,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,informationLabel,100,SpringLayout.NORTH,this);

        addNewQuestion = new StandardButton();
        addNewQuestion.setText("Add new question and answer");
        add(addNewQuestion);
        layout.putConstraint(SpringLayout.WEST,addNewQuestion,100,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,addNewQuestion,100,SpringLayout.NORTH,informationLabel);
        addNewQuestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddQuestionAndAnswerFrame addQuestionAndAnswerFrame = new AddQuestionAndAnswerFrame();
                addQuestionAndAnswerFrame.setSize(new Dimension(500,600));
                addQuestionAndAnswerFrame.setVisible(true);
                SwingUtilities.getWindowAncestor(HomePanel.this).dispose();
            }
        });
        startQuestionAndAnswer = new StandardButton();
        startQuestionAndAnswer.setText("Just start question and answer");
        add(startQuestionAndAnswer);
        layout.putConstraint(SpringLayout.WEST,startQuestionAndAnswer,100,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,startQuestionAndAnswer,75,SpringLayout.NORTH,addNewQuestion);

        startQuestionAndAnswer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QuestionAndAnswersFrame addQuestionAndAnswerFrame = new QuestionAndAnswersFrame();
                addQuestionAndAnswerFrame.setVisible(true);
                SwingUtilities.getWindowAncestor(HomePanel.this).dispose();
            }
        });
    }

    public void showHome(){
        MainFrame mainFrame=new MainFrame();
        mainFrame.setVisible(true);
    }
}
