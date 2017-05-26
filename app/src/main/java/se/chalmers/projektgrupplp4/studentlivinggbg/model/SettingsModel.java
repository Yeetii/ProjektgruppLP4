package se.chalmers.projektgrupplp4.studentlivinggbg.model;


public class SettingsModel {
    private static SettingsModel instance = null;
    private boolean pushEnabled = true;
    private String defaultSort;

    public static void setSettingsModel(SettingsModel model) {
        instance = model;
    }

    public static SettingsModel getInstance() {
        return instance;
    }

    public void setPushEnabled(boolean value) {
        pushEnabled = value;
    };

    public boolean isPushEnabled () {
        return pushEnabled;
    }

    public String getDefaultSort() {
        return defaultSort;
    }

    public void setDefaultSort(String sort) {
        this.defaultSort = sort;
    }
}
