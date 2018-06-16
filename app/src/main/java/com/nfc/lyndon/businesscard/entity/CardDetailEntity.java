package com.nfc.lyndon.businesscard.entity;

/**
 * Created by Administrator on 2018/6/15.
 */

public class CardDetailEntity {

    private CardEntity cardInfo;

    private CardEntity nfcBusinessCardInfo;

    public CardEntity getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(CardEntity cardInfo) {
        this.cardInfo = cardInfo;
    }

    public CardEntity getNfcBusinessCardInfo() {
        return nfcBusinessCardInfo;
    }

    public void setNfcBusinessCardInfo(CardEntity nfcBusinessCardInfo) {
        this.nfcBusinessCardInfo = nfcBusinessCardInfo;
    }
}
