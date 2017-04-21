package util.upload;


import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import org.springframework.web.multipart.MultipartFile;

/**
 * 用户上传工具类 2016-10-31 15:21
 * 
 * @author ziry
 */
public class UploadUtil {

	protected final static Logger logger = Logger.getLogger(UploadUtil.class);

	/**
	 * 基础路径，带盘符的基础路径 D:/..../ （读取配置文件）
	 */
	private static String BASEPATH = "";

	/**
	 * 根目录（读取配置文件）
	 */
	private static String ROOTPATE = "";
	
	/**
	 * 图片文件尾缀
	 */
	public final static String PhotoSuffix = "bmp,jpg,jpeg,png,gif";
	
	/**
	 * 视频文件尾缀
	 */
	public final static String VideoSuffix = "avi,rm,rmvb,mpeg,mpg,dat,mov,qt,asf,wmv,flv,mp4";
	
	/**
	 * 音频文件尾缀
	 */
	public final static String AudioSuffix = "mp3,amr";
	
	/**
	 * 静态语句块，程序加载则执行且只执行一次。
	 */
	static {

		// 得到配置文件路径
		String propPath = UploadUtil.class.getClassLoader().getResource("upload.properties").getPath().replaceAll("%20", " ");

		try {
			// 加载配置文件
			FileInputStream fis = new FileInputStream(propPath);
			Properties prop = new Properties();
			prop.load(fis);

			// 得到配置信息
			BASEPATH = prop.getProperty("BASEPATH");
			ROOTPATE = prop.getProperty("ROOTPATE");

		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}

	}

	/**
	 * 得到图片文件路径（jpg,png,gif,ioc ...）
	 * 
	 * @param isMkdirs
	 *            是否创建目录
	 * @return 硬盘地址路径，如 D:/..../
	 */
	public static String getPhotoPath(boolean isMkdirs) {
		String path = BASEPATH + ROOTPATE + "/" + getYearMonth() + "/photo/";
		if (isMkdirs) {
			File pathFile = new File(path);
			if (!pathFile.exists()) {
				pathFile.mkdirs();
			}
		}
		return path;
	}

	/**
	 * 得到视频文件路径 （mp4,avi ...）
	 * 
	 * @param isMkdirs
	 *            是否创建目录
	 * @return 硬盘地址路径，如 D:/..../
	 */
	public static String getVideoPath(boolean isMkdirs) {
		String path = BASEPATH + ROOTPATE + "/" + getYearMonth() + "/video/";
		if (isMkdirs) {
			File pathFile = new File(path);
			if (!pathFile.exists()) {
				pathFile.mkdirs();
			}
		}
		return path;
	}

	/**
	 * 得到音频文件路径 （mp3,amr ...）
	 * 
	 * @param isMkdirs
	 *            是否创建目录
	 * @return 硬盘地址路径，如 D:/..../
	 */
	public static String getAudioPath(boolean isMkdirs) {
		String path = BASEPATH + ROOTPATE + "/" + getYearMonth() + "/audio/";
		if (isMkdirs) {
			File pathFile = new File(path);
			if (!pathFile.exists()) {
				pathFile.mkdirs();
			}
		}
		return path;
	}

	/**
	 * 得到其他文件路径（word,txt ...）
	 * 
	 * @param isMkdirs
	 *            是否创建目录
	 * @return
	 */
	public static String getOtherPath(boolean isMkdirs) {
		String path = BASEPATH + ROOTPATE + "/" + getYearMonth() + "/other/";
		if (isMkdirs) {
			File pathFile = new File(path);
			if (!pathFile.exists()) {
				pathFile.mkdirs();
			}
		}
		return path;
	}
	
