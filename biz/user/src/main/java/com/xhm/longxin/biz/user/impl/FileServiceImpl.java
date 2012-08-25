package com.xhm.longxin.biz.user.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.commons.fileupload.FileItem;

import com.xhm.longxin.biz.user.interfaces.FileService;

public class FileServiceImpl implements FileService {
	// �ļ������·��
	private String basePath;

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String saveFile(FileItem file, String subPath) throws Exception {
		String fileName = String.valueOf(new Date().getTime());
		String fileType = getFileType(file.getName());
		String filePath = basePath + subPath + "/";
		File fileTmp = new File(filePath);
		if (fileTmp.exists()) {
			if (!fileTmp.isDirectory()) {
				throw new Exception("�ļ�����·������");
			}
		} else {
			fileTmp.mkdirs();
		}
		fileTmp = new File(filePath + fileName+"."+fileType);
        FileOutputStream fo = new FileOutputStream(fileTmp);
        fo.write(file.get());
        fo.close();
		return "/"+subPath + "/"+fileName+"."+fileType;
	}
	
	private static String getFileType(String fileName) {
        String type = "";
        int tmp = fileName.indexOf(".");
        if (tmp > 0) {
            type = fileName.substring(tmp + 1);
         }
        return type;
}

}
