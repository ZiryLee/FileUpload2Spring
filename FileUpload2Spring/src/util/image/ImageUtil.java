package util.image;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片处理工具类
 * 内部使用了EasyImage
 * @author ziry
 * @createDate 2016-11-01 10:33
 */
public class ImageUtil {

	/**
	 * 对图片裁剪，并把裁剪完的新图片保存 。
	 * 
	 * @param srcpath
	 *            源图片路径
	 * @param subpath
	 *            剪切图片存放路径
	 * @param x
	 *            剪切点x坐标
	 * @param y
	 *            剪切点y坐标
	 * @param endX
	 *            结束剪切点x坐标
	 * @param endY
	 *            结束剪切点y坐标
	 * @throws IOException
	 */
	public static void crop(String srcpath, 
							String subpath, 
							int startX, 
							int startY, 
							int endX, 
							int endY
							) throws IOException {
		
		util.image.EasyImage image = new util.image.EasyImage(srcpath);
		image.crop(startX, startY, endX, endY);
		image.saveAs(subpath);
	}
	
	/**
	 * 对图片裁剪，并把裁剪完的新图片保存 。
	 * 
	 * @param srcpath
	 *            源图片路径
	 * @param subpath
	 *            剪切图片存放路径
	 * @param x
	 *            剪切点x坐标
	 * @param y
	 *            剪切点y坐标
	 * @param endX
	 *            结束剪切点x坐标
	 * @param endY
	 *            结束剪切点y坐标
	 * @throws IOException
	 */
	public static void crop(String srcpath, int startX,int startY, int endX, int endY) throws IOException {
		util.image.EasyImage image = new util.image.EasyImage(srcpath);
		image.crop(startX, startY, endX, endY);
		image.save();
	}

	/**
	 * 截取图片中间正方型
	 * 
	 * @param srcpath
	 *            源图片路径
	 * @param subpath
	 *            剪切图片存放路径
	 * @param width
	 *            剪切宽度
	 * @param height
	 *            剪切高度
	 * @throws IOException
	 */
	public static void cropMiddle(String srcpath, String newpath)
			throws IOException {
		
		util.image.EasyImage image = new util.image.EasyImage(srcpath);
		
		int img_width = image.getWidth();
		int img_height = image.getHeight();

		int x = 0;
		int y = 0;
		int width = img_width;
		int height = img_height;

		if (img_width > img_height) {// 横的长方形
			x = (img_width / 2) - (img_height / 2);
			width = img_height;
		} else if (img_width < img_height) {// 竖的长方形
			y = (img_height / 2) - (img_width / 2);
			height = img_width;
		}

		image.crop(x, y, x+width, y+height);
		
		image.saveAs(newpath);
	}
	
	/**
	 * 截取图片中间正方型
	 * 
	 * @param srcpath
	 *            源图片路径
	 * @param subpath
	 *            剪切图片存放路径
	 * @param width
	 *            剪切宽度
	 * @param height
	 *            剪切高度
	 * @throws IOException
	 */
	public static void cropMiddle(String srcpath) throws IOException {
		util.image.EasyImage image = new util.image.EasyImage(srcpath);
		
		int img_width = image.getWidth();
		int img_height = image.getHeight();

		int x = 0;
		int y = 0;
		int width = img_width;
		int height = img_height;

		if (img_width > img_height) {// 横的长方形
			x = (img_width / 2) - (img_height / 2);
			width = img_height;
		} else if (img_width < img_height) {// 竖的长方形
			y = (img_height / 2) - (img_width / 2);
			height = img_width;
		}

		image.crop(x, y, x+width, y+height);
		
		image.save();
	}

