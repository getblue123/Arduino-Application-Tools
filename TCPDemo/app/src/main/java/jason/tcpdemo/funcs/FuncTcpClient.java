package jason.tcpdemo.funcs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jason.tcpdemo.R;
import jason.tcpdemo.coms.TcpClient;


public class FuncTcpClient extends Activity {
    private String TAG = "FuncTcpClient";
    @SuppressLint("StaticFieldLeak")
    public static Context context ;
    private Button btnStartClient,btnCloseClient,btnClientSend;
    private EditText editClientSend;
    private static TcpClient tcpClient = null;
    private MyBtnClicker myBtnClicker = new MyBtnClicker();
    ExecutorService exec = Executors.newCachedThreadPool();

    private class MyBtnClicker implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_tcpClientConn:
                    Log.i(TAG, "onClick: 開始");
                    btnStartClient.setEnabled(false);
                    btnCloseClient.setEnabled(true);
                    btnClientSend.setEnabled(true);
                    //tcpClient = new TcpClient();
                    exec.execute(tcpClient);
                    break;

                case R.id.btn_tcpClientClose:
                    tcpClient.closeSelf();
                    btnStartClient.setEnabled(true);
                    btnCloseClient.setEnabled(false);
                    btnClientSend.setEnabled(false);
                    break;

                case R.id.btn_tcpClientSend:
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
        setContentView(R.layout.tcp_client);
        context = this;
        bindID();
        bindListener();
        Ini();
    }



    private void bindID(){
        btnStartClient = (Button) findViewById(R.id.btn_tcpClientConn);
        btnCloseClient = (Button) findViewById(R.id.btn_tcpClientClose);
        btnClientSend = (Button) findViewById(R.id.btn_tcpClientSend);
        editClientSend = (EditText) findViewById(R.id.edit_tcpClientSend);
    }
    private void bindListener(){
        btnStartClient.setOnClickListener(myBtnClicker);
        btnCloseClient.setOnClickListener(myBtnClicker);
        btnClientSend.setOnClickListener(myBtnClicker);
    }

    private void Ini(){
        btnCloseClient.setEnabled(false);
        btnClientSend.setEnabled(false);

    }
}
