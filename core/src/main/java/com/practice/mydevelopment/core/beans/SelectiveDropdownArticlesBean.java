package com.practice.mydevelopment.core.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectiveDropdownArticlesBean {

    private String title;
    private String author;
    private String authorDescription;
    private Date publicationDate;

    public SelectiveDropdownArticlesBean(String title, String author, String authorDescription, Date publicationDate) {
        this.title = title;
        this.author = author;
        this.authorDescription = authorDescription;
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getAuthorDescription() {
        return authorDescription;
    }

    public String getPublicationDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(publicationDate);
    }
}
