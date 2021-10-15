//import UnitTestAssignment.src.main.java.TestDemo;
import org.junit.jupiter.params.ParameterizedTest;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.api;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
public class TestDemoTest {
    
    private static TestDemo testDemo;

    @BeforeEach
    void setUp() throws Exception {
        testDemo = new TestDemo();
    }

    @ParameterizedTest
    @MethodSource("TestDemoTest#argumentsForAddPositive")
    void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, Boolean expectException) {
        if (!expectException) {
            assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
        } else {
            assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
        }
    }

    static Stream<Arguments> argumentsForAddPositive() {
        // @formatter:off
        return Stream.of(
            arguments("1", "5", "6", false),
            arguments("2", "2", "4", false),
            arguments("0", "3", "3", true),
            arguments("4", "0", "4", true),
            arguments("-1", "0", "-1", true),
            arguments("0", "-3", "-3", true),
            arguments("-3", "5", "2", true),
            arguments("2", "-4", "-2", true)
        );
        // @formatter:on
    }

    @Test
    void assertThatNumberSquaredIsCorrect (int value) {
        TestDemo mockDemo = spy(testDemo);
        //Given: a random int is obtained
        doReturn(5).when(mockDemo).getRandomInt();
        //When: the int is multiplied by itself
        int fiveSquared = mockDemo.randomNumberSquared();
        //Then: the squared int is correct
        assertThat(fiveSquared).isEqualTo(25);
    }
}
