package com.example.myapplication.data;

import com.example.myapplication.data.db.News;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.ability.DataAbilityRemoteException;
import ohos.app.Context;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmObject;
import ohos.data.orm.OrmPredicates;
import ohos.data.rdb.ValuesBucket;
import ohos.data.resultset.ResultSet;
import ohos.utils.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class NewsDataUtil {

    public static List<News> queryNews(OrmContext context, Uri uri){

//        List<News> dataList = new ArrayList<>();
//        DataAbilityHelper helper = DataAbilityHelper.creator(context);
//
//        // 构造查询条件
////        DataAbilityPredicates predicates = new DataAbilityPredicates();
////        predicates.between("userId", 101, 103);
//
//        String[] columns = {"newsId", "title", "content"};
//
//        // 进行查询
//        ResultSet resultSet = null;
//        try {
//            resultSet = helper.query(uri,columns,null);
//            // 处理结果
//            resultSet.goToFirstRow();
//            do{
//                // 在此处理ResultSet中的记录
//                News news = new News();
//                news.setNewsId(resultSet.getInt(0));
//                news.setTitle(resultSet.getString(1));
//                news.setContent(resultSet.getString(2));
//                dataList.add(news);
//            }while(resultSet.goToNextRow());
//        } catch (DataAbilityRemoteException e) {
//            e.printStackTrace();
//        }

        OrmPredicates query = context.where(News.class);
        List<News> users = context.query(query);

        return users;
    }

    public static void insertThreeNews(OrmContext context, Uri uri) {
//        DataAbilityHelper helper = DataAbilityHelper.creator(context);
//        // 构造插入数据
//        ValuesBucket valuesBucket = new ValuesBucket();
//        valuesBucket.putInteger("newsId", 0);
//        valuesBucket.putString("title", "新闻标题");
//        valuesBucket.putString("content", "新闻内容");
//        try {
//            LogUtil.debugInfo("A3");
//            helper.insert(uri, valuesBucket);
//        } catch (DataAbilityRemoteException e) {
//            e.printStackTrace();
//        }

        News news = new News();
        news.setTitle("新闻标题");
        news.setContent("新闻内容");
        boolean isSuccessed = context.insert(news);
        isSuccessed = context.flush();
    }
}
