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
    public String getUniversityNameByUniversityId(int universityId) {
        return sessionFactory.getCurrentSession()
                .createQuery("select institutionName from russian_universities_in_QS where id =:universityId", String.class)
                .setParameter("universityId", universityId).list().get(0);
    }

    @Override
    public String getUniversityCriterionByCriterionId(int criterionId) {
        return sessionFactory.getCurrentSession()
                .createQuery("select criterionName from criteria_table_in_QS where id =:criterionId", String.class)
                .setParameter("criterionId", criterionId).list().get(0);
    }
}
