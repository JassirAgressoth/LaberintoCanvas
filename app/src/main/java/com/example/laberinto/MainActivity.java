package com.example.laberinto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{ //, SensorEventListener {

    //private ImageView ball;
    //private ImageView wall1;
    //private FrameLayout frame;

    private Drawable imageBola;
    private float ballX, ballY;
    private boolean up, down, left, right;

    private Timer timer=new Timer();
    private Handler handler= new Handler();
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Float sensorX;
    private Float sensorY;
    private Float sensorZ;
    private long lastSensorUpdateTime=0;

    private com.example.laberinto.Canvas canvas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sensorManager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //accelerometer= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        canvas = findViewById(R.id.canvas);
        canvas.bitmap=getBitmapFromVectorDrawable(this, R.drawable.bola);


        //ball=findViewById(R.id.ball);
        //wall1=findViewById(R.id.wall1);
        //frame=findViewById(R.id.frame);
        //imageBola= getResources().getDrawable(R.drawable.bola);

        //wall1.setX(-1);
        //wall1.setY(130);

        findViewById(R.id.arr).setOnTouchListener(this);
        findViewById(R.id.der).setOnTouchListener(this);
        findViewById(R.id.izq).setOnTouchListener(this);
        findViewById(R.id.aba).setOnTouchListener(this);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                        //if (sensorX<0){
                            //canvas.setCoordinate(new PointF(5,0));
                        //}else {
                       //    canvas.setCoordinate(new PointF(-5,0));
                       // }

                        //if (sensorY>0){
                        //    canvas.setCoordinate(new PointF(0,5));
                        //}else {
                        //    canvas.setCoordinate(new PointF(0f,-5));
                        //}
                    }
                });
            }
        },0,20);


        Bitmap imagen = getBitmapFromVectorDrawable(this, 2131165334);

    }
    public void changePos(){
        //ballX= ball.getX();
        //ballY=ball.getY();
        //arriba
        if (up) {
            canvas.setCoordinate(new PointF(0f,-20f));
            canvas.background(new PointF(0f,-20f),new PointF(400f,80f),new PointF(300f,350f),new PointF(600f,700f));
            canvas.setlimits(new PointF(0f, 0f));
            //ballY -=20;
        }

        //abajo
        if(down){
            canvas.setCoordinate(new PointF(0f,20f));
            canvas.background(new PointF(0f,-20f),new PointF(400f,80f),new PointF(300f,350f),new PointF(600f,700f));
            canvas.setlimits(new PointF(0f, 0f));
            //ballY +=20;
        }

        //Izquierda
        if (left) {
            canvas.setCoordinate(new PointF(-20f,0f));
            canvas.background(new PointF(0f,-20f),new PointF(400f,80f),new PointF(300f,350f),new PointF(600f,700f));
            canvas.setlimits(new PointF(0f, 0f));
            //ballX -= 20;
        }

        //Derecha
        if (right) {
            canvas.setCoordinate(new PointF(20f,0f));
            canvas.background(new PointF(0f,-20f),new PointF(400f,80f),new PointF(300f,350f),new PointF(600f,700f));
            canvas.setlimits(new PointF(0f, 0f));
            //ballX +=20;
        }


        //No salir de la pantalla vertical
        if(ballY < 0) ballY=0;
        //if (ballY> frame.getHeight()-ball.getHeight())ballY= frame.getHeight()-ball.getHeight();
        // No salir de la pantalla horizonal
        if (ballX <0) ballX=0;
        //if (ballX > frame.getWidth()-ball.getWidth())ballX=frame.getWidth()-ball.getWidth();
        //No chocar en pared1
        if (ballY > 125)ballY=125;



        //ball.setX(ballX);
        //ball.setY(ballY);




    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction()== MotionEvent.ACTION_DOWN){
            switch(view.getId()){
                case R.id.arr:
                    up=true;
                    break;
                case  R.id.aba:
                    down=true;
                    break;
                case R.id.izq:
                    left=true;
                    break;
                case R.id.der:
                    right=true;
                    break;

            }
        }else{
            up=false;
            down=false;
            left=false;
            right=false;

        }
        return true;
    }

    private Bitmap getBitmapFromVectorDrawable(
            Context context,
            int drawableId
    ) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888
        );
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    //@Override
    //public void onSensorChanged(SensorEvent sensorEvent) {
      //  Sensor mySensor= sensorEvent.sensor;
        //if(mySensor.getType()== Sensor.TYPE_ACCELEROMETER){

          //  long currentTime= System.currentTimeMillis();
            //if((currentTime - lastSensorUpdateTime)>100){
              //  lastSensorUpdateTime=currentTime;
                //sensorX=sensorEvent.values[0];
                //sensorY=sensorEvent.values[1];
                //sensorZ=sensorEvent.values[2];
            //}
        //}
    //}

    //@Override
    //public void onAccuracyChanged(Sensor sensor, int accuracy) {

    //}
}