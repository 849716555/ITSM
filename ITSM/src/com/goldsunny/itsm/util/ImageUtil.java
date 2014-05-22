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
 *  ͼƬѹ��������
 * @author yangwy       
 * @version 1.0     
 * @created 2014-5-11 ����9:18:33
 */
public class ImageUtil {

	/**
	 * ͨ������ͼƬ��������ѹ��ͼƬ
	 * 
	 * @param bmp
	 *            Ҫѹ����ͼƬ
	 * @param maxSize
	 *            ѹ����ͼƬ��С�����ֵ,��λKB
	 * @return ѹ�����ͼƬ
	 */
	public static Bitmap compressByQuality(Bitmap bitmap, int maxSize) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			int quality = 100;
			bitmap.compress(CompressFormat.JPEG, quality, baos);
			System.out.println("ͼƬѹ��ǰ��С��" + baos.toByteArray().length + "byte");
			while (baos.toByteArray().length / 1024 > maxSize) {
				quality -= 10;
				baos.reset();
				bitmap.compress(CompressFormat.JPEG, quality, baos);
				System.out.println("����ѹ����ԭ����" + quality + "%ʱ��СΪ��"
						+ baos.toByteArray().length + "byte");
			}
			System.out.println("ͼƬѹ�����С��" + baos.toByteArray().length + "byte");
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
	 * ����:  ͨ������ͼƬ��������ѹ��ͼƬ
	 * @param pathName ͼƬ·��
	 * @param maxSize ѹ����ͼƬ��С�����ֵ,��λKB
	 * @return ѹ�����ͼƬ
	 */
	public static Bitmap compressByQuality(String pathName, int maxSize) 
	{
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;// ��ȥ��Ľ���ͼƬ��ֻ�ǻ�ȡͼƬ��ͷ����Ϣ��������ߵȣ�
		Bitmap bitmap = BitmapFactory.decodeFile(pathName, opts);
		return compressByQuality(bitmap,maxSize);
	}

	/**
	 * ͨ��ѹ��ͼƬ�ĳߴ���ѹ��ͼƬ��С
	 * 
	 * @param pathName
	 *            ͼƬ������·��
	 * @param targetWidth
	 *            ���ŵ�Ŀ����
	 * @param targetHeight
	 *            ���ŵ�Ŀ��߶�
	 * @return ���ź��ͼƬ
	 */
	public static Bitmap compressBySize(String pathName, int targetWidth,
			int targetHeight) {
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;// ��ȥ��Ľ���ͼƬ��ֻ�ǻ�ȡͼƬ��ͷ����Ϣ��������ߵȣ�
		Bitmap bitmap = BitmapFactory.decodeFile(pathName, opts);
		// �õ�ͼƬ�Ŀ�ȡ��߶ȣ�
		int imgWidth = opts.outWidth;
		int imgHeight = opts.outHeight;
		// �ֱ����ͼƬ��ȡ��߶���Ŀ���ȡ��߶ȵı�����ȡ���ڵ��ڸñ�������С������
		int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
		int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
		if (widthRatio > 1 || widthRatio > 1) {
			if (widthRatio > heightRatio) {
				opts.inSampleSize = widthRatio;
			} else {
				opts.inSampleSize = heightRatio;
			}
		}
		// ���ú����ű����󣬼���ͼƬ�����ݣ�
		opts.inJustDecodeBounds = false;
		bitmap = BitmapFactory.decodeFile(pathName, opts);
		return bitmap;
	}

	/**
	 * ͨ��ѹ��ͼƬ�ĳߴ���ѹ��ͼƬ��С
	 * 
	 * @param bitmap
	 *            Ҫѹ��ͼƬ
	 * @param targetWidth
	 *            ���ŵ�Ŀ����
	 * @param targetHeight
	 *            ���ŵ�Ŀ��߶�
	 * @return ���ź��ͼƬ
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
			// �õ�ͼƬ�Ŀ�ȡ��߶ȣ�
			int imgWidth = opts.outWidth;
			int imgHeight = opts.outHeight;
			// �ֱ����ͼƬ��ȡ��߶���Ŀ���ȡ��߶ȵı�����ȡ���ڸñ�������С������
			int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
			int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
			if (widthRatio > 1 && widthRatio > 1) {
				if (widthRatio > heightRatio) {
					opts.inSampleSize = widthRatio;
				} else {
					opts.inSampleSize = heightRatio;
				}
			}
			// ���ú����ű����󣬼���ͼƬ���ڴ棻
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
	 * ͨ��ѹ��ͼƬ�ĳߴ���ѹ��ͼƬ��С��ͨ���������ķ�ʽ��������Ч��ֹ����ͼƬ�������γ�λͼ����ʱ�ڴ��������⣻
	 * 
	 * @param InputStream
	 *            Ҫѹ��ͼƬ����������ʽ����
	 * @param targetWidth
	 *            ���ŵ�Ŀ����
	 * @param targetHeight
	 *            ���ŵ�Ŀ��߶�
	 * @return ���ź��ͼƬ
	 * @throws IOException
	 *             ����������ʱ�����쳣
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
			// �õ�ͼƬ�Ŀ�ȡ��߶ȣ�
			int imgWidth = opts.outWidth;
			int imgHeight = opts.outHeight;
			// �ֱ����ͼƬ��ȡ��߶���Ŀ���ȡ��߶ȵı�����ȡ���ڸñ�������С������
			int widthRatio = (int) Math.ceil(imgWidth / (float) targetWidth);
			int heightRatio = (int) Math.ceil(imgHeight / (float) targetHeight);
			if (widthRatio > 1 && widthRatio > 1) {
				if (widthRatio > heightRatio) {
					opts.inSampleSize = widthRatio;
				} else {
					opts.inSampleSize = heightRatio;
				}
			}
			// ���ú����ű����󣬼���ͼƬ���ڴ棻
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
	 * ����:  �Ӹ�����·������ͼƬ����ָ���Ƿ��Զ���ת����
	 * @param imgpath ͼƬ·��
	 * @param adjustOritation �Ƿ���תͼƬ
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
                // ��ȡͼƬ�����������Ϣ  
                int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,  
                        ExifInterface.ORIENTATION_UNDEFINED);  
                // ������ת�Ƕ�  
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
                // ��תͼƬ  
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

