package com.gtchenr.utils;

import com.alibaba.fastjson.JSON;
import com.gtchenr.utils.use.SearchMethods;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.AbstractQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @Author gtchenr
 * @Date 2022/4/11 21:04
 * @Description ES检索工具类
 * @Since version-1.0
 */
public class ELKUtil<T> {

    private static String HOSTNAME = "localhost";
    private static Integer PORT = 9200;
    private static RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(HOSTNAME, PORT, "http")));
    private static long TIMEOUT = 2;
    private static int RETRY_NUM = 3;



    public static String getDocId(){

        return "";
    }
    /**
     * 通过文档id查找文档内容
     *
     * @param index
     * @param id
     * @return
     */
    public static String get(String index, String id) {
        GetRequest getRequest = new GetRequest(index, id);
        GetResponse getResponse = null;
        try {
            getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return getResponse.getSourceAsString();
    }

    /**
     * 通过文档id查找部分文档内容
     *
     * @param index
     * @param id
     * @param includes 要查找的字段
     * @param excludes 不用查找的字段
     * @return
     */
    public static String get(String index, String id, String[] includes, String[] excludes) {

        GetRequest getRequest = new GetRequest(index, id);
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        getRequest.fetchSourceContext(fetchSourceContext);
        GetResponse getResponse = null;
        try {
            getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return getResponse.getSourceAsString();
    }


    /**
     * 往索引添加文档
     *
     * @param index
     * @param o
     * @return
     */
    public static Boolean add(String index, Object o) {
        if (o == null)
            return false;
        String s = JSON.toJSONString(o);
        IndexRequest indexRequest = new IndexRequest(index);
        indexRequest.source(s, XContentType.JSON)
                .timeout(TimeValue.timeValueSeconds(TIMEOUT));
        try {
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 更新文档
     *
     * @param index
     * @param o
     * @return
     */
    public static Boolean update(String index, String id, Object o) {
        if (o == null)
            return false;
        String s = JSON.toJSONString(o);
        UpdateRequest updateRequest = new UpdateRequest(index, id);
        updateRequest.doc(s)
                .timeout(TimeValue.timeValueSeconds(TIMEOUT))
                .retryOnConflict(RETRY_NUM)
                .waitForActiveShards(2);
        UpdateResponse response = null;
        try {
            response = client.update(updateRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (response.getResult() == DocWriteResponse.Result.UPDATED)
            return true;
        return false;
    }

    /**
     * 删除文档
     *
     * @param index
     * @param id
     * @return
     */
    public static Boolean delete(String index, String id) {
        DeleteRequest request = new DeleteRequest(index, id);
        DeleteResponse deleteResponse = null;
        try {
            deleteResponse = client.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        if (deleteResponse.getResult() == DocWriteResponse.Result.DELETED)
            return true;
        return false;
    }

    /**
     * 根据请求参数创建搜索请求SearchRequest
     *
     * @param method
     * @param index
     * @param page
     * @param size
     * @return
     */
    public static SearchRequest createSearchRequest(SearchMethods method, String index, int page, int size, String text, String... param) {
        int from = (page - 1) * size;
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(from)
                .size(size)
                .query(selectSearchMethod(method, text, param));
        request.source(searchSourceBuilder);
        return request;
    }

    /**
     * 方法重载
     *
     * @param method
     * @param index
     * @return
     */
    public static SearchRequest createSearchRequest(SearchMethods method, String index, String text, String... param) {
        SearchRequest request = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(selectSearchMethod(method, text, param));
        request.source(searchSourceBuilder);
        return request;
    }

    /**
     * 选择进行query的方法
     *
     * @param method
     * @return
     */
    public static AbstractQueryBuilder selectSearchMethod(SearchMethods method, String text, String... param) {

        switch (method) {
            case MATCH_ALL:
                return QueryBuilders.matchAllQuery();
            case MULTI_MATCH:
                return QueryBuilders.multiMatchQuery(text, param);
            case IDS_SEARCH:
                return QueryBuilders.idsQuery().addIds(param);
            case MATCH_SEARCH:
                return QueryBuilders.matchQuery(param[0], text);
            case TERM_SEARCH:
                return QueryBuilders.termQuery(text,param[0]);
            case WILDCARD_SEARCH:
                return QueryBuilders.wildcardQuery(param[0],text);
        }
        return null;
    }

    /**
     * client通过SearchRequest获取SearchResponse
     *
     * @param searchRequest
     * @return
     */
    public static SearchResponse getSearchResponse(SearchRequest searchRequest) {
        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            return searchResponse;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对SearchResponse进行数据获取，得到的数据根据匹配度进行排序
     *
     * @param response
     * @return
     */
    public static List<String> parseSearchResponse(SearchResponse response) {
        List<String> list = new ArrayList<>();
        SearchHit[] hits1 = response.getHits().getHits();
        for (SearchHit hit : hits1) {
            String id = hit.getId();
            float score = hit.getScore();
            String sourceAsString = hit.getSourceAsString();
            list.add(sourceAsString);
        }
        return list;
    }


    /**
     * 进行查找,并对其的匹配分数进行排序，匹配度由高到低
     *
     * @param method
     * @param index
     * @param page
     * @param size
     * @return
     */
    public static List query(SearchMethods method, String index, int page, int size, String text, String... param) {

        SearchRequest searchRequest = createSearchRequest(method, index, page, size, text, param);
        SearchResponse searchResponse = getSearchResponse(searchRequest);
        return parseSearchResponse(searchResponse);
    }

    /**
     * 方法重载
     *
     * @param method
     * @param index
     * @return
     */
    public static List query(SearchMethods method, String index, String text, String... param) {

        SearchRequest searchRequest = createSearchRequest(method, index, text, param);
        SearchResponse searchResponse = getSearchResponse(searchRequest);
        return parseSearchResponse(searchResponse);
    }


    /**
     * 查找索引的所有文档
     *
     * @param index
     * @return
     */
    public static List queryAll(String index) {
        return query(SearchMethods.MATCH_ALL, index, "");
    }

    /**
     * 分页查找所有文档
     *
     * @param index
     * @param page
     * @param size
     * @return
     */
    public static List queryAll(String index, int page, int size) {
        return query(SearchMethods.MATCH_ALL, index, page, size, "");
    }

    /**
     * @param index
     * @param page
     * @param size
     * @param param
     * @return
     */
    public static List queryByMulti(String index, int page, int size, String text, String... param) {
        return query(SearchMethods.MULTI_MATCH, index, page, size, text, param);
    }

    /**
     * Ids搜索
     *
     * @param index
     * @param param
     * @return
     */
    public static List queryByIds(String index, String... param) {

        return query(SearchMethods.IDS_SEARCH, index, "", param);
    }

    /**
     * Match搜索
     *
     * @param index
     * @param page
     * @param size
     * @param text
     * @param name
     * @return
     */
    public static List<String> queryByMatch(String index, int page, int size, String text, String name) {

        return query(SearchMethods.MATCH_SEARCH, index, page, size, text, name);
    }

    /**
     * 精确查询，不会进行分词
     * @param index
     * @param text
     * @param name
     * @return
     */
    public static List<String> queryByTerm(String index, String text, String name){
        SearchRequest searchRequest = createSearchRequest(SearchMethods.TERM_SEARCH, index, text, name);
        SearchResponse searchResponse = getSearchResponse(searchRequest);
        return parseSearchResponse(searchResponse);
    }

    /**
     * 模糊查询
     * @param index
     * @param text
     * @param name
     * @return
     */
    public static List<String> queryByWildcard(String index, String text, String name){
        SearchRequest searchRequest = createSearchRequest(SearchMethods.WILDCARD_SEARCH, index, text, name);
        SearchResponse searchResponse = getSearchResponse(searchRequest);
        return parseSearchResponse(searchResponse);
    }
    
}
