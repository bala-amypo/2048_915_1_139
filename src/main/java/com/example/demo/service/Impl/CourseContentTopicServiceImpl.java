package com.example.demo.service.Impl;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.service.CourseContentTopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseContentTopicServiceImpl implements CourseContentTopicService {

    private final CourseContentTopicRepository repository;

    public CourseContentTopicServiceImpl(CourseContentTopicRepository repository) {
        this.repository = repository;
    }

    @Override
    public CourseContentTopic createTopic(CourseContentTopic topic) {
        return repository.save(topic);
    }

    @Override
    public List<CourseContentTopic> getTopicsByCourseId(Long courseId) {
        return repository.findByCourseId(courseId);
    }
}
