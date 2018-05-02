package com.example.os.crm.ui.camera;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.FaceDetectionListener;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;

import com.example.os.crm.utils.CamParaUtil;
import com.example.os.crm.utils.FileUtil;
import com.example.os.crm.utils.ImageUtil;

import org.json.JSONException;
import org.json.JSONObject;



public class CameraInterface {
	private static final String TAG = "YanZi";
	private Camera mCamera;
	private Camera.Parameters mParams;
	private boolean isPreviewing = false;
	private float mPreviwRate = -1f;
	private int mCameraId = -1;
	private boolean isGoolgeFaceDetectOn = false;
	private static CameraInterface mCameraInterface;

	//识别回调
//	private MyCallBack mCallBack;

	public interface CamOpenOverCallback{
		public void cameraHasOpened();
	}

//	public void setmCallBack(MyCallBack mCallBack) {
//		this.mCallBack = mCallBack;
//	}

	private CameraInterface(){

	}
	public static synchronized CameraInterface getInstance(){
		if(mCameraInterface == null){
			mCameraInterface = new CameraInterface();
		}
		return mCameraInterface;
	}
	/**��Camera
	 * @param callback
	 */
	public void doOpenCamera(CamOpenOverCallback callback, int cameraId){
		Log.i(TAG, "Camera open....");

		mCamera = Camera.open(cameraId);
		mCameraId = cameraId;
		if(callback != null){
			callback.cameraHasOpened();
		}
	}

	int x,y;

	/**����Ԥ��
	 * @param holder
	 * @param previewRate
	 */
	public void doStartPreview(SurfaceHolder holder, float previewRate){
		Log.i(TAG, "doStartPreview...");
		if(isPreviewing){
			mCamera.stopPreview();
			return;
		}
		if(mCamera != null){
			mParams = mCamera.getParameters();
			mParams.setPictureFormat(PixelFormat.JPEG);//�������պ�洢��ͼƬ��ʽ
			CamParaUtil.getInstance().printSupportPictureSize(mParams);
			CamParaUtil.getInstance().printSupportPreviewSize(mParams);
			//����PreviewSize��PictureSize
			Size pictureSize = CamParaUtil.getInstance().getPropPictureSize(
					mParams.getSupportedPictureSizes(),previewRate, 1);
			Size previewSize = CamParaUtil.getInstance().getPropPreviewSize(
					mParams.getSupportedPreviewSizes(), previewRate, 1);
			mParams.setPictureSize(pictureSize.width,pictureSize.height);
			mParams.setPreviewSize(previewSize.width, previewSize.height);

//			mCamera.setPreviewCallback(new Camera.PreviewCallback() {
//				@Override
//				public void onPreviewFrame(byte[] data, Camera camera) {
//					mByte = new byte[data.length];
//					mByte = data;
//				}
//			});

			mCamera.setDisplayOrientation(90);

			CamParaUtil.getInstance().printSupportFocusMode(mParams);
			List<String> focusModes = mParams.getSupportedFocusModes();
			if(focusModes.contains("continuous-video")){
				mParams.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
			}
			mCamera.setParameters(mParams);	

			try {
				mCamera.setPreviewDisplay(holder);
				mCamera.startPreview();//����Ԥ��
			} catch (IOException e) {

				e.printStackTrace();
			}

			isPreviewing = true;
			mPreviwRate = previewRate;

			mParams = mCamera.getParameters(); //����getһ��
			Log.i(TAG, "222PreviewSize--With = " + mParams.getPreviewSize().width
					+ "Height = " + mParams.getPreviewSize().height);
			Log.i(TAG, "222PictureSize--With = " + mParams.getPictureSize().width
					+ "Height = " + mParams.getPictureSize().height);
		}
	}
	/**
	 * ֹͣԤ�����ͷ�Camera
	 */
	public void doStopCamera(){
		if(null != mCamera)
		{
			mCamera.setPreviewCallback(null);
			mCamera.stopPreview(); 
			isPreviewing = false; 
			mPreviwRate = -1f;
			mCamera.release();
			mCamera = null;     
		}
	}
	/**
	 * ����
	 */
	public void doTakePicture(){
		if(isPreviewing && (mCamera != null)){

//			saveBitmap();
			mCamera.takePicture(mShutterCallback, null, null);
		}
	}
	
