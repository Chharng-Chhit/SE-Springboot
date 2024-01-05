package com.lambdacode.spring.boot.crud.Course;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok().body(course);
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody @Validated Course course) {
        Course createdCourse = courseService.createCourse(course);
        return ResponseEntity.ok().body(createdCourse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody @Validated Course course) {
        Course updatedCourse = courseService.updateCourse(id, course);
        return ResponseEntity.ok().body(updatedCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to join a course
    @PostMapping("/{courseId}/join/{userId}")
    public ResponseEntity<Void> joinCourse(@PathVariable Long courseId, @PathVariable Long userId) {
        courseService.joinCourse(userId, courseId);
        return ResponseEntity.noContent().build();
    }
}

