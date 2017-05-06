package com.projeman.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.download.URLConnectionImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.projeman.activities.Books;
import com.projeman.activities.BooksList.MyAsyncTask;



public class ImageDownloader {

	private final String directoryName = "snippet Image Downloder";
	
	private File cacheDir;
	private ImageLoader imageLoader;
	private DisplayImageOptions options;
	private ImageLoaderConfiguration config;
	
	public ImageDownloader(Context context) {
		
		cacheDir = StorageUtils.getOwnCacheDirectory(context, directoryName + "/Cache13");
		
		// Get singleton instance of ImageLoader
		imageLoader = ImageLoader.getInstance();
		
		// Create configuration for ImageLoader (all options are optional)
		config = new ImageLoaderConfiguration.Builder(context)
		            .threadPoolSize(2)
		            .threadPriority(Thread.MIN_PRIORITY + 2)
		            .denyCacheImageMultipleSizesInMemory()
		            .offOutOfMemoryHandling()
		            //.memoryCache(new UsingFreqLimitedMemoryCache(50 * 1024 * 1024))
		            .memoryCache(new UsingFreqLimitedMemoryCache(50))
		            .discCache(new UnlimitedDiscCache(cacheDir))
		            .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
		            .imageDownloader(new URLConnectionImageDownloader(5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)
		            .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
		            .enableLogging()
		            .build();
		
		// Initialize ImageLoader with created configuration. Do it once.
		imageLoader.init(config);		
		
		// Creates display image options for custom display task (all options are optional)
		options = new DisplayImageOptions.Builder()
		           .cacheInMemory()
		           .cacheOnDisc()
		           .imageScaleType(ImageScaleType.POWER_OF_2)
		           .build();
	}
	
	
	public void displayImage(final ImageView imageView, String imageURI, final ProgressBar spinner) {
		// Load and display image		
		imageLoader.displayImage(imageURI, imageView, options, new ImageLoadingListener() {
		    			
			
			@Override
		    public void onLoadingStarted() {
				
				class MyAsyncTask extends AsyncTask<String, Integer, Boolean> {
					
					private void setMax(int max) {
						spinner.setMax(max);				
					}
			        
					@Override
				    protected void onPreExecute() {

						spinner.setIndeterminate(false);
						///dialogPr.setIndeterminate(true);
						imageView.setVisibility(View.INVISIBLE);
						spinner.setVisibility(View.VISIBLE);
				    }
					
					@Override
					protected Boolean doInBackground(String... params) {
						//Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
						try{							
							
							setMax(1000000000);
							
							////
							int total = 1000000000;
							int length = 0;
														
							////
							spinner.setMax(total);
							while(length < total) {
																	
								////
								length++;
								////								
								publishProgress(total);
							}
							
							
							return true;
						}
						catch(Exception e) {
							return false;
						}
						//result t/f
					}

					@Override
				    protected void onPostExecute(Boolean result) {
						Log.i("aaa", "myAsyncTask finished its task.");
						
						imageView.setVisibility(View.INVISIBLE);
																								
				    }
					
					protected void onProgressUpdate(Integer... values) {					
						spinner.setProgress(values[0]);
					}
										
				}
				
				new MyAsyncTask().execute();
				
//		    	if(spinner != null) {
//		    		//spinner.setVisibility(View.VISIBLE);
//		    		spinner.setIndeterminate(false);
//		    		spinner.setVisibility(View.VISIBLE);
//		    	}
		    }
		    
		    @Override
		    public void onLoadingFailed(FailReason failReason) {
		    	if(spinner != null)
		    		spinner.setVisibility(View.INVISIBLE);
		    }
		    
		    @Override
		    public void onLoadingComplete(Bitmap loadedImage) {
		    	if(spinner != null) {
		    		spinner.setVisibility(View.GONE);
		    		imageView.setVisibility(View.VISIBLE);	
		    	}
		    }
		    
		    @Override
		    public void onLoadingCancelled() {
		        // Do nothing
		    }
		});
	}
}