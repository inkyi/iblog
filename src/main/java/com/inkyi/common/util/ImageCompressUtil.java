package com.inkyi.common.util;
/*
 * 1.图片大于1M 再进行处理
 * 2.如果图片不足500px 就不做任何动作
 * 手机端文件上传 大于10M
 */
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

/**
 * 图片缩放处理的工具类
 * @author InkYi
 *
 */

public class ImageCompressUtil {

	/**
	 * 以宽度为基准，等比例放缩图片
	 * 图片大于1M
	 * 压缩成宽度为500的图片
	 * @param files
	 * @param outputWidth 图片宽度
	 * @param outputHeight 图片高度
	 * @param flag 标识 0-不等比缩放 1-按照宽度等比缩放 2-按照高度等比缩放 3-按照最小等比缩放(和输入的宽高值有关)
	 * @return File数组
	 */
	public static File[] scalingImageForMult(MultipartFile[] files, int outputWidth, int outputHeight, int flag){
		List<String> list = new ArrayList<String>();
			try {
				for (MultipartFile file : files) {
					if(file.isEmpty()){
						continue;
					}
					String fileName = file.getOriginalFilename();//获取文件名
					String tempFilePath = getTempFilePath(fileName);//生成一个临时文件地址
					File imgFile = new File(tempFilePath);//生成一个临时文件的对象
					file.transferTo(imgFile);//将这个MultipartFile 写入临时文件
					Image img = ImageIO.read(imgFile);//生成一个图片对象
					long size = file.getSize();//文件大小
					//GIF压缩后会出问题，先屏蔽掉这个格式
					String fileType = fileName.substring( fileName.lastIndexOf( "." ) + 1 ).toLowerCase();
					if(fileType != "gif" ){
						if(size > (1024*1024*1) ){
							String newImagePath = imgProportionByWidth(imgFile, outputWidth, outputHeight, flag);
							list.add(newImagePath);
						}else{
							list.add(tempFilePath);
						}
					}else{
						list.add(tempFilePath);
					}
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return getFiles(list);
	}
	
	/**
	 * 以宽度为基准，等比例放缩图片
	 * 图片大于1M
	 * 压缩成宽度为500的图片
	 * @param files
	 * @param outputWidth 图片宽度
	 * @param outputHeight 图片高度
	 * @param flag 标识 0-不等比缩放 1-按照宽度等比缩放 2-按照高度等比缩放 3-按照最小等比缩放(和输入的宽高值有关)
	 * @return File数组
	 */
	public static File[] scalingImageForFile(File[] files, int outputWidth, int outputHeight, int flag){
		List<String> list = new ArrayList<String>();
			try {
				for (File file : files) {
					String fileName = file.getName();//获取文件名
					String tempFilePath = getTempFilePath(fileName);//生成一个临时文件地址
					File imgFile = new File(tempFilePath);//生成一个临时文件的对象
					// 写入临时文件
					FileChannel fcin = new FileInputStream(file).getChannel();
	                FileChannel fcout = new FileOutputStream(imgFile).getChannel();
	                long size = fcin.size();
	                fcin.transferTo(0, fcin.size(), fcout);
	                fcin.close();
	                fcout.close();
					long fileSize = file.length();//文件大小 单位B
					//GIF压缩后会出问题，先屏蔽掉这个格式
					String fileType = fileName.substring( fileName.lastIndexOf( "." ) + 1 ).toLowerCase();
					if(fileType != "gif" ){
						if(fileSize > (1000*1024*1) ){
							String newImagePath = imgProportionByWidth(imgFile, outputWidth, outputHeight, flag);
							list.add(newImagePath);
						}else{
							list.add(tempFilePath);
						}
					}else{
						list.add(tempFilePath);
					}
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return getFiles(list);
	}

	/**
	 * 指定图片宽度等比缩放
	 * @param imgFile 图片文件对象
	 * @auto InkYi
	 * @param outputWidth 输出宽度
	 * @param outputHeight 输出高度
	 * @param flag 标识 0-不等比缩放 1-按照宽度等比缩放 2-按照高度等比缩放 3-按照最小等比缩放(和输入的宽高值有关)
	 * @return 处理后的新图片路径
	 */
	public static String imgProportionByWidth(File imgFile, int outputWidth, int outputHeight, int flag){
		try {
			//处理图片
			Image img = ImageIO.read(imgFile);
			//等比缩放
			double rate1 = ((double) img.getWidth(null)) / (double) outputWidth;   
			double rate2 = ((double) img.getHeight(null)) / (double) outputHeight;
			int newWidth = 0;
			int newHeight = 0;
			double rate = 0;
			switch (flag) {
				case 0:
					newWidth = outputWidth;
		            newHeight = outputHeight;
				case 1:
					rate = rate1;
					break;
				case 2:
					rate = rate2;
					break;
				case 3:
					rate = rate1 > rate2 ? rate1 : rate2; 
					break;
				default:
					newWidth = outputWidth;
		            newHeight = outputHeight;
					break;
			}
			newWidth = (int) (((double) img.getWidth(null)) / rate);   
			newHeight = (int) (((double) img.getHeight(null)) / rate); 
			//获取文件后缀
			String fileName = imgFile.getName();
			String fileType = fileName.substring( fileName.lastIndexOf( "." ) + 1 ).toLowerCase();
			//处理不同的图片类型
			int imgType = getBufferedImageType(fileType);
			BufferedImage tag = new BufferedImage(newWidth, newHeight, imgType); 
            //压缩处理
			//Image.SCALE_DEFAULT 默认缩放 ;Image.SCALE_SMOOTH 生成的图片质量比较好 但速度慢 
			img = img.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
            tag.getGraphics().drawImage(img, 0, 0, null);
            //临时文件路径
            String tmpFilePath = getTempFilePath(fileName);
            //转化成流
            FileOutputStream out = new FileOutputStream(tmpFilePath);  
            //写入临时文件
            ImageIO.write(tag, fileType, out);
    		tag.flush();
            out.close();
            return tmpFilePath;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 处理不同的图片类型(应急方法)
	 * @author: InkYi
	 * 修改时间：2015年11月2日 - 下午2:59:04<br/>
	 * 功能说明：<br/>
	 * @param string 带有文件后缀的字符串
	 * @return
	 */
	private static int getBufferedImageType(String fileType) {
		int imageType = 0;
		switch (fileType) {
		case "png":
			imageType = BufferedImage.TYPE_INT_ARGB_PRE;
			break;
		case "gif":
			imageType = BufferedImage.TYPE_INT_ARGB_PRE;
			break;
		case "jpg":
			imageType = BufferedImage.TYPE_INT_RGB;
			break;
		case "jpeg":
			imageType = BufferedImage.TYPE_INT_RGB;
			break;
		case "bpm":
			imageType = BufferedImage.TYPE_INT_RGB;
			break;
		default:
			break;
		}
		return imageType;
	}
	
	
	/**
	 * 保存文件到服务器临时路径(用于文件上传)
	 * @param files 文件数组
	 * @param type 文件后缀
	 * @return 临时文件全路径
	 * @throws IOException 
	 */
	public static List<String> writeFile(File[] files, String type) {
		try {
			List<String> tmpFilePaths = new ArrayList<String>();
			for (File file : files) {
				InputStream is = new FileInputStream(file);
				String tmpFilePath = getTempFilePath(type);
				FileOutputStream fos = new FileOutputStream(tmpFilePath);
				byte[] readBytes = new byte[1024];// 缓冲大小
				int readed = 0;
				while ((readed = is.read(readBytes)) > 0) {
					fos.write(readBytes, 0, readed);
				}
				fos.close();
				tmpFilePaths.add(tmpFilePath);
			}
			return tmpFilePaths;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取临时文件全路径
	 * @param type 文件名 文件全路径 文件格式都可以
	 * @return 临时文件全路径
	 */
	public static String getTempFilePath(String type){
		String suffix = getSuffixByFilename(type);
		String folder=System.getProperty("java.io.tmpdir");
		String tmpFileName = (Math.random() * 10000 + "").replace(".", "");
		return folder + tmpFileName + suffix;
	}
	
	
	/**
	 * 根据给定的文件名,获取其后缀信息
	 * @param filename
	 * @return
	 */
	public static String getSuffixByFilename ( String filename ) {
		return filename.substring( filename.lastIndexOf( "." ) ).toLowerCase();
	}
	
	/**
	 * 将传入的地址集合转换成文件数组
	 * @param paths
	 * @return
	 */
	public static File[] getFiles (List<String> paths){
		List<File> files = new ArrayList<File>();
		for (String string : paths) {
			File file = new File(string);
			files.add(file);
		}
		 File[] fileArray = files.toArray(new File[files.size()]);
		 return fileArray;
	}
	
	public static String doCompress(InputStream is, int outputWidth, int outputHeight, int flag, String ext){
		String tmpFilePath = null;
		try {
			//处理图片
			Image img = ImageIO.read(is);
			//等比缩放
			double rate1 = ((double) img.getWidth(null)) / (double) outputWidth;   
			double rate2 = ((double) img.getHeight(null)) / (double) outputHeight;
			int newWidth = 0;
			int newHeight = 0;
			double rate = 0;
			switch (flag) {
				case 0:
					newWidth = outputWidth;
		            newHeight = outputHeight;
				case 1:
					rate = rate1;
					break;
				case 2:
					rate = rate2;
					break;
				case 3:
					rate = rate1 > rate2 ? rate1 : rate2; 
					break;
				default:
					newWidth = outputWidth;
		            newHeight = outputHeight;
					break;
			}
			newWidth = (int) (((double) img.getWidth(null)) / rate);   
			newHeight = (int) (((double) img.getHeight(null)) / rate); 
			BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);   
			img = img.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
            tag.getGraphics().drawImage(img, 0, 0, null);
            tmpFilePath = getTempFilePath(System.currentTimeMillis()+ "." + ext);
            FileOutputStream out = new FileOutputStream(tmpFilePath);   
            ImageIO.write(tag, ext, out);
    		tag.flush();
            out.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				is.close();
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		return tmpFilePath;
	}
	
	/**
	 * @author: data-zrb
	 * 修改时间：2015年11月3日 - 上午9:42:36<br/>
	 * 功能说明：传入流压缩图片<br/>
	 * @param is
	 * @param outputWidth
	 * @param outputHeight
	 * @param flag
	 * @return
	 */
	public static String doCompress(InputStream is, int outputWidth, int outputHeight, int flag){
		return doCompress(is,outputWidth,outputHeight,flag, "png");
	}
}