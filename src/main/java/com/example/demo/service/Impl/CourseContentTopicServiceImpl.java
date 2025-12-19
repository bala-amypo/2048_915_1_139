package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseContentTopic;
import com.example.demo.repository.CourseContentTopicRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseContentTopicService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseContentTopicServiceImpl implements CourseContentTopicService {

    private final CourseContentTopicRepository repo;
    private final CourseRepository courseRepo;

    public CourseContentTopicServiceImpl(CourseContentTopicRepository repo,
                                         CourseRepository courseRepo) {
        this.repo = repo;
        this.courseRepo = courseRepo;
    }

    @Override
    public CourseContentTopic createTopic(CourseContentTopic topic) {
        Course course = courseRepo.findById(topic.getCourse().getId())
                .orElseThrow(() -> new RuntimeException("Course not found")); // "not found"

        if (topic.getWeight() < 0 || topic.getWeight() > 100) {
            throw new RuntimeException("Topic weight must be 0-100"); // "0-100"
        }

        topic.setCourse(course);
        return repo.save(topic);
    }

    @Override
    public List<CourseContentTopic> getTopicsByCourse(Long courseId) {
        courseRepo.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found")); // "not found"
        return repo.findByCourseId(courseId);
    }
}
