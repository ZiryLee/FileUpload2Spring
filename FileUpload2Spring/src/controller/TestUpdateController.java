package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import util.upload.UploadUtil;
import util.upload.enntity.UploadEntity;
import util.upload.enntity.imp.DownloadEntityImp;
import util.upload.enntity.imp.UploadEntityImp;

@Controller
@RequestMapping("/")
public class TestUpdateController {
	
	@RequestMapping("/index.html")
	public String index() throws Exception {
		return "index";
	}
	
	
	@RequestMapping("/tUplode.html")
	public ModelAndView tUplode( 
	  		ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("tUplode",modelMap);
	}
	
	@RequestMapping("/tUplode.do")
	public ModelAndView tUplodeDo(@RequestParam(value = "file", required = false) MultipartFile file, 
			ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if( !UploadUtil.isNull(file) ) {
			UploadEntity uploadEntity = new UploadEntityImp(file);
			
			try {
				String photo = uploadEntity.savePhoto(true);
				modelMap.addAttribute("photo", photo);
				
				String photoSmall = uploadEntity.getSmallPhoto(160, 160);
				modelMap.addAttribute("photoSmall", photoSmall);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return new ModelAndView("tUplode",modelMap);
	}
	
	
	@RequestMapping("/tDownload.html")
	public ModelAndView tDownload( 
	  		ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = request.getParameter("url");
		if(url!=null && !url.equals("")) {
			UploadEntity uploadEntity = new DownloadEntityImp(url);
			
			String photo = uploadEntity.savePhoto(true);
			modelMap.addAttribute("photo", photo);
			
			String photoSmall = uploadEntity.getSmallPhoto(160, 160);
			modelMap.addAttribute("photoSmall", photoSmall);
		}
		
		return new ModelAndView("tDownload",modelMap);
	}
	
}
