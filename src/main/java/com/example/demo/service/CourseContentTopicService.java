package com.example.demo.service;

import com.example.demo.entity.CourseContentTopic;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseContentTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseContentTopicService {

    @Autowired
    private CourseContentTopicRepository topicRepository;

    public List<CourseContentTopic> getAllTopics() {
        return topicRepository.findAll();
    }

    public CourseContentTopic getTopicById(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Missing Entity: Topic with ID " + id + " not found"));
    }

    public List<CourseContentTopic> getTopicsByCourse(Long courseId) {
        List<CourseContentTopic> topics = topicRepository.findByCourseId(courseId);
        if (topics.isEmpty()) {
            throw new ResourceNotFoundException("Missing Entity: No active topics found for Course ID " + courseId);
        }
        return topics;
    }

    public CourseContentTopic addTopic(CourseContentTopic topic) {
        if (topic.getName() == null || topic.getName().isBlank()) {
            throw new IllegalArgumentException("Invalid Topic Name: Topic name cannot be empty");
        }
        return topicRepository.save(topic);
    }

    public CourseContentTopic updateTopic(Long id, CourseContentTopic updated) {
        CourseContentTopic existing = topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Missing Entity: Topic with ID " + id + " not found"));
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        return topicRepository.save(existing);
    }
}
