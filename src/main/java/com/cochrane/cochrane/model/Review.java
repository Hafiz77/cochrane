package com.cochrane.cochrane.model;

import com.cochrane.cochrane.repository.ReviewRepository;

import java.util.Date;

public class Review implements ReviewRepository {
    public static final String CSS_SELECTOR = ".search-results-section-body .search-results-item-body ";
    public static final String TITLE_CSS_SELECTOR = ".result-title > a";
    public static final String AUTHOR_CSS_SELECTOR = ".search-result-authors > div";
    public static final String DATE_CSS_SELECTOR = ".search-result-metadata-item .search-result-date";

    private String title;
    private String link;
    private String author;
    private Date date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
