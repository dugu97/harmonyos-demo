package com.example.myapplication.data;

import com.example.myapplication.ResourceTable;

import com.example.myapplication.data.db.News;
import com.example.myapplication.slice.NewsDetailAbilitySlice;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.RecycleItemProvider;
import ohos.agp.components.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListItemProvider extends RecycleItemProvider {

    private List<News> news = new ArrayList<>();
    private AbilitySlice mSlice;

    ListItemProvider(AbilitySlice abilitySlice) {
        mSlice = abilitySlice;
    }

    public void setNews(List<News> news) {
        this.news = news;
        notifyDataChanged();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getCount() {
        return news.size();
    }

    @Override
    public Component getComponent(int position, Component convertView, ComponentContainer parent) {
        Component component = LayoutScatter.getInstance(mSlice).parse(ResourceTable.Layout_list_item, null, false);
        if (!(component instanceof ComponentContainer)) {
            return null;
        }
        ComponentContainer rootLayout = (ComponentContainer) component;
        Text titleText = (Text) rootLayout.findComponentById(ResourceTable.Id_news_title);
        titleText.setText(news.get(position).getTitle());
        Text previewText = (Text) rootLayout.findComponentById(ResourceTable.Id_news_preview);
        previewText.setText(news.get(position).getContent());
        rootLayout.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                mSlice.present(new NewsDetailAbilitySlice(), new Intent());
                LogUtil.clickInfo("跳转-NewsDetailAbilitySlice-新闻详情");
            }
        });
        return component;
    }
}
