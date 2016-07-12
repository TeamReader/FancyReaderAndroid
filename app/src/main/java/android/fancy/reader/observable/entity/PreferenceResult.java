package android.fancy.reader.observable.entity;

/**
 * Created by inx95 on 16-7-10.
 */
public class PreferenceResult {
    private String userName;
    private String password;
    private String fontstyleApp;
    private String fontsizeApp;
    private String fontcolorApp;
    private String bgApp;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFontstyleApp() {
        return fontstyleApp;
    }

    public void setFontstyleApp(String fontstyleApp) {
        this.fontstyleApp = fontstyleApp;
    }

    public String getFontsizeApp() {
        return fontsizeApp;
    }

    public void setFontsizeApp(String fontsizeApp) {
        this.fontsizeApp = fontsizeApp;
    }

    public String getFontcolorApp() {
        return fontcolorApp;
    }

    public void setFontcolorApp(String fontcolorApp) {
        this.fontcolorApp = fontcolorApp;
    }

    public String getBgApp() {
        return bgApp;
    }

    public void setBgApp(String bgApp) {
        this.bgApp = bgApp;
    }
}
