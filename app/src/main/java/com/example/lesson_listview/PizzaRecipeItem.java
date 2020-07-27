package com.example.lesson_listview;

public class PizzaRecipeItem {
	private int     id;
	private int     imageResource;
	private String  group;
	private String  name;
	private String  ingredients;
	private String  recipe;
	
	public PizzaRecipeItem(int id, int imageResource, String name, String ingredients, String recipe) {
		this.id = id;
		this.name = name;
		this.imageResource = imageResource;
		this.ingredients = ingredients;
		this.recipe = recipe;
	}
	
	public PizzaRecipeItem(int imageResource, String name, String ingredients, String recipe) {
		this.imageResource = imageResource;
		this.name = name;
		this.ingredients = ingredients;
		this.recipe = recipe;
	}
	
	public PizzaRecipeItem(String name, String ingredients, String recipe) {
		this.name = name;
		this.ingredients = ingredients;
		this.recipe = recipe;
	}
	
	public PizzaRecipeItem() {
	}
	
	public int getImageResource() {
		return imageResource;
	}
	
	public String getName() {
		return name;
	}
	
	public String getRecipe() {
		return recipe;
	}
	
	public String getIngredients() {
		return ingredients;
	}
}
