package jason.tcpdemo.funcs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jason.tcpdemo.R;
import jason.tcpdemo.funcs.elecjpg.elecjpg;


public class ardintro extends Activity {
    private Button btn_fun, arduinoIntro;
    private MyButtonClick myButtonClick = new MyButtonClick();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.andrinointro);
        bindID();
        bindListener();
    }

    private class MyButtonClick implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent();
            switch (view.getId()) {
                case R.id.option:
                    intent.setClass(ardintro.this, ardintro2.class);
                    startActivity(intent);
                    break;
                case R.id.btn_arduino:
                    intent.setClass(ardintro.this, elecjpg.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    private void bindID() {
        btn_fun  = (Button) findViewById(R.id.option);
        arduinoIntro = (Button) findViewById(R.id.btn_arduino);

    }

    private void bindListener(){
        btn_fun.setOnClickListener(myButtonClick);
        arduinoIntro.setOnClickListener(myButtonClick);
    }

}
