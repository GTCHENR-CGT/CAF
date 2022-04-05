package com.gtchenr.service;

import com.gtchenr.pojo.Report;

import java.util.List;

public interface SearchService {

    /**
     * 全文检索，根据输入的关键词去检索全文，匹配符合的报告，并进行排序
     *
     * @param keyWords
     * @return
     */
    List<Report> ftr(List<String> keyWords);

    List<Report> searchByName();

    List<Report> searchByReporter();
}
