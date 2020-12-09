import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InputDialog extends Stage {

    private Button submit;
    private TextField usernameInput;
    private TextField vornameInput;
    private TextField nachnameInput;
    private TextField emailInput;

    public InputDialog(){
        super();

        VBox root = new VBox();
        root.setPadding(new Insets(10));

        Text username = new Text("username");
        usernameInput = new TextField();

        Text vorname = new Text("vorname");
        vornameInput = new TextField();

        Text nachname = new Text("nachname");
        nachnameInput = new TextField();

        Text email = new Text("email");
        emailInput = new TextField();

        submit = new Button("submit");

        root.getChildren().addAll(
                username,usernameInput,
                vorname,vornameInput,
                nachname,nachnameInput,
                email,emailInput,
                submit);

        Scene scene = new Scene(root,400,300);
        this.setScene(scene);
    }

    public Button getSubmit() {
        return submit;
    }

    public Person getPerson(){
        return new Person(
                usernameInput.getText(),
                vornameInput.getText(),
                nachnameInput.getText(),
                emailInput.getText());
    }
}
