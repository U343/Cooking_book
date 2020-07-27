package com.example.lesson_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecipeActivity extends AppCompatActivity {
	// Окно рецепта, здесь отображается подробная текстовая информация о блюде
	// В приложении с этой страницей взаимодействовать нельзя
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recipe);
		
		TextView title = findViewById(R.id.titleRecipe);        // Название рецепта
		TextView recipe = findViewById(R.id.recipeTextView);    // Сам рецепт
		
		Intent intent = getIntent();
		// Если страница кооректно запущена, то отображем инфформацию о рецепте
		if (intent != null) {
			title.setText(intent.getStringExtra("titleResource"));
			recipe.setText(intent.getStringExtra("recipeResource"));
		}
	}
}
