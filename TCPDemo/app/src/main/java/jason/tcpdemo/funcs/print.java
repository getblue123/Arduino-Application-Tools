package jason.tcpdemo.funcs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jason.tcpdemo.R;
import jason.tcpdemo.coms.TcpClient;

public class print extends Activity {

    private EditText editClientSend, editip;
    private Button btnClientSend, btnClientCon, btnClientDisCon;
    private TextView printtext;
    private static TcpClient tcpClient = null;
    private MyBtnClicker myBtnClicker = new MyBtnClicker();
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
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.printf);
        context = this;
        bindID();
        bindListener();
        Ini();
        initListener();
    }

    private void bindListener() {
        btnClientSend.setOnClickListener(myBtnClicker);
        btnClientCon.setOnClickListener(myBtnClicker);
        btnClientDisCon.setOnClickListener(myBtnClicker);
    }

    private void bindID() {
        editClientSend = (EditText) findViewById(R.id.printext);
        btnClientSend = (Button) findViewById(R.id.btn_clientSend);
        btnClientCon  = (Button) findViewById(R.id.btn_tcpCon);
        btnClientDisCon = (Button) findViewById(R.id.btn_tcpClo);
        printtext = (TextView)findViewById(R.id.PrintView);
        editip = (EditText) findViewById(R.id.Server_Ip);
    }
    private void Ini(){
        btnClientDisCon.setEnabled(false);
        btnClientSend.setEnabled(false);
    }

    private void initListener() {
        editClientSend.addTextChangedListener(new MyTextWatcher());
    }

    private class MyTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
                printtext.setText("lcd.print(\"" + editClientSend.getText() + "\")");
        }
    }
}

