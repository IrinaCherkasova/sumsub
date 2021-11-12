package sumsub;

import java.util.Comparator;

//Strings are supposed to be valid, i.e. containing only integers separated by "."
public class VersionsComparator implements Comparator<String> {

    @Override
    public int compare(String string1, String string2) {
        if (string1.equals(string2)) {
            return 0;
        }
        String[] firstSplitStr = string1.split("\\.");
        String[] secondSplitStr = string2.split("\\.");

        int minLength = Math.min(firstSplitStr.length, secondSplitStr.length);

        for (int i = 0; i < minLength; i++) {
            Integer element1 = Integer.parseInt(firstSplitStr[i]);
            Integer element2 = Integer.parseInt(secondSplitStr[i]);
            if (element1.equals(element2)) {
                continue;
            }
            return element1.compareTo(element2);
        }

        return compareTail(firstSplitStr, secondSplitStr);
    }

    private int compareTail(String[] firstSplitStr, String[] secondSplitStr) {
        int minLength = Math.min(firstSplitStr.length, secondSplitStr.length);

        boolean isFirstStrLongest = firstSplitStr.length > minLength;
        String[] longestString = isFirstStrLongest ? firstSplitStr : secondSplitStr;

        //Versions are considered to be identical if they are differed only by zeroes
        for (int i = minLength; i < longestString.length; i++) {
            if (!longestString[i].equals("0")) {
                return isFirstStrLongest ? 1 : -1;
            }
        }

        return 0;
    }
}
