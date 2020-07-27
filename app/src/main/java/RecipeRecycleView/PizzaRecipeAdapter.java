package RecipeRecycleView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson_listview.PizzaRecipeItem;
import com.example.lesson_listview.R;
import com.example.lesson_listview.RecipeActivity;

import java.util.ArrayList;
import java.util.Collection;

public class PizzaRecipeAdapter extends RecyclerView.Adapter<PizzaRecipeAdapter.PizzaRecipeViewHolder> {
	private ArrayList<PizzaRecipeItem>  pizzaRecipeItems;
	private Context                     context;
	
	public PizzaRecipeAdapter(ArrayList<PizzaRecipeItem> pizzaRecipeItems, Context context) {
		this.pizzaRecipeItems = pizzaRecipeItems;
		this.context = context;
	}
	
	public void setItems(Collection<PizzaRecipeItem> items) {
		pizzaRecipeItems.addAll(items);
		notifyDataSetChanged();
	}
	
	@NonNull
	@Override
	public PizzaRecipeAdapter.PizzaRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pizza_recipe_item,
				parent, false);
		return new PizzaRecipeViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(@NonNull PizzaRecipeViewHolder viewHolder, int position) {
		PizzaRecipeItem pizzaRecipeItem = pizzaRecipeItems.get(position);
		
		viewHolder.pizzaImagineView.setImageResource(pizzaRecipeItem.getImageResource());
		viewHolder.title.setText(pizzaRecipeItem.getName());
		viewHolder.description.setText(pizzaRecipeItem.getIngredients());
	}
	
	@Override
	public int getItemCount() {
		return pizzaRecipeItems.size();
	}
	
	class PizzaRecipeViewHolder extends RecyclerView.ViewHolder implements
			View.OnClickListener {
		public ImageView    pizzaImagineView;
		public TextView     title;
		public TextView     description;
		
		public PizzaRecipeViewHolder(@NonNull View itemView) {
			super(itemView);
			itemView.setOnClickListener(this);
			
			pizzaImagineView = itemView.findViewById(R.id.pizzaImagineView);
			title = itemView.findViewById(R.id.titleTextView);
			description = itemView.findViewById(R.id.descriptionTextView);
		}
		
		@Override
		public void onClick(View v) {
			PizzaRecipeItem item;
			int             position;
			
			position = getAdapterPosition();
			item = pizzaRecipeItems.get(position);
			Intent  intent = new Intent(context, RecipeActivity.class);
			intent.putExtra("titleResource", item.getName());
			intent.putExtra("ingredientsResource", item.getIngredients());
			intent.putExtra("recipeResource", item.getRecipe());
			context.startActivity(intent);
		}
	}
}
