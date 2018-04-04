package com.example.ulas.firebaseinstagram;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ULAS on 25.03.2018.
 */

public class PostClass extends ArrayAdapter<String> {
    private final ArrayList<String> userEmail;
    private final ArrayList<String> userImage;
    private final ArrayList<String> commendText;
    private final Activity context;

    ArrayAdapter arrayAdapter;


    public PostClass(ArrayList<String> userEmail, ArrayList<String> userImage, ArrayList<String> commendText, Activity context) {
        super(context,R.layout.custom_view,userEmail);
        this.userEmail = userEmail;
        this.userImage = userImage;
        this.commendText = commendText;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater =context.getLayoutInflater();
        View customView =layoutInflater.inflate(R.layout.custom_view,null,true);

        TextView userEmailText =(TextView) customView.findViewById(R.id.username);
        TextView commentText =(TextView) customView.findViewById(R.id.commentText);
        ImageView imageView=(ImageView) customView.findViewById(R.id.imageView);

        userEmailText.setText(userEmail.get(position));
        commentText.setText(commendText.get(position));

        Picasso.get().load(userImage.get(position)).into(imageView);


        return customView;
    }
}
