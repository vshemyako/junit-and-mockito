package tdd.classroom;

import java.util.Arrays;
import java.util.List;

public class ClassroomService {

    private List<Classroom> classrooms;

    public ClassroomService(List<Classroom> classrooms) {
        if (classrooms == null || classrooms.isEmpty()) {
            throw new IllegalArgumentException("Service have to serve classrooms: null / empty parameter is forbidden");
        }
        this.classrooms = classrooms;
    }

    public List<Classroom> getAllClassrooms() {
        return classrooms;
    }
}
