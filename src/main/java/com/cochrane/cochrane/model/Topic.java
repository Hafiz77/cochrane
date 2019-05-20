package com.cochrane.cochrane.model;

import com.cochrane.cochrane.repository.ReviewRepository;
import com.cochrane.cochrane.repository.TopicRepository;

import java.util.ArrayList;

public class Topic implements TopicRepository {
    public static final String CSS_SELECTOR = ".container .browse-by-list-item > a";
    private String title;
    private String link;
    private ArrayList<ReviewRepository> reviews = new ArrayList<>();

    public Topic(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    @Override
    public ArrayList<ReviewRepository> getReviews() {
        return reviews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setReviews(ArrayList<ReviewRepository> reviews) {
        this.reviews = reviews;
    }

    public void setReview(ReviewRepository review) {
        this.reviews.add(review);
    }

}
