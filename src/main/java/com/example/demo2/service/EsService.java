package com.example.demo2.service;

import com.example.demo2.domain.User;
import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EsService {


    /**
     * 创建加载配置文件的客户端工具，用来检索文档，单实例多线程安全
     */
    ClientInterface clientUtil = ElasticSearchHelper.getConfigRestClientUtil("esmapper/dsl.xml");

    public ESDatas<User> dslOne(Map<String, Object> params) {

        /**
         *执行查询，demo为索引表，_search为检索操作action
         * ESDatas包含当前检索的记录集合，最多10条记录，由dsl中的size属性指定
         * demo为索引表，_search为检索操作action
         * @param searchDatas esmapper/demo.xml中定义的dsl语句
         * @param params 变量参数
         * @param Demo.class  返回的文档封装对象类型
         */
        ESDatas<User> esDatas = clientUtil.searchList("cdr_2020080501/_search", "scriptDsl", params, User.class);

        return esDatas;
    }
}
