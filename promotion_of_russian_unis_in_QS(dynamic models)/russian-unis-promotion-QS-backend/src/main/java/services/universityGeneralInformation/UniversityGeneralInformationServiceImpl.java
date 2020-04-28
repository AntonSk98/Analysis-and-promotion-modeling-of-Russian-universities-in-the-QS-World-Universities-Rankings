package services.universityGeneralInformation;

import dao.topRussianUniversities.TopRussianUniversitiesDao;
import dao.universityGeneralInformation.UniversityGeneralInformationDao;
import models.UniversityClassificationModel;
import models.UniversityCriteriaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UniversityGeneralInformationServiceImpl implements UniversityGeneralInformationService {

    @Autowired
    UniversityGeneralInformationDao universityGeneralInformationDao;

    @Override
    @Transactional
    public List<UniversityClassificationModel> getUniversityClassificationById(int university_id) {
        return universityGeneralInformationDao.getClassificationById(university_id);
    }

    @Override
    @Transactional
    public List<UniversityCriteriaModel> getUniversityCriteriaById(int university_id) {
        return universityGeneralInformationDao.getCriteriaById(university_id);
    }
}
