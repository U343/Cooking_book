package RecipeRecycleView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson_listview.NewRecipeActivity;
import com.example.lesson_listview.PizzaRecipeItem;
import com.example.lesson_listview.R;

import com.example.lesson_listview.data.DataRecipeContract.RecipeEntry;

import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity {
	// constants for DB
	int                         dish_group;
	
	RecyclerView                recyclerView;   // Поле в котором будут карточки рецептов
	RecyclerView.Adapter        adapter;        // Основная логика RecyclerView
	RecyclerView.LayoutManager  layoutManager;  // Связывает adapter и RecyclerView
	ArrayList<PizzaRecipeItem>  elems = new ArrayList<>(); // Список с рецетами. PizzaRecipeItem - класс рецепта
	Button                      buttonNew;      // Кнопка для перехода на активити для создания нового рецепта
	
	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	protected void changeActionTitle(int group) {
		// В зависимости от выбранной группы будет свой заголовок
		// 0 - unknown; 1 - завтрак; 2 - обед; 3 - ужин; 4 - салат
		switch (group) {
			case 1:
				setTitle(R.string.group1);
				break;
			case 2:
				setTitle(R.string.group2);
				break;
			case 3:
				setTitle(R.string.group3);
				break;
			case 4:
				setTitle(R.string.group4);
				break;
			default:
				setTitle(R.string.group0);
		}
	}
	
	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Получаем группу рецепта
		dish_group = getIntent().getIntExtra(RecipeEntry.TABLE_GROUP, 0);
		// Устанавливаем заголовок в action Bar
		changeActionTitle(dish_group);
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
		
		int idColumnIndex = cursor.getColumnIndex(RecipeEntry.KEY_ID);
		int nameColumnIndex = cursor.getColumnIndex(RecipeEntry.KEY_NAME);
		int ingredientsColumnIndex = cursor.getColumnIndex(RecipeEntry.KEY_INGREDIENTS);
		int recipeColumnIndex = cursor.getColumnIndex(RecipeEntry.KEY_RECIPE);
		int groupColumnIndex = cursor.getColumnIndex(RecipeEntry.TABLE_GROUP);
		
		while (cursor.moveToNext()) {
			currentGroup = cursor.getInt(groupColumnIndex);
			if (currentGroup == dish_group) {
				currentId = cursor.getInt(idColumnIndex);
				currentName = cursor.getString(nameColumnIndex);
				currentIngredients = cursor.getString(ingredientsColumnIndex);
				currentRecipe = cursor.getString(recipeColumnIndex);
				
				item = new PizzaRecipeItem(currentId, currentName, currentIngredients, currentRecipe,
						currentGroup);
				elems.add(item);
			}
		}
	}
}
