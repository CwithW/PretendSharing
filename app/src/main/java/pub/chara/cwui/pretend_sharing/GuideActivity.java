package pub.chara.cwui.pretend_sharing;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by Administrator on 2017/4/26.
 */

public class GuideActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(Build.VERSION.SDK_INT>=21)
            setTheme(R.style.Material);
        else if (Build.VERSION.SDK_INT>=11)
            setTheme(R.style.Holo);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        setTitle(R.string.title_guide);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
