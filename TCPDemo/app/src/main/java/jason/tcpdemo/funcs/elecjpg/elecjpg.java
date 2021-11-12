package jason.tcpdemo.funcs.elecjpg;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import jason.tcpdemo.R;


public class elecjpg extends Activity {
    private ImageButton option1, option2, option3, option4;
    private MyImgButtonClick myButtonClick = new MyImgButtonClick();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elecjpg);
        bindID();
        bindListener();
    }

    private class MyImgButtonClick implements ImageButton.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            Uri uri;
            Intent i;
            switch (view.getId()) {
                case R.id.imagebutton:
                    uri= Uri.parse("https://i0.wp.com/www.esp8266learning.com/wp-content/uploads/2017/12/wemos-and-i2c-lcd_bb.png?resize=768%2C450");
                    i = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(i);
                    break;
                case R.id.imagebutton1:
                    uri= Uri.parse("https://www.deanzatech.com/uploads/4/0/1/4/4014610/9268014_orig.png");
                    i = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(i);
                    break;
                case R.id.imagebutton3:
                    uri= Uri.parse("https://blog.jmaker.com.tw/content/images/2020/06/rgb-1.PNG");
                    i = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(i);
                    break;
                case R.id.imagebutton4:
                    uri= Uri.parse("http://qqtrading.com.my/image/contents/L9110%2BH-Drive_bb.jpg");
                    i = new Intent(Intent.ACTION_VIEW,uri);
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
    }

    private void bindListener(){
        option1.setOnClickListener(myButtonClick);
        option2.setOnClickListener(myButtonClick);
        option3.setOnClickListener(myButtonClick);
        option4.setOnClickListener(myButtonClick);
    }
}
