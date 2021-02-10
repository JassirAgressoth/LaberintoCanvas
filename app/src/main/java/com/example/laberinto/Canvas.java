package com.example.laberinto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;

import java.util.Vector;

public class Canvas extends View {
    Paint paint = new Paint();
    Paint paint1=new Paint();
    Paint paint2=new Paint();
    Paint paint3=new Paint();
    Paint paint4= new Paint();
    PointF point = new PointF(100f, 90f);
    Bitmap bitmap;
    PointF points[] = {new PointF(300f, 400f), new PointF(700f, 400f), new PointF(500f, 1400f)};
    Path path= new Path();


    public Canvas(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint1.setColor(Color.RED);
        paint1.setStyle(Paint.Style.FILL);
        paint2.setColor(Color.BLUE);
        paint2.setStyle(Paint.Style.FILL);
        paint3.setColor(Color.GRAY);
        paint3.setStyle(Paint.Style.FILL);
        paint4.setColor(Color.BLACK);
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setStrokeWidth(3f);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20f);

        this.getHeight();
        this.getWidth();

        //path.moveTo(points[0].x, points[0].y);
        //for(int i=1; i < points.length; i++){
            //path.lineTo(points[i].x, points[i].y);
        //}
    }


    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0f,200f,900f,200f, paint);
        canvas.drawLine(1100f,450f,200f,450f, paint);
        canvas.drawLine(450f,450f,450f,650f, paint);
        canvas.drawLine(0f,900f,900f,900f, paint);
        canvas.drawLine(700f,900f,700f,700f, paint);
        canvas.drawLine(900f,900f,900f,1200f, paint);
        canvas.drawCircle(400f, 80f,30,paint1);
        canvas.drawCircle(300f, 350f,40,paint2);
        canvas.drawCircle(600f, 700f,35,paint3);


        canvas.drawBitmap(bitmap,point.x-60.5f, point.y-60.5f, new Paint());
        canvas.drawPath(path,paint);
        canvas.drawCircle(point.x, point.y, 60, paint4);
    }

    public void setCoordinate(PointF factorCoor) {
        point.x += factorCoor.x;
        point.y += factorCoor.y;
        invalidate();
    }
    public void background(PointF pointfirst, PointF pointsecond, PointF t,PointF c){
        pointfirst.x=point.x;
        pointfirst.y=point.y;
        pointsecond.x=400f;
        pointsecond.y=80f;
        t.x=300f;
        t.y=350f;
        c.x=600f;
        c.y=700f;
        //Magnitud rojo
        double magnitud=Math.sqrt(Math.pow(pointsecond.x-pointfirst.x,2)+Math.pow(pointsecond.y-pointfirst.y,2));
        if(magnitud<60+30){
            this.setBackgroundColor(Color.RED);
        }

        double magnitud2=Math.sqrt(Math.pow(t.x-pointfirst.x,2)+Math.pow(t.y-pointfirst.y,2));
        if(magnitud2<60+40) {
            this.setBackgroundColor(Color.BLUE);
        }
        double magnitud3=Math.sqrt(Math.pow(c.x-pointfirst.x,2)+Math.pow(c.y-pointfirst.y,2));
        if(magnitud3<60+35) {
            this.setBackgroundColor(Color.GRAY);
        }
       invalidate();
    }
    public void setlimits(PointF circ){
        circ.x=point.x;
        circ.y=point.y;
        //Magnitud de la línea
        double linemagni= Math.sqrt(Math.pow(900f-0f,2)+Math.pow(200f-200f,2));
        //Distancia circulo con linea
        double dot=(((circ.x-0f)*(900f-0f))+((circ.y-200f)*(200f-200f)))/Math.pow(linemagni,2);
        //Punto más cercano en la linea
        double closeX=0f+(dot*(900f-0f));
        double closeY=200f+(dot*(200f-200f));
        double buffer=0.1;





        double distX=closeX-circ.x;
        double distY=closeY-circ.y;
        double distance= Math.sqrt(Math.pow(distX,2)+Math.pow(distY,2));
        if (distX+distY>=linemagni-buffer && distX+distY<= linemagni+buffer){
            return;
        }
        if(distance<=60){
            circ.x=0f;
            circ.y=0f;
        }
        invalidate();
    }

}
