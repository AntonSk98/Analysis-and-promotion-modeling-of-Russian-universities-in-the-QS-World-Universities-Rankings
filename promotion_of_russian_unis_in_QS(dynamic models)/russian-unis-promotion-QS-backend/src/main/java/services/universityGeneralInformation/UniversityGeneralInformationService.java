package services.universityGeneralInformation;

import models.UniversityClassificationModel;
import models.UniversityCriteriaModel;

import java.util.List;

public interface UniversityGeneralInformationService {

    List<UniversityClassificationModel> getUniversityClassificationById(int university_id);

    List<UniversityCriteriaModel> getUniversityCriteriaById(int university_id);
}
