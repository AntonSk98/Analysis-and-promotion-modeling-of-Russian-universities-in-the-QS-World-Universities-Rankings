package dao.topRussianUniversities;

import entities.RussianUniversitiesInQS;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopRussianUniversitiesDaoImpl implements TopRussianUniversitiesDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<RussianUniversitiesInQS> getAllTopRussianUniversititesInQS() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from russian_universities_in_QS", RussianUniversitiesInQS.class).list();
    }
}

