package com.foysaldev.leduchoang_2050531200150_bt10_ketnoifirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    CongAnAdapter musicAdapter;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new GridLayoutManager(HomeActivity.this,2));

        FirebaseRecyclerOptions<CongAn> options =
                new FirebaseRecyclerOptions.Builder<CongAn>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Student"), CongAn.class)
                        .build();

        musicAdapter = new CongAnAdapter(options);
        recyclerView.setAdapter(musicAdapter);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        musicAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        musicAdapter.stopListening();
    }

    private void txtSearch(String str) {
        FirebaseRecyclerOptions<CongAn> options =
                new FirebaseRecyclerOptions.Builder<CongAn>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Student").orderByChild("name").startAt(str).endAt(str+"~"), CongAn.class)
                        .build();

        musicAdapter = new CongAnAdapter(options);
        musicAdapter.startListening();
        recyclerView.setAdapter(musicAdapter);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.search,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                txtSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                txtSearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}