package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import constants.Constants;
import entity.sto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

public class StoDAO {
    private final String url = Constants.url;
    private final String user = Constants.user;
    private final String password = Constants.password;

    public sto getSTOByNameLatLonPass(String name, double lat, double lon, String pass) {
        List<sto> stos = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = null;
            pstmt = conn.prepareStatement(//ST_DWithin для SRID 4326 використовує градуси, а не метри
                    "SELECT * " +
                            "FROM sto " +
                            "WHERE name = ? AND lat = ? AND lon = ? AND password = ?");

            pstmt.setString(1, name);
            pstmt.setDouble(2, lat);
            pstmt.setDouble(3, lon); // Convert the radius from kilometers to meters
            pstmt.setString(4, pass);

            ResultSet rs = pstmt.executeQuery();

            GetSTOList(stos, rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (stos.isEmpty())
            return null;
        return stos.get(0);//так як у нас тільки 1 елемент, то його й відправляємо
    }
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
    public List<sto> getStosInRangeOrOutOfRange(double centerLat, double centerLon, double rangeInMeters, boolean isInRange) {
        List<sto> stos = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = null;
            if (isInRange) { //якщо шукаємо в діапазоні
                 pstmt = conn.prepareStatement(//ST_DWithin для SRID 4326 використовує градуси, а не метри
                        "SELECT * " +
                                "FROM sto " +
                                "WHERE ST_DWithin(geo, ST_MakePoint(?, ?, 4326)::geography, ?)");
            }
            else {
                 pstmt = conn.prepareStatement(//ST_DWithin для SRID 4326 використовує градуси, а не метри
                        "SELECT * " +
                                "FROM sto " +
                                "WHERE NOT ST_DWithin(geo, ST_MakePoint(?, ?, 4326)::geography, ?)");
            }
            pstmt.setDouble(1, centerLat);
            pstmt.setDouble(2, centerLon);
            pstmt.setDouble(3, rangeInMeters); // Convert the radius from kilometers to meters

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
            sto.setQuality(rs.getDouble("quality"));
            sto.setSpeed(rs.getDouble("speed"));
            sto.setPrice(rs.getDouble("price"));
            sto.setServiceRange(rs.getDouble("service_range"));
            sto.setEvaluation(rs.getDouble("evaluation"));
            sto.setAddress(rs.getString("address"));
            sto.setLat(rs.getDouble("lat"));
            sto.setLon(rs.getDouble("lon"));
            sto.setResultValue(rs.getString("result_value"));
            sto.setPassword(rs.getString("password"));
            sto.setComments(rs.getString("comments"));
            sto.setWorking(rs.getString("working"));

            GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);
            Point pgGeo = gf.createPoint(new Coordinate(sto.getLat(), sto.getLon()));
            sto.setGeo(pgGeo);

            stos.add(sto);
        }
    }

}
