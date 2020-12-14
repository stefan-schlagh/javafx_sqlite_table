import person.Person;
import addPerson.AddPerson;
import data.Data;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private Data data;
    private ObservableList<Person> personList;

    @Override
    public void start(Stage stage) throws Exception {

        data = new Data();
        data.initPersons();

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("add..");
        MenuItem add = new MenuItem("add item");
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                AddPerson dialog = new AddPerson(data,personList);
                dialog.show();
            }
        });
        menu.getItems().add(add);
        menuBar.getMenus().add(menu);

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        Scene s = new Scene(root,500,400);

        Label lb = new Label("E-mail Adressen:");
        lb.setFont(new Font(20));

        TableView<Person> personTab = new TableView<Person>();

        TableColumn<Person,String> userSp = new TableColumn<Person,String>("Username");
        TableColumn<Person,String> vnSp = new TableColumn<Person,String>("Vorname");
        TableColumn<Person,String> nnSp = new TableColumn<Person,String>("Nachname");
        TableColumn<Person,String> emailSp = new TableColumn<Person,String>("Email");

        userSp.setMinWidth(100);
        userSp.setMaxWidth(200);

        //Spalten gruppieren
        TableColumn<Person,String> nameSp = new TableColumn<Person,String>("Name");
        nameSp.getColumns().addAll(vnSp,nnSp);

        personTab.getColumns().addAll(userSp,nameSp,emailSp);

        userSp.setCellValueFactory(new PropertyValueFactory<>("username"));
        vnSp.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        nnSp.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        emailSp.setCellValueFactory(new PropertyValueFactory<>("email"));

        //Daten in Tabelle einfügen
        personList = (ObservableList<Person>) data.getPersons();
        personTab.setItems(personList);

        //Daten in Tabelle editieren
        personTab.setEditable(true);

        addEventToTableColumn(1,userSp);
        addEventToTableColumn(2,vnSp);
        addEventToTableColumn(3,nnSp);
        addEventToTableColumn(4,emailSp);

        personTab.setRowFactory( tv -> {
            TableRow<Person> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getButton() == MouseButton.SECONDARY) {
                    Person p = row.getItem();

                    //https://tagmycode.com/snippet/5207/yes-no-cancel-dialog-in-javafx#.X9EYFLMxmUk
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Current project is modified");
                    alert.setContentText("Delete?");
                    ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                    ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
                    ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert.getButtonTypes().setAll(okButton, noButton, cancelButton);
                    alert.showAndWait().ifPresent(type -> {

                        if (type.getButtonData() == ButtonBar.ButtonData.YES) {
                            System.out.println("delete!");

                            data.deletePerson(p);
                            personList.remove(p);

                        } else if (type.getButtonData() == ButtonBar.ButtonData.NO) {
                            //do nothing
                        }
                    });
                }
            });
            return row ;
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(lb,personTab);
        root.setCenter(vBox);

        stage.setScene(s);
        stage.show();
    }

    public void addEventToTableColumn(int columnIndex,TableColumn<Person,String> tableColumn){

        tableColumn.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        tableColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> event) {
                String neuerWert = event.getNewValue();
                TablePosition<Person,String> pos = event.getTablePosition();
                System.out.println("Wert: " + neuerWert + " Person.Person: " + pos.getRow());

                Person p = event.getTableView().getItems().get(pos.getRow());
                p.setColumn(columnIndex,neuerWert);
            }
        });
    }

    public static ObservableList<Person> getPersonList(){
        Person pl = new Person ("Maxi", "Max", "Muster", "m.muster@gmail.com");
        Person p2 = new Person("Hansi","Max","Muster","m.mustergmail.com");
        Person p3 = new Person ("Eva", "Max", "Muster", "m.muster@gmail.com");
        Person p4 = new Person ("Karli","Max", "Muster", "m. muster@gmail.com");
        Person p5 = new Person ("Mini", "Max", "Muster","m. muster@gmail.com");

        ObservableList<Person> liste = FXCollections.observableArrayList (pl, p2,p3,p4,p5);
        return liste;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
