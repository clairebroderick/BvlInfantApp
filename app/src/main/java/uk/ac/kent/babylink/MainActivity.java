package uk.ac.kent.babylink;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 42350 on 22/06/2017.
 */

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MainActivity";

    EditText usernameField;
    EditText passwordField;
    Button  loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = (EditText) findViewById(R.id.username_field);
        passwordField = (EditText) findViewById(R.id.password_field);
        loginButton = (Button) findViewById(R.id.login_button);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameField.getText().toString();
                String password = passwordField.getText().toString();

                if (username.compareTo("admin") == 0 && password.compareTo("admin") == 0){
                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(MainActivity.this, "Login attempt failed", Toast.LENGTH_SHORT).show();
                }
            }
        };

        loginButton.setOnClickListener(buttonListener);
    }
}

