package com.example.acer.foodmonsters2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {
    public static ArrayList<IngredientPojo> widgetPojos = new ArrayList<>();
    JsonFood jsonFood;
    ArrayList<IngredientPojo> ingredientPojos = new ArrayList<>();
    ArrayList<StepsPojo> stepsPojos = new ArrayList<>();
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    public static ArrayList<IngredientPojo> getIngredients() {
        return widgetPojos;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String ingredients;
        JSONArray jsonArray = null;
        String steps;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        Intent intent = getIntent();
        Toast.makeText(getApplicationContext(), "click on fab to add widget", Toast.LENGTH_SHORT).show();
        ingredients = intent.getStringExtra("ingredients");
        steps = intent.getStringExtra("steps");
        try {
            jsonArray = new JSONArray(ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                double quantity = jsonObject.optDouble("quantity");
                String measure = jsonObject.optString("measure");
                String ingredientName = jsonObject.optString("ingredient");
                IngredientPojo ingredientPojo = new IngredientPojo(quantity, measure, ingredientName);
                ingredientPojos.add(ingredientPojo);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            jsonArray = new JSONArray(steps);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.optInt("id");
                String shortDescription = jsonObject.optString("shortDescription");
                String description = jsonObject.optString("description");
                String videourl = jsonObject.optString("videoURL");
                String thumbnailurl = jsonObject.optString("thumbnailURL");
                StepsPojo stepsPojo = new StepsPojo(id, shortDescription, description, videourl, thumbnailurl);
                stepsPojos.add(stepsPojo);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                widgetPojos = new ArrayList<IngredientPojo>();
                Toast.makeText(getApplicationContext(), "recepie added to widwget", Toast.LENGTH_SHORT).show();
                widgetPojos = ingredientPojos;
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, ingredientPojos, stepsPojos, mTwoPane));
        recyclerView.setFocusable(false);
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
        private final ArrayList<IngredientPojo> ingredientPojoList;
        private final ArrayList<StepsPojo> stepsPojoList;
        private final ItemListActivity mParentActivity;
        private final boolean mTwoPane;
        /*private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DummyContent.DummyItem item = (DummyContent.DummyItem) view.getTag();
                if (mTwoPane) {0
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);
                }
            }
        };*/

        SimpleItemRecyclerViewAdapter(ItemListActivity parent, ArrayList<IngredientPojo> ingredientPojos, ArrayList<StepsPojo> stepsPojos
                , boolean twoPane) {
            this.ingredientPojoList = ingredientPojos;
            this.stepsPojoList = stepsPojos;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            if (position == 0) {
                holder.mIdView.setVisibility(View.GONE);
                holder.mContentView.setText("Receipe Ingredients");
                holder.mContentView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mTwoPane) {
                            Bundle arguments = new Bundle();
                            arguments.putParcelableArrayList("ingredients", ingredientPojoList);
                            ItemDetailFragment fragment = new ItemDetailFragment();
                            fragment.setArguments(arguments);
                            mParentActivity.getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.item_detail_container, fragment)
                                    .commit();
                        } else {
                            Context context = v.getContext();
                            Intent intent = new Intent(context, ItemDetailActivity.class);
                            intent.putExtra("ingredients", ingredientPojoList);
                            context.startActivity(intent);
                        }
                    }
                });

            } else {
                holder.mIdView.setText("" + stepsPojoList.get(position - 1).getId());
                holder.mContentView.setText(stepsPojoList.get(position - 1).getShortdescription());
                //holder.itemView.setOnClickListener(mOnClickListener);
                holder.mContentView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mTwoPane) {
                            Bundle arguments = new Bundle();
                            arguments.putParcelableArrayList("widget", ingredientPojoList);
                            arguments.putString("description", stepsPojoList.get(position - 1).getDescription());
                            arguments.putString("video", stepsPojoList.get(position - 1).getVideourl());
                            arguments.putString("thumbnail", stepsPojoList.get(position - 1).getThumbnailurl());
                            ItemDetailFragment fragment = new ItemDetailFragment();
                            fragment.setArguments(arguments);
                            mParentActivity.getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.item_detail_container, fragment)
                                    .commit();
                        } else {
                            Context context = v.getContext();
                            Intent intent = new Intent(context, ItemDetailActivity.class);
                            Bundle bundle = new Bundle();
                            intent.putExtra("video", stepsPojoList.get(position - 1).getVideourl());
                            intent.putExtra("widget", ingredientPojoList);
                            intent.putExtra("description", stepsPojoList.get(position - 1).getDescription());
                            intent.putExtra("thumbnail", stepsPojoList.get(position - 1).getThumbnailurl());
                            context.startActivity(intent);
                        }
                    }
                });

            }
        }

        @Override
        public int getItemCount() {
            return stepsPojoList.size() + 1;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mIdView;
            final TextView mContentView;

            ViewHolder(View view) {
                super(view);
                mIdView = (TextView) view.findViewById(R.id.id_text);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}
