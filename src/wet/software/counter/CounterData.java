package wet.software.counter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import wet.software.counter.settings.Vars;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class CounterData {
	
	private SqlHelper dbHelper;  
	private SQLiteDatabase database;  
	
	private Context mContext;
	
	public CounterData(Context context)
	{
		dbHelper = new SqlHelper(context);  
		database = dbHelper.getWritableDatabase();
		
		mContext = context;
	}
	
	public void NewCounter(String name, int count, Bitmap image)
	{
		byte[] imageBlob = getBitmapAsByteArray(image);
		
		Toast.makeText(mContext, imageBlob.toString(), Toast.LENGTH_LONG).show();
		
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		cv.put("count", count);
		cv.put("image", imageBlob);
		
		database.insert(Vars.tableCounter, null, cv);
	}
	
	public void DeleteCounter(int id)
	{
		database.delete(Vars.tableCounter, "id = " + id, null);
	}
	
	public void SaveCounter(int id, int count)
	{
		ContentValues cv = new ContentValues();
		cv.put("count", count);
		
		database.update(Vars.tableCounter, cv, "id = " + id, null);
	}
	
	public void UpdateCounter(int id, String name, int count)
	{
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		cv.put("count", count);
		
		database.update(Vars.tableCounter, cv, "id = " + id, null);
	}
	
	public Counter GetCounter(int id)
	{
		String query = "SELECT * FROM " + Vars.tableCounter + " WHERE id = " + id;
		Cursor c = database.rawQuery(query, null);
		
		if (c.moveToFirst()) {
            int c_id = c.getInt(c.getColumnIndex("id"));
        	String name = c.getString(c.getColumnIndex("name"));
        	int count = c.getInt(c.getColumnIndex("count"));
        	Bitmap image = BitmapFactory.decodeByteArray(c.getBlob(c.getColumnIndex("image")), 0, c.getBlob(c.getColumnIndex("image")).length);
        	return new Counter(id, name, count, image);
		}   
		else
		{
			return null;			
		}
	}
	
	public List<Counter> GetCounters()
	{
		List<Counter> counters = new ArrayList<Counter>();
		
		String query = "SELECT * FROM " + Vars.tableCounter;
		Cursor c = database.rawQuery(query, null);
		
		if (c.moveToFirst()) {
            do {
               
            	int id = c.getInt(c.getColumnIndex("id"));
            	String name = c.getString(c.getColumnIndex("name"));
            	int count = c.getInt(c.getColumnIndex("count"));
            	
            	byte[] bytes = c.getBlob(c.getColumnIndex("image"));
            	
            	ByteArrayInputStream input = new ByteArrayInputStream(bytes);
            	Bitmap bit = BitmapFactory.decodeStream(input);
            	
            	Counter counter = new Counter(id, name, count, bit);
               
            	counters.add(counter);
               
            } while (c.moveToNext());
        }
		
		return counters;
	}
	
	private static byte[] getBitmapAsByteArray(Bitmap bitmap) {
	    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    bitmap.compress(CompressFormat.PNG, 0, outputStream);       
	    return outputStream.toByteArray();
	}
}
