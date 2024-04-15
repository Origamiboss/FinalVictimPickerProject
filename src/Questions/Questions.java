package src.Questions;

public class Questions {
    private String Question;
    private String[] Options;
    private String Answer;

    public Questions(String Question, String[] Options, String Answer){
        this.Question = Question;
        this.Options = Options;
        this.Answer = Answer;
    }

    public Questions(Questions inQuestion){
        this.Question = inQuestion.Question;
        this.Options = inQuestion.Options;
        this.Answer = inQuestion.Answer;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String[] getOptions() {
        return Options;
    }

    public void setOptions(String[] options) {
        Options = options;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }
}
