package dao.exportData;

import entities.UniversityModelingPromotion;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExportDataImpl implements ExportDataDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<UniversityModelingPromotion> getPromotionInfoByUniIdAndCriterionId(int universityId, int criterionId) {
        return sessionFactory.getCurrentSession()
                .createQuery("from university_modeling_promotion where russianUniversitiesInQS.id =:universityId and  criteriaTable.id =:criterionId", UniversityModelingPromotion.class)
                .setParameter("universityId", universityId)
                .setParameter("criterionId", criterionId).list();
    }

    @Override
    public int getUniversityIdByUniversityName(String universityName) {
        return sessionFactory.getCurrentSession()
                .createQuery("select id from russian_universities_in_QS where institutionName =:universityName", Integer.class)
                .setParameter("universityName", universityName).list().get(0);
    }

    @Override
    public int getCriterionIdByUniversityCriterion(String criterionName) {
        return sessionFactory.getCurrentSession()
                .createQuery("select id from criteria_table_in_QS where criterionName =:criterionName", Integer.class)
                .setParameter("criterionName", criterionName).list().get(0);
    }
}
