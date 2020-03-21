package com.corp.oa.share.image;

public class ImImageBO {

	// 根据id和类别返回对应的图片列表的sql
	public String getImagesListByTaskId() {
		return "select t.* from T_INFO_IMAGE_MUTIL t "
				+ " where t.state=1 and t.PARENT_ID=? and t.INFO_TYPE in (?) order by t.crt_date";
	}

	// 根据sid返回图片的信息
	public String getImageInfoBySid() {
		return "select * from T_INFO_IMAGE_MUTIL where sid=?";
	}
}
