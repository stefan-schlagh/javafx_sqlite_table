package addPerson;

import data.Data;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import person.*;

import java.io.IOException;
import java.util.List;

public class AddPerson extends Stage {

    public AddPerson(Data data, List<Person> personList){
        super();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            Parent root = fxmlLoader.load(getClass().getResource("addPerson.fxml").openStream());
            AddPersonController addPersonController = fxmlLoader.getController();

            addPersonController.addSubmitCallback(new SubmitCallback() {
                @Override
                public void submit(Person p) {
                    data.addPerson(p);

                    personList.add(p);

                    hide();
                }
            });

            Scene scene = new Scene(root);
            this.setScene(scene);
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
