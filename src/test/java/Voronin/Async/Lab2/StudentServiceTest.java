package Voronin.Async.Lab2;

import Voronin.Async.Lab2.services.StudentService;
import Voronin.Async.Lab2.entities.Exam;
import Voronin.Async.Lab2.entities.Exam.Type;
import Voronin.Async.Lab2.entities.Student;
import Voronin.Async.Lab2.repositories.StudentRepository;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StudentServiceTest {

    private Student firstStudent = Student.builder()
            .name("1")
            .rating(10)
            .exams(Arrays.asList(Exam.of(Type.ENGLISH, 181)))
            .build();
    private Student secondStudent = Student.builder()
            .name("2")
            .rating(11)
            .exams(Arrays.asList(Exam.of(Type.ENGLISH, 182),
                    Exam.of(Type.MATH, 190)))
            .build();
    private Student thirdStudent =
            Student.builder()
                    .name("3")
                    .rating(11)
                    .exams(Arrays.asList(Exam.of(Type.ENGLISH, 183),
                            Exam.of(Type.MATH, 191)))
                    .build();
    private Student fourthStudent = Student.builder()
            .name("4")
            .rating(11)
            .exams(Arrays.asList())
            .build();

    private StudentRepository createStudentRepositoryWithAllStudents() {
        StudentRepository studentRepository = mock(StudentRepository.class);
        List<Student> allStudents = Arrays.asList(firstStudent, secondStudent,
                thirdStudent, fourthStudent);
        when(studentRepository.findAll()).thenReturn(allStudents);
        return studentRepository;
    }

    @Test
    void should_find_student_with_max_english() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);
        Optional<Student> studentOpt = studentService.findWithMaxExam(Type.ENGLISH);
        assertEquals(Optional.of(thirdStudent), studentOpt);
    }

    @Test
    void should_not_find_student_with_max_math() {
        StudentRepository studentRepository = mock(StudentRepository.class);
        when(studentRepository.findAll()).thenReturn(Arrays.asList(firstStudent, fourthStudent));
        StudentService studentService = new StudentService(studentRepository);
        Optional<Student> studentOpt = studentService.findWithMaxExam(Type.MATH);
        assertEquals(Optional.empty(), studentOpt);
    }

    @Test
    void should_find_students_who_have_enough_math_grade() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);
        final double mathPassRate = 190.0;
        List<Student> studentsWithMath = studentService.findWithEnoughExam(Type.MATH, mathPassRate);
        assertThat(studentsWithMath, containsInAnyOrder(secondStudent, thirdStudent));
    }

    @Test
    void should_not_find_students_who_have_enough_english_grade() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);
        final double englishPassRate = 190.0;
        List<Student> studentsWithEnglish = studentService.findWithEnoughExam(Type.ENGLISH, englishPassRate);
        assertThat(studentsWithEnglish, hasSize(0));
    }

    /**
     *
     */
    //3
    @Test
    void should_find_students_without_exam_sum_less_than_300() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);
        List<Student> students = studentService.findWithExamSumLessThan300();
        assertThat(students, containsInAnyOrder(firstStudent, fourthStudent));
    }

    @Test
    void should_not_find_students_without_exam_sum_less_than_300() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        when(studentRepository.findAll()).thenReturn(Arrays.asList(secondStudent, thirdStudent));
        StudentService studentService = new StudentService(studentRepository);
        List<Student> students = studentService.findWithExamSumLessThan300();
        assertThat(students, hasSize(0));
    }
    //3

    /**
     *
     */
    //5
    @Test
    void should_find_student_with_two_exam() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);
        List<Student> students = studentService.findWithTwoExam();
        assertThat(students, containsInAnyOrder(secondStudent, thirdStudent));
    }

    @Test
    void should_not_find_student_with_two_exam() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        when(studentRepository.findAll()).thenReturn(Arrays.asList(firstStudent, fourthStudent));
        StudentService studentService = new StudentService(studentRepository);
        List<Student> students = studentService.findWithTwoExam();
        assertThat(students, hasSize(0));
    }
    //5

    /**
     *
     */
    //7
    @Test
    void should_find_student_with_rating_more_than_11_and_exam_english() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);
        List<Student> students = studentService.findWithRatingMoreThan11AndEnglishExam();
        assertThat(students, containsInAnyOrder(secondStudent, thirdStudent));
    }

    @Test
    void should_not_find_student_with_rating_more_than_11_and_exam_english() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        when(studentRepository.findAll()).thenReturn(Arrays.asList(firstStudent, fourthStudent));
        StudentService studentService = new StudentService(studentRepository);
        List<Student> students = studentService.findWithRatingMoreThan11AndEnglishExam();
        assertThat(students, hasSize(0));
    }
    //7

    /**
     *
     */
    //8
    @Test
    void should_find_student_exams_more_than_180_each() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);
        List<Student> students = studentService.findWithExamsMoreThan180Each();
        assertThat(students, containsInAnyOrder(secondStudent, thirdStudent));
    }

    @Test
    void should_not_find_student_exams_more_than_180_each() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        when(studentRepository.findAll()).thenReturn(Arrays.asList(firstStudent, fourthStudent));
        StudentService studentService = new StudentService(studentRepository);
        List<Student> students = studentService.findWithExamsMoreThan180Each();
        assertThat(students, hasSize(0));
    }
    //8

    /**
     *
     */
    //13
    @Test
    void should_find_student_with_max_english_exam() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);
        Optional<Student> studentOpt = studentService.findWithMaxEnglishExam();
        assertEquals(Optional.of(thirdStudent), studentOpt);
    }

    @Test
    void should_not_find_student_with_max_english_exam() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);
        when(studentRepository.findAll()).thenReturn(Collections.singletonList(fourthStudent));
        Optional<Student> studentOpt = studentService.findWithMaxEnglishExam();
        assertEquals(Optional.empty(), studentOpt);
    }
    //13
}