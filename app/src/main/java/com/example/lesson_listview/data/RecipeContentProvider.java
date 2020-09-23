package com.example.lesson_listview.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.lesson_listview.data.DataRecipeContract.RecipeEntry;

public class RecipeContentProvider extends ContentProvider {
	RecipeDbOpenHelper          dbOpenHelper;
	private static final int    MEMBERS = 1; // Константа для Uri_matcher (вся таблица)
	private static final int    MEMBER_ID = 2; // Константа для Uri_matcher (отдельная строка)
	
	// Создаю Uri matcher, который будет проверять правильность адреса
	private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	
	static {
		// Matcher для определения Uri, который будет взаимодействаовать со всей БД
		uriMatcher.addURI(DataRecipeContract.AUTHORITY, RecipeEntry.TABLE_NAME, MEMBERS);
		// Matcher для определения Uri, который будет взаимодействаовать с конктретной строкой БД
		// /# - обозначает номер произвольной строки в таблице
		uriMatcher.addURI(DataRecipeContract.AUTHORITY, RecipeEntry.TABLE_NAME + "/#", MEMBER_ID);
	}
	
	@Override
	public boolean onCreate() {
		// Создаем экземпляр класса, через который будем взаимодействовать с базой данных
		dbOpenHelper = new RecipeDbOpenHelper(getContext());
		return true;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		// Метод позволяет получить строку или всю таблицу
		//
		// uri - адресс объекта с которым мы будем взаимодействовать
		// projection - имена столбцов
		// selection - отбор, т.е. признак по которому мы выбираем элементы
		// selectionArgs - элементы отбора, элементы по которым выбирается нужная строка
		Cursor cursor;

		SQLiteDatabase db = dbOpenHelper.getReadableDatabase(); // Делаем запрос на чтение БД
		int match = uriMatcher.match(uri); // Определяем с чем нужно работать с 1 или 2
		switch (match) {
			case MEMBERS:
				cursor = db.query(RecipeEntry.TABLE_NAME, projection, selection, selectionArgs,
						null, null, sortOrder);
				break;
			case MEMBER_ID:
				selection = RecipeEntry.KEY_ID + "=?";
				selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
				cursor = db.query(RecipeEntry.TABLE_NAME, projection, selection, selectionArgs,
						null, null, sortOrder);
				break;
			default:
				throw new IllegalArgumentException("Can't query  incorrect Uri" + uri);
		}
		
		cursor.setNotificationUri(getContext().getContentResolver(), uri);
		return cursor;
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// Метод добавляет новую строку в БД
		//
		// На вход подается Uri всей таблицы
		// возвращается Uri строки, которая была добавлена
		// values - элементы, которые добавляем в таблицу
		Cursor  cursor;
		long    id;

		SQLiteDatabase db = dbOpenHelper.getWritableDatabase(); // Делаем запрос на запись в БД
		int match = uriMatcher.match(uri); // Определяем с чем нужно работать с 1 или 2
		if (match == MEMBERS) { // В данном случае есть смысл работать только со всей таблицей,
								// т.к. именно туда мы и добавляем новую строку
			id = db.insert(RecipeEntry.TABLE_NAME, null, values);
			if (id == -1) { // в случае ошибки insert вернет -1
				Log.e("InsertMethod", "Insertion of data  in the table failed for " + uri);
				return null;
			}
			getContext().getContentResolver().notifyChange(uri, null);
			return ContentUris.withAppendedId(uri, id);
		} else {
			throw new IllegalArgumentException("Can't query  incorrect Uri" + uri);
		}
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		
		int match = uriMatcher.match(uri); // Определяем с чем нужно работать с 1 или 2
		int rowsDeleted;
		
		switch (match) {
			case MEMBERS:
				rowsDeleted = db.delete(RecipeEntry.TABLE_NAME, selection, selectionArgs);
				break;
			case MEMBER_ID:
				selection = RecipeEntry.KEY_ID + "=?";
				selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
				rowsDeleted = db.delete(RecipeEntry.TABLE_NAME, selection, selectionArgs);
				break;
			default:
				throw new IllegalArgumentException("Can't delete this Uri" + uri);
		}
		
		if (rowsDeleted != 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return rowsDeleted;
	}
	
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		
		int match = uriMatcher.match(uri); // Определяем с чем нужно работать с 1 или 2
		int rowsUpdated;
		
		switch (match) {
			case MEMBERS:
				rowsUpdated = db.update(RecipeEntry.TABLE_NAME, values, selection, selectionArgs);
				break;
			case MEMBER_ID:
				selection = RecipeEntry.KEY_ID + "=?";
				selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
				rowsUpdated = db.update(RecipeEntry.TABLE_NAME, values, selection, selectionArgs);
				break;
			default:
				throw new IllegalArgumentException("Can't update this Uri" + uri);
		}
		
		if (rowsUpdated != 0) {
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return rowsUpdated;
	}
	
	@Override
	public String getType(Uri uri) {
		
		int match = uriMatcher.match(uri); // Определяем с чем нужно работать с 1 или 2
		
		switch (match) {
			case MEMBERS:
				return RecipeEntry.CONTENT_MULTIPLE_ITEMS;
			case MEMBER_ID:
				return RecipeEntry.CONTENT_SINGLE_ITEM;
			default:
				throw new IllegalArgumentException("Can't getType this Uri" + uri);
		}
	}
}
