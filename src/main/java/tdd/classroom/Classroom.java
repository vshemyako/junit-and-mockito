package tdd.classroom;

public interface Classroom {

    /**
     * @return unique name associated with this classroom
     */
    String getName();

    /**
     * @return equipment which current classroom possess
     */
    Equipment getEquipment();
}
