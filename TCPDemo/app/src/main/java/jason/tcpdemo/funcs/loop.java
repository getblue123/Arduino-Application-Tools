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
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jason.tcpdemo.R;
import jason.tcpdemo.coms.TcpClient;


public class loop extends Activity {
    private Button btnClientSend, btnCom, btnClientCon, btnClientDisCon, check;
    private EditText editClientSend;
    private TextView horncode;
    private static TcpClient tcpClient;

    private MyBtnClicker myBtnClicker = new MyBtnClicker();
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    ExecutorService exec = Executors.newCachedThreadPool();

    private class MyBtnClicker implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_tcpCon:
                    btnClientCon.setEnabled(false);
                    btnClientDisCon.setEnabled(true);
                    btnClientSend.setEnabled(true);
                    tcpClient = new TcpClient();
                    exec.execute(tcpClient);
                    break;

                case R.id.btn_tcpClo:
                    tcpClient.closeSelf();
                    btnClientCon.setEnabled(true);
                    btnClientDisCon.setEnabled(false);
                    btnClientSend.setEnabled(false);
                    break;

                case R.id.btn_command:
                    Intent intent = new Intent();
                    intent.setClass(loop.this, commandatahorn.class);
                    startActivity(intent);
                    break;

                case R.id.btn_clientSend:
                    Message message = Message.obtain();
                    message.what = 2;
                    message.obj = editClientSend.getText().toString();

                    exec.execute(new Runnable() {
                        @Override
                        public void run() {
                            tcpClient.send(editClientSend.getText().toString());
                        }
                    });
                    break;

                case R.id.code_view2:
                    if ("1".equals(editClientSend.getText().toString()))
                        horncode.setText("1");

                    else if (editClientSend.getText().toString().equals("2"))
                        horncode.setText("2");

                    else
                        horncode.setText("查無指令");
                    break;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loop);
        context = this;
        bindID();
        bindListener();
        Ini();
    }

    private void Ini(){
        btnClientDisCon.setEnabled(false);
        btnClientSend.setEnabled(false);
    }

    private void bindListener() {
        btnClientSend.setOnClickListener(myBtnClicker);
        btnClientCon.setOnClickListener(myBtnClicker);
        btnClientDisCon.setOnClickListener(myBtnClicker);
        btnCom.setOnClickListener(myBtnClicker);
        check.setOnClickListener(myBtnClicker);
    }

    private void bindID() {
        editClientSend = (EditText) findViewById(R.id.editcommand);
        btnClientSend = (Button) findViewById(R.id.btn_option);
        btnCom = (Button)findViewById(R.id.btn_command);
        btnClientCon  = (Button) findViewById(R.id.btn_tcpCon);
        btnClientDisCon = (Button) findViewById(R.id.btn_tcpClo);
        check = (Button)findViewById(R.id.code_view2);
        horncode = (TextView) findViewById(R.id.hornview);
    }
}