package com.cochrane.cochrane.scraper;

import com.cochrane.cochrane.model.Topic;
import com.cochrane.cochrane.repository.TopicRepository;
import com.cochrane.cochrane.services.ScrapedDataFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.File;
import java.util.ArrayList;

public class SeleniumSiteScraper {
    ArrayList<String> scrappedData = null;
    public SeleniumSiteScraper() {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "\\driver\\win\\chromedriver.exe");
        } else if (OS.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "/driver/mac/chromedriver");
        } else if ( (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"))) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "/driver/linux/chromedriver");
        }else {
            System.out.println("Your OS is not support!!");
        }
    }

    public ArrayList<String> getScrappedData(int topicsNumber)
    {
        WebDriver driver = new ChromeDriver();

        TopicScraper topicScraper = new SeleniumTopicScraper(driver);

        topicScraper.scrap("https://www.cochranelibrary.com/cdsr/reviews/topics");

        ArrayList<TopicRepository> topics = topicScraper.getTopics();
        ArrayList<TopicRepository> topicList = new ArrayList<>();

        for(int i = 0; i < topics.size() && i < topicsNumber; i++) {
            ReviewScraper reviewScraper = new SeleniumReviewScrapper(driver);
            Topic topic = (Topic) topics.get(i);
            reviewScraper.scrap(topic.getLink());
            topic.setReviews(reviewScraper.getReviews());
            topicList.add(topic);
        }
        driver.quit();
        scrappedData = new ArrayList<>();
        scrappedData.addAll(ScrapedDataFormatter.formatTopics(topicList));

        return scrappedData;
    }

    public void writeToFile(int topicsNumber)
    {
        String fileName = System.getProperty("user.dir") +  File.separator + "cochrane_reviews.txt";

        if (scrappedData == null) {
            this.getScrappedData(topicsNumber);
        }

        try  {
            FileUtils.writeLines(new File(fileName), scrappedData);

        } catch (Exception ex) {
            System.out.print("Error: "+ex);
        }
    }
}
