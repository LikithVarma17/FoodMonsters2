package com.example.acer.foodmonsters2;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class Food_Widget extends AppWidgetProvider {
    public static String text = "";
    private static String s;


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        ArrayList<IngredientPojo> ingredientPojos = new ArrayList<IngredientPojo>();
        ingredientPojos = ItemListActivity.getIngredients();
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.food__widget);
        if (!ingredientPojos.isEmpty()) {
            text = "";
            Log.i("ing", "" + ingredientPojos);
            String name, quantity, measure;
            for (int i = 0; i < ingredientPojos.size(); i++) {
                name = ingredientPojos.get(i).getIngredient();
                quantity = "" + ingredientPojos.get(i).getQuantity();
                measure = ingredientPojos.get(i).getMeasure();
                text = text + name + "\t" + quantity + "\t" + measure + "\n";
            }
            Log.i("text", text);
            remoteViews.setTextViewText(R.id.appwidget_text, text);
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

        } else {
            Log.i("ing", "null");
            remoteViews.setTextViewText(R.id.appwidget_text, "Select an item to display ingredients");
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
        // Instruct the widget manager to update the widget
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

