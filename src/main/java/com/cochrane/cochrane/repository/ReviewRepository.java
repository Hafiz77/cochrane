package com.cochrane.cochrane.repository;

import java.util.Date;

public interface ReviewRepository {
    String getTitle();
    String getAuthor();
    Date getDate();
    String getLink();
}
