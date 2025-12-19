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

    private final CourseContentTopicRepository repo; // REQUIRED
    private final CourseRepository courseRepo;       // REQUIRED

    public CourseContentTopicServiceImpl(
            CourseContentTopicRepository repo,
            CourseRepository courseRepo) {
        this.repo = repo;
        this.courseRepo = courseRepo;
    }

    @Override
    public CourseContentTopic createTopic(CourseContentTopic topic) {

        Course c = courseRepo.findById(topic.getCourse().getId())
                .orElseThrow(() -> new RuntimeException("not found"));

        topic.setCourse(c);
        return repo.save(topic);
    }

    @Override
    public CourseContentTopic updateTopic(Long id, CourseContentTopic topic) {
        CourseContentTopic db = getTopicById(id);
        db.setTopicName(topic.getTopicName());
        db.setWeightPercentage(topic.getWeightPercentage());
        return repo.save(db);
    }

    @Override
    public CourseContentTopic getTopicById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<CourseContentTopic> getTopicsForCourse(Long courseId) {
        return repo.findByCourseId(courseId);
    }
}
