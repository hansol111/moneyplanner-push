package com.example.example3;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;

public class MainActivity extends Activity {
    private int balance = 5000; // 초기 밸런스가 5000이라고 선언(지역변수)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        final int limit = intent.getIntExtra("limit",0);


        final TextView balanceTextView = findViewById(R.id.balanceTextView);// 밸런스뷰 선언
        //Log.d("mainBalance", balanceTextView.getText().toString());
        balanceTextView.setText(Integer.toString(balance));//인티져를 스트링으로 변환(값을 받아준다) 그 후 밸런스 값 보여준다

        final Button minusButton = findViewById(R.id.minusButton);// 마이너스 버튼 선언
        final Button resetButton = findViewById(R.id.resetButton);// 리셋 버튼 선언
        Button alarmButton = findViewById(R.id.alarmbutton);
        alarmButton.setOnClickListener(new Button.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Intent intent = new Intent(MainActivity.this, SubActivity.class);
                                               startActivity(intent);
                                           }
                                       });

                minusButton.setOnClickListener(new Button.OnClickListener()// 마이너스 버튼을 눌렀다면?
                {
                    @Override
                    public void onClick(View v) //클릭 했을 때 함수
                    {
                        Log.d("minusButton", "click"); //테스트 용도(창에 띄우기)
                        balance -= 2000; // 클릭때마다 밸런스 - 2000
                        balanceTextView.setText(Integer.toString(balance));//새로 바뀐 밸런스 보여주기
                        if (balance <= limit) // 만약 밸런스가 2000보다 작다면
                        {
                            Log.d("MainActivity", "limit"); // 리미트 표시
                            createNotification();
                        }
                    }
                });

                resetButton.setOnClickListener(new View.OnClickListener()// 리셋 버튼을 눌렀을때
                {
                    @Override
                    public void onClick(View v) // 눌렀을때
                    {
                        balance = 5000; //밸런스 5000
                        balanceTextView.setText(Integer.toString(balance));// 새로 바뀐 밸런스 보여주기
                    }
                });
     /*   minusButton.setOnClickListener(new Button.OnClickListener()// 마이너스 버튼을 눌렀다면?
        {
            @Override
            public void onClick(View v) //클릭 했을 때 함수
            {
                Log.d("minusButton", "click"); //테스트 용도(창에 띄우기)
                balance -= 2000; // 클릭때마다 밸런스 - 2000
                balanceTextView.setText(Integer.toString(balance));//새로 바뀐 밸런스 보여주기
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener()// 리셋 버튼을 눌렀을때
        {
            @Override
            public void onClick(View v) // 눌렀을때
            {
                balance = 5000; //밸런스 5000
                balanceTextView.setText(Integer.toString(balance));// 새로 바뀐 밸런스 보여주기
            }
        });*/
    }

    public void createNotification() {
        show();
    }

    private void show() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("Money Planner");
        builder.setContentText("돈 얼마 없음");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pendingIntent);

        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(largeIcon);

        builder.setColor(Color.RED);

        /*long[] vibrate = {0, 100, 200, 300};
        builder.setVibrate(vibrate);
        builder.setAutoCancel(true);*/ //진동

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }
        manager.notify(1, builder.build());
    }

   /* public void removeNotification() {
        hide();
    }

    private void hide() {
        NotificationManagerCompat.from(this).cancel(1);
    }*/
}
