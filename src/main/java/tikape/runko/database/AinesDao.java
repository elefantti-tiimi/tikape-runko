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
        //o.setPirtelot(this.findAllForAines(id));

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
        
        if(this.findOne(object.getId()) != null) {
            this.delete(object.getId());
        }
        
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Aines(nimi) VALUES(?)");

        stmt.setString(1, object.getNimi());
        stmt.executeUpdate();

        stmt.close();
        
//        if (!object.getPirtelot().isEmpty()) {
//            for (int i = 0; i < object.getPirtelot().size(); i++) {
//                PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO AinesPirtelo(pirtelo_id, aines_id) VALUES(?,?)");
//
//                stmt2.setInt(1, object.getPirtelot().get(i).getId());
//                stmt2.setInt(2, object.getId());
//
//                stmt2.executeUpdate();
//
//                stmt2.close();
//            }
//        }
        
        conn.close();
        
        Integer uusiId = findIdByName(object.getNimi());
        Aines a = new Aines(uusiId, object.getNimi());
        //a.setPirtelot(this.findAllForAines(uusiId));
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
    
    public ArrayList<Aines> findAllForPirtelo(Integer haettavaId) throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM AinesPirtelo WHERE pirtelo_id = ?");
        stmt.setInt(1, haettavaId);

        ResultSet rs = stmt.executeQuery();
        ArrayList<Aines> ainekset = new ArrayList<>();
        while (rs.next()) {
            Integer ainesId = rs.getInt("aines_id");
            ainekset.add(findOne(ainesId));
        }

        rs.close();
        stmt.close();
        connection.close();

        return ainekset;
    }
    
}
