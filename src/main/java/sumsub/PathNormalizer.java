package sumsub;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Path is considered to be correct, without trailing spaces
public class PathNormalizer {

    public static String normalize(String path) {
        String[] splitPathStr = path.split("/");

        int index = splitPathStr.length - 1;
        int skippedElements = 0;
        List<String> reversedResult = new ArrayList<>();

        //Going backwards to avoid rewriting elements
        while (index >= 0) {
            String pathElement = splitPathStr[index];

            //Need to skip previous element
            //Counting elements to skip as there could be several ".." in the row
            if (pathElement.equals("..")) {
                skippedElements++;
            } else if (!pathElement.equals(".") && !pathElement.isBlank()) {
                if (skippedElements == 0) {
                    reversedResult.add(pathElement);
                } else {
                    skippedElements--;
                }
            }
            index--;
        }

        if (reversedResult.isEmpty()) {
            return "/";
        }

        Collections.reverse(reversedResult);

        //Taking into account situation when the string is not started with a "/"
        boolean isLeadingSlashNeeded = "".equals(splitPathStr[0]);

        return constructPathFromElements(reversedResult, isLeadingSlashNeeded);
    }

    //Could be extended with a tail slash, though there is no difference for the path itself
    private static String constructPathFromElements(List<String> elementsList,
                                                    boolean isLeadingSlashNeeded) {
        StringBuilder normalizedPath = new StringBuilder();

        for (String element : elementsList) {
            normalizedPath.append("/");
            normalizedPath.append(element);
        }

        if (!elementsList.isEmpty() && !isLeadingSlashNeeded) {
            normalizedPath.deleteCharAt(0);
        }

        return normalizedPath.toString();
    }
}