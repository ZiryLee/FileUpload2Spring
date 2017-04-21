package util.upload;

import java.util.Map;
import java.util.TreeMap;

/**
 * 1.通过文件扩展名获取mime类型 如zip 则 返回 application/zip
 * 
 * 2.通过Mime类型获取文件扩展名 如application/zip， 则返回zip
 * 
 * @param fileType
 *            文件类型
 * @return
 * 
 * @author 2016-11-15：ziry
 * 
 */
public class ContentTypeUtil {

	private static Map<String, String> file2mimeMap = new TreeMap<String, String>();
	private static Map<String, String> mime2fileMap = new TreeMap<String, String>();
	
	/**图片文件常量值**/
	public final static String PhotoFile = "image";
	/**视频文件常量值**/
	public final static String VideoFile = "video";
	/**图片文件常量值**/
	public final static String AudioFile = "audio";
	/**除了以上的其他文件常量值**/
	public final static String OtherFile = "other";
	
	static {
		file2mimeMap.put("apk", "application/vnd.android.package-archive");
		file2mimeMap.put("3gp", "video/3gpp");
		file2mimeMap.put("ai", "application/postscript");
		file2mimeMap.put("aif", "audio/x-aiff");
		file2mimeMap.put("aifc", "audio/x-aiff");
		file2mimeMap.put("aiff", "audio/x-aiff");
		file2mimeMap.put("asc", "text/plain");
		file2mimeMap.put("atom", "application/atom+xml");
		file2mimeMap.put("au", "audio/basic");
		file2mimeMap.put("avi", "video/x-msvideo");
		file2mimeMap.put("bcpio", "application/x-bcpio");
		file2mimeMap.put("bin", "application/octet-stream");
		file2mimeMap.put("bmp", "image/bmp");
		file2mimeMap.put("cdf", "application/x-netcdf");
		file2mimeMap.put("cgm", "image/cgm");
		file2mimeMap.put("class", "application/octet-stream");
		file2mimeMap.put("cpio", "application/x-cpio");
		file2mimeMap.put("cpt", "application/mac-compactpro");
		file2mimeMap.put("csh", "application/x-csh");
		file2mimeMap.put("css", "text/css");
		file2mimeMap.put("dcr", "application/x-director");
		file2mimeMap.put("dif", "video/x-dv");
		file2mimeMap.put("dir", "application/x-director");
		file2mimeMap.put("djv", "image/vnd.djvu");
		file2mimeMap.put("djvu", "image/vnd.djvu");
		file2mimeMap.put("dll", "application/octet-stream");
		file2mimeMap.put("dmg", "application/octet-stream");
		file2mimeMap.put("dms", "application/octet-stream");
		file2mimeMap.put("doc", "application/msword");
		file2mimeMap.put("dtd", "application/xml-dtd");
		file2mimeMap.put("dv", "video/x-dv");
		file2mimeMap.put("dvi", "application/x-dvi");
		file2mimeMap.put("dxr", "application/x-director");
		file2mimeMap.put("eps", "application/postscript");
		file2mimeMap.put("etx", "text/x-setext");
		file2mimeMap.put("exe", "application/octet-stream");
		file2mimeMap.put("ez", "application/andrew-inset");
		file2mimeMap.put("flv", "video/x-flv");
		file2mimeMap.put("gif", "image/gif");
		file2mimeMap.put("gram", "application/srgs");
		file2mimeMap.put("grxml", "application/srgs+xml");
		file2mimeMap.put("gtar", "application/x-gtar");
		file2mimeMap.put("gz", "application/x-gzip");
		file2mimeMap.put("hdf", "application/x-hdf");
		file2mimeMap.put("hqx", "application/mac-binhex40");
		file2mimeMap.put("htm", "text/html");
		file2mimeMap.put("html", "text/html");
		file2mimeMap.put("ice", "x-conference/x-cooltalk");
		file2mimeMap.put("ico", "image/x-icon");
		file2mimeMap.put("ics", "text/calendar");
		file2mimeMap.put("ief", "image/ief");
		file2mimeMap.put("ifb", "text/calendar");
		file2mimeMap.put("iges", "model/iges");
		file2mimeMap.put("igs", "model/iges");
		file2mimeMap.put("jnlp", "application/x-java-jnlp-file");
		file2mimeMap.put("jp2", "image/jp2");
		file2mimeMap.put("jpe", "image/jpeg");
		file2mimeMap.put("jpeg", "image/jpeg");
		file2mimeMap.put("jpg", "image/jpeg");
		file2mimeMap.put("js", "application/x-javascript");
		file2mimeMap.put("kar", "audio/midi");
		file2mimeMap.put("latex", "application/x-latex");
		file2mimeMap.put("lha", "application/octet-stream");
		file2mimeMap.put("lzh", "application/octet-stream");
		file2mimeMap.put("m3u", "audio/x-mpegurl");
		file2mimeMap.put("m4a", "audio/mp4a-latm");
		file2mimeMap.put("m4p", "audio/mp4a-latm");
		file2mimeMap.put("m4u", "video/vnd.mpegurl");
		file2mimeMap.put("m4v", "video/x-m4v");
		file2mimeMap.put("mac", "image/x-macpaint");
		file2mimeMap.put("man", "application/x-troff-man");
		file2mimeMap.put("mathml", "application/mathml+xml");
		file2mimeMap.put("me", "application/x-troff-me");
		file2mimeMap.put("mesh", "model/mesh");
		file2mimeMap.put("mid", "audio/midi");
		file2mimeMap.put("midi", "audio/midi");
		file2mimeMap.put("mif", "application/vnd.mif");
		file2mimeMap.put("mov", "video/quicktime");
		file2mimeMap.put("movie", "video/x-sgi-movie");
		file2mimeMap.put("mp2", "audio/mpeg");
		file2mimeMap.put("mp3", "audio/mpeg");
		file2mimeMap.put("mp4", "video/mp4");
		file2mimeMap.put("mpe", "video/mpeg");
		file2mimeMap.put("mpeg", "video/mpeg");
		file2mimeMap.put("mpg", "video/mpeg");
		file2mimeMap.put("mpga", "audio/mpeg");
		file2mimeMap.put("ms", "application/x-troff-ms");
		file2mimeMap.put("msh", "model/mesh");
		file2mimeMap.put("mxu", "video/vnd.mpegurl");
		file2mimeMap.put("nc", "application/x-netcdf");
		file2mimeMap.put("oda", "application/oda");
		file2mimeMap.put("ogg", "application/ogg");
		file2mimeMap.put("ogv", "video/ogv");
		file2mimeMap.put("pbm", "image/x-portable-bitmap");
		file2mimeMap.put("pct", "image/pict");
		file2mimeMap.put("pdb", "chemical/x-pdb");
		file2mimeMap.put("pdf", "application/pdf");
		file2mimeMap.put("pgm", "image/x-portable-graymap");
		file2mimeMap.put("pgn", "application/x-chess-pgn");
		file2mimeMap.put("pic", "image/pict");
		file2mimeMap.put("pict", "image/pict");
		file2mimeMap.put("png", "image/png");
		file2mimeMap.put("pnm", "image/x-portable-anymap");
		file2mimeMap.put("pnt", "image/x-macpaint");
		file2mimeMap.put("pntg", "image/x-macpaint");
		file2mimeMap.put("ppm", "image/x-portable-pixmap");
		file2mimeMap.put("ppt", "application/vnd.ms-powerpoint");
		file2mimeMap.put("ps", "application/postscript");
		file2mimeMap.put("qt", "video/quicktime");
		file2mimeMap.put("qti", "image/x-quicktime");
		file2mimeMap.put("qtif", "image/x-quicktime");
		file2mimeMap.put("ra", "audio/x-pn-realaudio");
		file2mimeMap.put("ram", "audio/x-pn-realaudio");
		file2mimeMap.put("ras", "image/x-cmu-raster");
		file2mimeMap.put("rdf", "application/rdf+xml");
		file2mimeMap.put("rgb", "image/x-rgb");
		file2mimeMap.put("rm", "application/vnd.rn-realmedia");
		file2mimeMap.put("roff", "application/x-troff");
		file2mimeMap.put("rtf", "text/rtf");
		file2mimeMap.put("rtx", "text/richtext");
		file2mimeMap.put("sgm", "text/sgml");
		file2mimeMap.put("sgml", "text/sgml");
		file2mimeMap.put("sh", "application/x-sh");
		file2mimeMap.put("shar", "application/x-shar");
		file2mimeMap.put("silo", "model/mesh");
		file2mimeMap.put("sit", "application/x-stuffit");
		file2mimeMap.put("skd", "application/x-koan");
		file2mimeMap.put("skm", "application/x-koan");
		file2mimeMap.put("skp", "application/x-koan");
		file2mimeMap.put("skt", "application/x-koan");
		file2mimeMap.put("smi", "application/smil");
		file2mimeMap.put("smil", "application/smil");
		file2mimeMap.put("snd", "audio/basic");
		file2mimeMap.put("so", "application/octet-stream");
		file2mimeMap.put("spl", "application/x-futuresplash");
		file2mimeMap.put("src", "application/x-wais-source");
		file2mimeMap.put("sv4cpio", "application/x-sv4cpio");
		file2mimeMap.put("sv4crc", "application/x-sv4crc");
		file2mimeMap.put("svg", "image/svg+xml");
		file2mimeMap.put("swf", "application/x-shockwave-flash");
		file2mimeMap.put("t", "application/x-troff");
		file2mimeMap.put("tar", "application/x-tar");
		file2mimeMap.put("tcl", "application/x-tcl");
		file2mimeMap.put("tex", "application/x-tex");
		file2mimeMap.put("texi", "application/x-texinfo");
		file2mimeMap.put("texinfo", "application/x-texinfo");
		file2mimeMap.put("tif", "image/tiff");
		file2mimeMap.put("tiff", "image/tiff");
		file2mimeMap.put("tr", "application/x-troff");
		file2mimeMap.put("tsv", "text/tab-separated-values");
		file2mimeMap.put("txt", "text/plain");
		file2mimeMap.put("ustar", "application/x-ustar");
		file2mimeMap.put("vcd", "application/x-cdlink");
		file2mimeMap.put("vrml", "model/vrml");
		file2mimeMap.put("vxml", "application/voicexml+xml");
		file2mimeMap.put("wav", "audio/x-wav");
		file2mimeMap.put("wbmp", "image/vnd.wap.wbmp");
		file2mimeMap.put("wbxml", "application/vnd.wap.wbxml");
		file2mimeMap.put("webm", "video/webm");
		file2mimeMap.put("wml", "text/vnd.wap.wml");
		file2mimeMap.put("wmlc", "application/vnd.wap.wmlc");
		file2mimeMap.put("wmls", "text/vnd.wap.wmlscript");
		file2mimeMap.put("wmlsc", "application/vnd.wap.wmlscriptc");
		file2mimeMap.put("wmv", "video/x-ms-wmv");
		file2mimeMap.put("wrl", "model/vrml");
		file2mimeMap.put("xbm", "image/x-xbitmap");
		file2mimeMap.put("xht", "application/xhtml+xml");
		file2mimeMap.put("xhtml", "application/xhtml+xml");
		file2mimeMap.put("xls", "application/vnd.ms-excel");
		file2mimeMap.put("xml", "application/xml");
		file2mimeMap.put("xpm", "image/x-xpixmap");
		file2mimeMap.put("xsl", "application/xml");
		file2mimeMap.put("xslt", "application/xslt+xml");
		file2mimeMap.put("xul", "application/vnd.mozilla.xul+xml");
		file2mimeMap.put("xwd", "image/x-xwindowdump");
		file2mimeMap.put("xyz", "chemical/x-xyz");
		file2mimeMap.put("zip", "application/zip");

		mime2fileMap.put("video/3gpp", "3gp");
		mime2fileMap.put("application/postscript", "ai");
		mime2fileMap.put("audio/x-aiff", "aif");
		mime2fileMap.put("audio/x-aiff", "aifc");
		mime2fileMap.put("audio/x-aiff", "aiff");
		mime2fileMap.put("application/vnd.android.package-archive", "apk");
		mime2fileMap.put("text/plain", "asc");
		mime2fileMap.put("application/atom+xml", "atom");
		mime2fileMap.put("audio/basic", "au");
		mime2fileMap.put("video/x-msvideo", "avi");
		mime2fileMap.put("application/x-bcpio", "bcpio");
		mime2fileMap.put("application/octet-stream", "bin");
		mime2fileMap.put("image/bmp", "bmp");
		mime2fileMap.put("application/x-netcdf", "cdf");
		mime2fileMap.put("image/cgm", "cgm");
		mime2fileMap.put("application/octet-stream", "class");
		mime2fileMap.put("application/x-cpio", "cpio");
		mime2fileMap.put("application/mac-compactpro", "cpt");
		mime2fileMap.put("application/x-csh", "csh");
		mime2fileMap.put("text/css", "css");
		mime2fileMap.put("application/x-director", "dcr");
		mime2fileMap.put("video/x-dv", "dif");
		mime2fileMap.put("application/x-director", "dir");
		mime2fileMap.put("image/vnd.djvu", "djv");
		mime2fileMap.put("image/vnd.djvu", "djvu");
		mime2fileMap.put("application/octet-stream", "dll");
		mime2fileMap.put("application/octet-stream", "dmg");
		mime2fileMap.put("application/octet-stream", "dms");
		mime2fileMap.put("application/msword", "doc");
		mime2fileMap.put("application/xml-dtd", "dtd");
		mime2fileMap.put("video/x-dv", "dv");
		mime2fileMap.put("application/x-dvi", "dvi");
		mime2fileMap.put("application/x-director", "dxr");
		mime2fileMap.put("application/postscript", "eps");
		mime2fileMap.put("text/x-setext", "etx");
		mime2fileMap.put("application/octet-stream", "exe");
		mime2fileMap.put("application/andrew-inset", "ez");
		mime2fileMap.put("video/x-flv", "flv");
		mime2fileMap.put("image/gif", "gif");
		mime2fileMap.put("application/srgs", "gram");
		mime2fileMap.put("application/srgs+xml", "grxml");
		mime2fileMap.put("application/x-gtar", "gtar");
		mime2fileMap.put("application/x-gzip", "gz");
		mime2fileMap.put("application/x-hdf", "hdf");
		mime2fileMap.put("application/mac-binhex40", "hqx");
		mime2fileMap.put("text/html", "htm");
		mime2fileMap.put("text/html", "html");
		mime2fileMap.put("x-conference/x-cooltalk", "ice");
		mime2fileMap.put("image/x-icon", "ico");
		mime2fileMap.put("text/calendar", "ics");
		mime2fileMap.put("image/ief", "ief");
		mime2fileMap.put("text/calendar", "ifb");
		mime2fileMap.put("model/iges", "iges");
		mime2fileMap.put("model/iges", "igs");
		mime2fileMap.put("application/x-java-jnlp-file", "jnlp");
		mime2fileMap.put("image/jp2", "jp2");
		mime2fileMap.put("image/jpeg", "jpe");
		mime2fileMap.put("image/jpeg", "jpeg");
		mime2fileMap.put("image/jpeg", "jpg");
		mime2fileMap.put("application/x-javascript", "js");
		mime2fileMap.put("audio/midi", "kar");
		mime2fileMap.put("application/x-latex", "latex");
		mime2fileMap.put("application/octet-stream", "lha");
		mime2fileMap.put("application/octet-stream", "lzh");
		mime2fileMap.put("audio/x-mpegurl", "m3u");
		mime2fileMap.put("audio/mp4a-latm", "m4a");
		mime2fileMap.put("audio/mp4a-latm", "m4p");
		mime2fileMap.put("video/vnd.mpegurl", "m4u");
		mime2fileMap.put("video/x-m4v", "m4v");
		mime2fileMap.put("image/x-macpaint", "mac");
		mime2fileMap.put("application/x-troff-man", "man");
		mime2fileMap.put("application/mathml+xml", "mathml");
		mime2fileMap.put("application/x-troff-me", "me");
		mime2fileMap.put("model/mesh", "mesh");
		mime2fileMap.put("audio/midi", "mid");
		mime2fileMap.put("audio/midi", "midi");
		mime2fileMap.put("application/vnd.mif", "mif");
		mime2fileMap.put("video/quicktime", "mov");
		mime2fileMap.put("video/x-sgi-movie", "movie");
		mime2fileMap.put("audio/mpeg", "mp2");
		mime2fileMap.put("audio/mpeg", "mp3");
		mime2fileMap.put("video/mp4", "mp4");
		mime2fileMap.put("video/mpeg", "mpe");
		mime2fileMap.put("video/mpeg", "mpeg");
		mime2fileMap.put("video/mpeg", "mpg");
		mime2fileMap.put("audio/mpeg", "mpga");
		mime2fileMap.put("application/x-troff-ms", "ms");
		mime2fileMap.put("model/mesh", "msh");
		mime2fileMap.put("video/vnd.mpegurl", "mxu");
		mime2fileMap.put("application/x-netcdf", "nc");
		mime2fileMap.put("application/oda", "oda");
		mime2fileMap.put("application/ogg", "ogg");
		mime2fileMap.put("video/ogv", "ogv");
		mime2fileMap.put("image/x-portable-bitmap", "pbm");
		mime2fileMap.put("image/pict", "pct");
		mime2fileMap.put("chemical/x-pdb", "pdb");
		mime2fileMap.put("application/pdf", "pdf");
		mime2fileMap.put("image/x-portable-graymap", "pgm");
		mime2fileMap.put("application/x-chess-pgn", "pgn");
		mime2fileMap.put("image/pict", "pic");
		mime2fileMap.put("image/pict", "pict");
		mime2fileMap.put("image/png", "png");
		mime2fileMap.put("image/x-portable-anymap", "pnm");
		mime2fileMap.put("image/x-macpaint", "pnt");
		mime2fileMap.put("image/x-macpaint", "pntg");
		mime2fileMap.put("image/x-portable-pixmap", "ppm");
		mime2fileMap.put("application/vnd.ms-powerpoint", "ppt");
		mime2fileMap.put("application/postscript", "ps");
		mime2fileMap.put("video/quicktime", "qt");
		mime2fileMap.put("image/x-quicktime", "qti");
		mime2fileMap.put("image/x-quicktime", "qtif");
		mime2fileMap.put("audio/x-pn-realaudio", "ra");
		mime2fileMap.put("audio/x-pn-realaudio", "ram");
		mime2fileMap.put("image/x-cmu-raster", "ras");
		mime2fileMap.put("application/rdf+xml", "rdf");
		mime2fileMap.put("image/x-rgb", "rgb");
		mime2fileMap.put("application/vnd.rn-realmedia", "rm");
		mime2fileMap.put("application/x-troff", "roff");
		mime2fileMap.put("text/rtf", "rtf");
		mime2fileMap.put("text/richtext", "rtx");
		mime2fileMap.put("text/sgml", "sgm");
		mime2fileMap.put("text/sgml", "sgml");
		mime2fileMap.put("application/x-sh", "sh");
		mime2fileMap.put("application/x-shar", "shar");
		mime2fileMap.put("model/mesh", "silo");
		mime2fileMap.put("application/x-stuffit", "sit");
		mime2fileMap.put("application/x-koan", "skd");
		mime2fileMap.put("application/x-koan", "skm");
		mime2fileMap.put("application/x-koan", "skp");
		mime2fileMap.put("application/x-koan", "skt");
		mime2fileMap.put("application/smil", "smi");
		mime2fileMap.put("application/smil", "smil");
		mime2fileMap.put("audio/basic", "snd");
		mime2fileMap.put("application/octet-stream", "so");
		mime2fileMap.put("application/x-futuresplash", "spl");
		mime2fileMap.put("application/x-wais-source", "src");
		mime2fileMap.put("application/x-sv4cpio", "sv4cpio");
		mime2fileMap.put("application/x-sv4crc", "sv4crc");
		mime2fileMap.put("image/svg+xml", "svg");
		mime2fileMap.put("application/x-shockwave-flash", "swf");
		mime2fileMap.put("application/x-troff", "t");
		mime2fileMap.put("application/x-tar", "tar");
		mime2fileMap.put("application/x-tcl", "tcl");
		mime2fileMap.put("application/x-tex", "tex");
		mime2fileMap.put("application/x-texinfo", "texi");
		mime2fileMap.put("application/x-texinfo", "texinfo");
		mime2fileMap.put("image/tiff", "tif");
		mime2fileMap.put("image/tiff", "tiff");
		mime2fileMap.put("application/x-troff", "tr");
		mime2fileMap.put("text/tab-separated-values", "tsv");
		mime2fileMap.put("text/plain", "txt");
		mime2fileMap.put("application/x-ustar", "ustar");
		mime2fileMap.put("application/x-cdlink", "vcd");
		mime2fileMap.put("model/vrml", "vrml");
		mime2fileMap.put("application/voicexml+xml", "vxml");
		mime2fileMap.put("audio/x-wav", "wav");
		mime2fileMap.put("image/vnd.wap.wbmp", "wbmp");
		mime2fileMap.put("application/vnd.wap.wbxml", "wbxml");
		mime2fileMap.put("video/webm", "webm");
		mime2fileMap.put("text/vnd.wap.wml", "wml");
		mime2fileMap.put("application/vnd.wap.wmlc", "wmlc");
		mime2fileMap.put("text/vnd.wap.wmlscript", "wmls");
		mime2fileMap.put("application/vnd.wap.wmlscriptc", "wmlsc");
		mime2fileMap.put("video/x-ms-wmv", "wmv");
		mime2fileMap.put("model/vrml", "wrl");
		mime2fileMap.put("image/x-xbitmap", "xbm");
		mime2fileMap.put("application/xhtml+xml", "xht");
		mime2fileMap.put("application/xhtml+xml", "xhtml");
		mime2fileMap.put("application/vnd.ms-excel", "xls");
		mime2fileMap.put("application/xml", "xml");
		mime2fileMap.put("image/x-xpixmap", "xpm");
		mime2fileMap.put("application/xml", "xsl");
		mime2fileMap.put("application/xslt+xml", "xslt");
		mime2fileMap.put("application/vnd.mozilla.xul+xml", "xul");
		mime2fileMap.put("image/x-xwindowdump", "xwd");
		mime2fileMap.put("chemical/x-xyz", "xyz");
		mime2fileMap.put("application/zip", "zip");

	}

