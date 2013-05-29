package wet.software.counter;

import wet.software.counter.settings.Vars;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class SqlHelper extends SQLiteOpenHelper {

	private Context myContext;
	
	private final String createCounterTable = "" +
			"CREATE TABLE " + Vars.tableCounter + " (" +
			"id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
			"name TEXT NOT NULL, " +
			"count INTEGER NOT NULL, " +
			"image BLOB" +
			");";
	
	public SqlHelper(Context context) {
		super(context, "Counter", null, 1);
		myContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(createCounterTable);
		Toast.makeText(myContext, "created", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
}

