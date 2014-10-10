import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class BoothAdapter extends BaseAdapter 
{
	
    private Context mContext;


    // references to our images
    private Integer[] mBoothType = 
    {
            R.drawable.doublebooth,
            R.drawable.singlebooth
    };
    
    public BoothAdapter(Context c) 
    {
        mContext = c;
    }

    public int getCount() 
    {
        return mBoothType.length;
    }

    public Object getItem(int position) 
    {
        return null;
    }

    public long getItemId( int position)  
    {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView( int position, View convertView, ViewGroup parent) 
    {
    	Company company = new Company();
        ImageView imageView;
        if (convertView == null) 
        {  
        	// if it's not recycled, initialize some attributes
            imageView = new ImageView( mContext );
            imageView.setLayoutParams( new GridView.LayoutParams(85, 85) );
            imageView.setScaleType( ImageView.ScaleType.CENTER_CROP );
            imageView.setPadding( 1, 1, 1, 1 );
            imageView.setOnTouchListener( new BoothListener( company ));
            
        } 
        else 
        {
            imageView = (ImageView) convertView;
        }
        
        
        imageView.draw
        
        return imageView;
    }

}