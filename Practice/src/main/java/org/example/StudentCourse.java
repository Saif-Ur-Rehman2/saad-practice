package org.example;

import java.util.ArrayList;
import java.util.List;

public class StudentCourse {

    public static List<Student> students = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();
    public static List<Course> enrolledStudentsCourseList = new ArrayList<>();

    StudentCourse (){

        Course course1 = new Course();
        course1.setId(1);
        course1.setName("MATH");

        Course course2 = new Course();
        course2.setId(2);
        course2.setName("ENG");

        Course course3 = new Course();
        course3.setId(3);
        course3.setName("PHYS");

        Course course4 = new Course();
        course4.setId(4);
        course4.setName("CHEM");

        Course course5 = new Course();
        course5.setId(5);
        course5.setName("SCIE");



        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);
        courses.add(course5);


        Student student1 = new Student();
        student1.setId(1);
        student1.setName("hassan");

        Student student2 = new Student();
        student2.setId(2);
        student2.setName("saif");

        Student student3 = new Student();
        student3.setId(3);
        student3.setName("saad");

        Student student4 = new Student();
        student4.setId(4);
        student4.setName("ali");

        Student student5 = new Student();
        student5.setId(5);
        student5.setName("sheriar");

        Student student6 = new Student();
        student6.setId(6);
        student6.setName("Umer");

        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
        students.add(student5);
        students.add(student6);

        course1.enrollStudent(student1);
        course1.enrollStudent(student2);
        course1.enrollStudent(student3);

        course2.enrollStudent(student1);
        course2.enrollStudent(student2);
        course2.enrollStudent(student3);
        course2.enrollStudent(student4);

        course3.enrollStudent(student1);
        course3.enrollStudent(student2);
        course3.enrollStudent(student3);
        course3.enrollStudent(student4);
        course3.enrollStudent(student5);

        enrolledStudentsCourseList.add(course1);
        enrolledStudentsCourseList.add(course2);
        enrolledStudentsCourseList.add(course3);
    }


}
