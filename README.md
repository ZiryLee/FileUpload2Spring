# FileUpload2Spring
这是一个基于SpringMVC的文件上传封装，通过配置文件配置上传路径，上传文件只需save()一下，对图片文件可压缩，剪切等操作。  


基于spring mvc 的文件上传实体类  
内部包含MultipartFile  

示例：  
  
```
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
```
  
 
数组方式：
  1. @RequestParam(value = "files", required = false) List<MultipartFile> files  
  
  2. @RequestParam(value = "photos", required = false) MultipartFile[] files  
  
  3. MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
     List<MultipartFile> files = multipartRequest.getFiles("files");  
	        
```

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
```
  
  
删除图片：  
   1. UploadUtil.deleteFile( str );  
   2. 兼容旧版：  
	if( !UploadUtil.deleteFile( str ) ) {  
		UploadUtil.deleteWebFile(request, str);  
	}  
