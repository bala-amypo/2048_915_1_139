package com.example.demo.service;

import com.example.demo.entity.CourseContentTopic;
import java.util.List;

public interface CourseContentTopicService {

    CourseContentTopic create(CourseContentTopic topic);

    CourseContentTopic update(Long id, CourseContentTopic topic);

    CourseContentTopic getById(Long id);

    List<CourseContentTopic> getByCourse(Long courseId);
}
