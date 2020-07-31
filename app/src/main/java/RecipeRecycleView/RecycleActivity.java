package RecipeRecycleView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson_listview.NewRecipeActivity;
import com.example.lesson_listview.PizzaRecipeItem;
import com.example.lesson_listview.R;

import com.example.lesson_listview.data.DataRecipeContract.RecipeEntry;

import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity {
	RecyclerView                recyclerView;   // Поле в котором будут карточки рецептов
	RecyclerView.Adapter        adapter;        // Основная логика RecyclerView
	RecyclerView.LayoutManager  layoutManager;  // Связывает adapter и RecyclerView
	ArrayList<PizzaRecipeItem>  elems = new ArrayList<>(); // Список с рецетами. PizzaRecipeItem - класс рецепта
	Button                      buttonNew;      // Кнопка для перехода на активити для создания нового рецепта
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycle);
		// Инициализирую переменные
		buttonNew = findViewById(R.id.buttonNew);
		buttonNew.setOnClickListener(new View.OnClickListener() { // Обработка нажатия
			@Override
			public void onClick(View v) {
				// Вызываю activity создания нового рецепта
				Intent intent = new Intent(RecycleActivity.this, NewRecipeActivity.class);
				startActivity(intent);
			}
		});
		// Тестовый элемент, чтобы из-за пустого списка не падало приложение
		/*elems.add(new PizzaRecipeItem(               // Сейчас вроде не падает
				getResources().getString(R.string.title_pizza2),
				getResources().getString(R.string.description_pizza1),
				getResources().getString(R.string.recept1)
		));*/
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		elems = new ArrayList<>();
		arrayFiller();
		
		recyclerView = findViewById(R.id.recycleView);
		adapter = new PizzaRecipeAdapter(elems, this);
		layoutManager = new LinearLayoutManager(this);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(layoutManager);
	}
	
	protected void  arrayFiller() {
		PizzaRecipeItem     item;
		int                 currentId;
		String              currentName;
		String              currentIngredients;
		String              currentRecipe;
		int                 currentGroup;
		
		String[] projection = {
				RecipeEntry.KEY_ID,
				RecipeEntry.KEY_NAME,
				RecipeEntry.KEY_INGREDIENTS,
				RecipeEntry.KEY_RECIPE,
				RecipeEntry.TABLE_GROUP
		};
		
		Cursor cursor = getContentResolver().query(
				RecipeEntry.CONTENT_URI,
				projection,
				null,
				null,
				null
		);
		
		int idIndex = cursor.getColumnIndex(RecipeEntry.KEY_ID);
		int idName = cursor.getColumnIndex(RecipeEntry.KEY_NAME);
		int idIngredients = cursor.getColumnIndex(RecipeEntry.KEY_INGREDIENTS);
		int idRecipe = cursor.getColumnIndex(RecipeEntry.KEY_RECIPE);
		int idGroup = cursor.getColumnIndex(RecipeEntry.TABLE_GROUP);
		
		while (cursor.moveToNext()) {
			currentId = cursor.getInt(idIndex);
			currentName = cursor.getString(idName);
			currentIngredients = cursor.getString(idIngredients);
			currentRecipe = cursor.getString(idRecipe);
			currentGroup = cursor.getInt(idGroup);
			
			item = new PizzaRecipeItem(currentId, currentName, currentIngredients, currentRecipe,
					currentGroup);
			elems.add(item);
		}
		
	}
}
