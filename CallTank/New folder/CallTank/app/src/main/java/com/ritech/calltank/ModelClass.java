package com.ritech.calltank;

import android.net.Uri;

public class ModelClass {

    String path,filename;
    Uri uri;
    Long date;

    public ModelClass(String path, String filename,   Uri ur, Long date) {
        this.path = path;
        this.filename = filename;
        this.date = date;
        this.uri = uri;
    }

    public ModelClass() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }


    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
