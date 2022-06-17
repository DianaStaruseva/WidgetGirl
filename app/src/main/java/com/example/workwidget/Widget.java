package com.example.workwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Random;

public class Widget extends AppWidgetProvider {

    private static  final String SYNC_CLICKED = "WidgetImageClick";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        Random r = new Random();
        RemoteViews remoteViews;
        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
        ComponentName componentName;
        componentName = new ComponentName(context, Widget.class);
        remoteViews.setImageViewResource(R.id.imageView, setImage(r.nextInt(2)));
        remoteViews.setOnClickPendingIntent(R.id.imageView, getPendingSelfIntent(context, SYNC_CLICKED));
        appWidgetManager.updateAppWidget(componentName, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if(SYNC_CLICKED.equals(intent.getAction())){
            Random r = new Random();
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            RemoteViews remoteViews;
            ComponentName componentName;

            remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
            componentName = new ComponentName(context, Widget.class);
            remoteViews.setImageViewResource(R.id.imageView, setImage(r.nextInt(2)));

            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context,getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context,0,intent,0);
    }

    public int setImage(int num) {
        int image = 0;
        switch (num){
            case 0:
                image = R.drawable.img_smile;
                break;
            case 1:
                image = R.drawable.img_shock;
                break;
        }
        return image;
    }


}
