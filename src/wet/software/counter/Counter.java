package wet.software.counter;

import android.graphics.Bitmap;

public class Counter {

	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
	private int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	private Bitmap image = null;
	public Bitmap getImage() {
		return image;
	}
	public void setImage(Bitmap image) {
		this.image = image;
	}
	
	public Counter(String name)
	{
		this.name = name;		
		this.count = 0;
		this.image = null;
	}
	public Counter(String name, int count)
	{
		this.name = name;		
		this.count = count;
		this.image = null;
	}
	public Counter(int id, String name, int count)
	{
		this.id = id;
		this.name = name;		
		this.count = count;
		this.image = null;
	}
	public Counter(int id, String name, int count, Bitmap image)
	{
		this.id = id;
		this.name = name;		
		this.count = count;
		this.image = image;
	}
	
	public void addUp()
	{
		this.count++;
	}
	
	public void addDown()
	{
		this.count--;
	}
}
