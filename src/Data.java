import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Data {

    public static void main(String[] args) {
        Data d = new Data();
        d.addData();
        d.initPersons();

        System.out.println(d.persons.get(0).toString());
    }
    private Connection con;
    private Statement st;

    public List<Person> persons = new ArrayList<Person>();

    public Data(){
        try{
            Connection con = DriverManager.getConnection("jdbc:sqlite:D:\\workspace\\java\\javafx\\Tabellen\\DB.db");
            st = con.createStatement();

            st.execute("CREATE TABLE IF NOT EXISTS person (" +
                    "pid INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT," +
                    "vorname TEXT," +
                    "nachname TEXT," +
                    "email TEXT" +
                    ");");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void initPersons(){
        try{
            ResultSet res = st.executeQuery("SELECT * FROM person;");

            while(res.next()){
                Person p = new Person(
                        res.getInt(1),
                        st,
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5)
                );
                persons.add(p);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void addData(){
        try{

            st.execute("INSERT INTO person (username,vorname,nachname,email) " +
                    "VALUES ('Maxl1','Max','Mustermann','Max.mus1@xx.com');");
            st.execute("INSERT INTO person (username,vorname,nachname,email) " +
                    "VALUES ('Maxl2','Max','Mustermann','Max.mus2@xx.com');");
            st.execute("INSERT INTO person (username,vorname,nachname,email) " +
                    "VALUES ('Maxl3','Max','Mustermann','Max.mus3@xx.com');");

        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public ObservableList<Person> getPersonObservableList(){
        return FXCollections.observableArrayList(persons);
    }
}
