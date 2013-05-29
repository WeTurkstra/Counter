package wet.software.counter;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CounterDetailsActivity extends Activity {
	
	private Counter counter;
	
	private TextView tvName; 
	private TextView tvCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.counter_details);
	
		Intent sender = getIntent();
		
		int id = sender.getExtras().getInt("counter_id");
		
		CounterData cd = new CounterData(this);
		
		this.counter = cd.GetCounter(id);
				
		this.tvName = (TextView)findViewById(R.id.tvName);
		this.tvCount = (TextView)findViewById(R.id.tvCounter);
		
		this.tvName.setText(this.counter.getName());
		this.tvCount.setText("" + this.counter.getCount());
		
		Toast.makeText(this, "" + counter.getId(), Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.counter_list, menu);
		return true;
	}
}
