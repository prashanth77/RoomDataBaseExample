package com.winitsolutions.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Actvity_addUSer extends AppCompatActivity {
EditText edit_firstname,edit_lastname,edit_college,edit_phonenumber;
Button btn_add;

    public static final String first_name="first_name";
    public static final String last_name="last_name";
    public static final String college="college";
    public static final String phonenumber="phone_number";
    public static final String id="id";
   public static  final String type="type";


   int datatype;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        edit_firstname=findViewById(R.id.edit_first_name);
        edit_lastname=findViewById(R.id.edit_last_name);
        edit_college=findViewById(R.id.edit_text_college);
        edit_phonenumber=findViewById(R.id.edit_phone_number);
        btn_add=findViewById(R.id.btn_add);

        Bundle bundle=getIntent().getExtras();


        datatype=bundle.getInt(Actvity_addUSer.type);
        if(datatype==1){
            btn_add.setText("Add user");

        }
        else {
            btn_add.setText("update user");
            User user= (User) getIntent().getSerializableExtra("user");
            edit_firstname.setText(user.getFirstname());
            edit_lastname.setText(user.getLastname());
            edit_college.setText(user.getCollege());
            edit_phonenumber.setText(user.getPhone_numbe());


        }

     ////   first_name1=edit_firstname.getText().toString();
       // last_name1=edit_lastname.getText().toString();
       // college1=edit_college.getText().toString();
         //phone_number1=edit_phonenumber.getText().toString();


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (datatype==2){

                    User user1=new User(edit_firstname.getText().toString(),edit_lastname.getText().toString(),edit_college.getText().toString(),edit_phonenumber.getText().toString());
                    UserDataBase.getmInstance(getApplicationContext()).userDeo().update(user1);
                }

                if(datatype==1){
                    saveUser(datatype);
                }

            }
        });


    }


    private void saveUser(int datatype){
        Intent intent=new Intent();
        intent.putExtra(Actvity_addUSer.first_name, edit_firstname.getText().toString());
        intent.putExtra(Actvity_addUSer.last_name, edit_lastname.getText().toString());
        intent.putExtra(Actvity_addUSer.college, edit_college.getText().toString());
        intent.putExtra(Actvity_addUSer.phonenumber, edit_phonenumber.getText().toString());
        intent.putExtra(Actvity_addUSer.type, datatype);
        Log.e("dsdsadas", "number        "+edit_phonenumber.getText().toString());

        setResult(RESULT_OK, intent);
        finish();

    }


}
