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
    public void saveOrUpdatePromotionData(LocalDateTime currentDate, double initialValue, double promotionValue, double promotionCoefficient, int criterionId, int universityId, double startDate, double targetDate, double promotionStep, boolean autoCalculatedPromotion) {
        Session currentSession = sessionFactory.getCurrentSession();
        List<UniversityModelingPromotion> universityModelingPromotions = sessionFactory.getCurrentSession()
                .createQuery("from university_modeling_promotion where startValue =:initialValue and promotionValue =:promotionValue and promotionCoefficient =:promotionCoefficient and criteriaTable.id =:criterionId and russianUniversitiesInQS.id =:universityId and startDate =:startDate and targetDate =:targetDate and promotionStep =:promotionStep and autoCalculatedPromotion =:autoCalculatedPromotion", UniversityModelingPromotion.class)
                .setParameter("initialValue", initialValue)
                .setParameter("promotionValue", promotionValue)
                .setParameter("promotionCoefficient", promotionCoefficient)
                .setParameter("criterionId", criterionId)
                .setParameter("universityId", universityId)
                .setParameter("startDate", startDate)
                .setParameter("targetDate", targetDate)
                .setParameter("promotionStep", promotionStep)
                .setParameter("autoCalculatedPromotion",autoCalculatedPromotion).list();
        UniversityModelingPromotion promotionData = new UniversityModelingPromotion();
        promotionData.setCalculationDate(currentDate);
        promotionData.setPromotionCoefficient(promotionCoefficient);
        promotionData.setAutoCalculatedPromotion(autoCalculatedPromotion);
        promotionData.setStartDate(startDate);
        promotionData.setStartValue(initialValue);
        promotionData.setTargetDate(targetDate);
        promotionData.setPromotionValue(promotionValue);
        promotionData.setRussianUniversitiesInQS(getRussianUniversityById(universityId));
        promotionData.setCriteriaTable(getCriterionById(criterionId));
        promotionData.setPromotionStep(promotionStep);
        if(universityModelingPromotions.size() == 0) {
            currentSession.save(promotionData);
        } else {
            universityModelingPromotions.get(0).setCalculationDate(currentDate);
            currentSession.flush();
        }
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

    @Override
    public int getNumberOfUniversityPromotionLaunches(int universityId, int criterionId) {
        return sessionFactory.getCurrentSession().createQuery(
                "from university_modeling_promotion where russianUniversitiesInQS.id =:universityId and criteriaTable.id =:criterionId"
        )
                .setParameter("universityId", universityId)
                .setParameter("criterionId", criterionId)
                .list()
                .size();
    }
}
