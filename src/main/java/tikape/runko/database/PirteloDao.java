package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Pirtelo;

public class PirteloDao implements Dao<Pirtelo, Integer>{
    
    private Database database;

    public PirteloDao(Database database) {
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

        Pirtelo o = new Pirtelo(id,nimi);//,"",0.0);

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

            pirtelot.add(new Pirtelo(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return pirtelot;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Pirtelo WHERE id = ?");
        stmt.setObject(1, key);
        
        stmt.execute();
        stmt.close();
        connection.close();
    }
    
    @Override
    public Pirtelo saveOrUpdate(Pirtelo object) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Pirtelo(nimi) VALUES(?)");

        stmt.setString(1, object.getNimi());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        
        Pirtelo p = new Pirtelo(findIdByName(object.getNimi()), object.getNimi());
        return p;

    }
    
    public Integer findIdByName(String name) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Pirtelo WHERE nimi = ?");
        stmt.setObject(1, name);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        rs.close();
        stmt.close();
        connection.close();

        return id;
    }

}
