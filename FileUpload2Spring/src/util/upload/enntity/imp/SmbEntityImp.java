package util.upload.enntity.imp;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import util.upload.SmbUtil;
import util.upload.UploadUtil;

/**
 * 通过Smb协议传输一份文件到指定服务器
 * @author ziry
 * @Time 2017-04-24
 */
public class SmbEntityImp extends UploadEntityImp{

	public SmbEntityImp(MultipartFile file) {
		super(file);
	}

	@Override
	public String savePhoto(boolean isCompress, int x, int y, int width, int height) throws Exception {
		
		String result = super.savePhoto(isCompress, x, y, width, height);
		
		SmbUtil.smbPut(UploadUtil.getPhotoURL(), super.filePath);
		
		return result;
	}

	@Override
	public String savePhoto(boolean isCompress) throws Exception {
		String result = super.savePhoto(isCompress);
		
		SmbUtil.smbPut(UploadUtil.getPhotoURL(), super.filePath);
		
		return result;
	}

	@Override
	public String savePhoto(boolean isCompress, boolean cropMiddle) throws Exception {
		String result = super.savePhoto(isCompress, cropMiddle);
		SmbUtil.smbPut(UploadUtil.getPhotoURL(), super.filePath);
		return result;
	}

	@Override
	public String savePhoto(boolean isCompress, boolean cropMiddle, int width, int height) throws Exception {
		String result = super.savePhoto(isCompress, cropMiddle, width, height);
		SmbUtil.smbPut(UploadUtil.getPhotoURL(), super.filePath);
		return result;
	}

	@Override
	public String saveVideo() throws IllegalStateException, IOException {
		String result = super.saveVideo();
		SmbUtil.smbPut(UploadUtil.getVideoURL(), super.filePath);
		return result;
	}

	@Override
	public String saveAudio() throws IllegalStateException, IOException {
		String result = super.saveAudio();
		SmbUtil.smbPut(UploadUtil.getAudioURL(), super.filePath);
		return result;
	}

	@Override
	public String saveOther() throws IllegalStateException, IOException {
		String result = super.saveOther();
		SmbUtil.smbPut(UploadUtil.getOtherURL(), super.filePath);
		return result;
	}

	@Override
	public String getSmallPhoto(int width, int height) throws Exception {
		String result = super.getSmallPhoto(width, height);
		
		SmbUtil.smbPut(UploadUtil.getPhotoURL(), super.smallPhotoFilePath);
		
		return result;
	}
	

}
