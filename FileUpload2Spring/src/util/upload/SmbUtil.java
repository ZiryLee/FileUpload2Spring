package util.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

public class SmbUtil {
	
	public static final Logger logger = Logger.getLogger(SmbUtil.class);
	
	/**
	 * 基础路径
	 */
	private static String SMBPATH = "";
	
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
			SMBPATH = prop.getProperty("SMBPATH");

		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}

	}
	
	/**
	 * 从局域网中共享文件中得到文件并保存在本地磁盘上
	 * 
	 * @param remoteUrl
	 *            共享电脑路径 如：smb//administrator:123456@127.0.0.1/smb/1221.zip
	 *            , smb为共享文件 注：如果一直出现连接不上，有提示报错，并且错误信息是 用户名活密码错误 则修改共享机器的文件夹选项
	 *            查看 去掉共享简单文件夹的对勾即可。
	 * @param localDir
	 *            本地路径 如：D:/
	 */
	public static void smbGet(String remoteUrl, String localDir) {
		InputStream in = null;
		OutputStream out = null;
		try {
			SmbFile smbFile = new SmbFile(SMBPATH+remoteUrl);
			String fileName = smbFile.getName();
			File localFile = new File(localDir + File.separator + fileName);
			in = new BufferedInputStream(new SmbFileInputStream(smbFile));
			out = new BufferedOutputStream(new FileOutputStream(localFile));
			byte[] buffer = new byte[1024];
			while ((in.read(buffer)) != -1) {
				out.write(buffer);
				buffer = new byte[1024];
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				if(out != null) {
					out.close();
				}
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}

	/**
	 * 把本地磁盘中的文件上传到局域网共享文件下
	 * 
	 * @param remoteUrl
	 *            共享电脑路径 如：smb//administrator:123456@127.0.0.1/smb
	 * @param localFilePath
	 *            本地路径 如：D:/
	 */
	public static void smbPut(String remoteUrl, String localFilePath) {
		InputStream in = null;
		OutputStream out = null;
		try {
			File localFile = new File(localFilePath);
			String fileName = localFile.getName();
			
			SmbFile remoteFolder = new SmbFile(SMBPATH + remoteUrl);
			if (!remoteFolder.exists()) {
				remoteFolder.mkdirs();
			}
			
			SmbFile remoteFile = new SmbFile(SMBPATH+remoteUrl + "/" + fileName);
			in = new BufferedInputStream(new FileInputStream(localFile));
			out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
			byte[] buffer = new byte[1024];
			while ((in.read(buffer)) != -1) {
				out.write(buffer);
				buffer = new byte[1024];
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			try {
				if(out != null) {
					out.close();
				}
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}
	
	/**
	 * 把本地磁盘中的文件从局域网共享文件中删除
	 * 
	 * @param remoteUrl
	 *            共享电脑路径 如：smb//administrator:123456@127.0.0.1/smb
	 */
	public static void smbDel(String remoteUrl) {
		try {
			SmbFile remoteFile = new SmbFile(SMBPATH + remoteUrl);
			if (remoteFile.isFile() && remoteFile.exists()) {
				remoteFile.delete();
				logger.info("删除远程文件 ：" + remoteUrl);
			}
		} catch (Exception e) {
			logger.error(e);
		} 
	}

	public static void main(String[] args) {
		smbPut("/temp/aaaaa", "D:/名人图片/848柏拉图.jpg");
//		smbGet("smb://fileUplodeUser:123456@121.201.2.188/temp/test123.txt", "D:/");
//		smbDel("smb://fileUplodeUser:1234563@192.168.1.113/temp/test123.txt");

	}
}
