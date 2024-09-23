package org.example;

public class Main {
    public static void main(String[] args) {

        /*StudentCourse studentCourse = new StudentCourse();
        Course course = new Course();

        course.addStudentIntoCourse(2,4);
        System.out.println("Student Courses");
        course.listOfCoursesStudentEnrolledIn(2);
        System.out.println("Students Enrolled in courses:");
        course.listOfStudentsEnrolledInCourses(2);
        System.out.println("UnEnroll Student");
        course.UnEnrollStudentFromCourse(2,3);
        System.out.println("Student Courses");
        course.listOfCoursesStudentEnrolledIn(2);
        System.out.println("Check For Particular Student Enrolled In Specific Course:");
        System.out.println(course.checkForStudentEnrolledInCourse(4,3));
        System.out.println("Student Not Enrolled Yet:");
        course.checkForStudentsNotEnrolledInAnyCourse();
        System.out.println("Deleting Course 4");
        course.deleteCourse(4);
        System.out.println("Courses List After Delete:");
        System.out.println(StudentCourse.courses);
        System.out.println("Full Enrollment After Delete");
        System.out.println(StudentCourse.enrolledStudentsCourseList);
        System.out.println("Check Student For Both Course");
        course.checkForSameStudentsInDifferentCourse(2,3);*/

     /*   int[] arr = {8,3,7, 1, 99, 98};
        int currentNumber = 0;
        int max = 0;
        for(int i = 0; i < arr.length; i++){
            if(i == 0){
                max = arr[i+1];
                currentNumber = arr[i];
            }
            else if(i == arr.length-1){
                if(max < arr[(arr.length - 1) - 1]){
                    max = arr[(arr.length-1) - 1];
                    currentNumber = arr[i];
                }

            }
            else{
                if(i > 0 && i < arr.length) {
                    if(max < arr[i-1] + arr[i+1]){
                        max = arr[i - 1] + arr[i + 1];
                        currentNumber = arr[i];
                    }

                }
            }
        }
        System.out.println(max);
        System.out.println(currentNumber);*/

        CustomArrayList customArrayList = new CustomArrayList();
        for(int i = 1; i <=15; i++){
            customArrayList.addNumber(i);
        }

        customArrayList.print();
    }
}