	/**
	 * 通过文件类型获取mime类型 如zip 则 返回 application/zip 
	 * 
	 * @param fileType
	 *            文件类型
	 * @return
	 */
	public static String getMimeTypeByFileType(String fileType) {

		if (fileType == null || fileType.trim().length() == 0) {
			return "";
		}

		// 转成小写
		fileType = fileType.toLowerCase();

		String mimeType = file2mimeMap.get(fileType);
		if (mimeType == null) {
			mimeType = "";
		}

		return mimeType;

	}

	/**
	 * 通过Mime类型获取文件扩展名 如application/zip， 则返回zip
	 * 
	 * @param mimeType
	 *            Mime类型
	 * @return
	 */
	public static String getFileTypeByMimeType(String mimeType) {

		if (mimeType == null || mimeType.trim().length() == 0) {
			return "";
		}

		mimeType = mimeType.toLowerCase();

		String fileType = mime2fileMap.get(mimeType);
		if (fileType == null) {
			fileType = "";
		}

		return fileType;
	}
	
	/**
	 * 获取文件种类:photo,audio,video,other
	 * @param fileType
	 * @return
	 */
	public static String getFileClassByFileType(String fileType) {
		
		String mimeType = ContentTypeUtil.getMimeTypeByFileType(fileType);
		
		if(mimeType== null || mimeType.length() == 0) {
			return "";
		}
		
		if( mimeType.indexOf(ContentTypeUtil.PhotoFile) != -1) {
			return ContentTypeUtil.PhotoFile;
		}
		
		if( mimeType.indexOf(ContentTypeUtil.AudioFile) != -1) {
			return ContentTypeUtil.AudioFile;
		}
		
		if( mimeType.indexOf(ContentTypeUtil.VideoFile) != -1) {
			return ContentTypeUtil.VideoFile;
		}
		
		return ContentTypeUtil.OtherFile;
	}
	
	public static void main(String[] args) {
		System.out.println(ContentTypeUtil.getFileClassByFileType("jpg"));
	}

}
