package com.example.lesson_listview.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lesson_listview.data.DataRecipeContract.RecipeEntry;

// Класс через который осуществляется работа с базой данных
public class RecipeDbOpenHelper extends SQLiteOpenHelper {
	// Для работы класса необходимо длобавить конструктор и передать
	// параметры классу родителю
	public RecipeDbOpenHelper(Context context) {
		super(context, DataRecipeContract.DB_NAME, null, DataRecipeContract.TABLE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// Это обязательные метода для SQLiteOpenHelper
		// Здесь создается раметка SQL таблицы
		String CREATE_RECIPE_TABLE = "CREATE TABLE " + RecipeEntry.TABLE_NAME + "("
				+ RecipeEntry.KEY_ID + " INTEGER PRIMARY KEY,"
				+ RecipeEntry.KEY_NAME + " TEXT,"
				+ RecipeEntry.KEY_INGREDIENTS + " TEXT,"
				+ RecipeEntry.TABLE_RECIPE + " TEXT,"
				+ RecipeEntry.TABLE_GROUP + " INTEGER NOT NULL" + ")";
		db.execSQL(CREATE_RECIPE_TABLE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Еще один обязательный метод для SQLiteOpenHelper
		db.execSQL("DROP TABLE IF EXISTS " + DataRecipeContract.DB_NAME);
		onCreate(db);
	}
}
