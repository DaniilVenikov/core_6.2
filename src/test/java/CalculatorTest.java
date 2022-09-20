import org.hamcrest.Matcher;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CalculatorTest {

    Calculator sut;

    @BeforeEach
    public void init(){
        System.out.println("test started");
        sut = Calculator.calculatorSupplier.get();
    }

    @BeforeAll
    public static void started(){
        System.out.println("tests started");
    }

    @AfterEach
    public void finished(){
        System.out.println("test completed");
        sut = null;
    }

    @AfterAll
    public static void finishedAll(){
        System.out.println("tests completed");
    }


    @ParameterizedTest
    @ValueSource(doubles = {1, -3, 0, -2.5})
    public void absTest(double x){
        assertThat(sut.abs.apply(x), is(greaterThanOrEqualTo(0.0)));
    }


    @Test
    public void zeroDivision(){
        double x = 1, y = 0;

        assertThat(sut.division.apply(x,y), is(equalTo(0.0)));
    }


    //не понял как можно через matcher-ы это сделать
    @ParameterizedTest
    @NullSource
    public void nullDivision(Double argument){
        double y = 1;

        var expected = NullPointerException.class;

        assertThrowsExactly(expected,
                () -> sut.division.apply(argument, y));
    }


    @ParameterizedTest
    @MethodSource("sourceForDivision")
    public void divisionTest(double x, double y, double expected){
        assertThat(sut.division.apply(x, y), is(equalTo(expected)));
    }
    private static Stream<Arguments> sourceForDivision(){
        return Stream.of(Arguments.of(5,2,2.5), Arguments.of(10,5,2), Arguments.of(5.6,2.8,2));
    }


    @ParameterizedTest
    @MethodSource("sourceForMultiply")
    public void multiplyTest(double x, double y, double expected){
        assertThat(sut.multiply.apply(x,y), is(equalTo(expected)));
    }

    private static Stream<Arguments> sourceForMultiply(){
        double y = 3.1;
        return Stream.of(Arguments.of(2.8,y,8.68), Arguments.of(4.6,y,14.26), Arguments.of(5.8,y,17.98));
    }

   @Test
    public void addPositiveNumberTest(){
        Double[] numbers = {-0.11, 1.12, 3.65, 9.99, 0.0, -2.1};

        List<Double> actual = Arrays.asList(sut.addPositiveNumber(numbers));

        assertThat(actual, is(everyItem(greaterThanOrEqualTo(0.0))));
   }

   @Test
    public void checkCalculatorWorksCorrectly(){
        Double x = -1.5;

        assertThat(x, OurNumberMatcher.isOurNumber());
   }

}
