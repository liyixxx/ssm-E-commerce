package com.ego.search.service;

import java.io.IOException;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;

public interface TbItemService {

	/**
	 * solr初始化
	 */
	void init() throws Exception ;

	/**
	 * 分页查询
	 * @param query
	 * @return
	 */
	Map<String,Object> selByQuery(String query,int page,int rows) throws SolrServerException, IOException;

	/**
	 * 新增
	 * @param map
	 * @param desc
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	int add(Map<String,Object> map,String desc) throws SolrServerException, IOException;
}
