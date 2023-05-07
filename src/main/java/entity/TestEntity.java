package entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestEntity {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
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
}
