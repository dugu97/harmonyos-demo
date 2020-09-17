package com.example.myapplication;

import com.example.myapplication.slice.SecondAbilitySlice;
import com.example.myapplication.util.LogUtil;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class SecondAbility extends Ability {

    private final String TAG = "SecondAbility";

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        setMainRoute(SecondAbilitySlice.class.getName());
    }
}
