package com.example.favourate_movies;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Edit_Movie extends AppCompatActivity {

    Button saveChangesButton;
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
        setContentView(R.layout.activity_edit__movie);

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



        saveChangesButton=findViewById(R.id.addmoviebutton);
        ratingTextView=findViewById(R.id.ratingTextview);
        movienametext=findViewById(R.id.edittextmoviename);
        movieYeartext=findViewById(R.id.yearedittext);
        imdbtext=findViewById(R.id.edittextimdburl);
        descriptionText=findViewById(R.id.descriptioneditText);
        ratingSeekbar=findViewById(R.id.ratingseekBar);
        genreScrollDownspinner=findViewById(R.id.generescrolldown);

        if(getIntent()!=null && getIntent().getExtras()!=null){

            Movie movie=getIntent().getExtras().getParcelable(MainActivity.EDIT_MOVIE_CODE);
            Log.i("movieToEdit",movie.toString());

            movienametext.setText(movie.movieName);
              descriptionText.setText(movie.movieDescription);
                movieYeartext.setText(String.valueOf(movie.movieYear));
            imdbtext.setText(movie.movieImdb);


            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,movieGenereList);
            genreScrollDownspinner.setAdapter(arrayAdapter);

            genreScrollDownspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    //selectedGenre=movieGenereList.get(i);
                    // Log.i("selectedGenre",selectedGenre.toString());
                    selectedGenre=adapterView.getItemAtPosition(i).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            switch (movie.movieGenere)
            {
                case "Select": genreScrollDownspinner.setSelection(0);break;
                case "Action": genreScrollDownspinner.setSelection(1); break;
                case "Animation": genreScrollDownspinner.setSelection(2); break;
                case "Comedy": genreScrollDownspinner.setSelection(3); break;
                case "Documentary": genreScrollDownspinner.setSelection(4); break;
                case "Family": genreScrollDownspinner.setSelection(5); break;
                case "Horror": genreScrollDownspinner.setSelection(6); break;
                case "Crime": genreScrollDownspinner.setSelection(7); break;
                case "Others": genreScrollDownspinner.setSelection(8); break;


            }

            ratingSeekbar.setProgress(movie.movieRating);
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

            saveChangesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(movienametext.getText().toString().isEmpty()){
                        Toast.makeText(Edit_Movie.this, "Please enter the movie name", Toast.LENGTH_SHORT).show();
                    }else if(descriptionText.getText().toString().trim().isEmpty() || descriptionText.getText().toString().trim().length()>1000){
                        Toast.makeText(Edit_Movie.this, "Please enter the valid description", Toast.LENGTH_SHORT).show();
                    }else if(selectedGenre.equals("Select")){
                        Toast.makeText(Edit_Movie.this, "Please select the genere", Toast.LENGTH_SHORT).show();
                    }else if(movieYeartext.getText().toString().isEmpty() || movieYeartext.getText().toString().length()>4){
                        Toast.makeText(Edit_Movie.this, "Please enter the year", Toast.LENGTH_SHORT).show();
                    }else if(imdbtext.getText().toString().isEmpty()){
                        Toast.makeText(Edit_Movie.this, "Please enter valid url", Toast.LENGTH_SHORT).show();
                    }else {
                        movieName=movienametext.getText().toString();
                        movieDesc=descriptionText.getText().toString();
                        movieYear= Integer.valueOf(movieYeartext.getText().toString());
                        movieImdb=imdbtext.getText().toString();
                        movieRating= Integer.parseInt(ratingTextView.getText().toString());


                        Intent editIntent=new Intent();
                        editIntent.putExtra(MainActivity.EDIT_MOVIE_CODE,new Movie(movieName,movieDesc,selectedGenre,movieImdb,movieYear,movieRating));
                        setResult(RESULT_OK,editIntent);

                    }
                    finish();

                }
            });



        }



    }
}
