package com.hhp.huuphuoc372.ungdungquanlycongtrinh.app.model;

public class Option {
    private int imgOption;
    private String option;

    public Option(int imgOption, String option) {
        this.imgOption = imgOption;
        this.option = option;
    }

    public Option() {
    }

    public int getImgOption() {
        return imgOption;
    }

    public void setImgOption(int imgOption) {
        this.imgOption = imgOption;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "Option{" +
                "imgOption=" + imgOption +
                ", option='" + option + '\'' +
                '}';
    }
}
