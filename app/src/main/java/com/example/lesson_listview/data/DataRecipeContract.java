package com.example.lesson_listview.data;

public final class DataRecipeContract {
	//Этот класс служит шаблономо для будующей таблицы
	//Классы внутри и есть таблицы
	//DataRecipeContract - что то вроде хранилища для шаблонов таблиц
	
	private DataRecipeContract() {
	}
	
	public static final class RecipeEntry {
		//Это основная таблица для хранения рецептов
		
		public static final String      TABLE_NAME = "recipes";
		
		//Это поля таблицы, ее колонки другими словами
		//В них будет храниться информация о рецептах
		public static final String      KEY_ID = "id_recipe";
		public static final String      KEY_NAME = "name_recipe";
		public static final String      KEY_INGREDIENTS = "ingredients";
		public static final String      TABLE_RECIPE = "recipe";
		public static final String      TABLE_GROUP = "group_recipe";
		
		//Константы для категорий рецептов, вынесены в константы, потому что они неизменны
		//и также в дальнейшем будут использоваться для сортировки по категориям
		public static final int         UNKNOWN_GROUP = 0;
		public static final int         BREAKFAST_GROUP = 1;
		public static final int         LUNCH_GROUP = 2;
		public static final int         DINNER_GROUP = 3;
		public static final int         SALADS_GROUP = 4;
	}
}
