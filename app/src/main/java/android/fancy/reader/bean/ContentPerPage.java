package android.fancy.reader.bean;

/**
 * Created by inx95 on 16-7-12.
 */
public class ContentPerPage {
    private int startLine;
    private int continuanceLines;
    private int indexInStartLine;

    public ContentPerPage(int startLine, int continuanceLines, int indexInStartLine) {

        this.startLine = startLine;
        this.continuanceLines = continuanceLines;
        this.indexInStartLine = indexInStartLine;
    }

    public int getIndexInStartLine() {
        return indexInStartLine;
    }

    public void setIndexInStartLine(int indexInStartLine) {
        this.indexInStartLine = indexInStartLine;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getContinuanceLines() {
        return continuanceLines;
    }

    public void setContinuanceLines(int continuanceLines) {
        this.continuanceLines = continuanceLines;
    }
}
