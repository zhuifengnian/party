/*
 * Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hust.party.common;

import org.springframework.http.HttpRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;

/**
 * 
 * io工具类
 * 
 * @author <a href="mailto:1360527082@qq.com">王焕</a> 2017年6月10日
 */
public class UtilIo {

	public static final Charset utf8 = Charset.forName("UTF-8"), iso8859_1 = Charset.forName("ISO8859-1"),
			gbk = Charset.forName("GBK");

	private static final long KB = 1024, MB = 1024 * KB;
	public static final long GB = 1024 * MB;
	private static final long TB = 1024 * GB;
	private static final long PB = 1024 * TB;
	private static final long ZB = 1024 * PB;

	/**
	 * 保存图片，返回图片url
	 * @param file
	 * @param fileAbsPath 绝对路径，获取根路径参考{@link UtilIo#getWebAppDir}
	 * @return
	 */
	public static String saveWebFile(MultipartFile file, HttpServletRequest request,String fileAbsPath){
		String outdir=getWebAppDir(request);
		String name=file.getOriginalFilename();
		//get 后缀名 jpg，jpeg，png
		String fmt=name.substring(name.lastIndexOf('.')+1);
		//if(!(fmt.equals("jpg")||fmt.equals("jpeg")||fmt.equals("png")))
		//	throw new IllegalArgumentException("不是合法的图片");
		if(!fileAbsPath.startsWith("/")&&!fileAbsPath.startsWith("\\")){
			fileAbsPath="/"+fileAbsPath;
		}
		if(fileAbsPath.charAt(fileAbsPath.length()-1)=='\\'||fileAbsPath.charAt(fileAbsPath.length()-1)=='/')
			fileAbsPath=fileAbsPath.substring(0,fileAbsPath.length()-1);
		outdir=outdir+fileAbsPath;

		name= file.getName() + LocalDateTime.now()+"."+fmt;

		File newFile = new File(outdir+"/"+name);

		try {
			file.transferTo(newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
//request.getRequestURL().toString().replace(request.getRequestURI(),"")+
		return fileAbsPath+"/"+name;
	}

	/**
	 * 返回webapp根路径，不包括最后的/
	 * @param request
	 * @return
	 */

	public static String getWebAppDir(HttpServletRequest request){
		String ret = request.getServletContext().getRealPath("/");
		if(ret.endsWith("\\")||ret.endsWith("/"))
			ret=ret.substring(0,ret.length()-1);
		return  ret;
	}

	/**
	 *
	 * @param file
	 * @param destPath 输出的文件名
	 */
	public static void saveFile(File file, String destPath) {
		if (file == null)
			throw new IllegalArgumentException("file can't be null");
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			File newFile = new File(destPath);
			if (!newFile.exists()) {
				newFile.createNewFile();
			}
			inputStream = new FileInputStream(file);
			outputStream = new FileOutputStream(newFile);
			write(inputStream, outputStream);
		} catch (IOException e) {

		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (Exception e) {
			}
		}

	}

	public static String prettyByte(Number num, Number divisor) {
		if (num == null)
			return "null/" + divisor + "B";
		if (divisor == null)
			return num + "/null B";
		long div = divisor.longValue();
		if (div == 0)
			return prettyByte(num) + "/0";
		return prettyByte(num.longValue() / div);
	}

	public static String prettyByte(Number sizeOfByte) {
		if (sizeOfByte == null)
			return null;

		long size = sizeOfByte.longValue();
		if (size == 0)
			return "0 KB";
		if (size < KB)
			return sizeOfByte + " B";

		DecimalFormat format = new DecimalFormat("#.##");
		if (size < MB)
			return format.format(1.0 * size / KB) + "KB";
		if (size < GB)
			return format.format(1.0 * size / MB) + "MB";
		if (size < TB)
			return format.format(1.0 * size / GB) + "GB";
		if (size < PB)
			return format.format(1.0 * size / TB) + "TB";
		if (size < ZB)
			return format.format(1.0 * size / PB) + "PB";
		return format.format(1.0 * size / ZB) + "ZB";
	}

	public static void close(AutoCloseable... closeables) {
		for (AutoCloseable closeable : closeables) {
			if (closeable != null) {
				try {
					closeable.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/// 文件系统目录文件操作 start
	/** 删除文件或目录 */
	public static void delete(File file) throws IOException {
		delete(file.toPath());
	}

	/** 删除文件或目录 */
	public static synchronized void delete(Path path) throws IOException {
		if (!Files.exists(path))
			return;

		if (Files.isDirectory(path) && !Files.isSymbolicLink(path)) {
			for (Path child : path) {
				delete(child);
			}
		}
		Files.delete(path);
	}

	public static Path createTempDir(String prefix) throws IOException {
		return Files.createTempDirectory(prefix);
	}

	public static String getSeparator() {
		return FileSystems.getDefault().getSeparator();
	}

	public static File getTmpdir() {
		return new File(System.getProperty("java.io.tmpdir"));
	}

	public static void mkdirs(File file) {
		if (file.exists()) {
			if (file.isDirectory())
				throw new IllegalStateException(file + "不是目录");
			return;
		}
		file.mkdirs();
	}

	/** 创建新文件(相当于touch) */
	public static void createNewFile(File file) {
		if (file.exists()) {
			if (!file.isFile())
				throw new IllegalStateException(file + "不是文件");
			return;
		}

		mkdirs(file.getParentFile());
		try {
			file.createNewFile();
		} catch (IOException e) {
			throw new IllegalStateException(file + " 创建失败 " + e, e);
		}
	}
	/// 文件系统目录文件操作 end

	/**
	 * 加载资源
	 * 
	 * @param name
	 * @return 可能null：因为和{@linkplain ClassLoader#getResourceAsStream(String)}
	 *         保持一致
	 * @since 0.1.0
	 */
	public static InputStream getResourceAsStream(String name) {
		if (name == null || name.isEmpty())
			return null;

		String name0 = name.charAt(0) == '/' ? name.substring(1) : name;
		InputStream in = null;
		try {
			in = UtilIo.class.getClassLoader().getResourceAsStream(name0);
			if (in == null) {
				File file = new File(name0);
				if (!file.exists() && name != name0)
					file = new File(name);

				in = new FileInputStream(file);
			}
		} catch (FileNotFoundException e) {

		}
		return in;
	}

	/**
	 * 读取流为字节
	 * 
	 * @param in
	 *            输入流，本函数不负责关闭
	 * @param size
	 *            预计大小 (如果&lt;8192使用8192)
	 */
	public static ByteArrayOutputStream read(InputStream in, Integer size) {
		if (in == null)
			return null;

		try (ByteArrayOutputStream out = new ByteArrayOutputStream(size == null || size < 8192 ? 8192 : size)) {
			byte[] buf = new byte[8192];
			for (int len; (len = in.read(buf)) != -1;) {
				out.write(buf, 0, len);
			}
			return out;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static ByteArrayOutputStream read(File file) throws IOException {
		long length = file.length();
		if (length > Integer.MAX_VALUE)
			throw new IllegalArgumentException("文件太大, length=" + length);

		try (FileInputStream in = new FileInputStream(file)) {
			return read(in, (int) length);
		}
	}

	/**
	 * 读取为字符串
	 * 
	 * @param name
	 *            默认按资源读取，如果不存在按文件读取
	 */
	public static ByteArrayOutputStream readText(String name) {
		try (InputStream in = getResourceAsStream(name)) {
			return read(in, null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/** @see #readText(String) */
	public static String readText(String name, String charsetName) {
		ByteArrayOutputStream bout = readText(name);
		if (bout == null)
			return null;

		try {
			return bout.toString(charsetName);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static Properties loadProperties(String name) {
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = getResourceAsStream(name);
			if (in != null)
				props.load(in);
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return props;
	}

	/** 从in读取写入到out，直到in结束（in和out并不关闭） */
	public static long write(InputStream in, OutputStream out) throws IOException {
		long size = 0;
		byte[] buf = new byte[8192];
		for (int len; (len = in.read(buf)) != -1;) {
			size += len;
			out.write(buf, 0, len);
		}
		return size;
	}

	public static File touch(String file) {
		File f = new File(file);
		if (!f.exists()) {
			f.getParentFile().mkdirs();
			try {
				f.createNewFile();
			} catch (IOException e) {
				throw new IllegalArgumentException("file=" + file + ", " + e, e);
			}
		}
		return f;
	}

	public enum FileTyple{
		IMAGE;
	}
}
