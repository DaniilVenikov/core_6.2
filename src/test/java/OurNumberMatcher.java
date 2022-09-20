import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class OurNumberMatcher extends TypeSafeMatcher<Double> {
    Calculator calculator = Calculator.calculatorSupplier.get();

    public void describeTo(Description description){
        description.appendText("calculator works correctly");
    }

    @Override
    protected boolean matchesSafely(Double number){
        return (calculator.abs.apply(number) >= 0) && (calculator.power.apply(number) >= 0);
    }


    public static OurNumberMatcher isOurNumber() {
        return new OurNumberMatcher();
    }
}
