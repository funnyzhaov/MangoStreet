package me.funnyzhao.mangostreet.bean.success;

/**
 * Created by funnyzhao .
 */

public class FilesResultBody {
    private String cdn;
    private String filename;
    private String url;

    @Override
    public String toString() {
        return "FilesResultBody{" +
                "cdn='" + cdn + '\'' +
                ", filename='" + filename + '\'' +
                ", url='" + url + '\'' +
                '}';
    }


    public String getCdn() {
        return cdn;
    }

    public void setCdn(String cdn) {
        this.cdn = cdn;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
