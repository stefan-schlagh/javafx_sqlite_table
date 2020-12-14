package data;

import person.Person;
import javafx.collections.FXCollections;

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

    public List<Person> persons;

    public Data(){
        try{
            con = DriverManager.getConnection("jdbc:sqlite:D:\\workspace\\java\\javafx\\Tabellen\\DB.db");

            con.createStatement().execute("CREATE TABLE IF NOT EXISTS person (" +
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
            List<Person> personsN = new ArrayList<>();
            ResultSet res = con.createStatement().executeQuery("SELECT * FROM person;");

            while(res.next()){
                Person p = new Person(
                        res.getInt(1),
                        con.createStatement(),
                        res.getString(2),
                        res.getString(3),
                        res.getString(4),
                        res.getString(5)
                );
                personsN.add(p);
            }
            persons = FXCollections.observableArrayList(personsN);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void addData(){
        try{
            Statement st = con.createStatement();

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

    public List<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person p){
        try {
            con.createStatement().execute("INSERT INTO person (username,vorname,nachname,email)" +
                    "VALUES ('" + p.getUsername() + "','" + p.getVorname() + "','" + p.getNachname() + "','" + p.getEmail() + "');");

            // get max pid from table --> the obne that has been inserted
            ResultSet res = con.createStatement().executeQuery("SELECT MAX(pid) AS 'pid' FROM person;");

            res.next();
            p.setPid(res.getInt("pid"));
            p.setSt(con.createStatement());

        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void deletePerson(Person p){
        try {
            con.createStatement().execute("DELETE FROM person WHERE pid = " + p.getPid() + ";");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
