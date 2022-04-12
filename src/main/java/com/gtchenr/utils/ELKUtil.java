package com.gtchenr.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;

import java.io.IOException;


/**
 * @Author gtchenr
 * @Date 2022/4/11 21:04
 * @Description ES检索工具类
 * @Since version-1.0
 */
public class ELKUtil {

    private static String HOSTNAME = "localhost";
    private static Integer PORT = 9200;
    private static RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(HOSTNAME, PORT, "http")));
    private static long TIMEOUT = 2;
    private static int RETRY_NUM = 3;

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
        indexRequest.source(s)
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


    public static Boolean createIndex(){
        //设置mapping

    }

}
