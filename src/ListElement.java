import java.util.ArrayList;
import java.util.List;

public class ListElement implements Element {
    List<Element> elements = new ArrayList<>();

    public ListElement(Integer value) {
        elements.add(new NumberElement(value.toString()));
    }
    public ListElement(String fullArrayString) {
        String arrayContents = fullArrayString.substring(1, fullArrayString.length() - 1);

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < arrayContents.length(); i++) {

            char parsingCharacter = arrayContents.charAt(i);

            // character is number add to buffer
            if (isNumber(parsingCharacter)) {
                buf.append(parsingCharacter);
            }

            //end of number parsing
            if ((parsingCharacter == ',' && !buf.isEmpty())|| parsingCharacter == ']' || i == arrayContents.length() - 1) {
                elements.add(new NumberElement(buf.toString()));
                // empty buffer
                buf = new StringBuffer();
            }

            if (parsingCharacter == '[') {
                int matchingClosingBracketIndex = matchingClosingBracketIndex(arrayContents, i);
                elements.add(new ListElement(arrayContents.substring(i, matchingClosingBracketIndex + 1)));
                i = matchingClosingBracketIndex;
            }

        }


    }

    private int matchingClosingBracketIndex(String arrayContents, int openingBracketIndex) {
        int opening = 1;
        int closing  = 0;
        for(int i = openingBracketIndex + 1; ; i++) {
            char maybeBracket = arrayContents.charAt(i);
            if(maybeBracket == ']') {
                closing++;
                if (opening == closing) {
                    return i;
                }
            }
            if (maybeBracket == '[') {
                opening++;
            }
        }
    }

    private boolean isNumber(char character) {
        return character >= '0' && character <= '9';
    }
}
