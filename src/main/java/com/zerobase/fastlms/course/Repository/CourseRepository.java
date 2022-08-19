package com.zerobase.fastlms.course.Repository;

import com.zerobase.fastlms.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
