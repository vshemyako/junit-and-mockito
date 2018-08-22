package tdd.classroom;

import java.util.Collections;
import java.util.List;

public class ClassroomService {

    public List<Classroom> getAllClassrooms() {
        return Collections.singletonList(new Classroom());
    }
}
