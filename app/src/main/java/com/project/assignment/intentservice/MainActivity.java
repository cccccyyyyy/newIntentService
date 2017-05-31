package com.project.assignment.intentservice;

        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Handler;
        import android.os.Message;
        import android.os.Messenger;
        import android.support.v7.app.ActionBarActivity;
        import android.view.View;
        import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
    private Handler handler = new Handler() {
        public void handleMessage(Message message) {
            Object path = message.obj;
            if (message.arg1 == RESULT_OK && path !=null) {
                Toast.makeText(getApplicationContext(),
                        " " + path.toString() + "을 다운로드하였음", Toast.LENGTH_LONG)
                        .show();
            } else {
                Toast.makeText(getApplicationContext(),"다운로드 실패",
                        Toast.LENGTH_LONG).show();
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, DownloadService.class);
        Messenger messenger = new Messenger(handler);
        intent.putExtra("MESSENGER", messenger);
        intent.setData(Uri.parse("http://www.naver.com/"));
        intent.putExtra("urlpath", "http://www.naver.com/");
        startService(intent);
    }
}
