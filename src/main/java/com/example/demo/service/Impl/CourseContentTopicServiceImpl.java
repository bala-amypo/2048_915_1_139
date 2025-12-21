package com.example.demo.service;

import com.example.demo.entity.CourseContentTopic;

import java.util.List;

public interface CourseContentTopicService {

    CourseContentTopic saveTopic(CourseContentTopic topic);

    List<CourseContentTopic> getTopicsByCourse(Long courseId);
}
