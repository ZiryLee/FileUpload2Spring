package util.upload.enntity.imp;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import util.image.ImageUtil;
import util.upload.ContentTypeUtil;
import util.upload.UploadUtil;
import util.upload.enntity.UploadEntity;

/**
 * WEB地址文件加载到本地保存
 * 示例：<br/>
 * <pre>
 * UploadEntity uploadEntity = new DownloadEntityImp(http://....;);
 * System.out.println(uploadEntity.savePhoto(true));
 * System.out.println(uploadEntity.getSmallPhoto(160, 160));
 * </pre>
 * 
 * @author ziry 2016-11-01 10:05
 */
public class DownloadEntityImp extends UploadEntity{
	
	private String fileUrl;
	
	// mime类型
	private String contentType = "";

	public DownloadEntityImp(String fileUrl) {

		this.fileUrl = fileUrl;

		if (fileUrl == null || fileUrl.trim().length() == 0) {
			throw new NullPointerException("fileUrl 不能为空!");
		}

	}

	/**
	 * 上传文件
	 * 
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	protected void save() throws IllegalStateException, IOException {

		// 构造URL
		URL url = new URL(fileUrl);

		// 打开连接
		URLConnection con = url.openConnection();

		// 设置请求超时为5s
		con.setConnectTimeout(5 * 1000);

		// 输入流
		InputStream is = con.getInputStream();

		// 1K的数据缓冲
		byte[] bs = new byte[1024];

		// 读取到的数据长度
		int len;

		// 获取文件mime类型
		contentType = con.getContentType();

		// 通过Mime类型获取文件类型
		fileSuffix = ContentTypeUtil.getFileTypeByMimeType(contentType);

		// 路径
		path = UploadUtil.getPath(fileSuffix);

		// 文件名
		filename = UploadUtil.getFileName();

		// 全路径
		filePath = path + filename + "." + fileSuffix;

		// 访问地址
		webURL = UploadUtil.getURL(fileSuffix) + filename + "." + fileSuffix;

		// 输出的文件流
		OutputStream os = new FileOutputStream(filePath);

		// 开始读取
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}

		// 完毕，关闭所有链接
		os.close();
		is.close();
		
		isUpload = true;
	}

	/**
	 * 上传图片文件
	 * 
	 * @param isCompress
	 *            是否压缩
	 * @return 可供web访问的路径(/xxx/xxx.jpg)
	 * @throws Exception
	 */
	public String savePhoto(boolean isCompress) throws Exception {

		save();

		// 判断图片是否压缩
		if (isCompress) {
			try {
				ImageUtil.scaleCompress(filePath, filePath, comBase, 1d);
			}catch (Exception e) {
				logger.error("压缩图片失败!filePath="+filePath);
				logger.error(e);
			}
		}

		return webURL;
	}

	/**
	 * 上传图片文件
	 * 
	 * @param isCompress
	 *            是否压缩
	 * @param cropMiddle
	 *            是否取正方型
	 * @return 可供web访问的路径(/xxx/xxx.jpg)
	 * @throws Exception
	 */
	public String savePhoto(boolean isCompress, boolean cropMiddle) throws Exception {

		save();
		
		// 判断图片是否压缩
		if (isCompress) {

			try {
				ImageUtil.scaleCompress(filePath, filePath, 900d, 1d);
			} catch (Exception e) {
				logger.error("压缩图片失败!filePath=" + filePath);
				logger.error(e);
			}

		}

		// 判断图片是否取正方型
		if (cropMiddle) {
			try {
				ImageUtil.cropMiddle(filePath, filePath);
			} catch (Exception e) {
				logger.error("图片取正方型失败!filePath=" + filePath);
				logger.error(e);
			}
		}

		return webURL;
	}

	/**
	 * 上传图片文件
	 * 
	 * @param isCompress
	 *            是否压缩
	 * @param cropMiddle
	 *            是否取正方型
	 * @param width
	 *            压缩宽度
	 * @param height
	 *            压缩高度
	 * @return 可供web访问的路径(/xxx/xxx.jpg)
	 * @throws Exception
	 */
	public String savePhoto(boolean isCompress, boolean cropMiddle, int width, int height) throws Exception {

		save();
		
		// 判断图片是否压缩
		if (isCompress) {
			try {
				ImageUtil.scaleCompress(filePath, filePath, 900d, 1d);
			} catch (Exception e) {
				logger.error("压缩图片失败!filePath=" + filePath);
				logger.error(e);
			}
		}

		// 判断图片是否取正方型
		if (cropMiddle) {
			try {
				ImageUtil.cropMiddle(filePath, filePath);
			} catch (Exception e) {
				logger.error("图片取正方型失败!filePath=" + filePath);
				logger.error(e);
			}
		}

		// 按比例压缩
		try {
			ImageUtil.doCompress(filePath, width, height);
		} catch (Exception e) {
			logger.error("按比例压缩失败!filePath=" + filePath);
			logger.error(e);
		}

		return webURL;
	}

	/**
	 * 上传图片文件
	 * 
	 * @param isCompress
	 *            是否压缩
	 * @param cropMiddle
	 *            是否取正方型
	 * @param x
	 *            截取起始坐标X点
	 * @param y
	 *            截取起始坐标X点
	 * @param width
	 *            压缩宽度
	 * @param height
	 *            压缩高度
	 * @return 可供web访问的路径(/xxx/xxx.jpg)
	 * 
	 * @throws Exception
	 */
	@Override
	public String savePhoto(boolean isCompress, int x, int y, int width, int height) throws Exception {

		// 创建你要保存的文件的路径
		path = UploadUtil.getPhotoPath(true);
				
		save();

		// 可供web访问的路径
		webURL = UploadUtil.getPhotoURL() + filename + "." + fileSuffix;

		// 判断图片是否压缩
		if (isCompress) {
			try {
				ImageUtil.scaleCompress(filePath, filePath, comBase, 1d);
			}catch (Exception e) {
				logger.error("压缩图片失败!filePath="+filePath);
				logger.error(e);
			}
		}

		// 按比例截取
		try {
			ImageUtil.crop(filePath, x, y, x+width, y+height);
		}catch (Exception e) {
			logger.error("按比例压缩失败!filePath="+filePath);
			logger.error(e);
		}
		
		return webURL;
	}

	/**
	 * 上传其他类型文件
	 * 
	 * @return 可供web访问的路径(/xxx/xxx.txt)
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public String saveFile() throws IllegalStateException, IOException {

		save();

		return webURL;
	}

	/**
	 * 上传视频文件
	 * 
	 * @return 可供web访问的路径(/xxx/xxx.mp4)
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public String saveVideo() throws IllegalStateException, IOException {
		
		save();

		return webURL;
	}

	/**
	 * 上传音频文件
	 * 
	 * @return 可供web访问的路径(/xxx/xxx.mp3)
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public String saveAudio() throws IllegalStateException, IOException {
		
		save();

		return webURL;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public String getContentType() {
		return contentType;
	}


}
