package pl.pollubmy.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class DatabaseFile {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String databaseFileId;

    @OneToOne
    private FileInformation fileInformation;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    public DatabaseFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public DatabaseFile() {
    }

    public String getDatabaseFileId() {
        return databaseFileId;
    }

    public void setDatabaseFileId(String databaseFileId) {
        this.databaseFileId = databaseFileId;
    }

    public FileInformation getFileInformation() {
        return fileInformation;
    }

    public void setFileInformation(FileInformation fileInformation) {
        this.fileInformation = fileInformation;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
