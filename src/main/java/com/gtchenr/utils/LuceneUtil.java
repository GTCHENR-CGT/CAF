/*
package com.gtchenr.utils;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.*;

import com.gtchenr.mapper.UserMapper;
import com.gtchenr.utils.use.FieldCheck;
import com.gtchenr.utils.use.FieldObjects;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.*;
//import org.wltea.analyzer.lucene.IKAnalyzer;

*/
/**
 * lucene工具类
 *
 * @author Administrator
 * <p>
 * 把一组对象，放入文档，创建索引
 * @param list
 * @param tClass
 * @param <T>
 * @throws InvocationTargetException
 * @throws IllegalAccessException
 * @throws IOException
 * @param keyword
 * @param fieldName
 * @param <T>
 * @return
 * @throws ParseException
 * @throws IOException
 * @param key
 * @param value
 * @param <T>
 * @return
 *//*

public class LuceneUtil {

    public static UserMapper userMapper = MybatisUtil.getSqlSession().getMapper(UserMapper.class);
    //IK-Analyzer分词器,中文分词器
    private static Analyzer cnAnalyzer = new IKAnalyzer();

    private static final String path = "";

    */
/**
 * 把一组对象，放入文档，创建索引
 *
 * @param list
 * @param tClass
 * @param <T>
 * @throws InvocationTargetException
 * @throws IllegalAccessException
 * @throws IOException
 *//*

    public static <T> void createIndex(List<T> list, Class<T> tClass, Map map) throws InvocationTargetException, IllegalAccessException, IOException {
        //1.采集数据
        //2.创建文档对象
        List<Document> documentList = new ArrayList<>();
        for (T t : list) {
            //把要存储的对象进行封装，创建域对象并且放入文档
            FieldObjects<T> fieldObjects = new FieldObjects<>(t, (HashMap<String, FieldCheck>) map, tClass);
            for (Field field : selectField(fieldObjects)) {
                Document document = new Document();
                document.add(field);
            }
        }
        //4.创建directory对象,目录对象表示索引库的位置
        Directory directory = FSDirectory.open(Paths.get(path));
        //5.创建IndexWriterConfig对象，指定切分词的分词器
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(cnAnalyzer);
        //6.创建IndexWriter,输出流对象，指定输出流位置和使用config初始化对象
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        //7.写入文档到索引库
        for (Document doc : documentList) {
            indexWriter.addDocument(doc);
        }
        //8.释放资源
        indexWriter.close();
    }

    */
/**
 * @param keyword
 * @param fieldName
 * @param <T>
 * @return
 * @throws ParseException
 * @throws IOException
 *//*

    public static <T> List<T> searchInFieldName(String keyword, String fieldName) throws ParseException, IOException {

        List<T> resultList = new LinkedList<>();
        //1.创建分词器
        //2.创建查询对象:第一个参数 默认查询域，第二个参数：使用的分词器
        QueryParser queryParser = new QueryParser(fieldName, cnAnalyzer);
        //3.设置搜索关键词
        Query query = queryParser.parse(keyword);
        //4.创建Director对象
        Directory dir = FSDirectory.open(Paths.get(path));
        //5.创建输入流对象
        IndexReader indexReader = DirectoryReader.open(dir);
        //6.创建搜索对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //7.搜索
        TopDocs topDocs = indexSearcher.search(query, 10);
        //8.获取结果集
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        //9.遍历结果集
        if (scoreDocs != null) {
            for (ScoreDoc s : scoreDocs) {
                //获取文档唯一标识,并通过标识获取文档对象
                Document doc = indexSearcher.doc(s.doc);
            }
        }

        indexReader.close();
        return resultList;
    }

    public static <T> List<T> parseDocument(List<Document> docList, Map map, Class<T> tClass) {
        List<T> list = new ArrayList<>();
        for (Document doc : docList) {
            try {
                T t = (T) tClass.getConstructor().newInstance();
                Method[] methods = tClass.getMethods();
                for (Method method : methods) {

                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    */
/**
 * @param key
 * @param value
 * @param <T>
 * @return
 *//*

    public static <T> int update(String key, T value) {
        //需要变更的内容
        return 0;
    }


    public static List<Field> selectField(FieldObjects objects) throws InvocationTargetException, IllegalAccessException {
        Map<String, FieldCheck> map = objects.getMap();
        List<Field> fieldList = new ArrayList<>();
        for (Map.Entry e : map.entrySet()) {
            FieldCheck fieldCheck = (FieldCheck) e.getValue();
            String fieldName = (String) e.getKey();
            Object fieldValue = null;
            Method[] methods = objects.gettClass().getMethods();
            for (Method method : methods) {
                if (method.getName().equalsIgnoreCase("get" + fieldName)) {
                    fieldValue = method.invoke(objects.getT());
                    break;
                }
            }
            Field field = null;
            switch (fieldCheck) {
                case STRING_FIELD_STORED:
                    field = new StringField(fieldName, (String) fieldValue, Field.Store.YES);
                    break;
                case STRING_FIELD_UN_STORED:
                    field = new StringField(fieldName, (String) fieldValue, Field.Store.NO);
                    break;
                case DOUBLE_POINT:
                    field = new DoublePoint(fieldName, (Double) fieldValue);
                    break;
                case INT_POINT:
                    field = new IntPoint(fieldName, (int) fieldValue);
                    break;
                case LONG_POINT:
                    field = new LongPoint(fieldName, (long) fieldValue);
                    break;
                case FLOAT_POINT:
                    field = new FloatPoint(fieldName, (float) fieldValue);
                    break;
                case STORE_FIELD:
                    field = new StoredField(fieldName, String.valueOf(fieldValue));
                    break;
                case TEXT_FIELD_TRUE:
                    field = new TextField(fieldName, (String) fieldValue, Field.Store.YES);
                    break;
                case TEXT_FIELD_FALSE:
                    field = new TextField(fieldName, (String) fieldValue, Field.Store.NO);
                    break;
                case TEXT_FIELD_IO:
                    field = new TextField(fieldName, (Reader) fieldValue);
                    break;
                default:
                    System.out.println("field类型出错");
                    break;
            }
            fieldList.add(field);
        }
        return fieldList;
    }
}*/
