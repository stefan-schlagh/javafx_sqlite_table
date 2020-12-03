import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private Data d;

    @Override
    public void start(Stage stage) throws Exception {

        Data d = new Data();
        d.initPersons();

        Group root = new Group();
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
        ObservableList<Person> list = d.getPersonObservableList();
        personTab.setItems(list);

        //Daten in Tabelle editieren
        personTab.setEditable(true);

        addEventToTableColumn(1,userSp);
        addEventToTableColumn(2,vnSp);
        addEventToTableColumn(3,nnSp);
        addEventToTableColumn(4,emailSp);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(lb,personTab);
        root.getChildren().add(vBox);

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
                System.out.println("Wert: " + neuerWert + " Person: " + pos.getRow());

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