	/**��ȡCamera.Parameters
	 * @return
	 */
	public Camera.Parameters getCameraParams(){
		if(mCamera != null){
			mParams = mCamera.getParameters();
			return mParams;
		}
		return null;
	}
	/**��ȡCameraʵ��
	 * @return
	 */
	public Camera getCameraDevice(){
		return mCamera;
	}
	

	public int getCameraId(){
		return mCameraId;
	}
	
	
	
	
		
	

	/*Ϊ��ʵ�����յĿ������������ձ�����Ƭ��Ҫ���������ص�����*/
	ShutterCallback mShutterCallback = new ShutterCallback() 
	//���Ű��µĻص������������ǿ����������Ʋ��š����ꡱ��֮��Ĳ�����Ĭ�ϵľ������ꡣ
	{
		public void onShutter() {

			Log.i(TAG, "myShutterCallback:onShutter...");
		}
	};
	PictureCallback mRawCallback = new PictureCallback() 
	// �����δѹ��ԭ���ݵĻص�,����Ϊnull
	{

		public void onPictureTaken(byte[] data, Camera camera) {

			Log.i(TAG, "myRawCallback:onPictureTaken...");

		}
	};

	PictureCallback mJpegPictureCallback = new PictureCallback() 
	//��jpegͼ�����ݵĻص�,����Ҫ��һ���ص�
	{
		public void onPictureTaken(byte[] data, Camera camera) {

			Log.i(TAG, "myJpegCallback:onPictureTaken...");
			Bitmap b = null;
			if(null != data){
				b = BitmapFactory.decodeByteArray(data, 0, data.length);//data���ֽ����ݣ����������λͼ
				mCamera.stopPreview();
				isPreviewing = false;
			}
			//����ͼƬ��sdcard
			if(null != b)
			{
				//����FOCUS_MODE_CONTINUOUS_VIDEO)֮��myParam.set("rotation", 90)ʧЧ��
				//ͼƬ��Ȼ������ת�ˣ�������Ҫ��ת��
				Bitmap rotaBitmap = ImageUtil.getRotateBitmap(b, -90.0f);

				//回调方法，需要执行的操作
//				mCallBack.toDistinguish();
			}
			//�ٴν���Ԥ��
			mCamera.startPreview();
			isPreviewing = true;
		}
	};

	byte[] mByte;



	private String storagePath;
	private static final File parentPath = Environment.getExternalStorageDirectory();
	private static final String DST_FOLDER_NAME = "PlayCamera";
	private long dataTake;

	private void saveBitmap(){
		try {
			Size size = mParams.getPreviewSize();
			YuvImage image = new YuvImage(mByte, mParams.getPreviewFormat(),
					size.width, size.height, null);
			storagePath = parentPath.getAbsolutePath()+"/" + DST_FOLDER_NAME;
			dataTake = System.currentTimeMillis();
			String jpegName = storagePath + "/" + dataTake +".jpg";
			File file = new File(storagePath);

//			FileOutputStream fout = new FileOutputStream(jpegName);
//			BufferedOutputStream bos = new BufferedOutputStream(fout);
//			bos.flush();
//			bos.close();
//			FileOutputStream filecon = new FileOutputStream(file);
//			//按4:3来缩放图片
//			int width = image.getWidth();
//			int height = image.getHeight();
//			int iWidth;
//			int iHeigth;
//			iHeigth = (width*3)/4;
//			if(iHeigth>=height){
//				iHeigth = height;
//				iWidth = (height*4)/3;
//			}else{
//				iWidth = width;
//			}
//			image.compressToJpeg(
//					new Rect(0, 0, image.getWidth(), image.getHeight()), 100,
//					fout);
//			bos.flush();
//			bos.close();



		} catch (Exception e) {

		}
	}


}
