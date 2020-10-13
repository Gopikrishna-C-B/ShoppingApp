package com.gpk.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ShopSelectionListener{

       private RecyclerView recyclerView;
       private Toolbar toolbar;
       private ShopAdapter shopAdapter;
      private List<String> shopsList;

       private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shopsList = new ArrayList<>();
        shopsList.add("ALLEN SOLLY");
        shopsList.add("BUFFALO");
        shopsList.add("DENIM");
        shopsList.add("FASHION");
        shopsList.add("GUCCI");
        shopsList.add("HIGHLANDER");
        shopsList.add("JOCKEY");
        shopsList.add("LEVIS");
        shopsList.add("MUFTI");
        shopsList.add("NIKE");
        shopsList.add("PUMA");
        shopsList.add("REEBOK");
        shopsList.add("SUPERDRY");
        shopsList.add("TOMMY HILFIGER");
        shopsList.add("US POLO");
        shopsList.add("VAN HEUSEN");
        shopsList.add("WRANGLER");
        shopsList.add("XLAM");
        shopsList.add("YUV");
        shopsList.add("ZEMBO");




        recyclerView = findViewById(R.id.shop_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);


        shopAdapter = new ShopAdapter(shopsList, this);
        recyclerView.setAdapter(shopAdapter);

        toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");

    }


    @Override
    public void onShopSelected(String shopName)
    {

        Intent intent = new Intent(this,ShopActivity.class);
        intent.putExtra("shops",shopName);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                shopAdapter.getFilter().filter(newText);



                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }




}