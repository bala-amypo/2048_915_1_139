package com.example.demo.service.impl;

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
    public CourseContentTopic createTopic(CourseContentTopic topic) {
        if (topic.getWeightPercentage() < 0 || topic.getWeightPercentage() > 100) {
            throw new IllegalArgumentException("Weight percentage must be between 0 and 100");
        }
        return repository.save(topic);
    }

    @Override
    public CourseContentTopic updateTopic(Long id, CourseContentTopic topic) {
        CourseContentTopic existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found"));

        existing.setTopicName(topic.getTopicName());
        existing.setWeightPercentage(topic.getWeightPercentage());

        return repository.save(existing);
    }

    @Override
    public CourseContentTopic getTopicById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not found"));
    }

    @Override
    public List<CourseContentTopic> getTopicsByCourse(Long courseId) {
        return repository.findByCourseId(courseId);
    }
}
