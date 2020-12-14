package addPerson;


import javafx.scene.input.MouseEvent;
import person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddPersonController {

    private SubmitCallback callback;

    @FXML
    private TextField uName;

    @FXML
    private TextField vName;

    @FXML
    private TextField nName;

    @FXML
    private TextField eMail;

    @FXML
    public void personSubmitted(MouseEvent event) {
        Person p = new Person(uName.getText(),vName.getText(),nName.getText(),eMail.getText());
        callback.submit(p);
    }

    public void addSubmitCallback(SubmitCallback cb){
        callback = cb;
    }
}
