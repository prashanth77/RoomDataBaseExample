package com.winitsolutions.todolist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements onItemClick {

    FloatingActionButton fab1;
    public static final int ADD_NOTE_REQUEST = 1;
    RecyclerView recyclerView;


    Button btn_delete;

    userAdapter adapter;
    List<User> userList = new ArrayList<>();

    //List<User> userData=new ArrayList<>()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fab1 = findViewById(R.id.fab1);

        recyclerView = findViewById(R.id.recyclarview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent=new Intent(MainActivity.this, Actvity_addUSer.classs);

                Intent intent = new Intent(MainActivity.this, Actvity_addUSer.class);
                intent.putExtra(Actvity_addUSer.type, 1);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

        adapaterData();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            int type=data.getIntExtra(Actvity_addUSer.type, 1);

            String first_name = data.getStringExtra(Actvity_addUSer.first_name);
            String last_name = data.getStringExtra(Actvity_addUSer.last_name);
            String college = data.getStringExtra(Actvity_addUSer.college);
            String phonenumber = data.getStringExtra(Actvity_addUSer.phonenumber);
            User user = new User(first_name, last_name, college, phonenumber);
                UserDataBase.getmInstance(getApplicationContext()).userDeo().insert(user);
                adapaterData();
                Toast.makeText(this, "Added saved", Toast.LENGTH_SHORT).show();

            }
        }







    @Override
    public void itemDeleteClickListener(User user, int pos) {
//User user=new User();


        userList.remove(pos);
        adapter.notifyDataSetChanged();
        UserDataBase.getmInstance(getApplicationContext()).userDeo().delete(user);


    }

    @Override
    public void itemUpdateListenr(User user, int position) {
//     UserDataBase.getmInstance(getApplicationContext()).userDeo().update(user);

        Intent intent = new Intent(MainActivity.this, Actvity_addUSer.class);


        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);
        bundle.putInt(Actvity_addUSer.type, 2);
        intent.putExtras(bundle);
        startActivity(intent);


    }

    public void adapaterData() {
        userList = UserDataBase.getmInstance(getApplicationContext()).userDeo().getAllUser();
        adapter = new userAdapter(userList, this, this);
        recyclerView.setAdapter(adapter);
    }
}
