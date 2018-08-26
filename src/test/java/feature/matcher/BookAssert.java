package feature.matcher;

import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

/**
 * Custom matchers enhance readability of unit tests
 */
public class BookAssert extends GenericAssert<BookAssert, Book> {

    private static final String TITLE_ERROR_MESSAGE = "Expected title '%s' but was '%s'";

    private BookAssert(Book actual) {
        super(BookAssert.class, actual);
    }

    /**
     * Following common 'assertThat' approach
     */
    public static BookAssert assertThat(Book actual) {
        return new BookAssert(actual);
    }

    /**
     * Main assertion logic is contained here:
     * Verify that {@link Book} has expected {@code title}
     */
    public BookAssert hasTitle(String title) {
        super.isNotNull(); // verify that wrapped value is not null
        Assertions.assertThat(actual.getTitle())
                .isEqualTo(title)
                .overridingErrorMessage(TITLE_ERROR_MESSAGE);
        return this;
    }
}