	/**
	 * 通过文件名后缀获取对应路径
	 * @param fileSuffix 文件后缀
	 * @return
	 */
	public static String getPath(String fileSuffix) {
		
		String savePath = BASEPATH;// 默认路径
		
		//得到文件种类
		String fileClass = ContentTypeUtil.getFileClassByFileType(fileSuffix);
		
		// 图片文件路径
		if (fileClass.equals(ContentTypeUtil.PhotoFile)) {
			savePath = UploadUtil.getPhotoPath(true);
		} 
		
		// 音频文件路径
		else if (fileClass.equals(ContentTypeUtil.AudioFile)) {
			savePath = UploadUtil.getAudioPath(true);
		} 
		
		// 视频文件路径
		else if (fileClass.equals(ContentTypeUtil.VideoFile)) {
			savePath = UploadUtil.getVideoPath(true);
		} 
		
		// 其他文件路径
		else {
			savePath = UploadUtil.getOtherPath(true);
		}
		return savePath;
	}
	
	/**
	 * 通过文件名后缀获取对应web地址
	 * @param fileSuffix 文件后缀
	 * @return
	 */
	public static String getURL(String fileSuffix) {
		
		String url = BASEPATH;// 默认路径
		
		//得到文件种类
		String fileClass = ContentTypeUtil.getFileClassByFileType(fileSuffix);
		
		// 图片文件路径
		if (fileClass.equals(ContentTypeUtil.PhotoFile)) {
			url = UploadUtil.getPhotoURL();
		} 
		
		// 音频文件路径
		else if (fileClass.equals(ContentTypeUtil.AudioFile)) {
			url = UploadUtil.getAudioURL();
		} 
		
		// 视频文件路径
		else if (fileClass.equals(ContentTypeUtil.VideoFile)) {
			url = UploadUtil.getVideoURL();
		} 
		
		// 其他文件路径
		else {
			url = UploadUtil.getOtherURL();
		}
		return url;
	}

	/**
	 * 得到图片文件路径（jpg,png,gif,ioc ...）
	 * 
	 * @return web地址路径，如 /..../
	 */
	public static String getPhotoURL() {
		return ROOTPATE + "/" + getYearMonth() + "/photo/";
	}

	/**
	 * 得到视频文件路径 （mp4,avi ...）
	 * 
	 * @return web地址路径，如 /..../
	 */
	public static String getVideoURL() {
		return ROOTPATE + "/" + getYearMonth() + "/video/";
	}

	/**
	 * 得到音频文件路径 （mp3,amr ...）
	 * 
	 * @return web地址路径，如 /..../
	 */
	public static String getAudioURL() {
		return ROOTPATE + "/" + getYearMonth() + "/audio/";
	}

	/**
	 * 得到其他文件路径（word,txt ...）
	 * 
	 * @return web地址路径，如 /..../
	 */
	public static String getOtherURL() {
		return ROOTPATE + "/" + getYearMonth() + "/other/";
	}

