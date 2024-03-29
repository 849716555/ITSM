package com.goldsunny.itsm.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

/** 
 *  图片压缩公共类
 * @author yangwy       
 * @version 1.0     
 * @created 2014-5-11 下午9:18:33
 */
public class ImageUtil {

	/**
	 * 通过降低图片的质量来压缩图片
	 * 
	 * @param bmp
	 *            要压缩的图片
	 * @param maxSize
	 *            压缩后图片大小的最大值,单位KB
	 * @return 压缩后的图片
	 */
	public static Bitmap compressByQuality(Bitmap bitmap, int maxSize) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			int quality = 100;
			bitmap.compress(CompressFormat.JPEG, quality, baos);
			System.out.println("图片压缩前大小：" + baos.toByteArray().length + "byte");
			while (baos.toByteArray().length / 1024 > maxSize) {
				quality -= 10;
				baos.reset();
				bitmap.compress(CompressFormat.JPEG, quality, baos);
				System.out.println("质量压缩到原来的" + quality + "%时大小为："
						+ baos.toByteArray().length + "byte");
			}
			System.out.println("图片压缩后大小：" + baos.toByteArray().length + "byte");
			bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0,
					baos.toByteArray().length);
		} catch (Exception e) {
			// TODO: handle exception
		}finally
		{
			try {
				baos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return bitmap;
	}
	
	/** 
	 * 描述:  通过降低图片的质量来压缩图片
	 * @param pathName 图片路径
	 * @param maxSize 压缩后图片大小的最大值,单位KB
	 * @return 压缩后的图片
	 */
	public static Bitmap compressByQuality(String pathName, int maxSize) 
	{
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;// 不去真的解析图片，只是获取图片的头部信息，包含宽高等；
		Bitmap bitmap = BitmapFactory.decodeFile(pathName, opts);
		return compressByQuality(bitmap,maxSize);
	}

	/**
	 * 通过压缩图片的尺寸来压缩图片大小
	 * 
	 * @param pathName
	 *            图片的完整路径
	 * @param targetWidth
	 *            缩放的目标宽度
	 * @param targetHeight
	 *            缩放的目标高度
	 * @return 缩放后的图片
	 */
	public static Bitmap compressBySize(String pathName, int targetWidth,
			int targetHeight) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;// 不去真的解析图片，只是获取图片的头部信息，包含宽高等；
		Bitmap bitmap = BitmapFactory.decodeFile(pathName, opts);
		// 得到图片的宽度、高度；
		int imgWidth = opts.outWidth;
		int imgHeight = opts.outHeight;
		// 分别计算图片宽度、高度与目标宽度、高度的比例；取大于等于该比例的最小整数；
		int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
		int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
		if (widthRatio > 1 || widthRatio > 1) {
			if (widthRatio > heightRatio) {
				opts.inSampleSize = widthRatio;
			} else {
				opts.inSampleSize = heightRatio;
			}
		}
		// 设置好缩放比例后，加载图片进内容；
		opts.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(pathName, opts);
		return bitmap;
	}

	/**
	 * 通过压缩图片的尺寸来压缩图片大小
	 * 
	 * @param bitmap
	 *            要压缩图片
	 * @param targetWidth
	 *            缩放的目标宽度
	 * @param targetHeight
	 *            缩放的目标高度
	 * @return 缩放后的图片
	 */
	public static Bitmap compressBySize(Bitmap bitmap, int targetWidth,
			int targetHeight) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			bitmap.compress(CompressFormat.JPEG, 100, baos);
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inJustDecodeBounds = true;
			bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0,
					baos.toByteArray().length, opts);
			// 得到图片的宽度、高度；
			int imgWidth = opts.outWidth;
			int imgHeight = opts.outHeight;
			// 分别计算图片宽度、高度与目标宽度、高度的比例；取大于该比例的最小整数；
			int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
			int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
			if (widthRatio > 1 && widthRatio > 1) {
				if (widthRatio > heightRatio) {
					opts.inSampleSize = widthRatio;
				} else {
					opts.inSampleSize = heightRatio;
				}
			}
			// 设置好缩放比例后，加载图片进内存；
			opts.inJustDecodeBounds = false;
			bitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0,
					baos.toByteArray().length, opts);
		} catch (Exception e) {
			// TODO: handle exception
		} finally
		{
			try {
				baos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return bitmap;
	}
	
	/**
	 * 通过压缩图片的尺寸来压缩图片大小，通过读入流的方式，可以有效防止网络图片数据流形成位图对象时内存过大的问题；
	 * 
	 * @param InputStream
	 *            要压缩图片，以流的形式传入
	 * @param targetWidth
	 *            缩放的目标宽度
	 * @param targetHeight
	 *            缩放的目标高度
	 * @return 缩放后的图片
	 * @throws IOException
	 *             读输入流的时候发生异常
	 */
	public static Bitmap compressBySize(InputStream is, int targetWidth,
			int targetHeight) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			byte[] buff = new byte[1024];
			int len = 0;
			while ((len = is.read(buff)) != -1) {
				baos.write(buff, 0, len);
			}

			byte[] data = baos.toByteArray();
	 		BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inJustDecodeBounds = true;
			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
			// 得到图片的宽度、高度；
			int imgWidth = opts.outWidth;
			int imgHeight = opts.outHeight;
			// 分别计算图片宽度、高度与目标宽度、高度的比例；取大于该比例的最小整数；
			int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
			int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
			if (widthRatio > 1 && widthRatio > 1) {
				if (widthRatio > heightRatio) {
					opts.inSampleSize = widthRatio;
				} else {
					opts.inSampleSize = heightRatio;
				}
			}
			// 设置好缩放比例后，加载图片进内存；
			opts.inJustDecodeBounds = false;
			return  BitmapFactory.decodeByteArray(data, 0, data.length, opts);
		} catch (Exception e) {
			// TODO: handle exception
		}finally
		{
			try {
				baos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	 
	/** 
	 * 描述:  从给定的路径加载图片，并指定是否自动旋转方向
	 * @param imgpath 图片路径
	 * @param adjustOritation 是否旋转图片
	 * @return
	 */
    public static Bitmap loadBitmap(String imgpath, boolean adjustOritation) {  
        if (!adjustOritation) {  
            return BitmapFactory.decodeFile(imgpath);  
        } else {  
            Bitmap bm = compressBySize(imgpath,360,480);  
            int digree = 0;  
            ExifInterface exif = null;  
            try {  
                exif = new ExifInterface(imgpath);  
            } catch (IOException e) {  
                e.printStackTrace();  
                exif = null;  
            }  
            if (exif != null) {  
                // 读取图片中相机方向信息  
                int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,  
                        ExifInterface.ORIENTATION_UNDEFINED);  
                // 计算旋转角度  
                switch (ori) {  
                case ExifInterface.ORIENTATION_ROTATE_90:  
                    digree = 90;  
                    break;  
                case ExifInterface.ORIENTATION_ROTATE_180:  
                    digree = 180;  
                    break;  
                case ExifInterface.ORIENTATION_ROTATE_270:  
                    digree = 270;  
                    break;  
                default:  
                    digree = 0;  
                    break;  
                }  
            }  
            if (digree != 0) {  
                // 旋转图片  
                Matrix m = new Matrix();  
                m.postRotate(digree);  
                bm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),  
                        bm.getHeight(), m, true);  
            }  
            return bm;  
        }  
    } 
    
    public static boolean compressCopy(String ComePath,String toPath,int MaxSize) 
    {
    	boolean bl=false;
    	FileOutputStream os=null; 
    	Bitmap bitmap=null;
    	try {
    		File file=new File(toPath.substring(0, toPath.lastIndexOf("/")+1));
    		if(!file.exists())
    			file.mkdirs();
    		File tureFile=new File(toPath);
    		if(!tureFile.exists())
    			tureFile.createNewFile();
    		bitmap=compressBySize(ComePath,360,480);   
    		bitmap=compressByQuality(bitmap,MaxSize);
    		os=new FileOutputStream(tureFile);
    		String fileType=ComePath.substring(ComePath.lastIndexOf(".")+1); 
    		bitmap.compress(getCompressType(fileType), 100, os);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("");
		}
    	finally
    	{
    		try {
    			if(bitmap!=null)
    				bitmap.recycle();
    			if(os!=null)
        		{
        			os.flush();
        			os.close();
        		} 
			} catch (IOException e2) {
				// TODO: handle exception
			} 
    			
    	} 
    	return bl;
    }
    
    public static CompressFormat getCompressType(String imageType)
    {
    	if("jpg".equals(imageType)||"jpeg".equals(imageType))
    	{
    		return Bitmap.CompressFormat.JPEG;
    	}else if("png".equals(imageType))
    	{
    		return Bitmap.CompressFormat.PNG;
    	}
    	return Bitmap.CompressFormat.JPEG;
    }

}

