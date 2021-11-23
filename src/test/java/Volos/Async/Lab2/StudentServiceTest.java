package Volos.Async.Lab2;

import Volos.Async.Lab2.entities.Exam;
import Volos.Async.Lab2.entities.Exam.Type;
import Volos.Async.Lab2.entities.Student;
import Volos.Async.Lab2.repositories.StudentRepository;
import Volos.Async.Lab2.services.StudentService;
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
    //2
    @Test
    void should_find_first_student_without_math() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);
        Optional<Student> studentOpt = studentService.findFirstWithoutMath();
        assertEquals(Optional.of(firstStudent), studentOpt);
    }

    @Test
    void should_not_find_first_student_without_math() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        when(studentRepository.findAll()).thenReturn(Arrays.asList(secondStudent, thirdStudent));
        StudentService studentService = new StudentService(studentRepository);
        Optional<Student> studentOpt = studentService.findFirstWithoutMath();
        assertEquals(Optional.empty(), studentOpt);
    }
    //2

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
    //10
    @Test
    void should_find_student_with_math_result_more_than_average_and_has_english() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);
        List<Student> students = studentService.findMathExamResultMoreThenAverageAndHaveEnglish();
        assertThat(students, containsInAnyOrder(thirdStudent));
    }

    @Test
    void should_not_find_student_with_math_result_more_than_average_and_has_english() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        when(studentRepository.findAll()).thenReturn(Arrays.asList(firstStudent, fourthStudent));
        StudentService studentService = new StudentService(studentRepository);
        List<Student> students = studentService.findMathExamResultMoreThenAverageAndHaveEnglish();
        assertThat(students, hasSize(0));
    }
    //10

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

    /**
     *
     */
    //15
    @Test
    void should_find_sum_with_name_and_rating() {
        StudentRepository studentRepository = createStudentRepositoryWithAllStudents();
        StudentService studentService = new StudentService(studentRepository);
        List<String> studentOpt = studentService.findSumWithNameAndRating();
        List<String> expected = new LinkedList<>();
        expected.add(firstStudent.sumForTest() + " " + firstStudent.getRating() + " " + firstStudent.getName());
        expected.add(secondStudent.sumForTest() + " " + secondStudent.getRating() + " " + secondStudent.getName());
        expected.add(thirdStudent.sumForTest() + " " + thirdStudent.getRating() + " " + thirdStudent.getName());
        expected.add(fourthStudent.sumForTest() + " " + fourthStudent.getRating() + " " + fourthStudent.getName());
        assertEquals(expected, studentOpt);
    }
    //15
}