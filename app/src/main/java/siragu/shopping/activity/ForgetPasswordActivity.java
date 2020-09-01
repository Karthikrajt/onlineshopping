package siragu.shopping.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import siragu.shopping.R;
import siragu.shopping.fragment.LoginFragment;
import siragu.shopping.utils.UtilHelper;


public class ForgetPasswordActivity extends AppCompatActivity {

    private static final String TAG = "ForgetPasswordActivity";
    private EditText edt_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        initlise();
    }

    private void initlise() {
        Button bn_confirm = findViewById(R.id.bn_confirm);
        edt_mail = findViewById(R.id.edt_mail);
        bn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = edt_mail.getText().toString().trim();
                if (!email.isEmpty()) {
                    ForgotPass(email);

                } else {
                    edt_mail.setError("Enter Valid Email");
                }
            }
        });
    }

    private void ForgotPass(String email) {
        UtilHelper.showdialog(ForgetPasswordActivity.this);
        String url = getString(R.string.link) +"forgotpassword?email=" + email;

        AndroidNetworking.get(url)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e(TAG, "onResponse: "+response);
                         UtilHelper.hidedialog();
                        try {
                            JSONObject object = new JSONObject(String.valueOf(response));
                            int status  = object.getInt("status");
                            String msg  = object.getString("msg");
                            if (status == 1){
                                Toast.makeText(ForgetPasswordActivity.this,msg , Toast.LENGTH_SHORT).show();
                                Fragment fragment = new LoginFragment();
                                Loginfrag(fragment);
                            }else {
                                Toast.makeText(ForgetPasswordActivity.this,msg , Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(ForgetPasswordActivity.this,getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e(TAG, "onError: "+anError.getErrorBody() );
                        Toast.makeText(ForgetPasswordActivity.this,getString(R.string.try_again), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void Loginfrag(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public void backpress(View view) {
        onBackPressed();
    }
}