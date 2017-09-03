package pub.chara.cwui.pretend_sharing;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/4/27.
 */

public class GenericActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setResult(RESULT_OK);
        if(!sharedPreferences.getBoolean("disable_toast",false))
            Toast.makeText(this,R.string.msg_generic_share_success,Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }
}
