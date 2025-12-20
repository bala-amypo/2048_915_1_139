package com.example.demo.service;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseContentTopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseContentTopicService {

    private final CourseContentTopicRepository repository;

    public CourseContentTopicService(CourseContentTopicRepository repository) {
        this.repository = repository;
    }

    public CourseContentTopic create(CourseContentTopic topic) {
        return repository.save(topic);
    }

    public CourseContentTopic update(Long id, CourseContentTopic topic) {
        CourseContentTopic existing = getById(id);
        existing.setName(topic.getName());
        existing.setCourse(topic.getCourse());
        return repository.save(existing);
    }

    public CourseContentTopic getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    }

    public List<CourseContentTopic> getByCourse(Long courseId) {
        return repository.findByCourseId(courseId);
    }
}
