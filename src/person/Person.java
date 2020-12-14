package person;

import javafx.beans.property.SimpleStringProperty;

import java.sql.SQLException;
import java.sql.Statement;

public class Person {

    private int pid = 0;
    private Statement st;
    private SimpleStringProperty username;
    private SimpleStringProperty vorname;
    private SimpleStringProperty nachname;
    private SimpleStringProperty email;

    public Person(int pid, Statement st, String u, String vn, String nn, String email){
        this(u,vn,nn,email);
        this.pid = pid;
        this.st = st;
    }
    public Person(String u,String vn,String nn, String email){
        username = new SimpleStringProperty(u);
        vorname = new SimpleStringProperty(vn);
        nachname = new SimpleStringProperty(nn);
        this.email = new SimpleStringProperty(email);
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getPid() {
        return pid;
    }

    public void setSt(Statement st) {
        this.st = st;
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getVorname() {
        return vorname.get();
    }

    public SimpleStringProperty vornameProperty() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname.set(vorname);
    }

    public String getNachname() {
        return nachname.get();
    }

    public SimpleStringProperty nachnameProperty() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname.set(nachname);
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    @Override
    public String toString() {
        return "Person.Person{" +
                "username=" + username +
                ", vorname=" + vorname +
                ", nachname=" + nachname +
                ", email=" + email +
                '}';
    }

    public void setColumn(int columnIndex,String value){
        switch(columnIndex) {
            case 1:
                setUsername(value);
                break;
            case 2:
                setVorname(value);
                break;
            case 3:
                setNachname(value);
                break;
            case 4:
                setEmail(value);
                break;
            default:
                assert false;
        }
        update();
    }

    public void update(){
        try{
            st.execute("UPDATE person SET username = '" + getUsername() + "'," +
                    "vorname = '" + getVorname() + "'," +
                    "nachname = '" + getNachname() + "'," +
                    "email = '" + getEmail() + "' " +
                    "WHERE pid = " + getPid() + ";");

        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