	/**
	 * 获取当前年和月
	 * 
	 * @return yyyyMM
	 */
	public static String getYearMonth() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");// 设置日期格式
		return df.format(new Date());
	}

	/**
	 * 得到文件名
	 * 
	 * @return 如：16110111550359090357
	 */
	public static String getFileName() {
		int number = 8;
		int max = 9;
		int min = 1;
		for (int i = 1; i < number; i++) {
			min = min * 10;
			max = max * 10 + 9;
		}
		return new SimpleDateFormat("yyMMddHHmmss").format(new Date()) + (int) (Math.random() * (max - min) + min);
	}

	/**
	 * 得到文件名后缀
	 * 
	 * @param filename
	 *            文件名:xxx.jpg
	 * @return 如：.jpg
	 */
	public static String getFileSuffix(String filename) throws IllegalArgumentException {
		if (filename == null || filename.trim().length() == 0) {
			return "";
		}
		if (filename.lastIndexOf('.') == -1) {
			return "";
		}
		return filename.substring(filename.lastIndexOf('.') + 1, filename.length());
	}

	/**
	 * 删除文件
	 * 
	 * @param webURL
	 *            web可访问地址（不带http）如：/xxx/xxx.jpg
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static boolean deleteFile(String webURL) throws IllegalArgumentException {

		if (webURL == null || webURL.length() == 0) {
			return false;
		}

		File file = new File(BASEPATH + webURL);

		if (file.isFile() && file.exists()) {
			boolean isDelete = file.delete();
			logger.info("删除文件("+isDelete+") ：" + BASEPATH + webURL);
			return isDelete;
		}

		return false;

	}

	/**
	 * 删除文件
	 * 
	 * @param webURL
	 *            web可访问地址（不带http）如：/xxx/xxx.jpg
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static boolean deleteWebFile(HttpServletRequest request, String webURL) throws IllegalArgumentException {

		if (request == null) {
			throw new IllegalArgumentException("request 参数不能为空!");
		}
		if (webURL == null || webURL.length() == 0) {
			return false;
		}

		String path = request.getSession().getServletContext().getRealPath("/") + webURL;

		File file = new File(path);
		if (file.isFile() && file.exists()) {
			boolean isDelete = file.delete();
			logger.info("删除文件("+isDelete+") ：" + path);
			return isDelete;
		}

		return false;

	}

	/**
	 * 判断上传文件是否为NULL
	 * 
	 * @param MultipartFile
	 * @return NULL为TRUE
	 */
	public static boolean isNull(MultipartFile file) {
		if (file == null) {
			return true;
		}
		String originalFilename = file.getOriginalFilename();
		if (originalFilename == null || originalFilename.length() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断上传文件是否为图片
	 * 
	 * @param MultipartFile
	 * @return NULL为TRUE
	 */
	public static boolean isPhoto(MultipartFile file) {
		if (file == null) {
			return false;
		}
		String originalFilename = file.getOriginalFilename();
		if (originalFilename == null || originalFilename.length() == 0) {
			return false;
		}
		// 获取文件后缀
		String fileSuffix = UploadUtil.getFileSuffix(originalFilename);
		if (fileSuffix == null || fileSuffix.length() == 0) {
			// 通过Mime类型获取文件类型
			fileSuffix = ContentTypeUtil.getFileTypeByMimeType(file.getContentType());
		}
		if(fileSuffix==null || fileSuffix.equals("")) {
			return false;
		}
		if(PhotoSuffix.indexOf(fileSuffix.toLowerCase()) != -1){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断上传文件是否为视频
	 * 
	 * @param MultipartFile
	 * @return NULL为TRUE
	 */
	public static boolean isVideo(MultipartFile file) {
		if (file == null) {
			return false;
		}
		String originalFilename = file.getOriginalFilename();
		if (originalFilename == null || originalFilename.length() == 0) {
			return false;
		}
		// 获取文件后缀
		String fileSuffix = UploadUtil.getFileSuffix(originalFilename);
		if (fileSuffix == null || fileSuffix.length() == 0) {
			// 通过Mime类型获取文件类型
			fileSuffix = ContentTypeUtil.getFileTypeByMimeType(file.getContentType());
		}
		if(fileSuffix==null || fileSuffix.equals("")) {
			return false;
		}
		if(VideoSuffix.indexOf(fileSuffix.toLowerCase()) != -1){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断上传文件是否为音频
	 * 
	 * @param MultipartFile
	 * @return NULL为TRUE
	 */
	public static boolean isAudio(MultipartFile file) {
		if (file == null) {
			return false;
		}
		String originalFilename = file.getOriginalFilename();
		if (originalFilename == null || originalFilename.length() == 0) {
			return false;
		}
		// 获取文件后缀
		String fileSuffix = UploadUtil.getFileSuffix(originalFilename);
		if (fileSuffix == null || fileSuffix.length() == 0) {
			// 通过Mime类型获取文件类型
			fileSuffix = ContentTypeUtil.getFileTypeByMimeType(file.getContentType());
		}
		if(fileSuffix==null || fileSuffix.equals("")) {
			return false;
		}
		if(AudioSuffix.indexOf(fileSuffix.toLowerCase()) != -1){
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		// System.out.println(UploadUtil.getFileName());
		/*
		 * UploadUtil.download(
		 * "http://wx.qlogo.cn/mmopen/PiajxSqBRaEIpuTaAn4TMwddeBj2FkkicK2nhA1O6ZNWlGnNUbMJLMH90OGqUibZvmuOBWyPByehf8UFPXLDnOuyA/0"
		 * , "testwxphoto.jpg", "D:/temp");
		 */
		System.out.println(UploadUtil.getFileSuffix("aaa.jpt"));
	}

}
