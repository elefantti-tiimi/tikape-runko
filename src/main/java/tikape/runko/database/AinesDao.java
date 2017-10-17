package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Aines;

public class AinesDao implements Dao<Aines, Integer>{

    private Database database;

    public AinesDao(Database database) {
        this.database = database;
    }

    @Override
    public Aines findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aines WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        Aines o = new Aines(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return o;
    }

    @Override
    public List<Aines> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Opiskelija");

        ResultSet rs = stmt.executeQuery();
        List<Aines> ainekset = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            ainekset.add(new Aines(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return ainekset;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }
    
    public void saveOrUpdate(Aines object) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Aines(nimi= ?");

        stmt.setString(1, object.getNimi());
        stmt.executeUpdate();

        stmt.close();
        conn.close();

    }
    
    
}
