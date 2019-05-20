package com.cochrane.cochrane.scraper;

import com.cochrane.cochrane.model.Topic;
import com.cochrane.cochrane.repository.TopicRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SeleniumTopicScraper implements TopicScraper {
    private  ArrayList<TopicRepository> topicList;

    public SeleniumTopicScraper(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;

    @Override
    public ArrayList<TopicRepository> getTopics() {
        return this.topicList;
    }

    @Override
    public void scrap(String url) {

        driver.get(url);

        List<WebElement> topicElementList  = driver.findElements(By.cssSelector(Topic.CSS_SELECTOR));

        topicList = new ArrayList<>();

        for (int i = 0; i < topicElementList.size(); i++) {
            String title = topicElementList.get(i).getText();

            String link = topicElementList.get(i).getAttribute("href");

            this.topicList.add(new Topic(title, link));
        }

    }

}
