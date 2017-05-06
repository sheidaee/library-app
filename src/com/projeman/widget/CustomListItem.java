package com.projeman.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.projeman.library.R;
import com.projeman.utility.ImageDownloader;

public class CustomListItem extends RelativeLayout {

	private Context context;	
	
	private LayoutInflater mInflater;
	private RelativeLayout rlContainer;
	private ImageView imageView;
	private ImageView imageRate;
	private TextView  tvtitle;		
	private TextView  tvpublisher;	
	private ImageDownloader idLogo;
	private ProgressBar progressBar;
	
	
	public CustomListItem(Context context) {
		super(context);
		this.context = context;
		init();
	}
	
	public CustomListItem(final Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}
	
	public CustomListItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}
	
	public void init() {
		
		Log.i("CUSTOM LIST", "Start Init");

		// Get singleton instance of ImageLoader
		idLogo = new ImageDownloader(context);
		
		
		mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		RelativeLayout listItemView = (RelativeLayout) mInflater.inflate(R.layout.widget_list_item, null);
		addView(listItemView);
		
		rlContainer = (RelativeLayout) 	listItemView.findViewById(R.id.rlContainer);
		imageView   = (ImageView)		listItemView.findViewById(R.id.ivLogo);		
		tvtitle     = (TextView) 		listItemView.findViewById(R.id.tvTitle);		
		tvpublisher = (TextView) 		listItemView.findViewById(R.id.tvPublisher);	
		progressBar = (ProgressBar)     listItemView.findViewById(R.id.progressBar);
				
	}	
	
	public void setTitle(String title) {
		tvtitle.setText(title);
	}
	
	public void setPublisher(String publisher) {
		tvpublisher.setText(publisher);
	}
	
	public void setImageRate(int resId) {
		imageRate.setImageResource(resId);
	}
	
	public void setImage(String imageURL) {		
		idLogo.displayImage(imageView, imageURL, progressBar);
	}
	
	public void setImage(int resId) {		
		imageView.setImageResource(resId);
	}
	
	public void setBackground(int color) {
		rlContainer.setBackgroundColor(color);
	}
	
	public void setFont(Typeface font) {
		tvtitle.setTypeface(font);
		tvpublisher.setTypeface(font);		
		((TextView)findViewById(R.id.tvPublisherName)).setTypeface(font);
	}
}
