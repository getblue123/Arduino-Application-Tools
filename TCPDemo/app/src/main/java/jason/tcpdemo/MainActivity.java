package jason.tcpdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import jason.tcpdemo.funcs.mainoption;
import jason.tcpdemo.funcs.optionfinal;

public class MainActivity extends Activity {
    private Button btnFuncEnsure, btnoption;
    private MyButtonClick myButtonClick = new MyButtonClick();
    private MyButtonClick2 myButtonClick2 = new  MyButtonClick2();

    private class MyButtonClick implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_FunctionEnsure:
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, mainoption.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    private class MyButtonClick2 implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_option:
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, optionfinal.class);
                    startActivity(intent);
                    break;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function);
        bindID();
        bindListener();
    }

    private void bindID() {
        btnFuncEnsure = (Button) findViewById(R.id.btn_FunctionEnsure);

    }

    private void bindListener(){
        btnFuncEnsure.setOnClickListener(myButtonClick);
        
    }
}
