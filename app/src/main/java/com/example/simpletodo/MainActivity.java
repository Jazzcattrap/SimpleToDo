package com.example.simpletodo;

import android.os.Bundle;
import android.os.FileUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> items;

    Button btnAdd;
    EditText etItem;
    RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        etItem = findViewById(R.id.etItem);
        rvItems = findViewById(R.id.rvItems);
        final ItemsAdapter itemsAdapter;

        final ArrayList<String> items = new ArrayList<>();
        items.add("Buy milk");
        items.add("Go to the store");
        items.add("Have fun!");


        ItemsAdapter.OnLongClickListener = new ItemsAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClicked(int positon) {
             //delete the item from the model
                items.remove(position);
             //notify the adapter
                itemsAdapter.notifyItemRemoved(position);

            }
        };
        }
        final ItemsAdapter itemsAdapter = new ItemsAdapter(items, onLongClickListener);

        rvItems.setAdapter(itemsAdapter);
        rvItems.setLayoutManager(new LinearLayoutManager( this));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itdoItem = etItem.getText().toString();
                //add item to the model
                String todoItem;
                items.add(todoItem);
                //notify adapter that an item is inserted
                itemsAdapter.notifyItemInserted(items.size() - 1);
                etItem.setText("");
                Toast.makeText(getApplicationContext(), "Item was added", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private File getDataFile() {
return new File(getFilesDir(), "data.txt");
    }
    //this function will load item by reading every line of the data file
    private void loadItems() {
items = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset());
    }catch (IOException e){
        Log.e("MainActivity", "Error reading items", e);
        items = new ArrayList<>();
        }
    //this function saves items by writing them into the data file
        private void saveItems(){
        FileUtils.writeLines(getDataFile(), items);
        Log.e("MainActivity", "Error writing item", e);
        }
}