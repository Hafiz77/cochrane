package com.cochrane.cochrane.scraper;

import com.cochrane.cochrane.repository.ReviewRepository;

import java.util.ArrayList;

public interface ReviewScraper extends Scraper {
    ArrayList<ReviewRepository> getReviews();
}
