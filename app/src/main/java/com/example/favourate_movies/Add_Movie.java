package com.example.favourate_movies;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Add_Movie extends AppCompatActivity {

    Button addmoviebutton;
    EditText movienametext,movieYeartext,imdbtext,descriptionText;
    SeekBar ratingSeekbar;
    Spinner genreScrollDownspinner;
    String selectedGenre;
    TextView ratingTextView;
    private String movieName,movieDesc,movieImdb;
    private Integer movieYear;
    int movieRating;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__movie);

        final ArrayList<String> movieGenereList=new ArrayList<String>();
        movieGenereList.add("Select");
        movieGenereList.add(("Action"));
        movieGenereList.add("Animation");
        movieGenereList.add("Comedy");
        movieGenereList.add("Documentry");
        movieGenereList.add("Family");
        movieGenereList.add("Horror");
        movieGenereList.add("Crime");
        movieGenereList.add("Others");

        addmoviebutton=findViewById(R.id.addmoviebutton);
        ratingTextView=findViewById(R.id.ratingTextview);
        movienametext=findViewById(R.id.edittextmoviename);
        movieYeartext=findViewById(R.id.yearedittext);
        imdbtext=findViewById(R.id.edittextimdburl);
        descriptionText=findViewById(R.id.descriptioneditText);
        ratingSeekbar=findViewById(R.id.ratingseekBar);
        genreScrollDownspinner=findViewById(R.id.generescrolldown);

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,movieGenereList);
        genreScrollDownspinner.setAdapter(arrayAdapter);

        genreScrollDownspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedGenre=movieGenereList.get(i);
               // Log.i("selectedGenre",selectedGenre.toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ratingSeekbar.setMin(0);
        ratingSeekbar.setMax(5);
        ratingSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                movieRating=i;
                ratingTextView.setText(String.valueOf(movieRating));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        addmoviebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(movienametext.getText().toString().isEmpty()){
                    Toast.makeText(Add_Movie.this, "Please enter the movie name", Toast.LENGTH_SHORT).show();
                }else if(descriptionText.getText().toString().trim().isEmpty() || descriptionText.getText().toString().trim().length()>1000){
                    Toast.makeText(Add_Movie.this, "Please enter the valid description", Toast.LENGTH_SHORT).show();
                }else if(selectedGenre.equals("Select")){
                    Toast.makeText(Add_Movie.this, "Please select the genere", Toast.LENGTH_SHORT).show();
                }else if(movieYeartext.getText().toString().isEmpty() || movieYeartext.getText().toString().length()>4){
                    Toast.makeText(Add_Movie.this, "Please enter the year", Toast.LENGTH_SHORT).show();
                }else if(imdbtext.getText().toString().isEmpty()){
                    Toast.makeText(Add_Movie.this, "Please enter valid url", Toast.LENGTH_SHORT).show();
                }else {

                    movieName=movienametext.getText().toString();
                    movieDesc=descriptionText.getText().toString();
                    movieYear= Integer.valueOf(movieYeartext.getText().toString());
                    movieImdb=imdbtext.getText().toString();

                    Intent addintent=new Intent(Add_Movie.this,MainActivity.class);
                    addintent.putExtra(MainActivity.ADD_MOVIE_CODE,new Movie(movieName,movieDesc,selectedGenre,movieImdb,movieYear,movieRating));
                    setResult(RESULT_OK,addintent);
                    finish();

                    Toast.makeText(Add_Movie.this, "Movie Has been Added to list", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
