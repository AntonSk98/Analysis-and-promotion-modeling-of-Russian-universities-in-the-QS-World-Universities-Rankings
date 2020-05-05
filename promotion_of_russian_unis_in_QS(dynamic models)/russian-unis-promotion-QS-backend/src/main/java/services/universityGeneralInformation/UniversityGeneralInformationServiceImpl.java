package services.universityGeneralInformation;

import dao.universityGeneralInformation.UniversityGeneralInformationDao;
import models.UniversityClassificationModel;
import models.UniversityCriterionModel;
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
    public List<UniversityCriterionModel> getUniversityCriteriaById(int university_id) {
        return universityGeneralInformationDao.getCriteriaById(university_id);
    }
}
