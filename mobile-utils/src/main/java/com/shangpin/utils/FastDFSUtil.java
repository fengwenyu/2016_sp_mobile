package com.shangpin.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


public class FastDFSUtil {

	public static Logger logger = LoggerFactory.getLogger(FastDFSUtil.class);

	private static String configFilePath = PropertyUtil.getFilePath();

	private static FastDFSUtil instance = null;
	private StorageClient client = null;

	private FastDFSUtil() {
	}

	public static synchronized FastDFSUtil getInstance() {
		if (null == instance) {
			instance = new FastDFSUtil();
			return instance;
		} else {
			return instance;
		}
	}

	private void initDFS() {
		try {
			ClientGlobal.init(configFilePath);
			TrackerClient tracker = new TrackerClient();
			TrackerServer trackerServer = tracker.getConnection();
			StorageServer storageServer = null;
			client = new StorageClient(trackerServer, storageServer);
		} catch (FileNotFoundException e) {
			logger.error("init FastDFS server error : " + e);
		} catch (IOException e) {
			logger.error("init FastDFS server error : " + e);
		} catch (MyException e) {
			logger.error("init FastDFS server error : " + e);
		}
	}

	/**
	 * 上传文件到FastDFS服务器,并且返回上传文件的相对路径
	 * 
	 * @param file
	 * @return 返回的路径包含的groupName
	 * 
	 */
	public String uploadFileIncludeGroup(MultipartFile file) {
		StringBuilder filePath = new StringBuilder("");
		if (file == null) {
			return null;
		}
		try {
			initDFS();
			String fileName = file.getOriginalFilename();
			String fileExt = FileUtils.getFileExt(fileName);
			String fileSize = String.valueOf(file.getSize());
			NameValuePair[] metaList = genMetaList(fileName, fileExt, fileSize);
			String[] results = client.upload_file(file.getBytes(), fileExt,
					metaList);
			if (ArrayUtils.isEmpty(results)) {
				return null;
			}
			return filePath.append(results[0]).append("/").append(results[1])
					.toString();
		} catch (IOException e) {
			logger.error("upload file to FastDFS server error : " + e);
		} catch (MyException e) {
			logger.error("upload file to FastDFS server error : " + e);
		}
		return null;
	}

	/**
	 * 上传文件到FastDFS服务器,并且返回上传文件的相对路径
	 * 
	 * @param file
	 * @return 返回的路径不包含的groupName
	 * 
	 */
	public String uploadFileExcludeGroup(MultipartFile file) {
		if (file == null) {
			return null;
		}
		try {
			initDFS();
			String fileName = file.getOriginalFilename();
			String fileExt = FileUtils.getFileExt(fileName);
			String fileSize = String.valueOf(file.getSize());
			NameValuePair[] metaList = genMetaList(fileName, fileExt, fileSize);
			String[] results = client.upload_file(file.getBytes(), fileExt,
					metaList);
			if (ArrayUtils.isEmpty(results)) {
				return null;
			}
			return results[1];
		} catch (IOException e) {
			logger.error("upload file to FastDFS server error : " + e);
		} catch (MyException e) {
			logger.error("upload file to FastDFS server error : " + e);
		}
		return null;
	}

	/**
	 * 上传文件到FastDFS服务器,并且返回上传文件的相对路径
	 * 
	 * @param file
	 * @return
	 * 
	 */
	public String[] uploadFileToDFS(MultipartFile file) {
		if (file == null) {
			return null;
		}
		try {
			initDFS();
			String fileName = file.getOriginalFilename();
			String fileExt = FileUtils.getFileExt(fileName);
			String fileSize = String.valueOf(file.getSize());
			NameValuePair[] metaList = genMetaList(fileName, fileExt, fileSize);
			String[] results = client.upload_file(file.getBytes(), fileExt,
					metaList);
			if (ArrayUtils.isEmpty(results)) {
				return null;
			}
			return results;
		} catch (IOException e) {
			logger.error("upload file to FastDFS server error : " + e);
		} catch (MyException e) {
			logger.error("upload file to FastDFS server error : " + e);
		}
		return null;
	}

	/**
	 * 生成metaList,参数可变
	 * 
	 * @param params
	 *            [fileName,fileExtName,fileLength,width,heigth,author]
	 * @return
	 */
	public NameValuePair[] genMetaList(String... params) {
		if (ArrayUtils.isEmpty(params)) {
			return null;
		}
		List<NameValuePair> metaList = new ArrayList<NameValuePair>();
		for (int i = 0; i < params.length; i++) {
			String value = params[i];
			switch (i) {
			case 0:
				metaList.add(new NameValuePair("fileName", value));
				break;
			case 1:
				metaList.add(new NameValuePair("fileExtName", value));
				break;
			case 2:
				metaList.add(new NameValuePair("fileLength", value));
				break;
			case 3:
				metaList.add(new NameValuePair("width", value));
				break;
			case 4:
				metaList.add(new NameValuePair("heigth", value));
				break;
			case 5:
				metaList.add(new NameValuePair("author", value));
				break;
			default:
				break;
			}
		}
		return (NameValuePair[]) metaList.toArray(new NameValuePair[metaList
				.size()]);
	}
}
