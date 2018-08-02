package com.example.acer.foodmonsters2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link ItemListActivity}.
 */
public class ItemDetailActivity extends AppCompatActivity {

    JsonFood jsonFood;
    ArrayList<IngredientPojo> ingredientPojos;
    String videoURL, thumbnailURL, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            Bundle bundle = getIntent().getExtras();
            if (bundle.containsKey("ingredients")) {
                ingredientPojos = bundle.getParcelableArrayList("ingredients");
                Bundle bundle1 = new Bundle();
                bundle1.putParcelableArrayList("ingredients", ingredientPojos);
            }
            if (bundle.containsKey("video")) {
                Log.i("in", "act");
                description = bundle.getString("description");
                videoURL = bundle.getString("video");
                thumbnailURL = bundle.getString("thumbnail");
                Bundle bundle1 = new Bundle();
                bundle1.putString("description", description);
                bundle1.putString("video", videoURL);
                bundle1.putString("thumbnail", thumbnailURL);
            }

            ItemDetailFragment fragment = new ItemDetailFragment();
            fragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            NavUtils.navigateUpTo(this, new Intent(this, ItemListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
