package com.xhm.longxin.biz.user.interfaces;

import org.apache.commons.fileupload.FileItem;

/**
 * 
 * @author xhm.xuhm
 *
 */
public interface FileService {
	/**
	 * 保存上传文件
	 * @param file
	 * @param subPath
	 * @return 中间目录+文件名
	 */
	public String saveFile(FileItem file,String subPath)throws Exception ;
}
