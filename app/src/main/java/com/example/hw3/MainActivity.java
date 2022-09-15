package com.example.hw3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {


    // 주사위 값을 저장하는 Queue 생성
    Queue<Integer> queue = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        final ImageView imageView = (ImageView)findViewById(R.id.dice);
        final TextView textView = (TextView)findViewById(R.id.textView2);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                // 난수를 생성하여 주사위 값 결정
                Random random = new Random();
                int value = random.nextInt(5) + 1;
                // 큐에 최근 기록 저장 3개 넘어가면 pop
                queue.add(value);
                if(queue.size() > 3)
                    queue.poll();

                // 토스트 메시지 출력
                Toast.makeText(getApplicationContext(), "주사위를 굴렸습니다!", Toast.LENGTH_SHORT).show();

                // 주사위 이미지 변경
                changeImage(value,imageView);

                // 최근 나온 주사위 값을 알려주는 텍스트 변경
                changeText(textView, queue);
            }
        });
    }

    // value값에 따라 dice의 이미지를 바꾸는 메소드
    public static void changeImage(int n, ImageView imageView){

        if(n == 1)
            imageView.setImageResource(R.drawable.dice1);
        if(n == 2)
            imageView.setImageResource(R.drawable.dice2);
        if(n == 3)
            imageView.setImageResource(R.drawable.dice3);
        if(n == 4)
            imageView.setImageResource(R.drawable.dice4);
        if(n == 5)
            imageView.setImageResource(R.drawable.dice5);
        if(n == 6)
            imageView.setImageResource(R.drawable.dice6);

    }

    // 텍스트뷰의 텍스트를 바꾸는 메소드
    public static void changeText(TextView textView, Queue<Integer> queue){
        String s1 = "최근 나온 주사위 값:";
        String s2 = " ";

        int size = queue.size();

        for(int i = 0; i < size; i++){
            int num = queue.poll();
            s2 += num + " ";
            queue.add(num);
        }

        textView.setText(s1+s2);
    }
}