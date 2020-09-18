package com.example.myapplication.data;

import com.example.myapplication.data.db.News;
import com.example.myapplication.data.db.NewsStore;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.ability.DataAbilityRemoteException;
import ohos.aafwk.content.Intent;
import ohos.data.DatabaseHelper;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.data.dataability.DataAbilityUtils;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmPredicates;
import ohos.data.rdb.ValuesBucket;
import ohos.data.resultset.ResultSet;
import ohos.hiviewdfx.HiLog;
import ohos.utils.net.Uri;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsDataAbility extends Ability {

    public static final String DATABASE_NAME ="NewsDataAbility.db";
    public static final String DATABASE_NAME_ALIAS = "NewsDataAbility";
    private OrmContext ormContext = null;

    public final static String AUTHORITY = "dataability://com.example.myapplication.NewsDataAbility";
    private final Pattern pattern = Pattern.compile(AUTHORITY);

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        DatabaseHelper manager = new DatabaseHelper(this);
        ormContext = manager.getOrmContext(DATABASE_NAME_ALIAS, DATABASE_NAME, NewsStore.class);
    }

    @Override
    public ResultSet query(Uri uri, String[] columns, DataAbilityPredicates predicates) {
        if (ormContext == null) {
            LogUtil.dataInfo("failed to query, ormContext is null");
            return null;
        }

        // 查询数据库
        OrmPredicates ormPredicates = DataAbilityUtils.createOrmPredicates(predicates, News.class);
        ResultSet resultSet = ormContext.query(ormPredicates, columns);
        if (resultSet == null) {
            LogUtil.dataInfo("resultSet is null");
        }

        // 返回结果
        return resultSet;
    }

    @Override
    public int insert(Uri uri, ValuesBucket value) {
        // 参数校验
        if (ormContext == null) {
            LogUtil.dataInfo("failed to insert, ormContext is null");
            return -1;
        }

        Matcher matcher = pattern.matcher(uri.toString());
        if (!matcher.lookingAt()) {
            LogUtil.dataInfo("NewsDataAbility insert uri is not matched");
            return -1;
        }

        // 构造插入数据
        News news = new News();
        news.setNewsId(value.getInteger("newsId"));
        news.setTitle(value.getString("title"));
        news.setContent(value.getString("content"));

        // 插入数据库
        boolean isSuccessed = true;
        isSuccessed = ormContext.insert(news);
        if (!isSuccessed) {
            LogUtil.dataInfo("failed to insert");
            return -1;
        }
        isSuccessed = ormContext.flush();
        if (!isSuccessed) {
            LogUtil.dataInfo("failed to insert flush");
            return -1;
        }
        DataAbilityHelper.creator(this, uri).notifyChange(uri);
        int id = Math.toIntExact(news.getRowId());
        return id;
    }

}
