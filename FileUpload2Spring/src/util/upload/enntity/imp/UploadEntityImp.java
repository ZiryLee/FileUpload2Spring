package util.upload.enntity.imp;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import util.image.ImageUtil;
import util.upload.ContentTypeUtil;
import util.upload.UploadUtil;
import util.upload.enntity.UploadEntity;


/**
  基于spring mvc 的文件上传实体类<br/>
  内部包含MultipartFile<br/>
  示例：<br/>
  
  <pre>
   @RequestMapping(value = "/testUpload.html", method = RequestMethod.POST)
   public ModelAndView testUploadAction(
  		@RequestParam(value = "photo", required = false) MultipartFile file, 
  		ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    if( !UploadUtil.isNull(file) ) {
  		UploadEntity uploadEntity = new UploadEntityImp(file);
  
  		try {
  			System.out.println(uploadEntity.savePhoto(true));
  			System.out.println(uploadEntity.getSmallPhoto(160, 160));
  		} catch (Exception e) {
  			e.printStackTrace();
  			printLog(e);
  		}
    }
  	return createModelAndView("WeCharTest/testUpload", modelMap);
   }
  
 
  数组方式：
  1. @RequestParam(value = "files", required = false) List<MultipartFile> files,
  2. @RequestParam(value = "photos", required = false) MultipartFile[] files,
  3. MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
 	  List<MultipartFile> files = multipartRequest.getFiles("files");
	        

  @RequestMapping(value = "/testUpload.html", method = RequestMethod.POST)
  public ModelAndView testUploadAction(
  		@RequestParam(value = "photos", required = false) MultipartFile[] files, 
  		ModelMap modelMap,
  		HttpServletRequest request, HttpServletResponse response) {
  
  	try {
  		UploadEntity uploadEntity = null;
 		for (MultipartFile file : files) {
  			if (!UploadUtil.isNull(file)) {
  				uploadEntity = new UploadEntityImp(file);
  				System.out.println(uploadEntity.savePhoto(true));
  				System.out.println(uploadEntity.getSmallPhoto(160, 160));
  			}
  		}
  	} catch (Exception e) {
  		e.printStackTrace();
  	}
  
  	return createModelAndView("WeCharTest/testUpload", modelMap);
  }
  
  
   	删除图片：
   	1. UploadUtil.deleteFile( str );
   	2. 兼容旧版：
		if( !UploadUtil.deleteFile( str ) ) {
			UploadUtil.deleteWebFile(request, str);
		}
  </pre>
 * 
 * @author ziry 2016-11-01 10:05
 */
public class UploadEntityImp extends UploadEntity{

	private MultipartFile file;

	public UploadEntityImp(MultipartFile file) {

		this.file = file;

		if (file == null) {
			throw new NullPointerException("MultipartFil 不能为空!");
		}

		fileSizeByKB = file.getSize() / 1024;
		
		// 获取该文件的文件名
		originalFilename = file.getOriginalFilename();

		// 获取文件后缀
		fileSuffix = UploadUtil.getFileSuffix(originalFilename);
		if (fileSuffix == null || fileSuffix.length() == 0) {
			// 通过Mime类型获取文件类型
			fileSuffix = ContentTypeUtil.getFileTypeByMimeType(file.getContentType());
		}

		// 创建文件名
		filename = UploadUtil.getFileName();
	}
	
	/**
	 * 带文件大小限制的构造
	 * 
	 * @param file
	 *            MultipartFile
	 * @param maxSize2KB
	 *            限制文件大小
	 * @throws IOException
	 */
	public UploadEntityImp(MultipartFile file, long maxSizeByKB) throws MaxUploadSizeExceededException {

		this.file = file;

		if (file == null) {
			throw new NullPointerException("MultipartFil 不能为空!");
		}

		fileSizeByKB = file.getSize() / 1024;

		if (fileSizeByKB > maxSizeByKB) {
			throw new MaxUploadSizeExceededException(maxSizeByKB * 1024);
		}
		
		// 获取该文件的文件名
		originalFilename = file.getOriginalFilename();

		// 获取文件后缀
		fileSuffix = UploadUtil.getFileSuffix(originalFilename);
		if (fileSuffix == null || fileSuffix.length() == 0) {
			// 通过Mime类型获取文件类型
			fileSuffix = ContentTypeUtil.getFileTypeByMimeType(file.getContentType());
		}

		// 创建文件名
		filename = UploadUtil.getFileName();


	}

	/**
	 * 上传文件
	 * 
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	protected void save() throws IllegalStateException, IOException {
		
		// 实际文件路径
		filePath = path + filename + "." + fileSuffix;

		File targetFile = new File(filePath);

		// 保存
		file.transferTo(targetFile);
		
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
		
		if( UploadUtil.isPhoto(file) == false ) {
			throw new ClassFormatError("非图片格式（"+UploadUtil.PhotoSuffix+"）");
		}

		// 创建你要保存的文件的路径
		path = UploadUtil.getPhotoPath(true);
				
		save();

		// 可供web访问的路径
		webURL = UploadUtil.getPhotoURL() + filename + "." + fileSuffix;

		// 判断图片是否压缩
		if (isCompress && !fileSuffix.toLowerCase().equals("gif") && !fileSuffix.toLowerCase().equals("png")) {
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
		
		if( UploadUtil.isPhoto(file) == false ) {
			throw new ClassFormatError("非图片格式（"+UploadUtil.PhotoSuffix+"）");
		}
		
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

		// 判断图片是否取正方型
		if (cropMiddle) {
			try {
				ImageUtil.cropMiddle(filePath, filePath);
			}catch (Exception e) {
				logger.error("图片取正方型失败!filePath="+filePath);
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
		
		if( UploadUtil.isPhoto(file) == false ) {
			throw new ClassFormatError("非图片格式（"+UploadUtil.PhotoSuffix+"）");
		}

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

		// 判断图片是否取正方型
		if (cropMiddle) {
			try {
				ImageUtil.cropMiddle(filePath, filePath);
			}catch (Exception e) {
				logger.error("图片取正方型失败!filePath="+filePath);
				logger.error(e);
			}
		}

		// 按比例压缩
		try {
			ImageUtil.doCompress(filePath, width, height);
		}catch (Exception e) {
			logger.error("按比例压缩失败!filePath="+filePath);
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
		if( UploadUtil.isPhoto(file) == false ) {
			throw new ClassFormatError("非图片格式（"+UploadUtil.PhotoSuffix+"）");
		}

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
	 * 上传视频文件
	 * 
	 * @return 可供web访问的路径(/xxx/xxx.mp4)
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public String saveVideo() throws IllegalStateException, IOException {
		
		if( UploadUtil.isVideo(file) == false ) {
			throw new ClassFormatError("非视频格式（"+UploadUtil.VideoSuffix+"）");
		}
		
		// 创建你要保存的文件的路径
		path = UploadUtil.getVideoPath(true);
				
		save();

		// 可供web访问的路径
		webURL = UploadUtil.getVideoURL() + filename + "." + fileSuffix;

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
		
		if( UploadUtil.isAudio(file) == false ) {
			throw new ClassFormatError("非音频格式（"+UploadUtil.AudioSuffix+"）");
		}
		
		// 创建你要保存的文件的路径
		path = UploadUtil.getAudioPath(true);
		
		save();

		// 可供web访问的路径
		webURL = UploadUtil.getAudioURL() + filename + "." + fileSuffix;

		return webURL;
	}

}
