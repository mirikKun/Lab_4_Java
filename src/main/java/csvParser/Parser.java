package csvParser;

import java.util.ArrayList;
import java.util.List;


public class Parser {

    public static List<String> parseLine(String cvsLine, char separators, char quote) {

        List<String> result = new ArrayList<>();

        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inComment = false;
        boolean firstComment = false;
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {
            if (!inComment) {
                if (inQuotes) {

                    startCollectChar = true;
                    if (ch == quote) {
                        inQuotes = false;
                        doubleQuotesInColumn = false;
                    } else if (firstComment & ch == '*') {
                        inComment = true;

                    } else if (ch == '/') {
                        firstComment = true;
                    } else {

                        if (ch == '\"') {
                            if (!doubleQuotesInColumn) {
                                curVal.append(ch);
                                doubleQuotesInColumn = true;
                            }
                        } else if (firstComment) {
                            curVal.append('/');
                            curVal.append(ch);
                            firstComment = false;
                        } else
                            curVal.append(ch);
                    }
                } else {
                    if (ch == quote) {

                        inQuotes = true;

                        if (chars[0] != '"' && quote == '\"') {
                            curVal.append('"');
                        }

                        if (startCollectChar) {
                            curVal.append('"');
                        }

                    } else if (ch == separators) {

                        result.add(curVal.toString());

                        curVal = new StringBuffer();
                        startCollectChar = false;

                    } else if (ch == '\n') {

                        break;
                    } else if (firstComment) {
                        if (ch == '*')
                            inComment = true;
                        else {
                            firstComment = false;
                            curVal.append('/');
                            curVal.append(ch);
                        }
                    } else if (ch == '/') {
                        firstComment = true;
                    } else {
                        curVal.append(ch);
                    }
                }
            } else {
                if (firstComment) {
                    if (ch == '/') {
                        inComment = false;
                        firstComment = false;
                    } else
                        firstComment = false;
                }
                if (ch == '*')
                    firstComment = true;

            }
        }

        result.add(curVal.toString());

        return result;
    }
}
