package addNewQuestionAndAnswer;

import home.MainFrame;
import lucene.FilePathService;
import lucene.MainLucene;
import standards.StandardButton;
import standards.StandardLabel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddQuestionAndAnswerUsingSystem extends JPanel {
    private JLabel information,questionLabel,questionTypeLabel,answersLabel;
    private JTextField questionTextField,answerArea;
    private String questionTypes[] = {"List","Description","Definition"};
    private JComboBox questionTypeCombo;
    private BorderLayout layout;
    private JPanel centerPanel;
    private GridLayout centerLayout;
    private StandardButton addQuestionButton,backToHome;
    private FilePathService filePathService;
    private MainLucene mainLucene;

    public AddQuestionAndAnswerUsingSystem(){
        layout = new BorderLayout();
        setLayout(layout);
        setBorder(new EmptyBorder(10,60,10,20));

        filePathService = new FilePathService();
        mainLucene = new MainLucene(filePathService.getPath());

        information = new JLabel();
        information.setText("add your question, question type and answer");
        information.setFont(new Font("arial",Font.BOLD,17));
        information.setBorder(new EmptyBorder(0,0,25,0));
        add(information,BorderLayout.NORTH);

        centerLayout = new GridLayout(8,4,10,5);
        centerPanel = new JPanel();
        centerPanel.setSize(new Dimension(500,500));
        centerPanel.setLayout(centerLayout);
        add(centerPanel,BorderLayout.CENTER);

        questionLabel = new JLabel("Enter question");
        questionLabel.setFont(new Font("arial",Font.BOLD,17));
        centerPanel.add(questionLabel);

        questionTextField = new JTextField(10);
        questionTextField.setFont(new Font("arial",Font.BOLD,15));
        centerPanel.add(questionTextField);

        questionTypeLabel = new JLabel();
        questionTypeLabel.setText("Select question type");
        questionTypeLabel.setFont(new Font("arial",Font.BOLD,17));
        centerPanel.add(questionTypeLabel);

        questionTypeCombo = new JComboBox(questionTypes);
        questionTypeCombo.setFont(new Font("arial",Font.BOLD,17));
        centerPanel.add(questionTypeCombo);

        answersLabel = new JLabel("Enter answers");
        answersLabel.setFont(new Font("arial",Font.BOLD,17));
        centerPanel.add(answersLabel);

        answerArea = new JTextField(10);
        answerArea.setFont(new Font("arial",Font.BOLD,15));
        centerPanel.add(answerArea);

        addQuestionButton = new StandardButton();
        addQuestionButton.setBorder(new EmptyBorder(10,10,10,10));
        addQuestionButton.setText("Add question");
        addQuestionButton.setFont(new Font("arial",Font.BOLD,17));
        centerPanel.add(addQuestionButton);

        addQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String question = questionTextField.getText().toString();
                String selectedQuestionType = questionTypeCombo.getSelectedItem().toString();
                String questionAnswer = answerArea.getText().toString();
                questionTextField.setText("");
                answerArea.setText("");
               try {
                   mainLucene.addData(question,selectedQuestionType,questionAnswer);
               }catch (Exception exception){
                   System.out.println(exception.getMessage());
               }

            }
        });
        centerPanel.add(new JLabel("----------------------------------------" +
                "-----------------------------------------------------------"));

        backToHome = new StandardButton();
        backToHome.setText("Back to home");
        backToHome.setFont(new Font("arial",Font.BOLD,17));
        add(backToHome,BorderLayout.SOUTH);

        backToHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.getWindowAncestor(AddQuestionAndAnswerUsingSystem.this).dispose();
                MainFrame mainFrame = new MainFrame();
                mainFrame.showBack();
            }
        });


    }
}
