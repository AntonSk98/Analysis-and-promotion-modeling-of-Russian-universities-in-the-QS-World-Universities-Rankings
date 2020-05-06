package dao.universityPromotion;

import entities.CriteriaTableInQS;
import entities.RussianUniversitiesInQS;
import entities.UniversityModelingPromotion;
import models.UniversityCriterionModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class UniversityPromotionDaoImpl implements UniversityPromotionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int getCriterionIdByCriterionName(String criterionName) {
        return sessionFactory.getCurrentSession()
                .createQuery("select id from criteria_table_in_QS where criterionName = :criterion_name", Integer.class)
                .setParameter("criterion_name", criterionName).list().get(0);
    }

    @Override
    public int getUniversityIdByUniversityName(String universityName) {
        return sessionFactory.getCurrentSession()
                .createQuery("select id from russian_universities_in_QS where institutionName = :university_name", Integer.class)
                .setParameter("university_name", universityName).list().get(0);
    }

    @Override
    public UniversityCriterionModel getUniversityCriterionInfoByUniIdAndCriterionId(int uniId, int criterionId) {
        return sessionFactory.getCurrentSession()
                .createQuery("select new models.UniversityCriterionModel(id, russianUniversitiesInQS.institutionName, criteriaTable.criterionName, scoreRank2019, scoreRank2020) from university_criteria where russianUniversitiesInQS.id = :universityId and criteriaTable.id = :criterionId", UniversityCriterionModel.class)
                .setParameter("universityId", uniId)
                .setParameter("criterionId", criterionId)
                .list().get(0);
    }

    @Override
    public List<String> getQSCriteriaList() {
        return sessionFactory.getCurrentSession()
                .createQuery("select criterionName from criteria_table_in_QS where criterionName != 'Overall Score'", String.class).list();
    }

    @Override
    public void savePromotionData(LocalDateTime currentDate, double initialValue, double promotionValue, double promotionCoefficient, int criterionId, int universityId, double startDate, double targetDate, double promotionStep, boolean autoCalculatedPromotion) {
        Session currentSession = sessionFactory.getCurrentSession();
        UniversityModelingPromotion promotionDate = new UniversityModelingPromotion();
        promotionDate.setCalculationDate(currentDate);
        promotionDate.setPromotionCoefficient(promotionCoefficient);
        promotionDate.setAutoCalculatedPromotion(autoCalculatedPromotion);
        promotionDate.setStartDate(startDate);
        promotionDate.setStartValue(initialValue);
        promotionDate.setTargetDate(targetDate);
        promotionDate.setPromotionValue(promotionValue);
        promotionDate.setRussianUniversitiesInQS(getRussianUniversityById(universityId));
        promotionDate.setCriteriaTable(getCriterionById(criterionId));
        promotionDate.setPromotionStep(promotionStep);
        currentSession.save(promotionDate);
    }

    @Override
    public RussianUniversitiesInQS getRussianUniversityById(int universityId) {
        return sessionFactory.getCurrentSession().createQuery(
                "from russian_universities_in_QS where id =:university_id", RussianUniversitiesInQS.class)
                .setParameter("university_id", universityId).list().get(0);
    }

    @Override
    public CriteriaTableInQS getCriterionById(int criterionId) {
        return sessionFactory.getCurrentSession().createQuery(
                "from criteria_table_in_QS where id =:criterion_id", CriteriaTableInQS.class)
                .setParameter("criterion_id", criterionId).list().get(0);
    }

    @Override
    public double getAutocalculatedPromotionCoefficient(int universityId, int criterionId, double startDate, double targetDate, double promotionStep) {
        return sessionFactory.getCurrentSession().createQuery(
                "select promotionCoefficient from university_modeling_promotion where russianUniversitiesInQS.id =:universityId and criteriaTable.id =:criterionId and autoCalculatedPromotion = true and startDate =:startDate and targetDate =:targetDate and promotionStep =:promotionStep", Double.class
        )
                .setParameter("universityId", universityId)
                .setParameter("criterionId", criterionId)
                .setParameter("startDate", startDate)
                .setParameter("targetDate", targetDate)
                .setParameter("promotionStep", promotionStep)
                .list().get(0);
    }

    @Override
    public void deletePromotionDataByIds(int universityId, int criterionId) {
        sessionFactory.getCurrentSession().createQuery(
                "delete from university_modeling_promotion where russianUniversitiesInQS.id =:universityId and criteriaTable.id =:criterionId"
        )
                .setParameter("universityId", universityId)
                .setParameter("criterionId", criterionId)
                .executeUpdate();
    }
}
