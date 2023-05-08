package entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class TestEntity {
    public static void main(String[] args) {

        addEntity();
        //addEntity2();
    }

    //отримання в діапазоні
    public List<Sto> getStoWithinRange(double latitude, double longitude, double range) {//latitude, longitude - точкацентра
        //range: 0.1 is equivalent to 10 kilometers
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();

        CriteriaBuilder builder  = manager.getCriteriaBuilder();
        CriteriaQuery<Sto> query = builder .createQuery(Sto.class);
        Root<Sto> root = query.from(Sto.class);

        double earthRadius = 6371.0; // Earth's radius in kilometers

        double latitudeRadians = Math.toRadians(latitude);
        double longitudeRadians = Math.toRadians(longitude);

        double maxLatitude = Math.toDegrees(Math.asin(Math.sin(latitudeRadians) * Math.cos(range / earthRadius)
                + Math.cos(latitudeRadians) * Math.sin(range / earthRadius) * Math.cos(0)));

        double minLatitude = Math.toDegrees(Math.asin(Math.sin(latitudeRadians) * Math.cos(range / earthRadius)
                + Math.cos(latitudeRadians) * Math.sin(range / earthRadius) * Math.cos(Math.PI)));

        double maxLongitude = Math.toDegrees(longitudeRadians + Math.asin(Math.sin(Math.PI / 2) * Math.sin(range / earthRadius)
                / Math.cos(latitudeRadians)));

        double minLongitude = Math.toDegrees(longitudeRadians - Math.asin(Math.sin(Math.PI / 2) * Math.sin(range / earthRadius)
                / Math.cos(latitudeRadians)));

        query.select(root).where(
                builder.between(root.get("lat"), minLatitude, maxLatitude),
                builder.between(root.get("lon"), minLongitude, maxLongitude)
        );


        List<Sto> results = manager.createQuery(query).getResultList();

        manager.getTransaction().commit();
        manager.close();
        factory.close();
        return results;

    }


    static void addEntity(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        //додання в БД
        manager.getTransaction().begin();
        Sto sto = new Sto();
        sto.setPrice(7);
        sto.setQuality(5);
        sto.setSpeed(4);
        sto.setServiceRange(8);
        sto.setEvaluation(6.21);
        manager.persist(sto);


        manager.getTransaction().commit();
        manager.close();
        factory.close();
    }
//    static void addEntity2(){
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        // Open a new Hibernate session
//        Session session = sessionFactory.openSession();
//
//        // Begin a new transaction
//        Transaction transaction = session.beginTransaction();
//
//        // Create a new StoEntity object with the specified properties
//        Sto sto = new Sto();
//
//        // Save the new StoEntity object to the database
//        session.persist(sto);
//
//        // Commit the transaction
//        transaction.commit();
//
//        // Close the Hibernate session
//        session.close();
//    }


}
