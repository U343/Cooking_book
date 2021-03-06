package com.example.lesson_listview;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lesson_listview.data.DataRecipeContract.RecipeEntry;

public class NewRecipeActivity extends AppCompatActivity {
	Button          buttonSave; // кнопка сохранениея
	EditText        nameRecipe, ingredients, recipe; // поля для заполнения рецепта
	Spinner         groupSpinner;
	private int     group = 0;  // Число для Spinner, означает группу
	// 0 - unknown; 1 - завтрак; 2 - обед; 3 - ужин; 4 - салат
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		ArrayAdapter        spinnerAdapter;
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_recipe);
		
		buttonSave = findViewById(R.id.buttonNewSave);
		nameRecipe = findViewById(R.id.newName);
		ingredients = findViewById(R.id.newIngredient);
		recipe = findViewById(R.id.newRecipe);
		groupSpinner = findViewById(R.id.groupSpinner);
		
		// Создаю adapter, необходимый для связи листа со спинером
		// array со всеми элементами находится в values/arrays.xml
		spinnerAdapter = ArrayAdapter.createFromResource(this,
				R.array.array_group, android.R.layout.simple_spinner_item);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		groupSpinner.setAdapter(spinnerAdapter); // Привязывю адаптер к спинеру
		//Обработка выбора spinner
		groupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// Метод сработает как только будет выбран элемент спинера
				// parent - будет передан лист, который мы создали
				// position - номер выбранного элемента
				
				String  selectedGroup;
				// Спокойно кастуем к string так как в листе только строки
				selectedGroup = (String) parent.getItemAtPosition(position);
				if (!TextUtils.isEmpty(selectedGroup)) {
					if (selectedGroup.equals("Завтрак")) {
						// Ссылаюсь на константы из data.DataRecipeContract
						group = RecipeEntry.BREAKFAST_GROUP;
					} else if (selectedGroup.equals("Обед")) {
						group = RecipeEntry.LUNCH_GROUP;
					} else if (selectedGroup.equals("Ужин")) {
						group = RecipeEntry.DINNER_GROUP;
					} else if (selectedGroup.equals("Салаты")) {
						group = RecipeEntry.SALADS_GROUP;
					} else {
						group = RecipeEntry.UNKNOWN_GROUP;
					}
				} else {
					group = 0;
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				group = 0;
			}
		});
		
	}
	// Метод onClick для кнопки сохранения. Функция проверяет заполненность EditText
	// и если все нормально, то завершает работу activity
	public void saveRecipeClick(View view) {
		String          textName;         // Имя блюда
		String          textIngredients;  // Ингредиенты блюда
		String          textRecipe;       // Рецепт блюда
		ContentValues   contentValues;
		// Получаем значения из EditText
		textName = nameRecipe.getText().toString().trim();  // trim() обрезает пробелы в начале и конце
		textIngredients = ingredients.getText().toString().trim();
		textRecipe = recipe.getText().toString().trim();
		if ("".equals(textName)) { // Если хоть какой-то не зполнен, то вызываем тост с предупреждением
			Toast toast = Toast.makeText(getApplicationContext(), "Укажите название", Toast.LENGTH_SHORT);
			toast.show();
		} else if ("".equals(textIngredients)) {
			Toast toast = Toast.makeText(getApplicationContext(), "Укажите ингредиенты", Toast.LENGTH_SHORT);
			toast.show();
		} else if ("".equals(textRecipe)) {
			Toast toast = Toast.makeText(getApplicationContext(), "Укажите рецепт", Toast.LENGTH_SHORT);
			toast.show();
		} else if (group == 0) {
			Toast toast = Toast.makeText(getApplicationContext(), "Укажите категорию рецепта", Toast.LENGTH_SHORT);
			toast.show();
		} else {
			// Готовим данные для помещения в БД
			contentValues = new ContentValues();
			contentValues.put(RecipeEntry.KEY_NAME, textName);
			contentValues.put(RecipeEntry.KEY_INGREDIENTS, textIngredients);
			contentValues.put(RecipeEntry.KEY_RECIPE, textIngredients);
			contentValues.put(RecipeEntry.TABLE_GROUP, group);
			
			ContentResolver contentResolver = getContentResolver();
			// Через ContentResolver вызываем insert из ContentResolver
			Uri uri = contentResolver.insert(RecipeEntry.CONTENT_URI, contentValues);
			if (uri == null) {
				Toast.makeText(this, "Insertion of data  in the table failed for",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(this, "Data saved", Toast.LENGTH_LONG).show();
			}
		}
	}
}
