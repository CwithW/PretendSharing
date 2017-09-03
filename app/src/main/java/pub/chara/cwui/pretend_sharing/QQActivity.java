package pub.chara.cwui.pretend_sharing;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;



public class QQActivity extends Activity {
    private static final String string_share = "share";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //相关qq sdk内代码：搜索mqqapi
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        onButtonClick(null);
    }

    public void onButtonClick(View mView) {
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final Intent iGet = getIntent();
        final Uri uGet = iGet.getData();//获取Uri
        /*test*/ //if(this.getCallingActivity()!=null)
            //Toast.makeText(this,getCallingActivity().getClassName(),Toast.LENGTH_LONG).show();
        if(null!=uGet)
        {
            //Toast.makeText(this, uGet.getHost(), Toast.LENGTH_SHORT).show();
            if(!(string_share.equals(uGet.getHost()))) {
                Toast.makeText(this, R.string.msg_not_qq_share_action, Toast.LENGTH_SHORT).show();
            }
                //this.returnintent();
                //假装我是qq，而且假装分享成功了
                //相关qq sdk内代码：com.tencent.connect.common.UIListenerManager:onActivityResultData
                //  com.tencent.connect.common.AssistActivity:onActivityResult 和 setResultData
                //Intent i = getIntent();
                //Intent i = new Intent(this,this.getClass());
                //i.putExtra("key_response","action_share");
                //i.putExtra("key_action","action_share");
                //i.putExtra("result","complete");
                //i.putExtra("action","shareToQQ");
                //i.putExtra("key_response","");
                //qq json
                //i.putExtra("key_response","{\"openid\":\"666\",\"access_token\":\"233\"}");
                //i.putExtra("key_error_code",0);
                //i.putExtra("key_error_msg","");
                //i.putExtra("key_error_detail","");
                //i.putExtra("key_response","");
                //this.setResult(RESULT_OK,i);
                this.setResult(RESULT_OK,null);
                //if(this.getParent()!=null)
                //    getParent().setResult(RESULT_OK,i);
                //else
                //    setResult(RESULT_OK,i);
                Intent launchBackIntent = new Intent();
                String pkgName = iGet.getStringExtra("pkg_name");
                launchBackIntent.setData(Uri.parse("tencent222222://tauth.qq.com/?#action=shareToQQ&result=complete&response={\"ret\":0}"));
                launchBackIntent.setFlags(272629760);
                launchBackIntent.putExtra("fling_action_key",2);
                launchBackIntent.putExtra("preAct","LiteActivity");
                launchBackIntent.putExtra("leftViewText","分享成功");
                launchBackIntent.putExtra("fling_code_key",32253408);
                launchBackIntent.putExtra("preAct_time",1493381615748L);
                try{
                    launchBackIntent.setComponent(new ComponentName(pkgName,"com.tencent.tauth.AuthActivity"));
                    startActivity(launchBackIntent);
                }catch (Exception e){
                    Toast.makeText(this,String.format(getString(R.string.msg_error_no_callback),pkgName),Toast.LENGTH_SHORT).show();
                    irregularCallback();
                    super.onBackPressed();
                }
                if(!sharedPreferences.getBoolean("disable_toast",false))
                    Toast.makeText(this, R.string.msg_qq_share_success, Toast.LENGTH_SHORT).show();
                super.onBackPressed();
        }else {
            Toast.makeText(this, R.string.msg_not_action, Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }

    }

    @Override
    public void onBackPressed() {
        //do nothing
    }
    private void irregularCallback(){
        final Intent iGet = getIntent();
        Intent launchBackIntent = new Intent();
        String pkgName = iGet.getStringExtra("pkg_name");
        launchBackIntent.setData(Uri.parse("tencent222222://tauth.qq.com/?#action=shareToQQ&result=complete&response={\"ret\":0}"));
        launchBackIntent.setFlags(272629760);
        launchBackIntent.putExtra("fling_action_key",2);
        launchBackIntent.putExtra("preAct","LiteActivity");
        launchBackIntent.putExtra("leftViewText","分享成功");
        launchBackIntent.putExtra("fling_code_key",32253408);
        launchBackIntent.putExtra("preAct_time",1493381615748L);
        try{
            startActivity(launchBackIntent);
        }catch (Exception e){
            //super.onBackPressed();
        }
    }
}
