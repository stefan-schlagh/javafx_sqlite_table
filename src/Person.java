import javafx.beans.property.SimpleStringProperty;

public class Person {

    private SimpleStringProperty username;
    private SimpleStringProperty vorname;
    private SimpleStringProperty nachname;
    private SimpleStringProperty email;

    public Person(String u,String vn,String nn, String email){
        username = new SimpleStringProperty(u);
        vorname = new SimpleStringProperty(vn);
        nachname = new SimpleStringProperty(nn);
        this.email = new SimpleStringProperty(email);
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
}
