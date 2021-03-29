package com.example.searchviewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    List<FoodModel> foodModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);

        foodModelList=new ArrayList<>();

        foodModelList.add(new FoodModel("Hamburger",R.drawable.hamburger));
        foodModelList.add(new FoodModel("Pizza",R.drawable.pizza));
        foodModelList.add(new FoodModel("Pasta",R.drawable.pasta));
        foodModelList.add(new FoodModel("Orange Juice",R.drawable.juice));
        foodModelList.add(new FoodModel("Apple Juice",R.drawable.apple_juice));
        foodModelList.add(new FoodModel("Cold Coffee",R.drawable.coffee));
        foodModelList.add(new FoodModel("Hot Coffee",R.drawable.hot_coffee));
        foodModelList.add(new FoodModel("Chocolate",R.drawable.chocolate));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdapter=new RecyclerViewAdapter(foodModelList,MainActivity.this);

        recyclerView.setAdapter(recyclerViewAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);

        MenuItem searchItem=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerViewAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}