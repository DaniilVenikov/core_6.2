import java.util.Arrays;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Calculator {
    static Supplier<Calculator> calculatorSupplier = Calculator::new;

    BinaryOperator<Double> plus = (x, y) -> x + y;
    BinaryOperator<Double> minus = (x, y) -> x - y;
    BinaryOperator<Double> multiply = (x, y) -> x * y;
    BinaryOperator<Double> division = (x, y) -> y > 0 ? (x / y) : 0;

    UnaryOperator<Double> power = x -> x * x;
    UnaryOperator<Double> abs = x -> x >= 0 ? x : x * -1;

    Predicate<Double> isPositive = x -> x > 0;

    public Double[] addPositiveNumber (Double[] numbers){
        return Arrays.stream(numbers)
                .filter(x -> x >= 0).toArray(Double[]::new);
    }

    Consumer<Double> println = System.out::println;
}
