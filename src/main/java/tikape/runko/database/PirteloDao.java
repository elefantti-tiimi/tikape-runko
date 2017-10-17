package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Pirtelo;

public class PirteloDao implements Dao<Pirtelo, Integer>{
    
    private Tietokanta database;

    public PirteloDao(Tietokanta database) {
        this.database = database;
    }

    @Override
    public Pirtelo findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Pirtelo WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Pirtelo o = new Pirtelo(1,"","",0.0);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Pirtelo> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Pirtelo");

        ResultSet rs = stmt.executeQuery();
        List<Pirtelo> pirtelot = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

        //    pirtelot.add(new Pirtelo(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return pirtelot;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}
