package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Aines;
import tikape.runko.domain.Pirtelo;

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
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aines");

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
    
    public ArrayList<Pirtelo> findAllForAines(Integer haettavaId) throws SQLException {

        Connection connection = database.getConnection();
        PirteloDao pirteloDAO = new PirteloDao(this.database);
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM AinesPirtelo WHERE aines_id = ?");
        stmt.setInt(1, haettavaId);

        ResultSet rs = stmt.executeQuery();
        ArrayList<Pirtelo> pirtelo = new ArrayList<>();
        while (rs.next()) {
            Integer ainesId = rs.getInt("pirtelo_id");
            pirtelo.add(pirteloDAO.findOne(ainesId));
        }

        rs.close();
        stmt.close();
        connection.close();

        return pirtelo;
    }
    
    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Aines WHERE id = ?");
        stmt.setObject(1, key);
        
        stmt.execute();
        stmt.close();
        connection.close();
    }
    
    @Override
    public Aines saveOrUpdate(Aines object) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Aines(nimi) VALUES(?)");

        stmt.setString(1, object.getNimi());
        stmt.executeUpdate();

        stmt.close();
        conn.close();
        
        Aines a = new Aines(findIdByName(object.getNimi()), object.getNimi());
        return a;

    }
    
    @Override
    public Integer findIdByName(String name) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Aines WHERE nimi = ?");
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
