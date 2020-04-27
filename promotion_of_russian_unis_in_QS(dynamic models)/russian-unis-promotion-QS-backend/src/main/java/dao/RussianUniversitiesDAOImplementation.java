package dao;

import entities.RussianUniversitiesInQS;
import entities.UniversityClassification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RussianUniversitiesDAOImplementation implements RuissianUniversitiesDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<RussianUniversitiesInQS> getAllTopRussianUniversititesInQS() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from russian_universities_in_QS", RussianUniversitiesInQS.class).list();
    }

    @Override
    public List<UniversityClassification> getClassificationById(int university_id) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from university_classification where russianUniversitiesInQS.id = :university_id", UniversityClassification.class)
                .setParameter("university_id",university_id).list();
    }
}

