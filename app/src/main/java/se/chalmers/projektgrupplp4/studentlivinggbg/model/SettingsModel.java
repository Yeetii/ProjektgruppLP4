package se.chalmers.projektgrupplp4.studentlivinggbg.model;


/**
 * @author Peter Gärdenäs
 * Used by: Db4oDatabase, MainSearchActivity, NotificationSender, SearchActivityController, SettingsController
 * Uses: (None)
 * Responsibility: Holds users settings
 */

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
    }

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
