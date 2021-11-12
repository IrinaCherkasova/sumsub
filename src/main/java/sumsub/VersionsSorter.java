package sumsub;

import java.util.ArrayList;
import java.util.List;

public class VersionsSorter {

    public static List<String> sortDescending(final List<String> versions) {
        final List<String> sortedVersions = new ArrayList<>(versions);
        sortedVersions.sort((version1, version2) -> new VersionsComparator().reversed().compare(version1, version2));
        return sortedVersions;
    }
}
