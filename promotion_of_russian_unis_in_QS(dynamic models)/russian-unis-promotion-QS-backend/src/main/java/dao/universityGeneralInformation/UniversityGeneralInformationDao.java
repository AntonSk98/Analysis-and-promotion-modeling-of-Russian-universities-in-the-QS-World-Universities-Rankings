package dao.universityGeneralInformation;

import models.UniversityClassificationModel;
import models.UniversityCriteriaModel;

import java.util.List;

public interface UniversityGeneralInformationDao {

    List<UniversityClassificationModel> getClassificationById(int university_id);

    List<UniversityCriteriaModel> getCriteriaById(int university_id);
}
