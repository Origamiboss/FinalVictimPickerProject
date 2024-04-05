package src.Students.StudentFunctions;

public class Names {
    private String firstName = "";
    private String lastName = "";
    private String nickName = "";

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public boolean getHasNickName(){
        boolean hasNickname = this.nickName != "";
        return hasNickname;
    }
}
