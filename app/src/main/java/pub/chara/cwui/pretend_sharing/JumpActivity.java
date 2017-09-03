package pub.chara.cwui.pretend_sharing;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Administrator on 2017/5/2.
 */

public class JumpActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this,SettingsActivity.class));
        this.finish();
    }
}
