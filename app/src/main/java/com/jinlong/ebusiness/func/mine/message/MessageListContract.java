package com.jinlong.ebusiness.func.mine.message;

import com.jinlong.ebusiness.http.response.BaseResponse;
import com.jinlong.ebusiness.http.response.MessageListBean;
import com.xll.mvplib.base.BasePresenter;
import com.xll.mvplib.base.BaseView;

import java.util.Map;

/**
 * @author xll
 * @date 2018/9/24
 */

public class MessageListContract {

    interface View extends BaseView<Presenter> {

        /**
         * 登出成功
         */
        void getMessageListRequestSuccess(BaseResponse<MessageListBean> messageListBean);

        /**
         * 删除消息请求成功
         *
         * @param map
         */
        void deleteMessageByIdsRequestSuccess(Map<String, Object> map);

        /**
         * 消息已读请求成功
         *
         * @param map
         */
        void readMessageByIdsRequestSuccess(Map<String, Object> map);
    }

    interface Presenter extends BasePresenter {

        /**
         * 获取消息列表
         *
         * @param type
         * @param page
         * @param rows
         */
        void getMessageList(int type, int page, int rows);

        /**
         * 消息已读
         *
         * @param ids
         */
        void readMessageByIds(String ids);

        /**
         * 消息删除
         *
         * @param ids
         */
        void deleteMessageByIds(String ids);

    }
}
