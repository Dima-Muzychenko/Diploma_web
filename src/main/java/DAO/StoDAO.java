package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.sto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class StoDAO {
    private final String url = "jdbc:postgresql://localhost:5432/sto_evaluation";
    private final String user = "postgres";
    private final String password = "12345";
    private final double kmInOneDegree = 111.134861111;

    //отримуємо всі СТО
    public List<sto> getAllStos() {
        List<sto> stos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM sto")) {
            GetSTOList(stos, rs);//отримуємо список СТО
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stos;
    }

    //Отримуємо СТО в діапазоні
    public List<sto> getStosInRange(double centerLat, double centerLon, double rangeInMeters) {
        List<sto> stos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(//ST_DWithin для SRID 4326 використовує градуси, а не метри
                     "SELECT * " +
                             "FROM sto " +
                             "WHERE ST_DWithin(geo, ST_MakePoint(?, ?, 4326)::geography, ?)")) {

            pstmt.setDouble(1, centerLat);
            pstmt.setDouble(2, centerLon);
            pstmt.setDouble(3, rangeInMeters); // Convert the radius from kilometers to meters

            String st = pstmt.toString();

            ResultSet rs = pstmt.executeQuery();

            GetSTOList(stos, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stos;
    }

    //(допоміжна функція) Отримуємо список СТО
    private void GetSTOList(List<sto> stos, ResultSet rs) throws SQLException {
        while (rs.next()) {
            sto sto = new sto();

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

            GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);
            Point pgGeo = gf.createPoint(new Coordinate(sto.getLon(), sto.getLat()));
            sto.setGeo(pgGeo);

            stos.add(sto);
        }
    }

//    public List<Sto> getStosInRange(double centerLat, double centerLon, double range) {
//        List<Sto> stos = new ArrayList<>();
//
//        try (Connection conn = DriverManager.getConnection(url, user, password);
//             PreparedStatement stmt = conn.prepareStatement("SELECT geo, lat, lon, name FROM sto WHERE ST_DWithin(geo, ?, ?)")) {
//
//            // Create a point object from the center latitude and longitude
//            Point centerPoint = new Point(centerLon, centerLat);
//            centerPoint.setSrid(4326);
//
//            // Create a box object that represents the range around the center point
//            Geometry box = centerPoint.expand(range / 111.319, range / 111.319, true);
//            PGboxbase pgBox = new PGboxbase(box);
//
//            // Set the box object as a parameter for the prepared statement
//            stmt.setObject(1, pgBox);
//            stmt.setDouble(2, range);
//
//            try (ResultSet rs = stmt.executeQuery()) {
//                while (rs.next()) {
//                    Sto sto = new Sto();
//
//                    // Get the geometry column as a PGgeometry object and convert it to a JTS Point
//                    PGgeometry pgGeo = (PGgeometry) rs.getObject("geo");
//                    sto.setGeo((Point) pgGeo.getGeometry());
//
//                    sto.setLat(rs.getDouble("lat"));
//                    sto.setLon(rs.getDouble("lon"));
//                    sto.setName(rs.getString("name"));
//
//                    stos.add(sto);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return stos;
//    }
}
