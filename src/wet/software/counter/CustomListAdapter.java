package wet.software.counter;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends ArrayAdapter<Counter> {

	private Context mycontext; 
	
	public CustomListAdapter(Context context, int textViewResourceId,
			List<Counter> objects) {
		super(context, textViewResourceId, objects);
		
		this.mycontext = context;
	}

	 @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		 
		LayoutInflater inflater = ((Activity)mycontext).getLayoutInflater();
		View listItem = inflater.inflate(R.layout.counter_list_item, parent, false);
	    
	    TextView tvNaam = (TextView) listItem.findViewById(R.id.tvNaam);
	    TextView tvCount = (TextView) listItem.findViewById(R.id.tvCounter);
	    ImageView ivView = (ImageView) listItem.findViewById(R.id.ivImage);
	    
	    
	    Counter c = getItem(position);
	   
	    
	    listItem.setTag(c.getId());
	    
	    if (tvNaam != null)
	    {
	    	tvNaam.setText("" + c.getName());
        }
        if (tvCount != null)
        {
        	tvCount.setText("count: " + c.getCount());
        }
        if(ivView != null)
        {
        	ivView.setImageBitmap(c.getImage());	        	
        }
	    return listItem;
	  }
}
