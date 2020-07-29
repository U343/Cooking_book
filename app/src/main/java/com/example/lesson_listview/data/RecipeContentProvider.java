package com.example.lesson_listview.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

public class RecipeContentProvider extends ContentProvider {
	RecipeDbOpenHelper  dbOpenHelper;
	
	@Override
	public boolean onCreate() {
		// Создаем экземпляр класса, через который будем взаимодействовать с базой данных
		dbOpenHelper = new RecipeDbOpenHelper(getContext());
		return true;
	}
	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		return null;
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		return null;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}
	
	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return 0;
	}
	
	@Override
	public String getType(Uri uri) {
		return null;
	}
}
