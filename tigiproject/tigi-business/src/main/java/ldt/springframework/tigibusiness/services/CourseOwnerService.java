package ldt.springframework.tigibusiness.services;

import ldt.springframework.tigibusiness.commands.RateForm;
import ldt.springframework.tigibusiness.domain.Course;
import ldt.springframework.tigibusiness.domain.CourseOwner;
import ldt.springframework.tigibusiness.domain.User;
import ldt.springframework.tigibusiness.enums.OwerType;

import java.util.List;

/*
 * author: Luu Duc Trung
 * https://github.com/luuductrung1234
 * ---
 * 8/7/18
 */

public interface CourseOwnerService extends CRUDService<CourseOwner>{
    List<CourseOwner> findReviewByCourse(Course course);

    Float getCourseRateAvg(Course course);

    User findInstructor(Course course);

    RateForm getCourseRateFull(Course course);
}
