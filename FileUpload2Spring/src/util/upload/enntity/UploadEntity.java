package util.upload.enntity;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import util.image.ImageUtil;
import util.upload.UploadUtil;

public abstract class UploadEntity {
	
	protected final Logger logger = Logger.getLogger(UploadEntity.class);
	
	//图片宽高大于此比例则压缩
	protected final double comBase  = 1000d;

	// 创建你要保存的文件的路径
	protected String path = "";

	// 获取该文件的文件名
	protected String originalFilename = "";

	// 获取文件后缀
	protected String fileSuffix = "";

	// 创建文件名
	protected String filename = "";

	// 实际文件路径
	protected String filePath = "";

	// 可供web访问的路径
	protected String webURL = "";

	// 缩略图实际文件路径
	protected String smallPhotoFilePath = "";

	// 缩略图可供web访问的路径
	protected String smallPhotoWebURL = "";

	// 文件大小（KB）
	protected long fileSizeByKB = 0;
	
	//记录是否已上传
	protected boolean isUpload = false;

	/**
	 * 上传文件
	 * 
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	protected abstract void save() throws IllegalStateException, IOException;

	/**
	 * 上传图片文件
	 * 
	 * @param isCompress
	 *            是否压缩
	 * @return 可供web访问的路径(/xxx/xxx.jpg)
	 * @throws Exception
	 */
	public abstract String savePhoto(boolean isCompress) throws Exception ;

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
	public abstract String savePhoto(boolean isCompress, boolean cropMiddle) throws Exception ;

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
	public abstract String savePhoto(boolean isCompress, boolean cropMiddle, int width, int height) throws Exception ;
	
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
	public abstract String savePhoto(boolean isCompress, int x, int y, int width, int height) throws Exception ;

	/**
	 * 获取缩略图，注意一定要先调用uploadPhoto(),此方法是对已上传文件进行的拷贝压缩
	 * @param width
	 * @param height
	 * @return
	 * @throws Exception
	 */
	public String getSmallPhoto(int width, int height) throws Exception {
		
		if ( isUpload == false) {
			throw new NullPointerException("未检测到上传文件，请先调用save..()方法");
		}

		// 缩略图实际文件路径
		smallPhotoFilePath = path + filename + "_small"+width+"." + fileSuffix;

		// 缩略图可供web访问的路径
		smallPhotoWebURL = UploadUtil.getPhotoURL() + filename + "_small"+width+"." + fileSuffix;

		// 取正方型
		try {
			ImageUtil.cropMiddle(filePath, smallPhotoFilePath);
		}catch (Exception e) {
			logger.error("图片取正方型失败!filePath="+filePath);
			logger.error(e);
		}

		// 按比例压缩
		try {
			ImageUtil.doCompress(smallPhotoFilePath, width, height);
		}catch (Exception e) {
			logger.error("按比例压缩失败!smallPhotoFilePath="+smallPhotoFilePath);
			logger.error(e);
		}
		return smallPhotoWebURL;
	}

	/**
	 * 上传视频文件
	 * 
	 * @return 可供web访问的路径(/xxx/xxx.mp4)
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public abstract String saveVideo() throws IllegalStateException, IOException ;

	/**
	 * 上传音频文件
	 * 
	 * @return 可供web访问的路径(/xxx/xxx.mp3)
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public abstract String saveAudio() throws IllegalStateException, IOException ;

	/**
	 * 上传其他类型文件
	 * 
	 * @return 可供web访问的路径(/xxx/xxx.txt)
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public String saveOther() throws IllegalStateException, IOException {
		// 创建你要保存的文件的路径
		path = UploadUtil.getOtherPath(true);
		
		save();

		// 可供web访问的路径
		webURL = UploadUtil.getOtherURL() + filename + "." + fileSuffix;

		return webURL;
	}

	public String getPath() {
		return path;
	}

	public String getOriginalFilename() {
		return originalFilename;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public String getFilename() {
		return filename;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getWebURL() {
		return webURL;
	}

	public String getSmallPhotoFilePath() {
		return smallPhotoFilePath;
	}

	public String getSmallPhotoWebURL() {
		return smallPhotoWebURL;
	}

	public long getFileSizeByKB() {
		return fileSizeByKB;
	}

	public String getFullFileName() {
		return filename + "." + fileSuffix;
	}
	
	
	/**删除上传的文件**/
	public boolean delete() {
		
		//删除上传缩略图的文件
		deleteSmallPhoto();
		
		if (path == null || path.length() == 0) {
			return false;
		}

		File file = new File(path);

		if (file.isFile() && file.exists()) {
			return file.delete();
		}
		
		return false;
	}
	
	/**删除上传缩略图的文件**/
	public boolean deleteSmallPhoto() {
		
		if (smallPhotoFilePath == null || smallPhotoFilePath.length() == 0) {
			return false;
		}
		
		File file = new File(smallPhotoFilePath);
		
		if (file.isFile() && file.exists()) {
			return file.delete();
		}
		
		return false;
	}
	
	/**
	 * 更换后缀
	 * @param newFileSuffix
	 * @return
	 */
	public UploadEntity changeFileSuffix(String newFileSuffix) {
		
		if ( isUpload == false) {
			throw new NullPointerException("未检测到上传文件，请先调用save..()方法");
		}
		
		this.fileSuffix = newFileSuffix;
		
		StringBuffer pathBuffer = new StringBuffer(filePath);
		
		pathBuffer.replace(filePath.lastIndexOf('.')+1, filePath.length(), newFileSuffix);
		
		filePath = pathBuffer.toString();
		
		webURL = UploadUtil.getAudioURL() + filename + "." + fileSuffix;
		
		return this;
	}
	
}
