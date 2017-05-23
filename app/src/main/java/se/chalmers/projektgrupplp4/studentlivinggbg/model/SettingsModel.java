package se.chalmers.projektgrupplp4.studentlivinggbg.model;


public class SettingsModel {

    public static void setPushNotificationsEnabled() {
        IsEnabled.setEnabled();
        //Db4oDatabase.getInstance().delete(IsEnabled.class);
        //Db4oDatabase.getInstance().store(IsEnabled.class);

    }

    public static void resetPushNotificationsEnabled() {
        IsEnabled.resetEnabled();
        //Db4oDatabase.getInstance().delete(IsEnabled.class);
        //Db4oDatabase.getInstance().store(IsEnabled.class);
    }

    public static boolean isPushNotificationsEnabled() {
        //TODO get the thing I stored??
        //pushNotificationsEnabled = (IsEnabled) Db4oDatabase.getInstance().findAll(pushNotificationsEnabled.getClass()).get(0);
        //System.out.println(Db4oDatabase.getInstance().findAll(IsEnabled.class) == null);//) {
        //    IsEnabled.setEnabled();
        //} else {
           /* IsEnabled temp = (IsEnabled) Db4oDatabase.getInstance().findAll(IsEnabled.class).get(0);
            if (temp.isEnabled()) {
                IsEnabled.setEnabled();
            } else {
                IsEnabled.resetEnabled();
            }
        }
        return IsEnabled.isEnabled();*/
        return true;
    }
    private static class IsEnabled {

        private static boolean isEnabled;

        public static void setEnabled () {
            isEnabled = true;
        }

        public static void resetEnabled () {
            isEnabled = false;
        }

        public static boolean isEnabled () {
            return isEnabled;
        }
    }
}
