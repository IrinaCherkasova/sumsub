package sumsub;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PathNormalizerTest {

    @Test
    public void whenSimplePathThenNormalize() {
        String path = "/qq/ww/./../ee/rr/../../tt";
        String result = PathNormalizer.normalize(path);
        Assertions.assertEquals("/qq/tt", result);
    }

    @Test
    public void whenPathExceedsLimitThenReturnRootDir() {
        String path = "/qq/ww/./../ee/rr/../../tt/../../../../..";
        String result = PathNormalizer.normalize(path);
        Assertions.assertEquals("/", result);
    }

    @Test
    public void whenEmptyStrThenReturnRootDir() {
        String path = "";
        String result = PathNormalizer.normalize(path);
        Assertions.assertEquals("/", result);
    }

    @Test
    public void whenStartsWithNoSlashTenReturnWithoutSlash() {
        String path = "qq/ww/./d";
        String result = PathNormalizer.normalize(path);
        Assertions.assertEquals("qq/ww/d", result);
    }

    @Test
    public void whenDoubleSlashInTheBeginningThenStartWithOneSlash() {
        String path = "//qq/ww/./d";
        String result = PathNormalizer.normalize(path);
        Assertions.assertEquals("/qq/ww/d", result);
    }

    @Test
    public void whenDoubleSlashInTheMiddleThenIgnoreIt() {
        String path = "/qq/ww//g/./d";
        String result = PathNormalizer.normalize(path);
        Assertions.assertEquals("/qq/ww/g/d", result);
    }

}
