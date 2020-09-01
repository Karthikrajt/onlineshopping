package siragu.shopping.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import siragu.shopping.R;
import siragu.shopping.utils.UtilHelper;

public class AboutUs extends AppCompatActivity {

    private static final String TAG = "AboutUs";
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
         web = findViewById(R.id.web);
        setTermsCondition();
    }

    private void setTermsCondition() {
        UtilHelper.showdialog(AboutUs.this);
        String url = getString(R.string.link) + "page/1";
        Log.e(TAG, "sendTokentoserver: " + url);

        AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, "onResponse: "+response );
                        UtilHelper.hidedialog();
                        try {
                            JSONObject object = new JSONObject(String.valueOf(response));
                            JSONObject object1 = object.getJSONObject("data");
                            int status =  object1.getInt("status");
                            if (status == 1){
                                JSONObject page = object1.getJSONObject("page");
                                String desc = page.getString("description");
                                web.loadData(desc, "text/html", "UTF-8");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(TAG, "onError: "+anError.getErrorBody() );
                    }
                });


        
        
        
        /*WebView web = findViewById(R.id.web);
        web.loadUrl("file:///android_asset/" + getString(R.string.about_us));*/
    }
}
