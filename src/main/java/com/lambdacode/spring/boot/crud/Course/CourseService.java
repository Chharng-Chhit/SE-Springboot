package com.lambdacode.spring.boot.crud.Course;

import org.springframework.stereotype.Service;
import com.lambdacode.spring.boot.crud.User.User;
import com.lambdacode.spring.boot.crud.User.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.*;

public interface CourseService {
    Course createCourse(Course course);
    void joinCourse(Long userId, Long courseId);
    Course getCourseById(Long id);
    List<Course> getAllCourses();
    Course updateCourse(Long id, Course course);
    void deleteCourse(Long id);
}


@Service
class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

     @Override
    public void joinCourse(Long userId, Long courseId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));

        Set<User> enrolledUsers = course.getEnrolledUsers();
        
        // Check if the user is already enrolled in the course
        if (enrolledUsers != null && !enrolledUsers.contains(user)) {
            enrolledUsers.add(user);
            courseRepository.save(course); // Make sure CascadeType is set appropriately
        }
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));

        // Update existingCourse properties with the provided course object
        existingCourse.setCourseTitle(course.getCourseTitle());
        existingCourse.setDescription(course.getDescription());
        // Update other properties as needed

        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));
        courseRepository.delete(course);
    }
}