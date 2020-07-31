package com.example.lesson_listview;

public class PizzaRecipeItem {
	private int     id;
	private int     imageResource;
	private int     group;
	private String  name;
	private String  ingredients;
	private String  recipe;
	
	public PizzaRecipeItem(int id, int imageResource, String name, String ingredients,
	                       String recipe, int group) {
		this.id = id;
		this.name = name;
		this.imageResource = imageResource;
		this.ingredients = ingredients;
		this.recipe = recipe;
		this.group = group;
	}
	
	public PizzaRecipeItem(int id, String name, String ingredients, String recipe, int group) {
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.recipe = recipe;
		this.group = group;
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
