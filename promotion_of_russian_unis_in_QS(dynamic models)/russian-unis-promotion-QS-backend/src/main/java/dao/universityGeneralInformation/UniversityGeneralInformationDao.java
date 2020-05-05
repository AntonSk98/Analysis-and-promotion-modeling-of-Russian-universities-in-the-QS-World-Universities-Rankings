package dao.universityGeneralInformation;

import models.UniversityClassificationModel;
import models.UniversityCriterionModel;

import java.util.List;

public interface UniversityGeneralInformationDao {

    List<UniversityClassificationModel> getClassificationById(int university_id);

    List<UniversityCriterionModel> getCriteriaById(int university_id);
}
