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

public class AinesPirteloDao implements Dao<AinesPirtelo, Integer> {

    private Database database;

    public AinesPirteloDao(Database database) {
        this.database = database;
    }

    @Override
    public AinesPirtelo findOne(Integer i) throws SQLException {
        return null;
    }

    ;
    
    private AinesPirtelo findOne(AinesPirtelo ap) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM AinesPirtelo WHERE pirtelo_id =? AND aines_id =?");
            stmt.setInt(1, ap.getPirtelo().getId());
            stmt.setInt(2, ap.getAines().getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                AinesPirtelo uusiAP = new AinesPirtelo(ap.getPirtelo(), ap.getAines(), rs.getDouble("maara"), rs.getString("tyyppi"));
                rs.close();
                stmt.close();
                conn.close();
                return uusiAP;
            }
            rs.close();
            stmt.close();
            conn.close();
        }
        return null;
    }

    ;

    @Override
    public List<AinesPirtelo> findAll() throws SQLException {
        return null;
    }

    ;

    @Override
    public void delete(Integer i) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM AinesPirtelo WHERE pirtelo_id =?");
            stmt.setInt(1, i);
            stmt.execute();
            stmt.close();
            conn.close();
        }
    }

    ;
    
    public void deleteAllWithAines(Integer i) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM AinesPirtelo WHERE aines_id =?");
            stmt.setInt(1, i);
            stmt.execute();
            stmt.close();
            conn.close();
        }
    }

    ;
    
    public void deleteOne(Integer i, Integer j) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM AinesPirtelo WHERE pirtelo_id =? AND aines_id =?");
            stmt.setInt(1, i);
            stmt.setInt(2, j);
            stmt.execute();
            stmt.close();
            conn.close();
        }
    }

    ;
    
    @Override
    public Integer findIdByName(String name) throws SQLException {
        return null;
    }

    ;
    
    @Override
    public AinesPirtelo saveOrUpdate(AinesPirtelo ap) throws SQLException {
        if (findOne(ap) != null) {
            Connection conn = database.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE AinesPirtelo SET maara =?, tyyppi=?, kuvaus=? WHERE pirtelo_id=? AND aines_id=?");
            stmt.setDouble(1, ap.getMaara());
            stmt.setString(2, ap.getTyyppi());
            stmt.setString(3, ap.getKuvaus());
            stmt.setInt(4, ap.getPirtelo().getId());
            stmt.setInt(5, ap.getAines().getId());

            stmt.execute();
            stmt.close();
            conn.close();
            return null;
        }

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO AinesPirtelo (pirtelo_id, aines_id, maara, tyyppi, kuvaus) VALUES (?, ?, ?, ?, ?)");
        stmt.setInt(1, ap.getPirtelo().getId());
        stmt.setInt(2, ap.getAines().getId());
        stmt.setDouble(3, ap.getMaara());
        stmt.setString(4, ap.getTyyppi());
        stmt.setString(5, ap.getKuvaus());
        stmt.execute();
        stmt.close();
        conn.close();
        return null;
    }
    
    public ArrayList<AinesPirtelo> findAllForPirtelo(Integer haettavaId) throws SQLException {
        PirteloDao pirteloDao = new PirteloDao(database);
        AinesDao ainesDao = new AinesDao(database);
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM AinesPirtelo WHERE pirtelo_id = ?");
        stmt.setInt(1, haettavaId);

        ResultSet rs = stmt.executeQuery();
        ArrayList<AinesPirtelo> ap = new ArrayList<>();
        while (rs.next()) {
            Aines a = ainesDao.findOne(rs.getInt("aines_id"));
            Pirtelo p = pirteloDao.findOne(rs.getInt("pirtelo_id"));
            Double maara = rs.getDouble("maara");
            String tyyppi = rs.getString("tyyppi");
            AinesPirtelo uusiAP = new AinesPirtelo(p, a, maara, tyyppi);
            if (rs.getString("kuvaus") != null) {
                uusiAP.setKuvaus(rs.getString("kuvaus"));
            }
            else {
                uusiAP.setKuvaus("");
            }
            ap.add(uusiAP);
        }

        rs.close();
        stmt.close();
        connection.close();

        return ap;
    }

}
