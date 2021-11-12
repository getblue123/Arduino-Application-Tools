package jason.tcpdemo.funcs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import jason.tcpdemo.R;

public class optionfinal extends Activity {
    private ImageButton option1, option2, option3, option4, option5;
    private MyImgButtonClick myButtonClick = new MyImgButtonClick();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.optionfinal);
        bindID();
        bindListener();
    }

    private class MyImgButtonClick implements ImageButton.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            switch (view.getId()) {
                case R.id.imagebutton:
                    intent.setClass(optionfinal.this, print.class);
                    startActivity(intent);
                    break;
                case R.id.imagebutton1:
                    intent.setClass(optionfinal.this, loop.class);
                    startActivity(intent);
                    break;
                case R.id.imagebutton3:
                    intent.setClass(optionfinal.this, instant.class);
                    startActivity(intent);
                    break;
                case R.id.imagebutton4:
                    intent.setClass(optionfinal.this, condition.class);
                    startActivity(intent);
                    break;

                case R.id.imageButton5:
                    Uri uri= Uri.parse("https://www.arduino.cc/");
                    Intent i = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(i);
                    break;
            }
        }
    }


    private void bindID() {
        option1 = (ImageButton) findViewById(R.id.imagebutton);
        option2 = (ImageButton) findViewById(R.id.imagebutton1);
        option3 = (ImageButton) findViewById(R.id.imagebutton3);
        option4 = (ImageButton) findViewById(R.id.imagebutton4);
        option5 = (ImageButton) findViewById(R.id.imageButton5);
    }

    private void bindListener(){
        option1.setOnClickListener(myButtonClick);
        option2.setOnClickListener(myButtonClick);
        option3.setOnClickListener(myButtonClick);
        option4.setOnClickListener(myButtonClick);
        option5.setOnClickListener(myButtonClick);
    }
}