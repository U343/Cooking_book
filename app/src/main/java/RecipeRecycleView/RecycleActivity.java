package RecipeRecycleView;

import android.content.Intent;
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

import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity {
	RecyclerView                recyclerView;   // Поле в котором будут карточки рецептов
	RecyclerView.Adapter        adapter;        // Основная логика RecyclerView
	RecyclerView.LayoutManager  layoutManager;  // Связывает adapter и RecyclerView
	ArrayList<PizzaRecipeItem>  elems;          // Список с рецетами. PizzaRecipeItem - класс рецепта
	Button                      buttonNew;      // Кнопка для перехода на активити для создания нового рецепта
	
	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycle);
		// Инициализирую переменные
		elems = new ArrayList<>();
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
		
		recyclerView = findViewById(R.id.recycleView);
		adapter = new PizzaRecipeAdapter(elems, this);
		layoutManager = new LinearLayoutManager(this);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(layoutManager);
	}
}
