package com.example.impliciteintent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class HomeActivity extends Activity
{
	Button buttonPhotoGrid,buttonBrowser,buttonCamera,buttonCall,buttonData;
	EditText editData;
	ImageView image;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		buttonPhotoGrid=(Button) findViewById(R.id.button1);
		buttonBrowser=(Button) findViewById(R.id.button2);
		buttonCamera=(Button) findViewById(R.id.button3);	
		buttonCall=(Button) findViewById(R.id.button4);
		buttonData=(Button) findViewById(R.id.button5);
		
		editData=(EditText) findViewById(R.id.editText1);
		image=(ImageView) findViewById(R.id.imageView1);
		
		buttonPhotoGrid.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) 
			{
				Intent intentGrid = new Intent();
				intentGrid.setAction("wimc.action.photo_grid");
				startActivity(intentGrid);//-->OS
				
				
			}
		});
		buttonCamera.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intentcamer=new Intent();
				intentcamer.setAction("android.media.action.IMAGE_CAPTURE");
				startActivityForResult(intentcamer, 101);
				
			}
		});
		
		buttonBrowser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intentBrowse = new Intent();
				intentBrowse.setAction("android.intent.action.VIEW");
				
				//pass url to browser
				 String data=editData.getText().toString();
				 Uri u= Uri.parse(data);
				 intentBrowse.setData(u);
				
				
				startActivity(intentBrowse);
			}
		});
		
		
		buttonCall.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intentcall=new Intent();
				intentcall.setAction("android.intent.action.CALL");
				String number=editData.getText().toString();
				Uri u= Uri.parse(number);
				intentcall.setData(u);
				startActivity(intentcall);
			}
		});
		
		buttonData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try
				{
				ConnectivityManager dataManager;
				dataManager  = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
				Method dataMtd;
				
					dataMtd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
					dataMtd.setAccessible(true);
					dataMtd.invoke(dataManager, true);
					// TODO Auto-generated catch block
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
		});
		
		
	}//eof activity
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == 101)
		{
			Bundle bn= data.getExtras();
			Bitmap bmp=(Bitmap) bn.get("data");
			image.setImageBitmap(bmp);
		}
		
	}
	
	
	
	
	
	
	
	
}
