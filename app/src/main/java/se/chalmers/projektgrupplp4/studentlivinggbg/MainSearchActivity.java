package se.chalmers.projektgrupplp4.studentlivinggbg;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import java.util.ArrayList;
import se.chalmers.projektgrupplp4.studentlivinggbg.Controller.MainController;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.Accommodation;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.ImageModel;
import se.chalmers.projektgrupplp4.studentlivinggbg.Model.MainModel;

import static android.content.Intent.FLAG_ACTIVITY_NO_ANIMATION;

public class MainSearchActivity extends AppCompatActivity {

    private SearchView searchView;
    ListView listView;
    private AccommodationListViewAdapter adapter;
    private AccommodationRecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView;
    private Paint p = new Paint();

    private SearchView.OnClickListener onClickListenerSearch = new SearchView.OnClickListener() {
        @Override
        public void onClick(View view) {
            //switch (view.getId()) {
                //case R.id.searchField:
                    searchView.onActionViewExpanded();
             //       break;
            //}
        }
    };

    //Advanced search button
    private ImageButton.OnClickListener onClickListener = new ImageButton.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainSearchActivity.this, AdvancedSearchActivity.class);
            startActivity(intent);
        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    return true;
                case R.id.navigation_favorites:
                    Intent favorites = new Intent(MainSearchActivity.this, FavoritesActivity.class);
                    favorites.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(favorites);
                    return true;
                case R.id.navigation_notifications:
                    Intent searchWatcher = new Intent(MainSearchActivity.this, SearchWatcherActivity.class);
                    searchWatcher.addFlags(FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(searchWatcher);
                    return true;
                case R.id.navigation_settings:
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new MainController(getApplicationContext());
        setContentView(R.layout.activity_main_search);

        //adapter= new AccommodationListViewAdapter(new ArrayList<Accommodation>(),getApplicationContext());


        recyclerViewAdapter = new AccommodationRecyclerViewAdapter(MainModel.getInstance().getAccommodations(), getApplicationContext());

        recyclerView = (RecyclerView) findViewById(R.id.list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        initSwipe();
        recyclerView.setAdapter(recyclerViewAdapter);

        /*recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Accommodation dataModel= MainModel.getInstance().getAccommodations().get(position);

                Context context = view.getContext();
                Intent intent = new Intent(context, ObjectActivity.class);
                //intent.putExtra("ARG_POSITION", position);

                startActivity(intent);
            }
        });*/

        //listView=(ListView)findViewById(R.id.list);
        //listView.setAdapter(adapter);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Accommodation dataModel= MainModel.getInstance().getAccommodations().get(position);

                Context context = view.getContext();
                Intent intent = new Intent(context, ObjectActivity.class);
                intent.putExtra("ARG_POSITION", position);

                startActivity(intent);

                //When tapping on a household object
                Snackbar.make(view, dataModel.getAddress(), Snackbar.LENGTH_LONG).setAction("No action", null).show();
            }
        });*/

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ImageButton advancedSearch = (ImageButton) findViewById(R.id.advancedSearch);
        advancedSearch.setOnClickListener(onClickListener);
        searchView = (SearchView) findViewById(R.id.searchField);
        searchView.setOnClickListener(onClickListenerSearch);
        try {
            /*
            Wanted to use observer pattern but: "Only the original thread that created a view
            hierarchy can touch its views" And loading/creating the database seems to require
            threading.
            */

            MainModel.dbThread.join();
            displaySearch();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT){
                    recyclerViewAdapter.removeFromFavorites(position);
                    recyclerViewAdapter.notifyItemChanged(viewHolder.getAdapterPosition());

                } else { //right?
                    recyclerViewAdapter.addToFavorites(position);
                    recyclerViewAdapter.notifyItemChanged(viewHolder.getAdapterPosition());
                    //removeView();
                    //edit_position = position;
                    //alertDialog.setTitle("Edit Country");
                    //et_country.setText(countries.get(position));
                    //alertDialog.show();
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){

                    AccommodationRecyclerViewHolder accommodation = (AccommodationRecyclerViewHolder) viewHolder;

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if(dX > 0 && !accommodation.isFavorite()) {
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_edit_white);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    } else if (accommodation.isFavorite()) {
                        p.setColor(Color.parseColor("#D32F2F"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background,p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_delete_white);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
                        c.drawBitmap(icon,null,icon_dest,p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void displaySearch() {
        //adapter.clear();
        //adapter.addAll(MainModel.getInstance().getAccommodations());
        recyclerViewAdapter.clear();
        recyclerViewAdapter.addAll(MainModel.getInstance().getAccommodations());

    }

    @Override
    protected void onPause() {
        MainModel.getInstance().save();
        Db4oDatabase.getInstance().close();
        super.onPause();
    }

}
