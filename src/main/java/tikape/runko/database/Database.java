package tikape.runko.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        List<String> lauseet = sqliteLauseet();

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        lista.add("CREATE TABLE Pirtelo (id integer PRIMARY KEY, nimi varchar(255));");
        lista.add("CREATE TABLE Aines (id integer PRIMARY KEY, nimi varchar(255));");
        lista.add("CREATE TABLE AinesPirtelo (pirtelo_id integer, aines_id integer, FOREIGN KEY (pirtelo_id) REFERENCES Pirtelo(id), FOREIGN KEY (aines_id) REFERENCES Aines(id));");
        lista.add("INSERT INTO Pirtelo (nimi) VALUES ('peruspirtelö');");
        lista.add("INSERT INTO Aines (nimi) VALUES ('jäätelö');");
        lista.add("INSERT INTO Pirtelo (nimi) VALUES ('muupirtelö');");

        return lista;
    }
}
