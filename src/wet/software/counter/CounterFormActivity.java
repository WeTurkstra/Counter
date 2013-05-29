package wet.software.counter;

import wet.software.counter.settings.Vars;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CounterFormActivity extends Activity implements OnClickListener{
	
	private Bitmap image;
	
	private EditText txtName;
	private EditText txtCount;
	
	private Button btnSave;
	private Button btnCamera;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.counter_form);
		
		this.txtName = (EditText)findViewById(R.id.txtNaam);
		this.txtCount = (EditText)findViewById(R.id.txtCount);		

		this.btnSave = (Button)findViewById(R.id.btnSave);
		this.btnSave.setOnClickListener(this);		
		
		this.btnCamera = (Button)findViewById(R.id.btnCamera);
		this.btnCamera.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		switch(((Button)v).getId())
		{
			case R.id.btnSave:
				Save();
				break;
			case R.id.btnCamera:
				MakePicture();
				break;
		}
	}

	private void MakePicture()
	{
		Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(cameraIntent, Vars.cameraRequestCode);
	}
	
	private void Save() {
		CounterData cd = new CounterData(this);
		
		String name = "";
		int count = 0;
		
		name = txtName.getText().toString();
		try
		{
			count = Integer.parseInt(txtCount.getText().toString());	
		}
		catch(Exception e)
		{
			count = 0;			
		}
		
		if(name.equals("") || name.equals(null))
		{
			name = "";
		}
		if(count <= 0)
		{
			count = 0;
		}
	
		if(!name.equals(""))
		{
			cd.NewCounter(name, count, this.image);
			Intent i = new Intent(this, CounterListActivity.class);
			startActivity(i);
		}
		else
		{
			Toast.makeText(this, R.string.nameError, Toast.LENGTH_LONG).show();				
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	    if (requestCode == Vars.cameraRequestCode) {  
	    	this.image = (Bitmap) data.getExtras().get("data");  
	    	
	    	ImageView iv = (ImageView) findViewById(R.id.ivImage);  
	    	iv.setImageBitmap(this.image);  
	    }  
	}  
}
