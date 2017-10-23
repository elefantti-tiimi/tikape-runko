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
        lista.add("CREATE TABLE Pirtelo (id integer PRIMARY KEY, nimi varchar(255), ohje string, kuva string);");
        lista.add("CREATE TABLE Aines (id integer PRIMARY KEY, nimi varchar(255));");
        lista.add("CREATE TABLE AinesPirtelo (pirtelo_id integer, aines_id integer, maara float, tyyppi varchar(255), FOREIGN KEY (pirtelo_id) REFERENCES Pirtelo(id), FOREIGN KEY (aines_id) REFERENCES Aines(id));");
        lista.add("INSERT INTO Pirtelo (nimi, ohje, kuva) VALUES ('peruspirtelö', 'tunge kaikki aineet blenderiin ja muussaa kunnes sekaisin, lisää tarvittaessa maitoa tai jäätelöä jos haluat muokata koostumusta, huom. pirtelön pitää olla tarpeeksi hienoa, jottei fantin kärsä mene tukkoon!','/img/pirtelo.jpeg');");
        lista.add("INSERT INTO Pirtelo (nimi, ohje) VALUES ('banaanipirtelö', 'soseuta banaanit ja jäätelö blenderissä, nauti kookospalmun katveessa');");
        lista.add("INSERT INTO Pirtelo (nimi, ohje) VALUES ('suklaapirtelö', 'sulata suklaa vesihauteessa, surauta jäätelön kanssa blenderissä tasaiseksi mössöksi, lisää tarvittaessa maitoa');");
        lista.add("INSERT INTO Aines (nimi) VALUES ('jäätelö');");
        lista.add("INSERT INTO Aines (nimi) VALUES ('maito');");
        lista.add("INSERT INTO Aines (nimi) VALUES ('banaani');");
        lista.add("INSERT INTO Aines (nimi) VALUES ('suklaa');");
        lista.add("INSERT INTO Aines (nimi) VALUES ('kookospalmu');");
        lista.add("INSERT INTO AinesPirtelo (pirtelo_id, aines_id, maara, tyyppi) VALUES (1,1,1,'l')");
        lista.add("INSERT INTO AinesPirtelo (pirtelo_id, aines_id, maara, tyyppi) VALUES (1,2,2,'dl')");
        lista.add("INSERT INTO AinesPirtelo (pirtelo_id, aines_id, maara, tyyppi) VALUES (2,1,0.5,'l')");
        lista.add("INSERT INTO AinesPirtelo (pirtelo_id, aines_id, maara, tyyppi) VALUES (2,2,2.5,'dl')");
        lista.add("INSERT INTO AinesPirtelo (pirtelo_id, aines_id, maara, tyyppi) VALUES (2,3,2,'kpl')");
        lista.add("INSERT INTO AinesPirtelo (pirtelo_id, aines_id, maara, tyyppi) VALUES (2,5,1,'kpl, koristeeksi')");
        lista.add("INSERT INTO AinesPirtelo (pirtelo_id, aines_id, maara, tyyppi) VALUES (3,1,1,'l')");
        lista.add("INSERT INTO AinesPirtelo (pirtelo_id, aines_id, maara, tyyppi) VALUES (3,4,1,'levy')");
        

        return lista;
    }
}
