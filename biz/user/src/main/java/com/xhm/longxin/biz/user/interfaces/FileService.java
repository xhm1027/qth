package com.xhm.longxin.biz.user.interfaces;

import org.apache.commons.fileupload.FileItem;

/**
 * 
 * @author xhm.xuhm
 *
 */
public interface FileService {
	/**
	 * �����ϴ��ļ�
	 * @param file
	 * @param subPath
	 * @return �м�Ŀ¼+�ļ���
	 */
	public String saveFile(FileItem file,String subPath)throws Exception ;
}
