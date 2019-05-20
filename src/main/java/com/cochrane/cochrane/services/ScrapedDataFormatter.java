package com.cochrane.cochrane.services;

import com.cochrane.cochrane.repository.ReviewRepository;
import com.cochrane.cochrane.repository.TopicRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ScrapedDataFormatter {

    private static final String SEPERATOR = "|";

    public static ArrayList<String> formatTopics(ArrayList<TopicRepository> topicList)
    {

        ArrayList<String> formattedData = new ArrayList<>();

        for (int i = 0; i < topicList.size(); i++) {
            TopicRepository topic = topicList.get(i);
            formattedData.addAll(formatTopic(topic));
        }

        return  formattedData;
    }

    public static ArrayList<String> formatTopic(TopicRepository topic)
    {
        ArrayList<String> formattedData = new ArrayList<>();

        for (int j = 0; j < topic.getReviews().size(); j++) {
            ReviewRepository review = topic.getReviews().get(j);
            formattedData.add(formatReview(topic.getTitle(), review));
        }

        return  formattedData;
    }

    public static String formatReview(String topicTitle, ReviewRepository review)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return   review.getLink() + SEPERATOR + topicTitle + SEPERATOR + review.getTitle() + SEPERATOR
                + review.getAuthor()  + SEPERATOR + format.format(review.getDate());
    }
}
