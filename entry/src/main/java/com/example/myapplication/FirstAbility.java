package com.example.myapplication;

import com.example.myapplication.slice.FirstAbilitySlice;
import com.example.myapplication.slice.NewsDetailAbilitySlice;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class FirstAbility extends Ability {

    private final String TAG = "FirstAbility";

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(FirstAbilitySlice.class.getName());
        addActionRoute("$String:news_detail_action", NewsDetailAbilitySlice.class.getName());
    }
}
