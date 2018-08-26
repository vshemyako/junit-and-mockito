package feature.matcher;

import org.fest.assertions.Assertions;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Review of most popular {@link Matcher} libraries
 */
public class ThirdPartyMatchers {

    private static final String BOOK_TITLE = "Lord of the Rings";
    private Book book;

    /**
     * Test fixture
     */
    @Before
    public void setUp() {
        book = Mockito.mock(Book.class);
        Mockito.when(book.getTitle())
                .thenReturn(BOOK_TITLE);
    }

    /**
     * Compares approaches to arranges testing code with standard JUnit approach
     * and with enhanced FEST fluent approach
     */
    @Test
    public void titleShouldNotBeNull() {
        // JUnit approach
        Assert.assertNotNull(book.getTitle());

        // FEST fluent approach
        Assertions.assertThat(book.getTitle()).isNotNull();
    }

    /**
     * In a nutshell FEST approach is more readable and consistent
     */
    @Test
    public void titleShouldNotBeEmpty() {
        // JUnit approach
        Assert.assertFalse(book.getTitle().isEmpty()); // possible NPE?

        // FEST fluent approach
        Assertions.assertThat(book.getTitle()).isNotEmpty();
    }

    /**
     * Tests equality of book title using custom self-made matcher
     */
    @Test
    public void titleShouldMatch() {
        BookAssert.assertThat(book)
                .hasTitle(BOOK_TITLE);
    }
}
