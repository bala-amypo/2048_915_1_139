package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.University;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository repo;
    private UniversityRepository univRepo;

    public CourseServiceImpl(CourseRepository repo, UniversityRepository univRepo) {
        this.repo = repo;
        this.univRepo = univRepo;
    }

    @Override
    public Course createCourse(Course course) {
        University univ = univRepo.findById(course.getUniversity().getId())
                .orElseThrow(() -> new RuntimeException("University not found")); // "not found"

        if (course.getCreditHours() <= 0) {
            throw new RuntimeException("Credit hours must be > 0"); // "> 0"
        }

        if (repo.findByUniversityIdAndCourseCode(univ.getId(), course.getCourseCode()) != null) {
            throw new RuntimeException("Course code already exists"); // "exists"
        }

        if (course.getActive() == null) {
            course.setActive(true);
        }

        course.setUniversity(univ);
        return repo.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found")); // "not found"

        if (course.getCreditHours() <= 0) {
            throw new RuntimeException("Credit hours must be > 0"); // "> 0"
        }

        existing.setCourseCode(course.getCourseCode());
        existing.setCourseName(course.getCourseName());
        existing.setCreditHours(course.getCreditHours());
        existing.setDescription(course.getDescription());
        existing.setDepartment(course.getDepartment());
        existing.setActive(course.getActive());

        return repo.save(existing);
    }

    @Override
    public Course getCourseById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found")); // "not found"
    }

    @Override
    public List<Course> getCoursesByUniversity(Long universityId) {
        return repo.findByUniversityIdAndActiveTrue(universityId);
    }

    @Override
    public void deactivateCourse(Long id) {
        Course existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found")); // "not found"
        existing.setActive(false);
        repo.save(existing);
    }
}
