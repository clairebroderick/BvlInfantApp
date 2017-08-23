package uk.ac.kent.babylink;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by 42350 on 31/07/2017.
 */

public class GeneratorActivity extends AppCompatActivity {

    private String TAG = "GeneratorActivity";

    private Button mButton;
    private View.OnClickListener mButtonListener;

    private String encryptedKey;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generator);

        DateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
        final String randomKey = df.format(Calendar.getInstance().getTime()) + "BVL";

        SharedPreferences.Editor editor = getSharedPreferences("CODE", MODE_PRIVATE).edit();
        editor.putString("code", randomKey);
        editor.apply();
        encryptedKey = encryption(randomKey);

        mButton = (Button) findViewById(R.id.button);
        mButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button:

                    WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
                    Display display = manager.getDefaultDisplay();
                    Point point = new Point();
                    display.getSize(point);
                    int width = point.x;
                    int height = point.y;
                    int smallerDimension = width < height ? width : height;
                    smallerDimension = smallerDimension * 3/4;

                    CodeEncoder qrCodeEncoder = new CodeEncoder(encryptedKey, null, "TEXT_TYPE", BarcodeFormat.QR_CODE.toString(), smallerDimension);
                    try {
                        Bitmap bitmap = qrCodeEncoder.encodeAsBitmap();
                        ImageView myImage = (ImageView) findViewById(R.id.imageView);
                        myImage.setImageBitmap(bitmap);

                    } catch (WriterException e) {
                        e.printStackTrace();
                    }

                    break;
            }
            }
        };

        mButton.setOnClickListener(mButtonListener);
    }

    public String encryption(String stringToEncrypt){

        String passPhrase = "ProjectBabyVideoLink";
        String encryptedPhrase = new String();
        try {
            encryptedPhrase = AESEncrypter.encrypt(passPhrase, stringToEncrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedPhrase;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
