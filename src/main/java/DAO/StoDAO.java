package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.sto;
import org.locationtech.jts.geom.Point;
import org.postgis.PGgeometry;

public class StoDAO {
    private final String url = "jdbc:postgresql://localhost:5432/sto_evaluation";
    private final String user = "postgres";
    private final String password = "12345";

    public List<sto> getAllStos() {
        List<sto> stos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM sto")) {

            while (rs.next()) {
                sto sto = new sto();

                // Get the geometry column as a PGgeometry object and convert it to a JTS Point
                PGgeometry pgGeo = (PGgeometry) rs.getObject("geo");

                sto.setGeo(pgGeo.getGeometry());

                sto.setInfoId(rs.getInt("info_id"));
                sto.setName(rs.getString("name"));
                sto.setOwner(rs.getString("owner"));
                sto.setQuality(rs.getInt("quality"));
                sto.setSpeed(rs.getInt("speed"));
                sto.setPrice(rs.getInt("price"));
                sto.setServiceRange(rs.getInt("service_range"));
                sto.setEvaluation(rs.getDouble("evaluation"));
                sto.setAddress(rs.getString("address"));
                sto.setLat(rs.getDouble("lat"));
                sto.setLon(rs.getDouble("lon"));
                sto.setResultValue(rs.getString("result_value"));

                stos.add(sto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stos;
    }
}
