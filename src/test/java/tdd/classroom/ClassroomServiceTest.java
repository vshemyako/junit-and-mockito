package tdd.classroom;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

public class ClassroomServiceTest {

    /**
     * Verifies that {@link ClassroomService} does have classrooms
     */
    @Test
    public void serviceShouldReturnAllExistingClassroomsOnRequest() {
        ClassroomService classroomService = new ClassroomService();
        List<Classroom> classrooms = classroomService.getAllClassrooms();
        Assert.assertThat(classrooms, getNotEmptyCollectionMatcher());
    }

    /**
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