	/**
	 * 质量压缩
	 * 
	 * @param destImagePath
	 *            被压缩文件路径
	 * @param quality
	 *            0.0 - 1.0的质量期望的质量水平。
	 * @return
	 * @throws Exception
	 */
	public static void qualityCompress(String destImagePath, float quality)
			throws Exception {
		FileOutputStream out = null;
		try {
			// 目标文件
			File resizedFile = new File(destImagePath);

			// 压缩
			Image targetImage = ImageIO.read(resizedFile);
			int width = targetImage.getWidth(null);
			int height = targetImage.getHeight(null);
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(targetImage, 0, 0, width, height, null);
			g.dispose();

			out = new FileOutputStream(resizedFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(image);
			jep.setQuality(quality, false);
			encoder.setJPEGEncodeParam(jep);
			encoder.encode(image);

		} catch (Exception e) {
			throw e;
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	/**
	 * 根据宽高压缩图片
	 * 
	 * @param sourceFile
	 *            源文件地址
	 * @param width
	 *            压缩后的宽
	 * @param height
	 *            压缩后的高
	 * @return
	 * @throws IOException
	 */
	public static String doCompress(String sourceFile, int width, int height)
			throws Exception {
		FileOutputStream out = null;
		try {

			if (sourceFile == null || sourceFile.length() == 0 || width <= 0
					|| height <= 0) {
				throw new IllegalArgumentException("参数不能为空!");
			}

			String newImage = null;
			File file = new File(sourceFile);
			if (!file.exists()) {
				return null;
			}
			Image srcFile = ImageIO.read(file);
			int new_w = width;
			int new_h = height;

			BufferedImage tag = new BufferedImage(new_w, new_h,
					BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(srcFile, 0, 0, new_w, new_h, null);

			String filePrex = sourceFile.substring(0,
					sourceFile.lastIndexOf('.'));
			newImage = filePrex + sourceFile.substring(filePrex.length());

			out = new FileOutputStream(newImage);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);

			jep.setQuality(1, true);
			encoder.encode(tag, jep);

			srcFile.flush();
			return newImage;

		} catch (Exception e) {
			throw e;
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	/**
	 * 等比例压缩算法： 算法思想：根据压缩基数和压缩比来压缩原图，生产一张图片效果最接近原图的缩略图
	 * 
	 * @param srcURL
	 *            原图地址
	 * @param deskURL
	 *            缩略图地址
	 * @param comBase
	 *            压缩基数（宽或高大于此值才压缩）
	 * @param scale
	 *            压缩限制(宽/高)比例 一般用1：
	 *            当scale>=1,缩略图height=comBase,width按原图宽高比例;若scale
	 *            <1,缩略图width=comBase,height按原图宽高比例
	 * @throws IOException
	 * @throws Exception
	 */
	public static void scaleCompress(String srcURL, String deskURL,
			double comBase, double scale) throws IOException {
		FileOutputStream deskImage = null;
		try {

			File srcFile = new java.io.File(srcURL);
			Image src = ImageIO.read(srcFile);

			int srcHeight = src.getHeight(null);
			int srcWidth = src.getWidth(null);

			int deskHeight = 0;// 缩略图高
			int deskWidth = 0;// 缩略图宽

			double srcScale = (double) srcHeight / srcWidth;

			/** 缩略图宽高算法 */
			if ((double) srcHeight > comBase || (double) srcWidth > comBase) {
				if (srcScale >= scale || 1 / srcScale > scale) {
					if (srcScale >= scale) {
						deskHeight = (int) comBase;
						deskWidth = srcWidth * deskHeight / srcHeight;
					} else {
						deskWidth = (int) comBase;
						deskHeight = srcHeight * deskWidth / srcWidth;
					}
				} else {
					if ((double) srcHeight > comBase) {
						deskHeight = (int) comBase;
						deskWidth = srcWidth * deskHeight / srcHeight;
					} else {
						deskWidth = (int) comBase;
						deskHeight = srcHeight * deskWidth / srcWidth;
					}
				}
			} else {
				deskHeight = srcHeight;
				deskWidth = srcWidth;
			}

			BufferedImage tag = new BufferedImage(deskWidth, deskHeight,
					BufferedImage.TYPE_3BYTE_BGR);
			tag.getGraphics().drawImage(src, 0, 0, deskWidth, deskHeight, null); // 绘制缩小后的图
			deskImage = new FileOutputStream(deskURL); // 输出到文件流
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(deskImage);
			encoder.encode(tag); // 近JPEG编码

		} catch (IOException ioe) {
			throw ioe;
		} finally {
			if (deskImage != null) {
				deskImage.close();
			}
		}
	}

	public static void main(String[] args) throws Exception {

		ImageUtil.cropMiddle("D:/temp/errorimg/t4.png","D:/temp/errorimg/ttt44.png");
	}
}

