package kz.alisher.diodey.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kz.alisher.diodey.R;

/**
 * Created by Alikdemon on 29.05.2016.
 */
public class LoginActivity extends AppCompatActivity {

    private Button btnSignIn;
    private ProgressDialog pDialog;
    private EditText inputPassword;
    private EditText inputLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputLogin = (EditText) findViewById(R.id.username);
        inputPassword = (EditText) findViewById(R.id.password);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String login = inputLogin.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                if (!login.isEmpty() && !password.isEmpty()) {
                    checkLogin(login, password);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }

    private void checkLogin(final String login, final String password) {
        pDialog.setMessage("Logging in ...");
        showDialog();

        if (login.equals("admin") && password.equals("admin")) {
            Toast.makeText(this, "Successfully", Toast.LENGTH_LONG).show();
            hideDialog();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Invalid login or password!", Toast.LENGTH_SHORT).show();
            hideDialog();
        }
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
