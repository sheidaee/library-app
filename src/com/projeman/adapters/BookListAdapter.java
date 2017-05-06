package com.projeman.adapters;

import java.util.List;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.projeman.activities.Books;
import com.projeman.library.R;
import com.projeman.widget.CustomListItem;

public class BookListAdapter extends BaseAdapter {

	private final String TAG = "*** CategoryListAdapter ***";
	private final int[] colors = new int[] { 0xFFE5E5E5, 0xFFFFFFFF };
	
	private LayoutInflater myInflater;
	private List<Books> bookList;		
	
	private ViewHolder holder;
	
	private Typeface face;
	
	public BookListAdapter(Context context, List<Books> bookList, Typeface Face) {
		myInflater = LayoutInflater.from(context);
		
		this.bookList = bookList;
		this.face = Face;					
		
		Log.i(TAG, "Adapter setuped successfully.");
						
	}
	
	@Override
	public int getCount() {
		return bookList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
		

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		        		
		if (convertView == null) {
        	convertView = myInflater.inflate(R.layout.list_item, null);
        	holder 		= new ViewHolder();
        	
        	holder.customListItem = (CustomListItem) convertView.findViewById(R.id.customWidgetItem);                	
        	        				
			convertView.setTag(holder);										        	        	
	        
        } else {
        	holder = (ViewHolder) convertView.getTag();
        }
		
		
		int colorPos = position % colors.length;
		holder.customListItem.setBackground(colors[colorPos]);
						
		holder.customListItem.setTitle(bookList.get(position).getBookTitle());
		holder.customListItem.setPublisher(bookList.get(position).getBookPublisher());
		holder.customListItem.setImage(bookList.get(position).getBookImageURL());
		holder.customListItem.setFont(face);			
		
		return convertView;
	}
		
	static class ViewHolder {					
		CustomListItem customListItem;		
	}
				
}