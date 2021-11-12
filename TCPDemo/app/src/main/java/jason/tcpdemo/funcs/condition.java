package jason.tcpdemo.funcs;

import android.annotation.SuppressLint;
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


public class condition extends optionfinal {
    private Button btnClientSend, btnCom, btnClientCon, btnClientDisCon, check;
    private EditText editClientSend;
    private TextView Condcode;
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
                    intent.setClass(condition.this, commandata.class);
                    startActivity(intent);
                    break;

                case R.id.btn_option:
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

                case R.id.code_view:
                    if ("forward".equals(editClientSend.getText().toString()))
                        Condcode.setText("if(輸入指令 == \"forward\") {\n" + "digitalWrite(IA, HIGH);\n" + "digitalWrite(IB, LOW);\n" + "digitalWrite(IA1, HIGH);\n" + "digitalWrite(IB1, LOW); }");

                    else if (editClientSend.getText().toString().equals("backward"))
                        Condcode.setText("if(輸入指令 == \"backward\") {\n" + "digitalWrite(IA, LOW);\n" + "digitalWrite(IB, HIGH);\n" + "digitalWrite(IA1, LOW);\n" + "digitalWrite(IB1, HIGH); }");

                    else if (editClientSend.getText().toString().equals("stop"))
                        Condcode.setText("if(輸入指令 == \"stop\") {\n" + "digitalWrite(IA, LOW);\n" + "digitalWrite(IB, LOW);\n" + "digitalWrite(IA1, LOW);\n" + "digitalWrite(IB1, LOW); }");

                    else if (editClientSend.getText().toString().equals("left"))
                        Condcode.setText("if(輸入指令 == \"left\") {\n" + "digitalWrite(IA, HIGH);\n" + "digitalWrite(IB, LOW);\n" + "digitalWrite(IA1, LOW);\n" + "digitalWrite(IB1, LOW); }");

                    else if(editClientSend.getText().toString().equals("right"))
                        Condcode.setText("if(輸入指令 == \"right\") {\n" + "digitalWrite(IA, LOW);\n" + "digitalWrite(IB, LOW);\n" + "digitalWrite(IA1, HIGH);\n" + "digitalWrite(IB1, LOW); }");

                    else
                       Condcode.setText("查無指令");
                    break;
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.condition);
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
        Condcode = (TextView) findViewById(R.id.CondText);
        check = (Button)findViewById(R.id.code_view);
    }

}