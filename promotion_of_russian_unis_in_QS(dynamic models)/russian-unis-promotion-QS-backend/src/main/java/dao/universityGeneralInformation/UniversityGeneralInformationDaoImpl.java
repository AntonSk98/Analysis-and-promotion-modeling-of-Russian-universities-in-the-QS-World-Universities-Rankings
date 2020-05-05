package dao.universityGeneralInformation;

import models.UniversityClassificationModel;
import models.UniversityCriterionModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UniversityGeneralInformationDaoImpl implements UniversityGeneralInformationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<UniversityClassificationModel> getClassificationById(int university_id) {
        String query = "select new models.UniversityClassificationModel(id, russianUniversitiesInQS.institutionName, classificationTable.classificationName, classificationType) from university_classification where russianUniversitiesInQS.id = :university_id";
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(
                query,
                UniversityClassificationModel.class)
                .setParameter("university_id", university_id).list();
    }

    @Override
    public List<UniversityCriterionModel> getCriteriaById(int university_id) {
        String query = "select new models.UniversityCriterionModel(id, russianUniversitiesInQS.institutionName, criteriaTable.criterionName, scoreRank2019, scoreRank2020) from university_criteria  where russianUniversitiesInQS.id = :university_id";
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(
                query,
                UniversityCriterionModel.class)
                .setParameter("university_id", university_id).list();
    }
}
