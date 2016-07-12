package android.fancy.reader.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.Map;

/**
 * Created by inx95 on 16-7-12.
 */
public class CalcuThread extends Thread {
    private Map<Integer, Integer> pageLineBeginMap;
    private Map<Integer, Integer> pageLineNumsMap;
    private File file;
    private int pageNums;


    public CalcuThread(Map<Integer, Integer> pageLineBeginMap, Map<Integer, Integer> pageLineNumsMap, File file, int pageNums) {
        this.pageLineBeginMap = pageLineBeginMap;
        this.pageLineNumsMap = pageLineNumsMap;
        this.file = file;
        this.pageNums = pageNums;
    }

    @Override

    public void run() {
        double height=0;
        double width=0;
        if (height == 0) {
            height = 700;
        }
        if (width == 0) {
            width = 1200;
        }
        int linePerpage = (int) (height / 20);
        int wordNumsPerLine = (int) (width / 12);
        //第几页
        int pageNums = 1;
        //从第几行开始显示
        int lineBegin = 0;
        //显示的行数用来判断剩余的行 能否容纳这么多行
        int lineNums = 0;
        // 实际读取的行数
        int trueLineNums = 0;
        int defaultLinePerPage = linePerpage;
        try {
            String charset = TextUtil.getCharset(file);
            LineNumberReader lineNumberReader = new LineNumberReader(
                    new InputStreamReader(
                            new FileInputStream(file), charset
                    )
            );
            String tempData;
            while ((tempData = lineNumberReader.readLine()) != null) {
                int lines = (int) Math.ceil(tempData.length() / wordNumsPerLine);
                if (lines == 0) {
                    lines = 1;
                }
                if (defaultLinePerPage < lines) {
                    //表示剩余的行无法满足容纳者一句话
                    pageLineBeginMap.put(pageNums, lineBegin);
                    pageLineNumsMap.put(pageNums, trueLineNums);
                    lineBegin = lineNumberReader.getLineNumber();
                    trueLineNums = 0;
                    defaultLinePerPage = linePerpage;
                    pageNums++;
                    lineNums = 0;
                    int templines = (int) Math.ceil(tempData.length() / wordNumsPerLine);
                    if (templines == 0) {
                        templines = 1;
                    }
                    lineNums += templines;
                    defaultLinePerPage -= templines;
                    continue;
                }
                trueLineNums++;
                lineNums += lines;
                defaultLinePerPage -= lines;
            }
            //处理最后的
            pageLineBeginMap.put(pageNums, lineBegin);
            pageLineNumsMap.put(pageNums, lineNums);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
