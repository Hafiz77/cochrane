package com.cochrane.cochrane.scraper;

import com.cochrane.cochrane.repository.TopicRepository;

import java.util.ArrayList;

public interface TopicScraper extends Scraper {

    ArrayList<TopicRepository> getTopics();
}
