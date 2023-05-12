package EntityOperations;

import DAO.StoDAO;
import entity.sto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EntityOp {
    public static void main(String[] args) {
        List<sto> ress = get();
    }
    public static List<sto> get(){
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
////        EntityManager manager = factory.createEntityManager();
////        String st = manager.createQuery("FROM sto ").toString();
////
////        GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);
////        List<sto> list = manager.createQuery("SELECT s.lat, s.lon FROM sto s").getResultList();
////        Point point = gf.createPoint(new Coordinate(list.get(0).getLat(),list.get(0).getLon()));
////
////        return list;
        StoDAO st = new StoDAO();
        List<sto> res = st.getAllStos();
        return res;
    }
}
