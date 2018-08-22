package tdd.classroom;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ClassroomServiceTest {

    private List<Classroom> classrooms;

    @Before
    public void setUp() {
        Classroom classroomA1 = Mockito.mock(Classroom.class, "classroomA1");
        Classroom classroomA2 = Mockito.mock(Classroom.class, "classroomA2");
        Classroom classroomA3 = Mockito.mock(Classroom.class, "classroomA3");
        this.classrooms = Arrays.asList(classroomA1, classroomA2, classroomA3);
    }

    /**
     * Verifies that {@link ClassroomService} throws {@link IllegalArgumentException}
     * in case {@code null} value is passed as parameter
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowExceptionOnNullParam() {
        new ClassroomService(null);
    }

    /**
     * Verifies that constructor of {@link ClassroomService} class
     * will throw {@link IllegalArgumentException} in case empty list is
     * passed as a parameter
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowExceptionOnEmptyList() {
        new ClassroomService(Collections.emptyList());
    }

    /**
     * Verifies that collection of {@link Classroom}s passed to constructor
     * is used for all other service functionality
     */
    @Test
    public void constructorShouldSetPassedClassrooms() {
        ClassroomService classroomService = new ClassroomService(classrooms);
        Assert.assertEquals(classrooms, classroomService.getAllClassrooms());
    }

    /**
     * Verifies that {@link ClassroomService} does have classrooms
     */
    @Test
    public void serviceShouldReturnAllExistingClassroomsOnRequest() {
        ClassroomService classroomService = new ClassroomService(classrooms);
        Assert.assertThat(classroomService.getAllClassrooms(), getNotEmptyCollectionMatcher());
    }

    /**
     * Note: helper method
     * <p>
     * Composes {@link Matcher} to verify that collection is not {@code null}
     * and does contain elements
     */
    private static <E> Matcher<Collection<E>> getNotEmptyCollectionMatcher() {
        return Matchers.allOf(
                Matchers.notNullValue(),
                Matchers.not(Matchers.empty())
        );
    }
}
