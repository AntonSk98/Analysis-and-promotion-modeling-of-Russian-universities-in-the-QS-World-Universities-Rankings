package services.allRussianUniversities;

import entities.RussianUniversitiesInQS;
import models.UniversityClassificationModel;
import models.UniversityCriteriaModel;

import java.util.List;

public interface TopRussianUniversitiesService {

    List<RussianUniversitiesInQS> getAllRussianTopUniversities();
}
