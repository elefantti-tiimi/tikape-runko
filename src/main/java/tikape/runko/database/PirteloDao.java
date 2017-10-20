package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Aines;
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
            
            Pirtelo uusi = new Pirtelo(id, nimi);
            
            pirtelot.add(uusi);
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
        
        if(this.findOne(object.getId()) != null) {
            this.delete(object.getId());
        }
        
        
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Pirtelo(nimi) VALUES(?)");

        stmt.setString(1, object.getNimi());
        stmt.executeUpdate();

        stmt.close();
        
        if (!object.getAinekset().isEmpty()) {
            for (int i = 0; i < object.getAinekset().size(); i++) {
                PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO AinesPirtelo(aines_id, pirtelo_id) VALUES(?,?)");

                stmt2.setInt(1, object.getAinekset().get(i).getId());
                stmt2.setInt(2, object.getId());

                stmt2.executeUpdate();

                stmt2.close();
            }
        }
        
        conn.close();
        
        
        Integer uusiId = findIdByName(object.getNimi());
        Pirtelo p = new Pirtelo(uusiId, object.getNimi());
        p.setAinekset(this.findAllForPirtelo(uusiId));
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
    
    public ArrayList<Aines> findAllForPirtelo(Integer haettavaId) throws SQLException {

        Connection connection = database.getConnection();
        AinesDao ainesDAO = new AinesDao(this.database);
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM AinesPirtelo WHERE pirtelo_id = ?");
        stmt.setInt(1, haettavaId);

        ResultSet rs = stmt.executeQuery();
        ArrayList<Aines> ainekset = new ArrayList<>();
        while (rs.next()) {
            Integer ainesId = rs.getInt("aines_id");
            ainekset.add(ainesDAO.findOne(ainesId));
        }

        rs.close();
        stmt.close();
        connection.close();

        return ainekset;
    }

}
