//}}}
//{{{ shiftIndentRight() method
/**
	 * Shifts the indent of each line in the specified list to the right.
	 * @param lines The line numbers
	 * @since jEdit 3.2pre1
	 */
public void shiftIndentRight(int[] lines) {
    try {
        beginCompoundEdit();
        int tabSize = getTabSize();
        int indentSize = getIndentSize();
        boolean noTabs = getBooleanProperty("noTabs");
        for (int l : lines) {
            int lineStart = getLineStartOffset(l);
            CharSequence line = getLineSegment(l);
            int whiteSpace = StandardUtilities.getLeadingWhiteSpace(line);
            // silly usability hack
            //if(lines.length != 1 && whiteSpace == 0)
            //	continue;
            int whiteSpaceWidth = StandardUtilities.getLeadingWhiteSpaceWidth(line, tabSize) + indentSize;
            insert(lineStart + whiteSpace, StandardUtilities.createWhiteSpace(whiteSpaceWidth, noTabs ? 0 : tabSize));
            remove(lineStart, whiteSpace);
        }
    } finally {
        endCompoundEdit();
    }
}