package com.example.lesson_listview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.edit_member_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item) {
		switch (item.getItemId()) {
			case R.id.edit_member:
				return true;
			case R.id.delete_member:
				return true;
			case android.R.id.home:
				NavUtils.navigateUpFromSameTask(this);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
