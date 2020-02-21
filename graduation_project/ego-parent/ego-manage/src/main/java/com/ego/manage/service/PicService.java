package com.ego.manage.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * @TODO 文件上传
 * @author 柒
 * @data 2019年11月19日 下午10:12:57
 */
public interface PicService {

	Map<String , Object> upload(MultipartFile file) throws IOException ;
}
