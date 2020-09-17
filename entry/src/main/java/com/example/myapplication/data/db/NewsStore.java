package com.example.myapplication.data.db;

import ohos.data.orm.OrmDatabase;
import ohos.data.orm.annotation.Database;
import ohos.data.rdb.RdbOpenCallback;

@Database(entities = {News.class}, version = 1)
public abstract class NewsStore extends OrmDatabase {

}
