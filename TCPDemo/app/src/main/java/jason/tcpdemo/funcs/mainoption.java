package jason.tcpdemo.funcs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import jason.tcpdemo.R;

public class mainoption extends Activity {
    private Button btn_fun, arduinoIntro;
    private MyButtonClick myButtonClick = new MyButtonClick();


    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainoption);
        bindID();
        bindListener();
    }

    private class MyButtonClick implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            switch (view.getId()) {
                case R.id.option:
                    intent.setClass(mainoption.this, optionfinal.class);
                    startActivity(intent);
                    break;
                case R.id.btn_arduino:
                    intent.setClass(mainoption.this, ardintro.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    private void bindID() {
       btn_fun = (Button) findViewById(R.id.option);
       arduinoIntro = (Button) findViewById(R.id.btn_arduino);

    }

    private void bindListener(){
        btn_fun.setOnClickListener(myButtonClick);
        arduinoIntro.setOnClickListener(myButtonClick);
    }
}
