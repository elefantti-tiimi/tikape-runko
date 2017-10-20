package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Aines;
import tikape.runko.domain.Pirtelo;
import tikape.runko.domain.AinesPirtelo;

public class AinesPirteloDao implements Dao<AinesPirtelo, Integer>{
    
    private Database database;

    public AinesPirteloDao(Database database) {
        this.database = database;
    }
    
    @Override
    public AinesPirtelo findOne(Integer i) throws SQLException {
        return null;
    };

    @Override
    public List<AinesPirtelo> findAll() throws SQLException {
        return null;
    };

    @Override
    public void delete(Integer i) throws SQLException {
    };
    
    @Override
    public Integer findIdByName(String name) throws SQLException {
        return null;
    };
    
    @Override
    public AinesPirtelo saveOrUpdate(AinesPirtelo ap) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO AinesPirtelo (pirtelo_id, aines_id, lukumaara) VALUES (?, ?, ?)");
            stmt.setInt(1, ap.getPirteloId());
            stmt.setInt(2, ap.getAinesId());
            stmt.setInt(3, ap.getMaara());
            stmt.executeUpdate();
        }

        return null;
    }
    
    public ArrayList<Aines> findAllForPirtelo(Integer haettavaId) throws SQLException {
        
        AinesDao ainesDao = new AinesDao(database);
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM AinesPirtelo WHERE pirtelo_id = ?");
        stmt.setInt(1, haettavaId);

        ResultSet rs = stmt.executeQuery();
        ArrayList<Aines> aines = new ArrayList<>();
        while (rs.next()) {
            Integer ainesId = rs.getInt("aines_id");
            aines.add(ainesDao.findOne(ainesId));
        }

        rs.close();
        stmt.close();
        connection.close();

        return aines;
    }
    
}
