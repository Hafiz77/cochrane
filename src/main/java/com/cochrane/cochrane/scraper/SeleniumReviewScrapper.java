package com.cochrane.cochrane.scraper;

import com.cochrane.cochrane.model.Review;
import com.cochrane.cochrane.repository.ReviewRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SeleniumReviewScrapper implements ReviewScraper {
    private  ArrayList<ReviewRepository> reviewList = null;

    public SeleniumReviewScrapper(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    @Override
    public ArrayList<ReviewRepository> getReviews() {
        return this.reviewList;
    }

    @Override
    public void scrap(String url) {

        reviewList = new ArrayList<>();

        this.reviewList.addAll(this.scrapReviews(url));

        ArrayList<String> paginationLinks = new ArrayList<>();

        List<WebElement> paginationElementList = driver.findElements(By.cssSelector(".pagination .pagination-page-links .pagination-page-list-item > a"));

        ArrayList<String> pageUrls = new ArrayList<>();

        //Scrap from second page
        for (int i = 1; i < paginationElementList.size(); i++) {
            String pageUrl = paginationElementList.get(i).getAttribute("href");
            if (pageUrl != null && pageUrl.length()>0) {
                pageUrls.add(pageUrl);
            }
        }

        for (int i = 0; i < pageUrls.size(); i++) {
            this.reviewList.addAll(this.scrapReviews(pageUrls.get(i)));
        }
    }

    public ArrayList<ReviewRepository> scrapReviews(String url) {

        driver.get(url);

        List<WebElement> topicElementList  = driver.findElements(By.cssSelector(Review.CSS_SELECTOR));

        ArrayList<ReviewRepository> reviewList = new ArrayList<>();

        for (int i = 0; i < topicElementList.size(); i++) {
            String title = topicElementList.get(i).findElement(By.cssSelector(Review.TITLE_CSS_SELECTOR)).getText();
            String link = topicElementList.get(i).findElement(By.cssSelector(Review.TITLE_CSS_SELECTOR)).getAttribute("href");
            String author = topicElementList.get(i).findElement(By.cssSelector(Review.AUTHOR_CSS_SELECTOR)).getText();
            String date = topicElementList.get(i).findElement(By.cssSelector(Review.DATE_CSS_SELECTOR)).getText();

            Review review = new Review();

            review.setTitle(title);
            review.setLink(link);
            review.setAuthor(author);
            SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");

            try {
                review.setDate(format.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            reviewList.add(review);
        }

        return reviewList;
    }



}