package android.fancy.reader.bean;

/**
 * Created by inx95 on 16-7-12.
 */
public class ContentPerPage {
    private int startLine;
    private int continuanceLines;

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

    public ContentPerPage(int startLine, int continuanceLines) {

        this.startLine = startLine;
        this.continuanceLines = continuanceLines;
    }
}
