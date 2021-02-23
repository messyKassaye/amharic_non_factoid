package lucene.model;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class Employees {
    @Expose
    List<Question> questions = new ArrayList<>();

    public List<Question> getEmps() {
        return questions;
    }
    public void addEmployee(Question name) {
        this.questions.add(name);
    }
}
