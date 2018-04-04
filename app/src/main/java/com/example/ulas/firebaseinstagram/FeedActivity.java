package com.example.ulas.firebaseinstagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class FeedActivity extends AppCompatActivity {

    ArrayList<String> userEmailsFromFB;
    ArrayList<String> userImagesFromFB;
    ArrayList<String> userCommentsFromFB;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    PostClass adapter;
    ListView listView;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_post,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add_post)
        {
            Intent intent = new Intent(this,UploadActivity.class);
            startActivity(intent);

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        userEmailsFromFB =new ArrayList<String>();
        userCommentsFromFB =new ArrayList<String>();
        userImagesFromFB =new ArrayList<String>();

        firebaseDatabase =FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference();

        adapter = new PostClass(userEmailsFromFB,userImagesFromFB,userCommentsFromFB,this);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

            getDataFromFB();
    }

    protected void getDataFromFB()
    {
            DatabaseReference newRef= firebaseDatabase.getReference("Posts");
            newRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                   /* System.out.println("Children "+ dataSnapshot.getChildren());

                    System.out.println("key "+ dataSnapshot.getKey());

                    System.out.println("Value "+ dataSnapshot.getValue());*/

                   for(DataSnapshot ds: dataSnapshot.getChildren())
                   {
                       HashMap<String,String> hashMap =(HashMap<String,String>)ds.getValue();
                       userEmailsFromFB.add(hashMap.get("useremail"));
                       userImagesFromFB.add(hashMap.get("downloadUrl"));
                       userCommentsFromFB.add(hashMap.get("comment"));
                       adapter.notifyDataSetChanged();

                   }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
    }
}
