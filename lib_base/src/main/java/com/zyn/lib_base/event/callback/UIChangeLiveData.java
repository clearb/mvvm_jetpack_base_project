package com.zyn.lib_base.event.callback;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.zyn.lib_base.bus.event.SingleLiveEvent;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
/**
 ************************************************
 * 包路径:com.czl.lib_base.base
 * 类描述:
 * 创建者 @author:YangJiangLin[PHONE：181****4380]
 * 创建者邮箱:307806546@qq.com
 * 创建时间:2023/2/17
 * 项目名称:TsenfBase
 * 项目版本: 1.0
 * 修改人：
 * 修改时间：
 * 修改备注：
 ************************************************
 */
public class UIChangeLiveData extends SingleLiveEvent {
    public SingleLiveEvent<String> showLoadingEvent;
    public SingleLiveEvent<Void> dismissDialogEvent;
    public SingleLiveEvent<Map<String, Object>> startActivityEvent;
    public SingleLiveEvent<HashMap<String,Object>> startFragmentEvent;
    public SingleLiveEvent<Map<String, Object>> startContainerActivityEvent;
    public SingleLiveEvent<Void> finishEvent;
    public SingleLiveEvent<Void> onBackPressedEvent;


    public SingleLiveEvent<String> getShowLoadingEvent() {
        return showLoadingEvent = createLiveData(showLoadingEvent);
    }

    public SingleLiveEvent<Void> getDismissDialogEvent() {
        return dismissDialogEvent = createLiveData(dismissDialogEvent);
    }

    public SingleLiveEvent<Map<String, Object>> getStartActivityEvent() {
        return startActivityEvent = createLiveData(startActivityEvent);
    }

    public SingleLiveEvent<HashMap<String,Object>> getStartFragmentEvent() {
        return startFragmentEvent = createLiveData(startFragmentEvent);
    }

    public SingleLiveEvent<Map<String, Object>> getStartContainerActivityEvent() {
        return startContainerActivityEvent = createLiveData(startContainerActivityEvent);
    }

    public SingleLiveEvent<Void> getFinishEvent() {
        return finishEvent = createLiveData(finishEvent);
    }

    public SingleLiveEvent<Void> getOnBackPressedEvent() {
        return onBackPressedEvent = createLiveData(onBackPressedEvent);
    }


    private <T> SingleLiveEvent<T> createLiveData(SingleLiveEvent<T> liveData) {
        if (liveData == null) {
            liveData = new SingleLiveEvent<>();
        }
        return liveData;
    }

    @Override
    public void observe(@NotNull LifecycleOwner owner, @NotNull Observer observer) {
        super.observe(owner, observer);
    }
}