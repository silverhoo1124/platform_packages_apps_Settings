/*
                         $$$$$$$$$$$$$$$
                      $$$$$$        $$  $$$$
                     $$$             $     $$$
                   $$$$$$$          $$$      $$$
                  $$$$ $$$$        $$          $$$
                 $$$      $$       $            $$$
     FFFF       $$$$     $$                $$$    $$
    UUUUUU     $$$  $$$$$$              $$$$$$$$$  $$
               $$$                     $$     $$$  $$
               $$    $$$$$$$$$         $$      $$  $$
                $$ $$  $$$ $$$$$$$$$     $$$$$$    $$
                 $ $$      $$$  $$  $$$           $$
  $$$             $$$              $$  $$        $$$
   $$$$             $$$$$$$            $$      $$$
 $$$$$$$$$$$         $$$  $$$$$       $$     $$$$
    $$$  $$$$$$$$$   $$$     $$$$$   $$  $$$$$
  $$$$        $$$$$$$$$$$$$$$    $$$$$$$$$$$$
 $$$$               $$$     $$$$$$$$$$$
      $$           $$$            $$$
        $$        $$$            $$$
        $$$$$$$$$$$$$$$$$$       $$$
     $$$$$$$$     $$$$$$$$$$$$$  $$
        $$$$      $$$            $$
       $$$        $$            $$$
                  $$            $$$
                  $$$$$$$$$$$$$$$$$
                   $$$$$$$$$$$$$$$
                     $$$       $$$
                    $$$         $$$
                   $$$           $$$
                  $$$             $$$
                 $$$               $$$
                $$$                 $$$
               $$$                   $$$
            $$$$$                    $$$$
           $$$$$$                    $$$
                                   $$$$
*/

package com.android.settings.biantai;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.provider.Settings;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class NotificationSettings extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {

    private static final String TAG = "NotificationSettings";

    private static final String KEY_NOTIFICATION_PULSE = "notification_pulse";

    private PreferenceScreen mNotificationPulse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.notification_settings);

        PreferenceScreen prefScreen = getPreferenceScreen();

        boolean hasNotificationLed = getResources().getBoolean(
                com.android.internal.R.bool.config_intrusiveNotificationLed);
        mNotificationPulse = (PreferenceScreen) findPreference(KEY_NOTIFICATION_PULSE);
        if (mNotificationPulse != null) {
            if (!hasNotificationLed) {
                getPreferenceScreen().removePreference(mNotificationPulse);
                mNotificationPulse = null;
            }
        }
    }

    private void updateLightPulseSummary() {
        if (Settings.System.getInt(getActivity().getContentResolver(),
                Settings.System.NOTIFICATION_LIGHT_PULSE, 0) == 1) {
            mNotificationPulse.setSummary(R.string.notification_light_enabled);
        } else {
            mNotificationPulse.setSummary(R.string.notification_light_disabled);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mNotificationPulse != null) {
            updateLightPulseSummary();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return false;
    }
}
