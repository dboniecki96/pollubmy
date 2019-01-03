package pl.pollubmy.server.entity.studyInformation;

import java.util.ArrayList;
import java.util.List;

public class StudyInformation {

    private String faculty;
    private List<FieldOfStudy> fieldOfStudies = new ArrayList<>();

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public List<FieldOfStudy> getFieldOfStudies() {
        return fieldOfStudies;
    }

    public void setFieldOfStudies(List<FieldOfStudy> fieldOfStudies) {
        this.fieldOfStudies = fieldOfStudies;
    }
}
