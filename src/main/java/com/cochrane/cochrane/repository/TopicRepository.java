package com.cochrane.cochrane.repository;

import java.util.ArrayList;

public interface TopicRepository {
     String getTitle();
     String getLink();
     ArrayList<ReviewRepository> getReviews();
}
