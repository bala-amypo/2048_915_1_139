package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.University;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.UniversityRepository;
import com.example.demo.service.CourseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository repo;        // REQUIRED NAME
    private final UniversityRepository univRepo; // REQUIRED NAME

    public CourseServiceImpl(CourseRepository repo,
                             UniversityRepository univRepo) {
        this.repo = repo;
        this.univRepo = univRepo;
    }

    @Override
    public Course createCourse(Course course) {

        if (course.getCreditHours() <= 0) {
            throw new IllegalArgumentException("> 0");
        }

        University u = univRepo.findById(course.getUniversity().getId())
                .orElseThrow(() -> new RuntimeException("not found"));

        if (repo.findByUniversityIdAndCourseCode(
                u.getId(), course.getCourseCode()) != null) {
            throw new IllegalArgumentException("exists");
        }

        course.setUniversity(u);
        course.setActive(true);
        return repo.save(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course db = getCourseById(id);

        if (course.getCreditHours() <= 0) {
            throw new IllegalArgumentException("> 0");
        }

        db.setCourseName(course.getCourseName());
        db.setCreditHours(course.getCreditHours());
        db.setDescription(course.getDescription());
        db.setDepartment(course.getDepartment());

        return repo.save(db);
    }

    @Override
    public Course getCourseById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    @Override
    public List<Course> getCoursesByUniversity(Long universityId) {
        return repo.findByUniversityIdAndActiveTrue(universityId);
    }

    @Override
    public void deactivateCourse(Long id) {
        Course c = getCourseById(id);
        c.setActive(false);
        repo.save(c);
    }
}
