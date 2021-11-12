package jason.tcpdemo.funcs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jason.tcpdemo.R;
import jason.tcpdemo.coms.TcpClient;


public class instant extends Activity {

    private Button btnClientCon, btnClientDisCon, off;
    private ImageButton red, green, blue;
    private static TcpClient tcpClient = null;
    private MyBtnClicker myBtnClicker = new MyBtnClicker();
    private TextView code;
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    ExecutorService exec = Executors.newCachedThreadPool();

    private class MyBtnClicker implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_tcpCon:
                    btnClientCon.setEnabled(false);
                    btnClientDisCon.setEnabled(true);
                    tcpClient = new TcpClient();
                    exec.execute(tcpClient);
                    break;

                case R.id.btn_tcpClo:
                    tcpClient.closeSelf();
                    btnClientCon.setEnabled(true);
                    btnClientDisCon.setEnabled(false);
                    break;

                case R.id.btn_off:
                    if(tcpClient != null) {
                        exec.execute(new Runnable() {
                            @Override
                            public void run() {
                                tcpClient.send("off");
                            }
                        });
                        code.setText("RGB_color(0,0,0);");
                    }
                    else
                        code.setText("尚未連線");
                    break;

                case R.id.red_btn:
                    if(tcpClient != null) {
                        exec.execute(new Runnable() {
                            @Override
                            public void run() {
                                tcpClient.send("red");
                            }
                        });
                        code.setText("RGB_color(255,0,0);");
                    }
                    else
                        code.setText("尚未連線");
                    break;

                case R.id.green_btn:
                    if(tcpClient != null) {
                        exec.execute(new Runnable() {
                            @Override
                            public void run() {
                                tcpClient.send("green");
                            }
                        });
                        code.setText("RGB_color(0,255,0);");
                    }
                    else
                        code.setText("尚未連線");
                    break;

                case R.id.blue_btn:
                    if(tcpClient != null) {
                        exec.execute(new Runnable() {
                            @Override
                            public void run() {
                                tcpClient.send("blue");
                            }
                        });
                        code.setText("RGB_color(0,0,255);");
                    }
                    else
                        code.setText("尚未連線");
                    break;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instant);
        bindID();
        bindListener();
        Ini();
    }

    private void bindListener() {
        btnClientCon.setOnClickListener(myBtnClicker);
        btnClientDisCon.setOnClickListener(myBtnClicker);
        red.setOnClickListener(myBtnClicker);
        green.setOnClickListener(myBtnClicker);
        blue.setOnClickListener(myBtnClicker);
        off.setOnClickListener(myBtnClicker);
    }

    private void bindID() {
        btnClientCon  = (Button) findViewById(R.id.btn_tcpCon);
        btnClientDisCon = (Button) findViewById(R.id.btn_tcpClo);
        off = (Button)findViewById(R.id.btn_off);
        red = (ImageButton) findViewById(R.id.red_btn);
        green = (ImageButton)findViewById(R.id.green_btn);
        blue = (ImageButton)findViewById(R.id.blue_btn);
        code = (TextView)findViewById(R.id.codeView);
    }


    private void Ini(){
        btnClientDisCon.setEnabled(false);
    }
}