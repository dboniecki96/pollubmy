package pl.pollubmy.server.entity.tool;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import pl.pollubmy.server.entity.studyInformation.FieldOfStudy;
import pl.pollubmy.server.entity.studyInformation.StudyInformation;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReaderXML {

    private List<StudyInformation> studyInformationList = new ArrayList<>();

    public List<StudyInformation> scanFile() {

        String facultyValue;
        String fieldOfStudyValue;
        String courseValue;
        StudyInformation studyInformation = null;
        FieldOfStudy fieldOfStudyObject;

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("studyInformation/study.xml")).getFile());

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(file);

            NodeList facultyList = document.getElementsByTagName("faculty");

            for (int i = 0; i < facultyList.getLength(); i++) {

                Node faculty = facultyList.item(i);

                if (faculty.getNodeType() == Node.ELEMENT_NODE) {

                    studyInformation = new StudyInformation();

                    Element facultyElement = (Element) faculty;

                    facultyValue = facultyElement.getAttribute("name");
                    studyInformation.setFaculty(facultyValue);

                    NodeList fieldOfStudyList = facultyElement.getChildNodes();

                    for (int j = 0; j < fieldOfStudyList.getLength(); j++) {

                        Node fieldOfStudy = fieldOfStudyList.item(j);

                        if (fieldOfStudy.getNodeType() == Node.ELEMENT_NODE) {
                            Element fieldOfStudyElement = (Element) fieldOfStudy;

                            fieldOfStudyValue = fieldOfStudyElement.getAttribute("name");
                            fieldOfStudyObject = new FieldOfStudy();

                            fieldOfStudyObject.setFieldOfStudy(fieldOfStudyValue);

                            NodeList courseList = fieldOfStudyElement.getChildNodes();

                            for (int k = 0; k < courseList.getLength(); k++) {

                                Node course = courseList.item(k);

                                if (course.getNodeType() == Node.ELEMENT_NODE) {
                                    Element courseElement = (Element) course;
                                    courseValue = courseElement.getTextContent();
                                    fieldOfStudyObject.getCourses().add(courseValue);
                                }
                            }
                            studyInformation.getFieldOfStudies().add(fieldOfStudyObject);
                        }
                    }
                }
                this.studyInformationList.add(studyInformation);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return this.studyInformationList;
    }
}