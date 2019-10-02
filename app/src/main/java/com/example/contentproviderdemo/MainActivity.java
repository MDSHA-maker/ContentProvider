package com.example.contentproviderdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button showcontact;
    EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showcontact=(Button)findViewById(R.id.button);
        name=(EditText)findViewById(R.id.edit_query);
        showcontact.setOnClickListener(new View.OnClickListener() {
          //  @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String projection []={ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone.NUMBER};
                String selection=ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+"=?";
                String selectionarg[]={name.getText().toString()};
                Cursor cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,projection,selection,selectionarg,null);//projection is no of colmn
                if(cursor!=null&&cursor.getCount()>0)
                {
                        StringBuffer buffer=new StringBuffer();
                        while(cursor.moveToNext())
                        {
                            buffer.append("Name"+cursor.getString(0)+"\n");
                            buffer.append("Phone No"+cursor.getString(1)+"\n\n");

                        }
                        displayContacts("Contact info",buffer.toString());


                }
                else
                {

                    displayContacts("Error","Contacts not found");

                }

            }
        });


    }
    public void displayContacts(String title, String msg)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(title);
        builder.setMessage(msg);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();



    }
}
