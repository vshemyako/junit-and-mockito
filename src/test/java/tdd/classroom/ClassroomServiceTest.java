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

    private static final String A1_CLASSROOM = "A1";
    private static final String A2_CLASSROOM = "A2";
    private static final String A3_CLASSROOM = "A3";
    private static final String A4_CLASSROOM = "A4";

    private List<Classroom> classrooms;
    private ClassroomService classroomService;

    @Before
    public void setUp() {
        Classroom classroomA1 = Mockito.mock(Classroom.class, A1_CLASSROOM);
        Mockito.when(classroomA1.getName()).thenReturn(A1_CLASSROOM);
        Mockito.when(classroomA1.getEquipment()).thenReturn(Equipment.MICROPHONE);

        Classroom classroomA2 = Mockito.mock(Classroom.class, A2_CLASSROOM);
        Mockito.when(classroomA2.getName()).thenReturn(A2_CLASSROOM);
        Mockito.when(classroomA2.getEquipment()).thenReturn(Equipment.PROJECTOR);

        Classroom classroomA3 = Mockito.mock(Classroom.class, A3_CLASSROOM);
        Mockito.when(classroomA3.getName()).thenReturn(A3_CLASSROOM);
        Mockito.when(classroomA3.getEquipment()).thenReturn(Equipment.WHITEBOARD);

        this.classrooms = Arrays.asList(classroomA1, classroomA2, classroomA3);
        this.classroomService = new ClassroomService(this.classrooms);
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
        Assert.assertEquals(classrooms, classroomService.getAllClassrooms());
    }

    /**
     * Verifies that {@link ClassroomService} does have classrooms
     */
    @Test
    public void serviceShouldReturnAllExistingClassroomsOnRequest() {
        Assert.assertThat(classroomService.getAllClassrooms(), getNotEmptyCollectionMatcher());
    }

    /**
     * Verifies that it's possible to book a {@link Classroom} by name
     */
    @Test
    public void shouldAllowToBookClassroomByName() {
        boolean booked = classroomService.bookByName(A1_CLASSROOM);
        Assert.assertTrue("Failed to book chosen classroom by name", booked);
    }

    /**
     * Verifies that the same classroom cannot be booked twice
     */
    @Test
    public void shouldFailedToBookTheSameClassroomByNameTwice() {
        classroomService.bookByName(A1_CLASSROOM);
        boolean booked = classroomService.bookByName(A1_CLASSROOM);

        Assert.assertFalse("It should be impossible to book the same room twice", booked);
    }

    /**
     * Verifies that only existing classrooms can be booked
     */
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionInCaseNoExistingClassroomWasBooked() {
        classroomService.bookByName(A4_CLASSROOM);
    }

    /**
     * Tests functionality to book a classroom by chosen {@link Equipment}
     */
    @Test
    public void shouldAllowToBookClassroomWithSpecifiedEquipment() {
        classroomService.bookByEquipment(Equipment.PROJECTOR);
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
