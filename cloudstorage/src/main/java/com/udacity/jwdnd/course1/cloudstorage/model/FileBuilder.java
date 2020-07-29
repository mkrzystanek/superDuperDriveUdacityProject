package com.udacity.jwdnd.course1.cloudstorage.model;

public class FileBuilder {

    private Integer fileId;
    private String filename;
    private String contenttype;
    private String filesize;
    private Integer userid;
    private byte[] filedata;

    public FileBuilder withFileId(Integer fileId) {
        this.fileId = fileId;
        return this;
    }

    public FileBuilder withFileName(String fileName) {
        this.filename = fileName;
        return this;
    }

    public FileBuilder withContentType(String contentType) {
        this.contenttype = contentType;
        return this;
    }

    public FileBuilder withFileSize(String userId) {
        this.filesize = userId;
        return this;
    }

    public FileBuilder withUserId(Integer userId) {
        this.userid = userId;
        return this;
    }

    public FileBuilder withFileData(byte[] fileData) {
        this.filedata = fileData;
        return this;
    }

    public File build() {
        return new File(fileId, filename, contenttype, filesize, userid, filedata);
    }
}
