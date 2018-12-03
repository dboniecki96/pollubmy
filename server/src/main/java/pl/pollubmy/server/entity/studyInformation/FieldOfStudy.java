package pl.pollubmy.server.entity.studyInformation;

import java.util.ArrayList;
import java.util.List;

public class FieldOfStudy {

    private String fieldOfStudy;
    private List<String> courses = new ArrayList<>();

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }
}
