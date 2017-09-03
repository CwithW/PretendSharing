package pub.chara.cwui.pretend_sharing;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/5/2.
 */

public class SettingsActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(Build.VERSION.SDK_INT>=21)
            setTheme(R.style.Material);
        else if (Build.VERSION.SDK_INT>=11)
            setTheme(R.style.Holo);
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_settings);
        findPreference("disable_qq").setOnPreferenceChangeListener(new disabler("QQActivity"));
        findPreference("disable_wechat").setOnPreferenceChangeListener(new disabler("WechatActivity"));
        findPreference("disable_generic").setOnPreferenceChangeListener(new disabler("GenericActivity"));
        findPreference("disable_this").setOnPreferenceChangeListener(new disabler("JumpActivity"));
        findPreference("show_help").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                startActivity(new Intent(SettingsActivity.this, GuideActivity.class));
                return true;
            }
        });
    }

    private class disabler implements Preference.OnPreferenceChangeListener{
        private static final String packageName = "pub.chara.cwui.pretend_sharing";
        private String ActivityName;
        public disabler(String ActivityName){
            super();
            this.ActivityName = packageName+"."+ActivityName;
        }
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            try {
                ComponentName targetActivity = new ComponentName(packageName, ActivityName);
                PackageManager p = getPackageManager();
                if ((boolean) newValue) {
                    p.setComponentEnabledSetting(targetActivity, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                } else {
                    p.setComponentEnabledSetting(targetActivity, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                }
            }catch (Exception e){
                Toast.makeText(SettingsActivity.this,R.string.msg_disable_error,Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
        }
    }
}
