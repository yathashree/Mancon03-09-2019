package com.anitha.offsitefinal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.view.SurfaceHolder.Callback;

public class CaptureImage {

    private static final String TAG = "CaptureImage Class - ";
    public static volatile boolean capturingImage = false;

    @SuppressWarnings("deprecation")
    public static void takePhoto(final Context context, final String deviceNo, final boolean captureImageFromServer) {
        final SurfaceView preview = new SurfaceView(context);
        SurfaceHolder holder = preview.getHolder();
        // deprecated setting, but required on Android versions prior to 3.0
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        holder.addCallback(new Callback() {
            @Override
            //The preview must happen at or after this point or takePicture fails
            public void surfaceCreated(SurfaceHolder holder) {
                Log.e("Surface created",TAG);

                Camera camera = null;

                try {
                    camera = Camera.open(CameraInfo.CAMERA_FACING_FRONT);//Camera.open();

                    Log.e("Opened camera",TAG);

                    try {
                        camera.setPreviewDisplay(holder);
                        //camera.autoFocus(autoFocusCallBack);

                    } catch (IOException e) {
                        capturingImage = false;
                        throw new RuntimeException(e);
                    }

                    camera.startPreview();
                    final Camera finalCamera = camera;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finalCamera.takePicture(null, null, new PictureCallback() {

                                @Override
                                public void onPictureTaken(byte[] data, Camera camera) {
                                    try {
                                        Log.e("Took picture",TAG);

                                      //  UploadImage.sendCapturedImageToServer(deviceNo, data, captureImageFromServer);
                                        camera.release();
                                        capturingImage = false;
                                    } catch (Exception e) {
                                        // TODO Auto-generated catch block
                                        capturingImage = false;
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    }, 1000);
                    Log.e("Started preview",TAG);




                } catch (Exception e) {
                    if (camera != null)
                        camera.release();
                    capturingImage = false;
                    throw new RuntimeException(e);
                }
            }

            @Override public void surfaceDestroyed(SurfaceHolder holder) {}
            @Override public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
        });

        WindowManager wm = (WindowManager)context
                .getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                200, 200, //Must be at least 1x1
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                0,
                //Don't know if this is a safe default
                PixelFormat.UNKNOWN);

        //Don't set the preview visibility to GONE or INVISIBLE
        wm.addView(preview, params);
    }
}
