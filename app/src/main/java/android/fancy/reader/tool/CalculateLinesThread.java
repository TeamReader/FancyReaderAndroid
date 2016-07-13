package android.fancy.reader.tool;

import android.fancy.reader.bean.ContentPerPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Map;

/**
 * Created by inx95 on 16-7-12.
 */
public class CalculateLinesThread extends Thread {
    private Map<Integer, ContentPerPage> pages;
    private File file;
    private final int linePerPageInTv;
    private final int charCountPerLine;
    private int currentPage = 0;
    private int startLine = 0;
    private int currentLine = 0;
    private int usedLinesShow = 0;
    private int indexInStartLine = 0;


    public CalculateLinesThread(Map<Integer, ContentPerPage> pages, File file, int linePerPageInTv, int charCountPerLine) {
        this.pages = pages;
        this.file = file;
        this.linePerPageInTv = linePerPageInTv;
        this.charCountPerLine = charCountPerLine;
    }

    @Override
    public void run() {
        try {
            String charset = TextUtil.getCharset(file);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, charset);
            LineNumberReader lnr = new LineNumberReader(isr);

            String line;
            while ((line = lnr.readLine()) != null) {
                consumeLine(line);
                currentLine++;
            }
            pages.put(currentPage, new ContentPerPage(startLine, currentLine - startLine + 1, indexInStartLine));
            fis.close();
            isr.close();
            lnr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void consumeLine(String line) {
        int thisLineCountShowLine = (int) Math.ceil((double) line.length() / charCountPerLine);
        if (thisLineCountShowLine == 0) thisLineCountShowLine = 1; //\n情况
        if (usedLinesShow + thisLineCountShowLine < linePerPageInTv) {
            usedLinesShow += thisLineCountShowLine;
        } else if (usedLinesShow + thisLineCountShowLine == linePerPageInTv) {
            generatePage();
            startLine = currentLine + 1;
            indexInStartLine = 0;
        } else {
            int needLineCount = linePerPageInTv - usedLinesShow;//尚需多少显示行完成一页
            generatePage();
            startLine = currentLine;
            indexInStartLine = charCountPerLine * needLineCount;
            consumeLine(line.substring(indexInStartLine));
        }
    }

    private void generatePage() {
        pages.put(currentPage, new ContentPerPage(startLine, currentLine - startLine + 1, indexInStartLine));
        currentPage++;
        usedLinesShow = 0;
    }
}
