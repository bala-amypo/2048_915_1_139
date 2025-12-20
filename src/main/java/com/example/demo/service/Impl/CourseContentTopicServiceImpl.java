package com.example.demo.service.Impl;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.exception.ResourceNotFoundException;
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
    public CourseContentTopic create(CourseContentTopic topic) {
        return repository.save(topic);
    }

    @Override
    public CourseContentTopic update(Long id, CourseContentTopic topic) {
        CourseContentTopic existing = getById(id);
        existing.setTitle(topic.getTitle());
        return repository.save(existing);
    }

    @Override
    public CourseContentTopic getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    }

    @Override
    public List<CourseContentTopic> getByCourse(Long courseId) {
        return repository.findByCourseId(courseId);
    }
}
