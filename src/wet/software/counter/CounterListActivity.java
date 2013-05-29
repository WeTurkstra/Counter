package wet.software.counter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CounterListActivity extends Activity implements OnItemClickListener {

	private ListView lvCounters;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.counter_list);
		
		lvCounters = (ListView)findViewById(R.id.lvCounters);
		
		CounterData cd = new CounterData(this);
			
		List<Counter> counters = cd.GetCounters();
		
		CustomListAdapter cla = new CustomListAdapter(this, R.layout.counter_list_item, counters);
		lvCounters.setAdapter(cla);
		lvCounters.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.counter_list, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.newButton:
	        newButtonClick();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	private void newButtonClick()
	{
		Intent i = new Intent(this, CounterFormActivity.class);
		startActivity(i);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
	{
		if(lvCounters.getCount() > 0)
		{
			Counter counter = (Counter)lvCounters.getItemAtPosition(position); 
			
			Intent i = new Intent(this, CounterDetailsActivity.class);
			i.putExtra("counter_id", counter.getId());
			startActivity(i);
		}
	}
}
