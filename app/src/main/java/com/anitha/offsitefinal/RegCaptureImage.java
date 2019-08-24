package com.anitha.offsitefinal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegCaptureImage {

	static File cameraFile = null;
	private static Camera mCamera;
	private static RegCameraPreview mCameraPreview;
	static Dialog cameraDialog;
	private static String ClockNo;
	static Activity act = null;
	static void startCamera(String ClockNum, Activity activity)
	{  ClockNo =ClockNum;
		mCamera = getCameraInstance();
		act = activity;
		mCameraPreview = new RegCameraPreview(activity, mCamera,"potrait");
		cameraPreviewDialog(activity);
	}
	
	@SuppressLint("NewApi")
	private static Camera getCameraInstance() {
		Camera camera = null;
		try{
			//camera = Camera.open(CameraInfo.CAMERA_FACING_FRONT);
			camera = Camera.open(CameraInfo.CAMERA_FACING_BACK);
		} 
		catch (Exception e) {
			e.printStackTrace();
			Log.e("RegCaptureImage", " " + e);
		}
			return camera;
	}
	
	static void cameraPreviewDialog(final Activity activity) {
		activity.runOnUiThread(new Runnable() {
		@Override
		public void run() {
			act = activity;
			cameraDialog = null;
			cameraDialog = new Dialog(activity);
			
			cameraDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			cameraDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
			           WindowManager.LayoutParams.FLAG_FULLSCREEN);
			cameraDialog.setContentView(R.layout.reg_camera_dialog);
			cameraDialog.setCancelable(false);
			Button btncamicn=(Button) cameraDialog.findViewById(R.id.btncamicndia);
			Button btncamcancelicn=(Button) cameraDialog.findViewById(R.id.btncamicndia);
			FrameLayout preview = (FrameLayout)cameraDialog.findViewById(R.id.camera_preview);
			preview.addView(mCameraPreview);
			
			btncamicn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mCamera.takePicture(shutterCallback, null, mPicture);
				}
			});
			btncamcancelicn.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//GmsEmpRegs.onlypic=false;
					dismissCameraDailog();
					//Activity activity = App.getAct();
					activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				}
			});
			WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
			lp.copyFrom(cameraDialog.getWindow().getAttributes());
			Display display = activity.getWindowManager().getDefaultDisplay();
			int width = display.getWidth();
			int height = display.getHeight();
			lp.width = width;
			lp.height = height;
			cameraDialog.show();
			cameraDialog.getWindow().setAttributes(lp);
		}
		});
	}
	
	static ShutterCallback shutterCallback = new ShutterCallback() {
		@Override
		public void onShutter() {
			
		}
	};
	
	
	static PictureCallback mPicture = new PictureCallback() {
		@Override
		public void onPictureTaken(final byte[] data, Camera camera) {
				File pictureFile = getOutputMediaFile();
				if (pictureFile == null) {
				return;
				}
				try {
					cameraFile = null;
	                FileOutputStream fos = new FileOutputStream(pictureFile);
	                Bitmap bm=null;

	                if (data != null) {
	                    int screenWidth = act.getResources().getDisplayMetrics().widthPixels;
	                    int screenHeight = act.getResources().getDisplayMetrics().heightPixels;
	                    bm = BitmapFactory.decodeByteArray(data, 0, (data != null) ? data.length : 0);
	                    if (act.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
	                        Bitmap scaled = Bitmap.createScaledBitmap(bm, screenHeight, screenWidth, true);
	                        int w = scaled.getWidth();
	                        int h = scaled.getHeight();
	                        Matrix mtx = new Matrix();
	                        boolean cameraFront = false;
	                        int CameraEyeValue = setPhotoOrientation(act, cameraFront==true ? 1:0); // CameraID = 1 : front 0:back
	                        if(cameraFront) {
	                            if (CameraEyeValue == 270) {
	                                mtx.postRotate(90);
	                            } else if (CameraEyeValue == 90) {
	                                mtx.postRotate(270);
	                            }
	                        }else{
	                                mtx.postRotate(CameraEyeValue);
	                        }
	                        bm = Bitmap.createBitmap(scaled, 0, 0, w, h, mtx, true);
	                    }else{
	                        Bitmap scaled = Bitmap.createScaledBitmap(bm, screenWidth, screenHeight, true);
	                        bm=scaled;
	                    }
	                }

	                ByteArrayOutputStream stream = new ByteArrayOutputStream();
	                bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);
	                byte[] byteArray = stream.toByteArray();
	                fos.write(byteArray);
	                //fos.write(data);
	                fos.close();
	                cameraFile=pictureFile;
					//RegistrationActivity.uploadEmployeeImage();////////////////////////////////////////////////////////////////////
					//TTSService.speak("Photo Saved click on enroll");
					//GmsEmpRegs.updateuivaluescamerawebstatic();
	                
				} catch (Exception e) {
					e.printStackTrace();
					Log.e("RegCaptureImage", " " + e);
				}
				dismissCameraDailog();
				//Activity activity = App.getAct();
				act.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
		}
	};
	
	public static int setPhotoOrientation(Activity activity, int cameraId) {
	    CameraInfo info = new CameraInfo();
	    Camera.getCameraInfo(cameraId, info);
	    int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
	    int degrees = 0;
	    switch (rotation) {
	        case Surface.ROTATION_0:
	            degrees = 0;
	            break;
	        case Surface.ROTATION_90:
	            degrees = 90;
	            break;
	        case Surface.ROTATION_180:
	            degrees = 180;
	            break;
	        case Surface.ROTATION_270:
	            degrees = 270;
	            break;
	    }

	    int result;
	    if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
	        result = (info.orientation + degrees) % 360;
	        result = (360 - result) % 360;
	    } else {
	        result = (info.orientation - degrees + 360) % 360;
	    }
	    return result;
	}
		
	private static File getOutputMediaFile() {
			File mediaStorageDir = new File(
			Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"GmsFps");
			if (!mediaStorageDir.exists()) {
				if (!mediaStorageDir.mkdirs()) {
				Log.e("RegCaptureImage = " , "Failed to create folder");
				return null;
				}
			}
			String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
			.format(new Date());
			File mediaFile;
			mediaFile = new File(mediaStorageDir.getPath() + File.separator+ClockNo+".jpg");
			return mediaFile;
	}
		
	static byte[] resizeImage(byte[] input) {
		 Bitmap original = BitmapFactory.decodeByteArray(input , 0, input.length);
		 Bitmap resized = Bitmap.createScaledBitmap(original, 500, 500, true);
		 ByteArrayOutputStream blob = new ByteArrayOutputStream();
		 resized.compress(Bitmap.CompressFormat.JPEG, 60, blob);
		 return blob.toByteArray();
	}
		
	static void dismissCameraDailog() {
		if (mCamera != null) {
		mCamera = null;
		}
		if (cameraDialog != null) {
		cameraDialog.dismiss();
		}
	}
}
