package sumsub;


import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VersionsSorterTest {

    @Test
    public void whenStringsAreDifferentThenSortDesc() {
        List<String> expectedResult = Arrays.asList(
                "10.5.7.3.5",
                "10.4.7",
                "4.5.6.7",
                "4.5",
                "1.4.1",
                "1.1");
        List<String> result = VersionsSorter.sortDescending(Arrays.asList("1.1",
                "4.5.6.7",
                "1.4.1",
                "10.5.7.3.5",
                "4.5",
                "10.4.7"));
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void whenStringsAreIdenticalExceptZeroesThenPreserveOrder() {
        List<String> expectedResult = Arrays.asList(
                "10.5",
                "10.5.0.0");
        List<String> expectedResultReversed = Arrays.asList(
                "10.5.0.0",
                "10.5");
        List<String> result = VersionsSorter.sortDescending(expectedResult);
        Assertions.assertEquals(expectedResult, result);

        result = VersionsSorter.sortDescending(expectedResultReversed);
        Assertions.assertEquals(expectedResultReversed, result);
    }
}
