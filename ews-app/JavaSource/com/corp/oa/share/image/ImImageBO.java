package com.corp.oa.share.image;

public class ImImageBO {

	// ����id����𷵻ض�Ӧ��ͼƬ�б��sql
	public String getImagesListByTaskId() {
		return "select t.* from T_INFO_IMAGE_MUTIL t "
				+ " where t.state=1 and t.PARENT_ID=? and t.INFO_TYPE in (?) order by t.crt_date";
	}

	// ����sid����ͼƬ����Ϣ
	public String getImageInfoBySid() {
		return "select * from T_INFO_IMAGE_MUTIL where sid=?";
	}
}
