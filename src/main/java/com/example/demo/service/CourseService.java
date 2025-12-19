package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Missing Entity: Course with ID " + id + " not found"));
    }

    public Course addCourse(Course course) {
        if (courseRepository.existsByName(course.getName())) {
            throw new IllegalArgumentException("Duplicate Name: Course already exists");
        }
        return courseRepository.save(course);
    }

    public Course updateCourse(Long id, Course updated) {
        Course existing = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Missing Entity: Course with ID " + id + " not found"));
        existing.setName(updated.getName());
        existing.setActive(updated.getActive());
        return courseRepository.save(existing);
    }

    public Course deactivateCourse(Long id) {
        Course existing = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Missing Entity: Course with ID " + id + " not found"));
        if (!existing.getActive()) {
            throw new RuntimeException("Inactive Course: Course is already inactive");
        }
        existing.setActive(false);
        return courseRepository.save(existing);
    }
}
