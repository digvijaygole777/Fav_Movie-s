package com.example.favourate_movies;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addmovie,editmovie,deletemovie,showListByYear,showListByRating;

    private int REQ_MOVIE_CODE=100,REQ_EDIT=50;
    static  String ADD_MOVIE_CODE="added";
    static  String EDIT_MOVIE_CODE="movieedit";
    ArrayList<Movie> moviesAddedList=new ArrayList<>();
    String[] moviesToedit;
    int j=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        addmovie=findViewById(R.id.addMovie);
        editmovie=findViewById(R.id.editmovie);
        deletemovie=findViewById(R.id.deletemovie);
        showListByYear=findViewById(R.id.showlistyear);
        showListByRating=findViewById(R.id.showlistrating);

        Movie movie=new Movie("toystory","Sfkafuoahd","Comedy","sfaijgs",1996,3);
        Movie movie1=new Movie("joker","Sfkafd","Action","sfaisfaf",1996,5);
        moviesAddedList.add(movie);
        moviesAddedList.add(movie1);

        addmovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addmovieintent=new Intent(MainActivity.this,Add_Movie.class);
                startActivityForResult(addmovieintent,REQ_MOVIE_CODE);
            }
        });

        deletemovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(moviesAddedList.isEmpty()){
                    Toast.makeText(MainActivity.this,"please add the movie", Toast.LENGTH_SHORT).show();
                }
                else {
                        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Pick a Movie");
                        moviesToedit=new String[moviesAddedList.size()];

                        for(int k=0;k<moviesAddedList.size();k++){

                            moviesToedit[k]=moviesAddedList.get(k).movieName;
                        }

                        builder.setItems(moviesToedit, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, moviesToedit[i], Toast.LENGTH_SHORT).show();
                                for (j = 0; j < moviesAddedList.size(); j++) {
                                    if (moviesToedit[i].equals(moviesAddedList.get(j).movieName)) {
                                        break;
                                    }
                                }
                                moviesAddedList.remove(moviesAddedList.get(i));

                            }
                        });

                        builder.show();
                }
            }
        });

        editmovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(moviesAddedList.isEmpty()){
                        Toast.makeText(MainActivity.this, "Please add the movie first", Toast.LENGTH_SHORT).show();
                    }else {
                        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Pick a Movie");
                        moviesToedit=new String[moviesAddedList.size()];

                        for(int k=0;k<moviesAddedList.size();k++){

                           moviesToedit[k]=moviesAddedList.get(k).movieName;
                        }

                        builder.setItems(moviesToedit, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                               // Toast.makeText(MainActivity.this, moviesToedit[i], Toast.LENGTH_SHORT).show();
                                Intent editmovieIntent=new Intent(MainActivity.this,Edit_Movie.class);
                                for( j=0;j<moviesAddedList.size();j++){

                                    if(moviesToedit[i].equals(moviesAddedList.get(j).movieName)){
                                            break;
                                    }
                                }
                                editmovieIntent.putExtra(EDIT_MOVIE_CODE,moviesAddedList.get(j));
                                startActivityForResult(editmovieIntent,REQ_EDIT);
                            }
                        });

                        builder.show();



                    }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==REQ_MOVIE_CODE){
            if(resultCode==RESULT_OK){
                if(data!=null && data.getExtras()!=null){

                    Movie movies=data.getExtras().getParcelable(ADD_MOVIE_CODE);

                    moviesAddedList.add(movies);

                }
            }
        }


        if(requestCode==REQ_EDIT){
            if(resultCode==RESULT_OK){
                Movie movie=data.getExtras().getParcelable(EDIT_MOVIE_CODE);
                Toast.makeText(this,"Movie Edited successfully", Toast.LENGTH_SHORT).show();
                moviesAddedList.set(j,movie);
            }
        }


    }
}
