package com.example.favourate_movies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sort_by_Rating extends AppCompatActivity {


    Button finishButton;
    EditText movienametext,movieYeartext,imdbtext,descriptionText;
    SeekBar ratingSeekbar;
    Spinner genreScrollDownspinner;
    ImageView firstMovieIv,prevMovieIv,nextMovieIv,lastMovieIv;
    String selectedGenre;
    TextView ratingTextView,generTextview;
    ArrayList<Movie> list=new ArrayList<>();
    private String movieName,movieDesc,movieImdb;
    private Integer movieYear;
    int movieRating;
    int i=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_by__rating);

        finishButton=findViewById(R.id.finish);
        ratingTextView=findViewById(R.id.ratingTextview);
        movienametext=findViewById(R.id.edittextmoviename);
        movieYeartext=findViewById(R.id.yearedittext);
        imdbtext=findViewById(R.id.edittextimdburl);
        descriptionText=findViewById(R.id.descriptioneditText);
        ratingSeekbar=findViewById(R.id.ratingseekBar);
        genreScrollDownspinner=findViewById(R.id.generescrolldown);
        firstMovieIv=findViewById(R.id.showFirstMovie);
        prevMovieIv=findViewById(R.id.showprevious);
        nextMovieIv=findViewById(R.id.shownext);
        lastMovieIv=findViewById(R.id.showlastmovie);
        generTextview=findViewById(R.id.generetextview);

            if(getIntent()!=null && getIntent().getExtras()!=null){
                list=getIntent().getParcelableArrayListExtra(MainActivity.SORT_RATING_CODE);
                final int j = list.size();

                Collections.sort(list, new Comparator<Movie>() {
                    @Override
                    public int compare(Movie m1, Movie m2) {
                        return Integer.compare(m2.movieRating,m1.movieRating);
                    }
                });
                i=0;
                movienametext.setText(list.get(i).movieName);
                descriptionText.setText(list.get(i).movieDescription);
                generTextview.setText(list.get(i).movieGenere);
                ratingTextView.setText(String.valueOf(list.get(i).movieRating));
                movieYeartext.setText(String.valueOf(list.get(i).movieYear));
                imdbtext.setText(list.get(i).movieImdb);


                firstMovieIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (i == 0) {
                            Toast.makeText(Sort_by_Rating.this, "This is First Movie", Toast.LENGTH_SHORT).show();
                        } else {
                            i = 0;
                            movienametext.setText(list.get(i).movieName);
                            descriptionText.setText(list.get(i).movieDescription);
                            generTextview.setText(list.get(i).movieGenere);
                            ratingTextView.setText(String.valueOf(list.get(i).movieRating));
                            movieYeartext.setText(String.valueOf(list.get(i).movieYear));
                            imdbtext.setText(list.get(i).movieImdb);
                        }
                    }
                });

                prevMovieIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (i == 0) {
                            Toast.makeText(Sort_by_Rating.this, "This is the first movie", Toast.LENGTH_SHORT).show();
                        } else {
                            i--;
                            movienametext.setText(list.get(i).movieName);
                            descriptionText.setText(list.get(i).movieDescription);
                            generTextview.setText(list.get(i).movieGenere);
                            ratingTextView.setText(String.valueOf(list.get(i).movieRating) + "\5");
                            movieYeartext.setText(String.valueOf(list.get(i).movieYear));
                            imdbtext.setText(list.get(i).movieImdb);
                        }
                    }
                });

                nextMovieIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (i ==j-1){
                            Toast.makeText(Sort_by_Rating.this, "This is the last movie", Toast.LENGTH_SHORT).show();
                        }else{
                            i++;
                            movienametext.setText(list.get(i).movieName);
                            descriptionText.setText(list.get(i).movieDescription);
                            generTextview.setText(list.get(i).movieGenere);
                            ratingTextView.setText(String.valueOf(list.get(i).movieRating)  + "\5");
                            movieYeartext.setText(String.valueOf(list.get(i).movieYear));
                            imdbtext.setText(list.get(i).movieImdb);
                        }
                    }
                });

                lastMovieIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(i==j-1){
                            Toast.makeText(Sort_by_Rating.this, "This is the last movie", Toast.LENGTH_SHORT).show();
                        }else {
                            i=j-1;
                            movienametext.setText(list.get(i).movieName);
                            descriptionText.setText(list.get(i).movieDescription);
                            generTextview.setText(list.get(i).movieGenere);
                            ratingTextView.setText(String.valueOf(list.get(i).movieRating) + "\5");
                            movieYeartext.setText(String.valueOf(list.get(i).movieYear));
                            imdbtext.setText(list.get(i).movieImdb);
                        }
                    }
                });

                finishButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });


            }
    }
}
