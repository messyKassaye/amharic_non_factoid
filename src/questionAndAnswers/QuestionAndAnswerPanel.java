package questionAndAnswers;

import com.google.gson.JsonObject;
import lucene.FilePathService;
import lucene.MainLucene;
import standards.StandardLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestionAndAnswerPanel extends JPanel {
    private SpringLayout layout;
    private StandardLabel questionLabel,questionTypeLabel,answerLabel;
    private JTextArea questionTextField,answerTextArea;
    private JTextField questionTypeField;
    private JScrollPane questionPane,answerPane;
    private JButton answerButton,refreshButton;
    private MainLucene mainLucene;
    private FilePathService filePathService;
    public QuestionAndAnswerPanel(){
        layout = new SpringLayout();
        setLayout(layout);

        filePathService = new FilePathService();
        mainLucene = new MainLucene(filePathService.getPath());

        questionLabel = new StandardLabel();
        questionLabel.setText("Question:");
        questionLabel.setFont(new Font("arial",Font.PLAIN,17));
        add(questionLabel);

        layout.putConstraint(SpringLayout.WEST,questionLabel,25,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,questionLabel,40,SpringLayout.NORTH,this);

        questionTextField = new JTextArea(2,40);
        questionTextField.setFont(new Font("arial",Font.PLAIN,17));
        questionPane = new JScrollPane(questionTextField,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(questionPane);
        layout.putConstraint(SpringLayout.WEST,questionPane,120,SpringLayout.WEST,questionLabel);
        layout.putConstraint(SpringLayout.NORTH,questionPane,25,SpringLayout.NORTH,this);

        questionTypeLabel = new StandardLabel();
        questionTypeLabel.setText("Question type:");
        questionTypeLabel.setFont(new Font("arial",Font.PLAIN,17));
        add(questionTypeLabel);

        layout.putConstraint(SpringLayout.WEST,questionTypeLabel,25,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,questionTypeLabel,120,SpringLayout.NORTH,questionLabel);

        questionTypeField = new JTextField(12);
        questionTypeField.setEnabled(false);
        questionTypeField.setFont(new Font("arial",Font.PLAIN,17));
        add(questionTypeField);
        layout.putConstraint(SpringLayout.WEST,questionTypeField,120,SpringLayout.WEST,questionTypeLabel);
        layout.putConstraint(SpringLayout.NORTH,questionTypeField,120,SpringLayout.NORTH,questionLabel);

        answerLabel = new StandardLabel();
        answerLabel.setText("Answer: ");
        answerLabel.setFont(new Font("arial",Font.PLAIN,17));
        add(answerLabel);
        layout.putConstraint(SpringLayout.WEST,answerLabel,25,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,answerLabel,140,SpringLayout.NORTH,questionTypeLabel);

        answerTextArea = new JTextArea(10,65);
        answerPane = new JScrollPane(answerTextArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(answerPane);
        layout.putConstraint(SpringLayout.WEST,answerPane,120,SpringLayout.WEST,answerLabel);
        layout.putConstraint(SpringLayout.NORTH,answerPane,120,SpringLayout.NORTH,questionTypeLabel);

        answerButton = new JButton("Answer");
        answerButton.setFont(new Font("arial",Font.PLAIN,17));
        add(answerButton);
        layout.putConstraint(SpringLayout.WEST,answerButton,200,SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH,answerButton,220,SpringLayout.NORTH,answerLabel);

        refreshButton = new JButton("Refresh");
        refreshButton.setFont(new Font("arial",Font.PLAIN,17));
        add(refreshButton);
        layout.putConstraint(SpringLayout.WEST,refreshButton,200,SpringLayout.WEST,answerButton);
        layout.putConstraint(SpringLayout.NORTH,refreshButton,220,SpringLayout.NORTH,answerLabel);


        answerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String answerKey = questionTextField.getText();
                JsonObject answerResponse = mainLucene.readData(answerKey);
                if (answerResponse==null){
                    questionTypeField.setText("");
                    answerTextArea.setText("No answer");
                }else {
                    questionTypeField.setText(answerResponse.get("question_type").getAsString());
                    answerTextArea.setText(answerResponse.get("answer").getAsString());
                }
            }
        });
    }

    public void initialize(){

    }
}
