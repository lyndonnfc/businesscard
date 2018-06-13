package com.nfc.lyndon.businesscard.model;

import com.lzy.okgo.callback.StringCallback;
import com.nfc.lyndon.businesscard.contract.CardListContract;
import com.nfc.lyndon.businesscard.manager.HttpManager;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CardModel implements CardListContract.CardListModel{

//    @Override
//    public List<CardModel> getData() {
//        List<CardModel> mData = new ArrayList<>();
//        CardModel entity;
//        for (int i = 0; i < 10; i++) {
//            entity = new CardModel(i+"", "范冰冰","Fan Bingbing",
//                    "http://img.zcool.cn/community/01432f5958ecc1a8012193a375857c.jpg",
//                    "设计总监", "Design Director", "深圳市腾讯计算机系统有限公司",
//                    "Shenzhen city Tencent computer system Co.Ltd.", "18674036966",
//                    "123456789", "888888@qq.com", "广东省深圳市", "Guangdong province Shenzhen city");
//            mData.add(entity);
//        }
//        return mData;
//    }
    @Override
    public void requestCardList(long uid, String keyword, StringCallback callback) {
        HttpManager.getInstance().getCardList(uid, keyword, callback);
    }
}
