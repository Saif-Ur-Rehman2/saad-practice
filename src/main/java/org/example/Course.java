package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Course {
    public Integer id;
    public String name;
    List<Student> students = new ArrayList<>();

    public void enrollStudent(Student student){
        this.students.add(student);
    }

    public void addStudentIntoCourse(Integer studentId, Integer courseId){
        Course course = null;
        Student student = null;
        for(Course el: StudentCourse.courses){
            if(el.getId() == courseId){
                course = el;
                break;
            }
        }
        for(Student el: StudentCourse.students){
            if(el.getId() == studentId){
                student = el;
            }
        }
        course.enrollStudent(student);
        StudentCourse.enrolledStudentsCourseList.add(course);
    }

    public void listOfCoursesStudentEnrolledIn(Integer studentId){
        for(Course enrolledCourse : StudentCourse.enrolledStudentsCourseList){
            for(Student student : enrolledCourse.getStudents()){
                if(student.getId() == studentId){
                    System.out.println(enrolledCourse);
                }
            }
        }

    }
    public void listOfStudentsEnrolledInCourses(Integer courseId){
        for(Course enrolledCourse : StudentCourse.enrolledStudentsCourseList){
            if(enrolledCourse.getId() == courseId){
                /*for(Student student : enrolledCourse.getStudents()){
                    System.out.println(student);
                }*/
                System.out.println(enrolledCourse.getStudents());
            }

        }
    }

    public void UnEnrollStudentFromCourse(Integer courseId, Integer studentId){
        for(Course enrolledCourse : StudentCourse.enrolledStudentsCourseList){
            if(enrolledCourse.getId() == courseId){
                for(Student student : enrolledCourse.getStudents()){
                    if(student.getId() == studentId){
                        enrolledCourse.getStudents().remove(student);

                    }
                }
            }
        }
        System.out.println(StudentCourse.enrolledStudentsCourseList);
    }

    public Boolean checkForStudentEnrolledInCourse(Integer courseId, Integer studentId){
        Boolean isEnrolled = false;
        for(Course enrolledCourse : StudentCourse.enrolledStudentsCourseList){
            if(enrolledCourse.getId() == courseId){
                for(Student student : enrolledCourse.getStudents()){
                    if(student.getId() == studentId){
                        isEnrolled = true;
                        break;
                    }
                }
            }
        }
        return isEnrolled;
    }

    public void checkForStudentsNotEnrolledInAnyCourse(){
        for(Student student : StudentCourse.students){
            Boolean exist = false;
            for(Course enrolledCourse: StudentCourse.enrolledStudentsCourseList){
                if(enrolledCourse.getStudents().contains(student)){
                    exist = true;
                }
            }
            if(!exist){
                System.out.println(student);
            }
        }
    }

    public void deleteCourse(Integer courseId){
        for(Course enrolledCourse : StudentCourse.enrolledStudentsCourseList){
            if(enrolledCourse.getId() == courseId){
                StudentCourse.enrolledStudentsCourseList.remove(enrolledCourse);
                break;
            }
        }
    }

    public void checkForSameStudentsInDifferentCourse(Integer courseId, Integer course2Id){
        List<Course> filteredCourse = new ArrayList<>();
        for(Course enrolledCourse : StudentCourse.enrolledStudentsCourseList){
            if(enrolledCourse.getId() == courseId || enrolledCourse.getId() == course2Id){
                filteredCourse.add(enrolledCourse);
            }
        }
        Course course = filteredCourse.get(0);
        for(Student student : filteredCourse.get(1).getStudents()){
            if(course.getStudents().contains(student)){
                System.out.println(student);
            }
        }
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }



    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
