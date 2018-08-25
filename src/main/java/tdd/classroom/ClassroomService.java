package tdd.classroom;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ClassroomService {

    private List<Classroom> classrooms;
    private Map<Classroom, List<DayOfWeek>> classroomToBookedDays;

    public ClassroomService(List<Classroom> classrooms) {
        if (classrooms == null || classrooms.isEmpty()) {
            throw new IllegalArgumentException("Service have to serve classrooms: null / empty parameter is forbidden");
        }
        this.classrooms = classrooms;
        this.classroomToBookedDays = classrooms
                .stream()
                .collect(Collectors.toMap(Function.identity(), room -> new ArrayList<>()));
    }

    /**
     * @return list of classrooms booking of which current
     * service is managing
     */
    public List<Classroom> getAllClassrooms() {
        return classrooms;
    }

    /**
     * Books a classroom chosen by {@code classroomName}
     *
     * @param classroomName - name of a classroom to choose
     * @param dayOfWeek     - desired day of the week to book
     * @return true in case classroom was successfully booked
     */
    public boolean bookByName(String classroomName, DayOfWeek dayOfWeek) {
        Classroom classroomToBook = getByName(classroomName);
        return bookClassroom(classroomToBook, dayOfWeek);
    }

    /**
     * Books a classroom chosen by {@code classroomName} and specified
     * {@code equipment}
     */
    public boolean bookByEquipment(Equipment equipment, DayOfWeek dayOfWeek) {
        Classroom classroomToBook = getByEquipment(equipment);
        return bookClassroom(classroomToBook, dayOfWeek);
    }

    /**
     * Adds chosen classroom to a list of booked classrooms if this classroom
     * wasn't previously booked
     */
    private boolean bookClassroom(Classroom classroomToBook, DayOfWeek dayToBook) {
        List<DayOfWeek> bookedDays = classroomToBookedDays.get(classroomToBook);
        boolean alreadyBooked = bookedDays.contains(dayToBook);
        return !alreadyBooked && bookedDays.add(dayToBook);
    }

    /**
     * Returns found {@link Classroom} with the name specified or
     * throws {@link IllegalArgumentException} in case such classroom do not exists
     */
    private Classroom getByName(String name) {
        return getByValueExtractor(Classroom::getName, name);
    }

    /**
     * Returns found {@link Classroom} with the equipment specified or
     * throws {@link IllegalArgumentException} in case such classroom do not exists
     */
    private Classroom getByEquipment(Equipment equipment) {
        return getByValueExtractor(Classroom::getEquipment, equipment);
    }

    /**
     * Returns found {@link Classroom} with matching {@code targetValue} extracted by {@code valueExtractor}
     */
    private <T> Classroom getByValueExtractor(Function<Classroom, T> valueExtractor, T targetValue) {
        return classrooms.stream()
                .filter(room -> Objects.equals(valueExtractor.apply(room), targetValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No such classroom exists"));
    }
}
