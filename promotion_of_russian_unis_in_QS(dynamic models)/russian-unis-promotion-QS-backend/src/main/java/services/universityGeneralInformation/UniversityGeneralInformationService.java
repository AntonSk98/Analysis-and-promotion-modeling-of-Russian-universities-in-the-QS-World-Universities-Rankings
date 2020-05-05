package services.universityGeneralInformation;

import models.UniversityClassificationModel;
import models.UniversityCriterionModel;

import java.util.List;

public interface UniversityGeneralInformationService {

    List<UniversityClassificationModel> getUniversityClassificationById(int university_id);

    List<UniversityCriterionModel> getUniversityCriteriaById(int university_id);
}
