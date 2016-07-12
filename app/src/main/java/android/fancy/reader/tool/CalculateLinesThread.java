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
    private int currentPage=0;
    private int currentStartLine = 0;
    private int continuanceLine = 0;


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

            int usedLines = 0;
            String line;
            while ((line = lnr.readLine()) != null) {
                usedLines = consumeLine(line,usedLines);
            }
            pages.put(currentPage, new ContentPerPage(currentStartLine, continuanceLine));

            fis.close();
            isr.close();
            lnr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int consumeLine(String line,int usedLinesShow) {
        int thisLineCountShowLine = (int)Math.ceil((double) line.length() / charCountPerLine);
        if(thisLineCountShowLine==0) thisLineCountShowLine = 1;
        if (usedLinesShow + thisLineCountShowLine <= linePerPageInTv) {
            continuanceLine++;
            usedLinesShow += thisLineCountShowLine;
        } else {
            pages.put(currentPage, new ContentPerPage(currentStartLine, continuanceLine));
            currentPage++;
            usedLinesShow = thisLineCountShowLine;
            currentStartLine += continuanceLine;
            continuanceLine = 0;
        }
        return usedLinesShow;
    }
}
