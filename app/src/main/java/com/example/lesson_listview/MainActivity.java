package com.example.lesson_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import RecipeRecycleView.RecycleActivity;

public class MainActivity extends AppCompatActivity {
	// This buttons open RecycleView with only category
	Button      buttonBreakfast;
	Button      buttonLunch;
	Button      buttonDinner;
	Button      buttonSalads;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start); // Set start activity
		// Init variables
		buttonBreakfast = findViewById(R.id.groupBreakfast);
		buttonLunch = findViewById(R.id.groupLunch);
		buttonDinner = findViewById(R.id.groupDinner);
		buttonSalads = findViewById(R.id.groupSalads);
	}
	
	
	
	public void onClickBreakfast(View view) {
		// Вызываю activity с карточками рецептов
		Intent intent = new Intent(this, RecycleActivity.class);
		startActivity(intent);
	}
	
	public void onClickLunch(View view) {
	}
	
	public void onClickDinner(View view) {
	}
	
	public void onClickSalads(View view) {
	}
}
