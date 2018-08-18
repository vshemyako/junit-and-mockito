package mock.mail;

import java.util.concurrent.ThreadLocalRandom;

public class ErrorBasedProgramming {
    public static void main(String[] args) {
        if (lucky()) {
            sayHello();
        }
    }

    private static void sayHello() {
        System.out.println("Hello");
    }

    private static boolean lucky() {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
