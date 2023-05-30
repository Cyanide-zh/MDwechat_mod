package com.blanke.mdwechat

import com.blanke.mdwechat.bean.ViewTree
import com.blanke.mdwechat.bean.ViewTreeItem
import com.blanke.mdwechat.config.ViewTreeConfig

//import java.lang.reflect.Method

// 当前微信版本适用的repo
// based on ViewTreeRepo
object ViewTreeRepoThisVersion {
    //    val Tmp: ViewTree by lazy {
//        ViewTreeConfig.get().tmp.invoke(ViewTreeRepo) as ViewTree
//    }
    val ConversationListViewItem: ViewTree by lazy {
        ViewTreeConfig.get().conversationListViewItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ContactLayoutListenerViewItem: ViewTree by lazy {
        ViewTreeConfig.get().contactLayoutListenerViewItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ContactListViewItem: ViewTree by lazy {
        ViewTreeConfig.get().contactListViewItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ContactHeaderItem: ViewTree by lazy {
        ViewTreeConfig.get().contactHeaderItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ContactWorkItem: ViewTree by lazy {
        ViewTreeConfig.get().contactWorkItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ContactWorkContactsItem: ViewTree by lazy {
        ViewTreeConfig.get().contactWorkContactsItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ContactMyWorkItem: ViewTree by lazy {
        ViewTreeConfig.get().contactMyWorkItem.invoke(ViewTreeRepo) as ViewTree
    }
    val DiscoverViewItem: ViewTree by lazy {
        ViewTreeConfig.get().discoverViewItem.invoke(ViewTreeRepo) as ViewTree
    }
    val SettingAvatarView: ViewTree by lazy {
        ViewTreeConfig.get().settingAvatarView.invoke(ViewTreeRepo) as ViewTree
    }
    val ChatHinterItem: ViewTree by lazy {
        ViewTreeConfig.get().chatHinterItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ChatLeftContactCardItem: ViewTree by lazy {
        ViewTreeConfig.get().chatLeftContactCardItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ChatLeftPositionItem: ViewTree by lazy {
        ViewTreeConfig.get().chatLeftPositionItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ChatLeftPictureItem: ViewTree by lazy {
        ViewTreeConfig.get().chatLeftPictureItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ChatLeftRedPacketItem: ViewTree by lazy {
        ViewTreeConfig.get().chatLeftRedPacketItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ChatLeftSharingItem: ViewTree by lazy {
        ViewTreeConfig.get().chatLeftSharingItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ChatRightContactCardItem: ViewTree by lazy {
        ViewTreeConfig.get().chatRightContactCardItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ChatRightPositionItem: ViewTree by lazy {
        ViewTreeConfig.get().chatRightPositionItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ChatRightPictureItem: ViewTree by lazy {
        ViewTreeConfig.get().chatRightPictureItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ChatRightRedPacketItem: ViewTree by lazy {
        ViewTreeConfig.get().chatRightRedPacketItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ChatRightSharingItem: ViewTree by lazy {
        ViewTreeConfig.get().chatRightSharingItem.invoke(ViewTreeRepo) as ViewTree
    }

    val ChatRightMessageItem: ViewTree by lazy {
        ViewTreeConfig.get().chatRightMessageItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ChatLeftMessageItem: ViewTree by lazy {
        ViewTreeConfig.get().chatLeftMessageItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ChatRightAudioMessageItem: ViewTree by lazy {
        ViewTreeConfig.get().chatRightAudioMessageItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ChatLeftAudioMessageItem: ViewTree by lazy {
        ViewTreeConfig.get().chatLeftAudioMessageItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ChatRightCallMessageItem: ViewTree by lazy {
        ViewTreeConfig.get().chatRightCallMessageItem.invoke(ViewTreeRepo) as ViewTree
    }
    val ChatLeftCallMessageItem: ViewTree by lazy {
        ViewTreeConfig.get().chatLeftCallMessageItem.invoke(ViewTreeRepo) as ViewTree
    }
    val RefRightMessageItem: ViewTree by lazy {
        ViewTreeConfig.get().refRightMessageItem.invoke(ViewTreeRepo) as ViewTree
    }
    val RefLeftMessageItem: ViewTree by lazy {
        ViewTreeConfig.get().refLeftMessageItem.invoke(ViewTreeRepo) as ViewTree
    }

    //    小程序上方的action bar
    val ActionBarItem: ViewTree by lazy {
        ViewTreeConfig.get().actionBarItem.invoke(ViewTreeRepo) as ViewTree
    }

    // 聊天界面的action bar
    val ActionBarInConversationItem: ViewTree by lazy {
        ViewTreeConfig.get().actionBarInConversationItem.invoke(ViewTreeRepo) as ViewTree
    }

    // //通过搜索进入的聊天界面的action bar
    val ActionBarInSearchConversationItem: ViewTree by lazy {
        ViewTreeConfig.get().actionBarInSearchConversationItem.invoke(ViewTreeRepo) as ViewTree
    }

    // 包含聊天内容和底部的部分
    val ChattingScrollLayoutItem: ViewTree by lazy {
        ViewTreeConfig.get().chattingScrollLayoutItem.invoke(ViewTreeRepo) as ViewTree
    }

    // 聊天界面
    val ChattingUILayoutItem: ViewTree by lazy {
        ViewTreeConfig.get().chattingUILayoutItem.invoke(ViewTreeRepo) as ViewTree
    }

    // 聊天界面(搜索进入)
    val ChattingUILayoutInSearchItem: ViewTree by lazy {
        ViewTreeConfig.get().chattingUILayoutInSearchItem.invoke(ViewTreeRepo) as ViewTree
    }

    // 主界面的action bar
    val ActionBarContainerItem: ViewTree by lazy {
        ViewTreeConfig.get().actionBarContainerItem.invoke(ViewTreeRepo) as ViewTree
    }
//
//    // 朋友圈的action bar
//    val ActionBarInFriendsgroupItem: ViewTree by lazy {
//        ViewTreeConfig.get().actionBarInFriendsgroupItem.invoke(ViewTreeRepo) as ViewTree
//    }
}

// 所有微信版本的repo
@Suppress("unused")
object ViewTreeRepo {
    val tmp: ViewTree by lazy {
        ViewTree(
                mapOf("tmp" to intArrayOf(0, 1, 0)),
                ViewTreeItem("com.tencent.mm.ui.contact."))
    }

    //region 主界面
    //region 0
    val ConversationListViewItem: ViewTree by lazy {
        ViewTree(
                mapOf("contentView" to intArrayOf(1),
                        "chatNameView" to intArrayOf(1, 0, 0, 0),
                        "chatTimeView" to intArrayOf(1, 0, 1),
                        "recentMsgView" to intArrayOf(1, 1, 0, 1),
                        "unreadCountView" to intArrayOf(0, 1),
                        "unreadView" to intArrayOf(0, 2)),
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem(CC.ImageView.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.ImageView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                        ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ImageView.name)))
                                )))))))
    }
    val ConversationListViewItem_7_0_5: ViewTree by lazy {
        ViewTree(
                mapOf("contentView" to intArrayOf(1),
                        "chatNameView" to intArrayOf(1, 0, 0, 0),
                        "chatTimeView" to intArrayOf(1, 0, 1),
                        "recentMsgView" to intArrayOf(1, 1, 0, 1),
                        "unreadCountView" to intArrayOf(0, 1),
                        "unreadView" to intArrayOf(0, 2)),
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem(CC.ImageView.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.ImageView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                        ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.WeImageView),
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ImageView.name)))
                                )))))))
    }
    val ConversationListViewItem_7_0_12: ViewTree by lazy {
        ViewTree(
                mapOf("contentView" to intArrayOf(1),
                        "chatNameView" to intArrayOf(1, 0, 0, 0),
                        "chatTimeView" to intArrayOf(1, 0, 1),
                        "recentMsgView" to intArrayOf(1, 1, 0, 1),
                        "unreadCountView" to intArrayOf(0, 1),
                        "unreadView" to intArrayOf(0, 2)),
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem(CC.ImageView.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.ImageView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                        ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.WeImageView),
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.WeImageView)))
                                )))))))
    }
    val ConversationListViewItem_7_0_13: ViewTree by lazy {
        ViewTree(
                mapOf("contentView" to intArrayOf(1),
                        "chatNameView" to intArrayOf(1, 0, 0, 0),
                        "chatTimeView" to intArrayOf(1, 0, 1),
                        "recentMsgView" to intArrayOf(1, 1, 0, 1),
                        "unreadCountView" to intArrayOf(0, 1),
                        "unreadView" to intArrayOf(0, 2)),
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem(CC.ImageView.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.ImageView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                        ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.WeImageView),
                                                ViewTreeItem(CC.WeImageView),
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.WeImageView)))
                                )))))))
    }
    val ConversationListViewItem_7_0_14: ViewTree by lazy {
        ViewTree(
                mapOf("contentView" to intArrayOf(1),
                        "chatNameView" to intArrayOf(1, 0, 0, 0),
                        "chatTimeView" to intArrayOf(1, 0, 1),
                        "recentMsgView" to intArrayOf(1, 1, 0, 1),
                        "unreadCountView" to intArrayOf(0, 1),
                        "unreadView" to intArrayOf(0, 2)),
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem(CC.ImageView.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.ImageView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                        ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.WeImageView),
                                                ViewTreeItem(CC.WeImageView),
                                                ViewTreeItem(CC.WeImageView)))
                                )))))))
    }
    val ConversationListViewItem_8_0_10: ViewTree by lazy {
        ViewTree(
                mapOf("contentView" to intArrayOf(0, 1),
                        "chatNameView" to intArrayOf(0, 1, 0, 0, 0),
                        "chatTimeView" to intArrayOf(0, 1, 0, 1),
                        "recentMsgView" to intArrayOf(0, 1, 1, 0, 1),
                        "unreadCountView" to intArrayOf(0, 0, 1),
                        "unreadView" to intArrayOf(0, 0, 2)),
                ViewTreeItem("com.tencent.mm.ui.conversation.ConversationFolderItemView", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                                ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.ImageView.name),
                                                        ViewTreeItem(Classes.NoMeasuredTextView.name))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageView),
                                                        ViewTreeItem(CC.WeImageView),
                                                        ViewTreeItem(CC.WeImageView)))
                                        )))))))
                ))
    }
    //endregion

    //region 1
    //8.0.7 版本开始的 新联系人界面
    val ContactLayoutListenerViewItem: ViewTree by lazy {
        ViewTree(
                mapOf(
                        "backgroundImage" to intArrayOf(0),
                        "backgroundMask" to intArrayOf(0, 0),
                        "blackBroad" to intArrayOf(0, 1, 0),
                        "WxRecyclerView" to intArrayOf(0, 1, 0, 0, 0),
                        "WxRecyclerView_ContactHeaderItem" to intArrayOf(0)
                ),
                ViewTreeItem("com.tencent.mm.ui.LayoutListenerView", arrayOf(
                        ViewTreeItem("com.tencent.mm.ui.widget.pulldown.MMWeUIBounceView", arrayOf(//backgroundImage
                                ViewTreeItem("androidx.core.widget.NestedScrollView", arrayOf(//backgroundMask
                                        ViewTreeItem(CC.LinearLayout.name)
                                )),
                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(//blackBroad
                                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                        ViewTreeItem("com.tencent.mm.view.recyclerview.WxRecyclerView", arrayOf(//WxRecyclerView
                                                                ViewTreeRepoThisVersion.ContactHeaderItem.item,//ContactHeaderItem
                                                                ViewTreeRepoThisVersion.ContactListViewItem.item//ContactListViewItem
                                                                //后面都是ContactListViewItem
                                                        ))
                                                ))
                                        ))
                                ))
                        ))
                ))
        )
    }
    //8.0.14 版本开始更换联系人界面的 hook 点, 不检测 ContactListViewItem
    val ContactLayoutListenerViewItem_8_0_14: ViewTree by lazy {
        ViewTree(
                mapOf(
                        "backgroundImage" to intArrayOf(0),
                        "backgroundMask" to intArrayOf(0, 0),
                        "blackBroad" to intArrayOf(0, 1, 0),
                        "WxRecyclerView" to intArrayOf(0, 1, 0, 0, 0),
                        "WxRecyclerView_ContactHeaderItem" to intArrayOf(0)
                ),
                ViewTreeItem("com.tencent.mm.ui.LayoutListenerView", arrayOf(
                        ViewTreeItem("com.tencent.mm.ui.widget.pulldown.MMWeUIBounceView", arrayOf(//backgroundImage
                                ViewTreeItem("androidx.core.widget.NestedScrollView", arrayOf(//backgroundMask
                                        ViewTreeItem(CC.LinearLayout.name)
                                )),
                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(//blackBroad
                                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                        ViewTreeItem("com.tencent.mm.view.recyclerview.WxRecyclerView", arrayOf(//WxRecyclerView
                                                                ViewTreeRepoThisVersion.ContactHeaderItem.item//ContactHeaderItem
                                                        )),
                                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                ViewTreeItem("com.tencent.mm.ui.base.AlphabetScrollBar")
                                                        ))
                                                ))
                                        ))
                                ))
                        ))
                ))
        )
    }

    //单个联系人
    val ContactListViewItem: ViewTree by lazy {
        ViewTree(
                mapOf(
                        "innerView" to intArrayOf(1, 0),
                        "contentView" to intArrayOf(1),
                        "titleView" to intArrayOf(1, 0, 3),
                        "headTextView" to intArrayOf(0)),
                ViewTreeItem(Classes.NoDrawingCacheLinearLayout.name, arrayOf(
                        ViewTreeItem(CC.TextView.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem("com.tencent.mm.ui.AddressView"))))))))
    }
    val ContactListViewItem_7_0_0: ViewTree by lazy {
        ViewTree(
                mapOf("headerView" to intArrayOf(0),
                        "innerView" to intArrayOf(1, 0),
                        "contentView" to intArrayOf(1),
                        "titleView" to intArrayOf(1, 0, 3, 1),
                        "headTextView" to intArrayOf(0, 0)),
                ViewTreeItem(Classes.NoDrawingCacheLinearLayout.name, arrayOf(
                        ViewTreeItem(CC.RelativeLayout.name),//headerView
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(//contentView
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(//innerView
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem("com.tencent.mm.ui.chatting.view.AvatarImageView"),
                                                ViewTreeItem("com.tencent.mm.ui.AddressView")//titleView
                                        )))))))))
    }
    val ContactListViewItem_8_0_0: ViewTree by lazy {
        ViewTree(
                mapOf("headerView" to intArrayOf(0),
                        "innerView" to intArrayOf(1, 0),
                        "contentView" to intArrayOf(1),
                        "titleView_8_0" to intArrayOf(1, 0, 3, 1),
                        "headTextView" to intArrayOf(0, 0)),
                ViewTreeItem(Classes.NoDrawingCacheLinearLayout.name, arrayOf(
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(//headerView
                                ViewTreeItem(CC.TextView.name)//headTextView
                        )),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(//contentView
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(//innerView
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem("com.tencent.mm.ui.chatting.view.AvatarImageView"),
                                                ViewTreeItem(Classes.NoMeasuredTextView.name)//titleView_8_0
                                        )))))))))
    }
    val ContactListViewItem_8_0_19: ViewTree by lazy {
        ViewTree(
                mapOf("headerView" to intArrayOf(0),
                        "innerView" to intArrayOf(1, 0, 0, 1),
                        "contentView" to intArrayOf(1),
                        "titleView_8_0" to intArrayOf(1, 0, 0, 1, 0, 0),
                        "headTextView" to intArrayOf(0, 0)),
                ViewTreeItem(Classes.NoDrawingCacheLinearLayout.name, arrayOf(
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(//headerView
                                ViewTreeItem(CC.TextView.name)//headTextView
                        )),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(//contentView
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.WeImageView),
                                                ViewTreeItem(CC.TableLayout.name, arrayOf(//innerView
                                                        ViewTreeItem(CC.TableRow.name, arrayOf(
                                                                ViewTreeItem(CC.TextView.name),//titleView_8_0
                                                                ViewTreeItem(CC.TextView.name)
//                                                ViewTreeItem(Classes.NoMeasuredTextView.name)
                                                        ))))
                                        )))))))))
    }
    val ContactListViewItem_8_0_23: ViewTree by lazy {
        ViewTree(
                mapOf("headerView" to intArrayOf(0),
                        "innerView" to intArrayOf(1, 0, 0, 1),
                        "contentView" to intArrayOf(1),
                        "titleView_8_0" to intArrayOf(1, 0, 0, 1, 0, 0),
                        "headTextView" to intArrayOf(0, 0)),
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(//headerView
                                ViewTreeItem(CC.TextView.name)//headTextView
                        )),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(//contentView
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.WeImageView),
                                                ViewTreeItem(CC.TableLayout.name, arrayOf(//innerView
                                                        ViewTreeItem(CC.TableRow.name, arrayOf(
                                                                ViewTreeItem(CC.TextView.name),//titleView_8_0
                                                                ViewTreeItem(CC.TextView.name)
//                                                ViewTreeItem(Classes.NoMeasuredTextView.name)
                                                        ))))
                                        )))))))))
    }

    // 联系人上方分组列表
    val ContactHeaderItem: ViewTree by lazy {
        ViewTree(
                // 企业联系人分组
                mapOf("ContactWorkItemBorderTop" to intArrayOf(5, 0, 0),
                        "ContactWorkItem" to intArrayOf(7)),
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        // 新的电话
                        ViewTreeItem("com.tencent.mm.ui.contact."),
                        // 新的朋友
                        ViewTreeItem("com.tencent.mm.ui.contact."),
                        // 仅聊天的朋友
                        ViewTreeItem("com.tencent.mm.ui.contact."),
                        // 群聊
                        ViewTreeItem("com.tencent.mm.ui.contact."),
                        // 标签
                        ViewTreeItem("com.tencent.mm.ui.contact."),
                        // 公众号
                        ViewTreeItem("com.tencent.mm.ui.contact.BizContactEntranceView", arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name)))
                        )),
                        // --
                        ViewTreeItem("com.tencent.mm.ui.contact."),
                        // 我的企业及企业联系人
                        ViewTreeItem("com.tencent.mm.ui.contact."))))
    }

    // region 企业联系人分组 7_0_17
    val ContactWorkItem: ViewTree by lazy {
        ViewTree(
                mapOf(
                        "borderLineTop" to intArrayOf(0, 0),
                        "ContactContentsItem" to intArrayOf(0, 1, 0),
                        "ContactContentsItem1" to intArrayOf(0, 1, 1)),
                ViewTreeItem("com.tencent.mm.ui.contact.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name),//borderLineTop
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem("com.tencent.mm.ui.contact.")
                                )))))))
    }

    val ContactWorkContactsItem: ViewTree by lazy {
        ViewTree(
                mapOf("titleView" to intArrayOf(0),
                        "borderLineBottom" to intArrayOf(0, 0, 1),
                        "headTextView" to intArrayOf(0, 0, 1, 0)),
                ViewTreeItem("com.tencent.mm.ui.contact.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.ImageView.name))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(//borderLineBottom
                                                ViewTreeItem(CC.TextView.name))))))))))
    }
    val ContactMyWorkItem: ViewTree by lazy {
        ViewTree(
                mapOf("titleView" to intArrayOf(0),
                        "borderLineBottom" to intArrayOf(0, 0),
                        "headTextView" to intArrayOf(0, 0, 1)),
                ViewTreeItem("com.tencent.mm.ui.contact.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.ImageView.name))),
                                        ViewTreeItem(CC.TextView.name))))))))
    }
    // endregion
    //endregion

    //region 2
    val DiscoverViewItem: ViewTree by lazy {
        ViewTree(
                mapOf("iconImageView" to intArrayOf(0, 0, 0, 0),
                        "titleView" to intArrayOf(0, 0, 0, 1, 0, 0)),
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem("com.tencent.mm.ui.MMImageView"),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.TextView.name),
                                                                ViewTreeItem(CC.TextView.name),
                                                                ViewTreeItem(CC.ImageView.name))),
                                                        ViewTreeItem(CC.TextView.name))))))))))))
    }
    val DiscoverViewItem_7_0_0: ViewTree by lazy {
        ViewTree(
                mapOf("iconImageView" to intArrayOf(0, 0, 0, 0),
                        "titleView" to intArrayOf(0, 0, 0, 1, 0, 0, 0),
                        "unreadPointView" to intArrayOf(0, 0, 0, 1, 2, 1),
                        "unreadCountView" to intArrayOf(0, 0, 0, 1, 0, 0, 1),

                        "groupBorderTop" to intArrayOf(0),
                        "contentBorder" to intArrayOf(0),
                        "groupBorderBottom" to intArrayOf(0, 0, 0, 1),
                        "borderRight" to intArrayOf(1)),
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem("com.tencent.mm.ui.MMImageView"),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.TextView.name),
                                                                        ViewTreeItem(CC.TextView.name),
                                                                        ViewTreeItem(CC.ImageView.name))),
                                                                ViewTreeItem(CC.TextView.name))))))))))))))
    }
    val DiscoverViewItem_7_0_10: ViewTree by lazy {
        ViewTree(
                mapOf("iconImageView" to intArrayOf(1, 0, 0, 0, 0),
                        "titleView" to intArrayOf(1, 0, 0, 0, 1, 0, 0, 0),
                        "unreadPointView" to intArrayOf(1, 0, 0, 0, 1, 2, 1),
                        "unreadCountView" to intArrayOf(1, 0, 0, 0, 1, 0, 0, 1),

                        "groupBorderTop" to intArrayOf(0),
                        "contentBorder" to intArrayOf(1, 0),
                        "groupBorderBottom" to intArrayOf(1, 0, 0, 0, 1),
                        "borderRight" to intArrayOf(1, 1)
                ),
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        ViewTreeItem(CC.View.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem("com.tencent.mm.ui.MMImageView"),
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                ViewTreeItem(CC.TextView.name),
                                                                                ViewTreeItem(CC.TextView.name),
                                                                                ViewTreeItem(CC.ImageView.name))),
                                                                        ViewTreeItem(CC.TextView.name))))))))))))))))
    }
    val DiscoverViewItem_7_0_22: ViewTree by lazy {
        ViewTree(
                mapOf("iconImageView" to intArrayOf(1, 0, 0, 0, 0),
                        "titleView" to intArrayOf(1, 0, 0, 0, 1, 0, 0, 0),
                        "unreadCountView" to intArrayOf(1, 0, 0, 0, 1, 0, 0, 1),

                        "groupBorderTop" to intArrayOf(0),
                        "contentBorder" to intArrayOf(1, 0),
                        "groupBorderBottom" to intArrayOf(1, 0, 0, 0, 1),

//                        未写出
                        "unreadPointView" to intArrayOf(1, 0, 0, 0, 1, 2, 1),
                        "borderRight" to intArrayOf(1, 1)
                ),
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        ViewTreeItem(CC.View.name),//groupBorderTop
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(//contentBorder
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem("com.tencent.mm.ui.MMImageView"),//iconImageView
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(//groupBorderBottom
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                ViewTreeItem(CC.TextView.name),//titleView
                                                                                ViewTreeItem(CC.TextView.name),//unreadCountView
                                                                                ViewTreeItem(CC.TextView.name),
                                                                                ViewTreeItem(CC.ImageView.name))),
                                                                        ViewTreeItem(CC.TextView.name))))))))))))))))
    }
    //endregion`

    //region 3
    val SettingAvatarView: ViewTree by lazy {
        ViewTree(
                mapOf(
                        "nickNameView" to intArrayOf(0, 1, 0),
                        "wechatTextView" to intArrayOf(0, 1, 1)),
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem(CC.ImageView.name),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(Classes.NoMeasuredTextView.name),
                                        ViewTreeItem(CC.TextView.name))),
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.ImageView.name))))
    }
    val SettingAvatarView_7_0_0: ViewTree by lazy {
        ViewTree(
                mapOf("headView" to intArrayOf(0),
                        "nickNameView" to intArrayOf(1, 0, 1, 0),
                        "wechatTextView" to intArrayOf(1, 0, 1, 1)),
                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                        ViewTreeItem(CC.ImageView.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(Classes.NoMeasuredTextView.name),
                                                ViewTreeItem(CC.TextView.name)))
                                )))))))
    }
    val SettingAvatarView_7_0_12: ViewTree by lazy {
        ViewTree(
                mapOf("headView" to intArrayOf(0),
                        "nickNameView" to intArrayOf(2, 0, 1, 0, 0),
                        "wechatTextView" to intArrayOf(2, 0, 1, 1, 0)),
                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                        ViewTreeItem(CC.ImageView.name),
                        ViewTreeItem(CC.FrameLayout.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(Classes.NoMeasuredTextView.name))))))))))))
    }
    val SettingAvatarView_8_0_0: ViewTree by lazy {
        ViewTree(
                mapOf("headView" to intArrayOf(1, 0, 0, 0),
                        "nickNameView" to intArrayOf(3, 0, 1, 0, 0),
                        "wechatTextView" to intArrayOf(3, 0, 1, 1, 0),
                        "q1" to intArrayOf(4, 0)
//                        "q2" to intArrayOf(4, 2, 1)
                ),
                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                        ViewTreeItem(CC.ImageView.name),//headView
                        ViewTreeItem(CC.ImageView.name),
                        ViewTreeItem(CC.FrameLayout.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(Classes.NoMeasuredTextView.name))),//nickNameView
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name)))//wechatTextView
                                        )))))),
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem(CC.RelativeLayout.name)//q1
//                        ViewTreeItem(CC.ImageView.name),
//                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
//                                ViewTreeItem(CC.LinearLayout.name),
//                                ViewTreeItem(CC.View.name)))//q2
                        )))))
    }
    val SettingAvatarView_8_0_3: ViewTree by lazy {
        ViewTree(
                mapOf("headView" to intArrayOf(1, 0, 0, 0),
                        "nickNameView" to intArrayOf(3, 0, 1, 0, 0),
                        "wechatTextView" to intArrayOf(3, 0, 1, 1, 0),
                        "q1" to intArrayOf(5, 0)
                ),
                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                        ViewTreeItem(CC.ImageView.name),//headView
                        ViewTreeItem(CC.ImageView.name),
                        ViewTreeItem(CC.FrameLayout.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(Classes.NoMeasuredTextView.name))),//nickNameView
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name)))//wechatTextView
                                        )))))),
                        ViewTreeItem(CC.View.name),
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name),//q1
                                ViewTreeItem(CC.ImageView.name),
                                ViewTreeItem(CC.RelativeLayout.name)
                        )))))
    }

    val SettingAvatarView_8_0_19: ViewTree by lazy {
        ViewTree(
                mapOf("headView" to intArrayOf(1, 0, 0, 0),
                        "nickNameView" to intArrayOf(3, 0, 1, 0, 0),
                        "wechatTextView" to intArrayOf(3, 0, 1, 1, 0),
                        "q1" to intArrayOf(5, 0)
                ),
                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                        ViewTreeItem(CC.ImageView.name),//headView
                        ViewTreeItem(CC.ImageView.name),
                        ViewTreeItem(CC.FrameLayout.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(Classes.NoMeasuredTextView.name))),//nickNameView
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name)))//wechatTextView
                                        )))))),
                        ViewTreeItem(CC.View.name),
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name),//q1
                                ViewTreeItem(CC.ImageView.name),
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.RelativeLayout.name)
                        )))))
    }
    //endregion
    //endregion

    //region 聊天气泡
    val ChatHinterItem: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(2, 0),
                        "timeView" to intArrayOf(1)),
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        ViewTreeItem(CC.TextView.name),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.widget.MMNeat7extView")//msgView
                        )))))
    }

    //region 图片
    val ChatLeftPictureItem: ViewTree by lazy {
        ViewTree(
                mapOf("nickNameView" to intArrayOf(4, 1, 0),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),// timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),//nickNameView
                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ProgressBar.name)
                                        )))))))))
    }
    val ChatRightPictureItem: ViewTree by lazy {
        ViewTree(
                mapOf(
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),// timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.ImageView.name),
                                                        ViewTreeItem(CC.ProgressBar.name),
                                                        ViewTreeItem(CC.ImageView.name)
                                                )))))))))))
    }
    //endregion

    //region 名片
    val ChatLeftContactCardItem: ViewTree by lazy {
        ViewTree(
                mapOf("nickNameView" to intArrayOf(4, 1, 0),
                        "bgView" to intArrayOf(4, 1, 1, 0),
                        "msgView" to intArrayOf(4, 1, 1, 0, 0, 1, 0),
                        "titleView" to intArrayOf(4, 1, 1, 0, 2),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),// timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),//nickNameView
                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(//bgView
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.ImageView.name),
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMTextView"),//msgView
                                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMTextView")
                                                                )))),
                                                        ViewTreeItem(CC.ImageView.name),
                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMTextView")//titleView
                                                )),
                                                ViewTreeItem(CC.ImageView.name)
                                        )))))),
                        ViewTreeItem(CC.View.name),
                        ViewTreeItem(CC.ImageView.name)
                )))
    }
    val ChatRightContactCardItem: ViewTree by lazy {
        ViewTree(
                mapOf("titleView" to intArrayOf(4, 1, 0, 3, 0, 0, 1, 0),
                        "msgView" to intArrayOf(4, 1, 0, 3, 0, 0, 1, 1),
                        "msgView1" to intArrayOf(4, 1, 0, 3, 0, 2),
                        "bgView" to intArrayOf(4, 1, 0, 3, 0),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ProgressBar.name),
                                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.ImageView.name),
                                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                ViewTreeItem("com.tencent.mm.ui.widget.MMTextView"),//titleView
                                                                                ViewTreeItem("com.tencent.mm.ui.widget.MMTextView")//msgView
                                                                        )))),
                                                                ViewTreeItem(CC.ImageView.name),
                                                                ViewTreeItem("com.tencent.mm.ui.widget.MMTextView"))),//msgView1
                                                        ViewTreeItem(CC.ImageView.name))))))))))))
    }
    //endregion

    //region 分享 + 小程序分享
    val ChatLeftSharingItem: ViewTree by lazy {
        ViewTree(
                mapOf("nickNameView" to intArrayOf(4, 2, 0),
                        "miniProgramBgView" to intArrayOf(4, 2, 1, 0, 0),
                        "miniProgramBgView_miniProgramNameView" to intArrayOf(0, 0, 0, 1),
                        "miniProgramBgView_miniProgramTitleView" to intArrayOf(0, 0, 1),
                        "miniProgramBgView_bgView" to intArrayOf(0, 2),
                        "bgView_titleView" to intArrayOf(0),
                        "bgView_fileNameView" to intArrayOf(2, 0, 0, 0),
                        "bgView_msgView" to intArrayOf(2, 0, 0, 1),
                        "bgView_msgView1" to intArrayOf(2, 2, 0, 1),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),// timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.LinearLayout.name),
                                //skip
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),//nickNameView
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(//miniProgramBgView
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                ViewTreeItem("com.tencent.mm.ui.widget.EllipsizeLayout", arrayOf(
                                                                                        ViewTreeItem(CC.ImageView.name),
                                                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMTextView")//miniProgramNameView
                                                                                )),
                                                                                ViewTreeItem("com.tencent.mm.ui.widget.MMTextView")//miniProgramTitleView
                                                                                //skip
                                                                        )),
                                                                        //skip
                                                                        ViewTreeItem(CC.LinearLayout.name),
                                                                        //skip
                                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf( //bgView
                                                                                ViewTreeItem("com.tencent.mm.ui.widget.MMTextView"),//titleView
                                                                                ViewTreeItem(CC.ImageView.name),
                                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMNeat7extView"),//fileNameView
                                                                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMTextView")//msgView
                                                                                                )),
                                                                                                ViewTreeItem(CC.RelativeLayout.name))),
                                                                                        ViewTreeItem(CC.ImageView.name),
                                                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                                                        ViewTreeItem(CC.ImageView.name),
                                                                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMTextView")//msgView1
                                                                                                )))))))))))))))))))))))
    }
    val ChatRightSharingItem: ViewTree by lazy {
        ViewTree(
                mapOf(
                        "miniProgramBgView" to intArrayOf(4, 1, 1, 1, 0),
                        "miniProgramBgView_miniProgramNameView" to intArrayOf(0, 0, 0, 1),
                        "miniProgramBgView_miniProgramTitleView" to intArrayOf(0, 0, 1),
                        "miniProgramBgView_bgView" to intArrayOf(0, 2),
                        "bgView_titleView" to intArrayOf(0),
                        "bgView_fileNameView" to intArrayOf(2, 0, 0, 0),
                        "bgView_msgView" to intArrayOf(2, 0, 0, 1),
                        "bgView_msgView1" to intArrayOf(2, 2, 0, 1),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),// timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name),
                                //skip
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name),
                                        //skip
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(//miniProgramBgView
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                ViewTreeItem("com.tencent.mm.ui.widget.EllipsizeLayout", arrayOf(
                                                                                        ViewTreeItem(CC.ImageView.name),
                                                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMTextView")//miniProgramNameView
                                                                                )),
                                                                                ViewTreeItem("com.tencent.mm.ui.widget.MMTextView")//miniProgramTitleView
                                                                                //skip
                                                                        )),
                                                                        //skip
                                                                        ViewTreeItem(CC.LinearLayout.name),
                                                                        //skip
                                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf( //bgView
                                                                                ViewTreeItem("com.tencent.mm.ui.widget.MMTextView"),//titleView
                                                                                ViewTreeItem(CC.ImageView.name),
                                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMNeat7extView"),//fileNameView
                                                                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMTextView")//msgView
                                                                                                )),
                                                                                                ViewTreeItem(CC.RelativeLayout.name))),
                                                                                        ViewTreeItem(CC.ImageView.name),
                                                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                                                        ViewTreeItem(CC.ImageView.name),
                                                                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMTextView")//msgView1
                                                                                                )))))))))))))))))))))))
    }
    //endregion

    //region 位置
    val ChatLeftPositionItem: ViewTree by lazy {
        ViewTree(
                mapOf("nickNameView" to intArrayOf(4, 1, 0),
                        "bgView" to intArrayOf(4, 1, 1, 0),
                        "msgView" to intArrayOf(4, 1, 1, 0, 0, 0, 0),
                        "msgView1" to intArrayOf(4, 1, 1, 0, 0, 0, 1),
//                        "titleView" to intArrayOf(4, 1, 1, 0, 2, 0),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),// timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),//nickNameView
                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(//bgView
                                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.TextView.name),//msgView
                                                                        ViewTreeItem(CC.TextView.name)//msgView1
                                                                )),
                                                                ViewTreeItem(CC.ProgressBar.name)
                                                        )),
                                                        ViewTreeItem(CC.ImageView.name),
                                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                                ViewTreeItem(CC.ImageView.name),//titleView
                                                                ViewTreeItem(CC.ImageView.name),
                                                                ViewTreeItem(CC.ProgressBar.name),
                                                                ViewTreeItem(CC.View.name))))))))))))))
    }
    val ChatRightPositionItem: ViewTree by lazy {
        ViewTree(
                mapOf(
                        "bgView" to intArrayOf(4, 2, 1, 0),
                        "msgView" to intArrayOf(4, 2, 1, 0, 0, 0, 0),
                        "msgView1" to intArrayOf(4, 2, 1, 0, 0, 0, 1),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),// timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.ImageView.name),
                                ViewTreeItem(CC.ImageView.name),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(//bgView
                                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.TextView.name),//msgView
                                                                        ViewTreeItem(CC.TextView.name)//msgView1
                                                                )),
                                                                ViewTreeItem(CC.ProgressBar.name)
                                                        )),
                                                        ViewTreeItem(CC.ImageView.name),
                                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                                ViewTreeItem(CC.ImageView.name),//titleView
                                                                ViewTreeItem(CC.ImageView.name),
                                                                ViewTreeItem(CC.ProgressBar.name),
                                                                ViewTreeItem(CC.View.name))))))))))))))
    }
    //endregion

    //region 红包
    val ChatLeftRedPacketItem: ViewTree by lazy {
        ViewTree(
                mapOf("nickNameView" to intArrayOf(4, 1, 0),
                        "bgView" to intArrayOf(4, 1, 1),
                        "adsView" to intArrayOf(4, 1, 1, 0),
//                        "adsView1" to intArrayOf(4, 1,1,0,1),
                        "msgView" to intArrayOf(4, 1, 1, 1, 0, 1, 0),
                        "msgView1" to intArrayOf(4, 1, 1, 1, 0, 1, 1),
                        "titleView" to intArrayOf(4, 1, 1, 1, 2, 0),
                        "leftPicView" to intArrayOf(4, 1, 1, 1, 0, 0),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),// timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),//nickNameView
                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(//bgView
                                                ViewTreeItem(CC.FrameLayout.name, arrayOf(//adsView
                                                        ViewTreeItem(CC.ImageView.name),
                                                        ViewTreeItem(CC.ImageView.name))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.ImageView.name),//leftPicView
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMTextView"),//msgView
                                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMTextView")//msgView1
                                                                )))),
                                                        ViewTreeItem(CC.View.name),
                                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                ViewTreeItem(CC.TextView.name),//titleView
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.TextView.name),
                                                                        ViewTreeItem(CC.ImageView.name),
                                                                        ViewTreeItem(CC.TextView.name))))))))))))))))
    }
    val ChatLeftRedPacketItem_8_0_19: ViewTree by lazy {
        ViewTree(
                mapOf("nickNameView" to intArrayOf(4, 1, 0, 0),
                        "bgView" to intArrayOf(4, 1, 0, 1),
                        "adsView" to intArrayOf(4, 1, 0, 1, 0),
//                        "adsView1" to intArrayOf(4, 1,1,0,1),
                        "msgView" to intArrayOf(4, 1, 0, 1, 1, 0, 1, 0),
                        "msgView1" to intArrayOf(4, 1, 0, 1, 1, 0, 1, 1),
                        "titleView" to intArrayOf(4, 1, 0, 1, 1, 2, 0),
                        "leftPicView" to intArrayOf(4, 1, 0, 1, 1, 0, 0),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),// timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                ViewTreeItem(CC.TextView.name),//nickNameView
                                                ViewTreeItem(CC.FrameLayout.name, arrayOf(//bgView
                                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(//adsView
                                                                ViewTreeItem(CC.ImageView.name),
                                                                ViewTreeItem(CC.ImageView.name))),
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.ImageView.name),//leftPicView
                                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                ViewTreeItem("com.tencent.mm.ui.widget.MMTextView"),//msgView
                                                                                ViewTreeItem("com.tencent.mm.ui.widget.MMTextView")//msgView1
                                                                        )))),
                                                                ViewTreeItem(CC.View.name),
                                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.TextView.name),//titleView
                                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                ViewTreeItem(CC.TextView.name),
                                                                                ViewTreeItem(CC.ImageView.name),
                                                                                ViewTreeItem(CC.TextView.name))))))))))))))))))
    }
    val ChatRightRedPacketItem: ViewTree by lazy {
        ViewTree(
                mapOf("bgView" to intArrayOf(4, 0),
                        "adsView" to intArrayOf(4, 0, 0),
//                      "adsView1" to intArrayOf(4, 0,0,1),
                        "leftPicView" to intArrayOf(4, 0, 1, 0, 0),
                        "msgView" to intArrayOf(4, 0, 1, 0, 1, 0),
                        "msgView1" to intArrayOf(4, 0, 1, 0, 1, 1),
                        "titleView" to intArrayOf(4, 0, 1, 2, 0),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),// timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.FrameLayout.name, arrayOf(//bgView
                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(//adsView
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ImageView.name))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.ImageView.name),//leftPicView
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem("com.tencent.mm.ui.widget.MMTextView"),//msgView
                                                                ViewTreeItem("com.tencent.mm.ui.widget.MMTextView")//msgView1
                                                        )))),
                                                ViewTreeItem(CC.View.name),
                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),//titleView
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.TextView.name),
                                                                ViewTreeItem(CC.ImageView.name),
                                                                ViewTreeItem(CC.TextView.name))))))))))))))
    }
    val ChatRightRedPacketItem_8_0_19: ViewTree by lazy {
        ViewTree(
                mapOf("bgView" to intArrayOf(4, 0, 0),
                        "adsView" to intArrayOf(4, 0, 0, 0),
//                      "adsView1" to intArrayOf(4, 0,0,1),
                        "leftPicView" to intArrayOf(4, 0, 0, 1, 0, 0),
                        "msgView" to intArrayOf(4, 0, 0, 1, 0, 1, 0),
                        "msgView1" to intArrayOf(4, 0, 0, 1, 0, 1, 1),
                        "titleView" to intArrayOf(4, 0, 0, 1, 2, 0),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),// timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(//bgView
                                                ViewTreeItem(CC.FrameLayout.name, arrayOf(//adsView
                                                        ViewTreeItem(CC.ImageView.name),
                                                        ViewTreeItem(CC.ImageView.name))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.ImageView.name),//leftPicView
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMTextView"),//msgView
                                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMTextView")//msgView1
                                                                )))),
                                                        ViewTreeItem(CC.View.name),
                                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                ViewTreeItem(CC.TextView.name),//titleView
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.TextView.name),
                                                                        ViewTreeItem(CC.ImageView.name),
                                                                        ViewTreeItem(CC.TextView.name))))))))))))))))
    }
    //endregion

    //region 气泡
    val ChatRightMessageItem: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(3, 1, 1, 3),
                        "timeView" to intArrayOf(1)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ProgressBar.name),
                                                ViewTreeItem(CC.ImageView.name),
                                                null
                                        )),
                                        ViewTreeItem(CC.ViewStub.name))),
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))))),
                        ViewTreeItem(CC.View.name))))
    }
    val ChatRightMessageItem_7_0_7: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(3, 1, 1, 3, 0),
                        "timeView" to intArrayOf(1)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ProgressBar.name),
                                                ViewTreeItem(CC.ImageView.name),
                                                null
                                        )),
                                        ViewTreeItem(CC.ViewStub.name))),
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))))),
                        ViewTreeItem(CC.View.name))))
    }
    val ChatRightMessageItem_7_0_18: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(4, 1, 1, 3, 0),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ProgressBar.name),
                                                ViewTreeItem(CC.ImageView.name),
                                                null
                                        )),
                                        ViewTreeItem(CC.ViewStub.name))),
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))))),
                        ViewTreeItem(CC.View.name))))
    }
    val ChatRightMessageItem_8_0_23: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(4, 3, 1, 3, 0),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem("com.tencent.mm.ui.base.AnimImageView"),
                                ViewTreeItem(CC.ProgressBar.name),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ProgressBar.name),
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMNeat7extView")//msgView
                                                )))),
                                        ViewTreeItem(CC.ViewStub.name))),
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))))),
                        ViewTreeItem(CC.View.name))))
    }
    val ChatRightMessageItem_8_0_27: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(4, 3, 1, 3, 0),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem("com.tencent.mm.ui.base.AnimImageView"),
                                ViewTreeItem(CC.ProgressBar.name),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ProgressBar.name),
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMNeat7extView")//msgView
                                                )))),
                                        ViewTreeItem(CC.ViewStub.name))),
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))))),
                        ViewTreeItem(CC.View.name))))
    }
    val ChatLeftMessageItem: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(3, 1, 1),
                        "timeView" to intArrayOf(1)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),// timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),
                                        null,
                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.TextView.name))), null
                                )))),
                        ViewTreeItem(CC.View.name))))
    }
    val ChatLeftMessageItem_7_0_18: ViewTree by lazy {
        ViewTree(
                mapOf("nickNameView" to intArrayOf(4, 1, 0),
                        "msgView" to intArrayOf(4, 1, 1),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),// timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),//nickNameView
                                        ViewTreeItem("com.tencent.mm.ui.widget.MMNeat7extView"),//msgView
                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.TextView.name))), null
                                )))),
                        ViewTreeItem(CC.View.name))))
    }
    val ChatLeftMessageItem_8_0_23: ViewTree by lazy {
        ViewTree(
                mapOf("nickNameView" to intArrayOf(4, 1, 0),
                        "msgView" to intArrayOf(4, 1, 1, 0),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),// timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),// nickNameView
                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                ViewTreeItem("com.tencent.mm.ui.widget.MMNeat7extView"), // msgView
                                                ViewTreeItem("com.tencent.mm.ui.base.AnimImageView"),
                                                ViewTreeItem(CC.ProgressBar.name)
                                        )),
                                        ViewTreeItem(CC.RelativeLayout.name),
                                        ViewTreeItem(CC.LinearLayout.name),
                                        ViewTreeItem(CC.LinearLayout.name),
                                        ViewTreeItem(CC.ViewStub.name))))),
                        ViewTreeItem(CC.View.name))))
    }
    val ChatLeftMessageItem_8_0_27: ViewTree by lazy {
        ViewTree(
                mapOf("nickNameView" to intArrayOf(4, 1, 0),
                        "msgView" to intArrayOf(4, 1, 1, 0),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),// timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),// nickNameView
                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                ViewTreeItem("com.tencent.mm.ui.widget.MMNeat7extView"), // msgView
                                                ViewTreeItem("com.tencent.mm.ui.base.AnimImageView"),
                                                ViewTreeItem(CC.ProgressBar.name)
                                        )),
                                        ViewTreeItem(CC.RelativeLayout.name),
                                        ViewTreeItem(CC.LinearLayout.name),
                                        ViewTreeItem(CC.LinearLayout.name),
                                        ViewTreeItem(CC.ViewStub.name))))),
                        ViewTreeItem(CC.View.name))))
    }
    //endregion

    //region 语音
    val ChatRightAudioMessageItem: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(3, 5, 0, 0),
                        "msgAnimView" to intArrayOf(3, 5, 0, 1),
                        "timeView" to intArrayOf(1)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.ProgressBar.name))),
                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                ViewTreeItem(CC.TextView.name),//msgView
                                                ViewTreeItem("com.tencent.mm.ui.base.AnimImageView"))))))),
                        ViewTreeItem(CC.View.name))))
    }
    val ChatRightAudioMessageItem_7_0_18: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(4, 5, 0, 0),
                        "msgAnimView" to intArrayOf(4, 5, 0, 1),
                        "audioLengthView" to intArrayOf(4, 5, 0, 3),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.ProgressBar.name))),
                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                ViewTreeItem(CC.TextView.name),//msgView
                                                ViewTreeItem("com.tencent.mm.ui.base.AnimImageView"),//msgAnimView
                                                ViewTreeItem(CC.ProgressBar.name),
                                                ViewTreeItem(CC.TextView.name)//audioLengthView
                                        )))))),
                        ViewTreeItem(CC.View.name))))
    }
    val ChatLeftAudioMessageItem: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(3, 4, 0, 0),
                        "msgAnimView" to intArrayOf(3, 4, 0, 1),
                        "timeView" to intArrayOf(1)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.TextView.name),
                                null,
                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                ViewTreeItem(CC.TextView.name),//msgView
                                                ViewTreeItem("com.tencent.mm.ui.base.AnimImageView")//msgAnimView
                                        )))))),
                        ViewTreeItem(CC.View.name))))
    }
    val ChatLeftAudioMessageItem_7_0_0: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(3, 1, 3, 0, 0),
                        "msgAnimView" to intArrayOf(3, 1, 3, 0, 1),
                        "timeView" to intArrayOf(1)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.TextView.name),
                                        null,
                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),//msgView
                                                        ViewTreeItem("com.tencent.mm.ui.base.AnimImageView")//msgAnimView
                                                )))))))),
                        ViewTreeItem(CC.View.name))))
    }
    val ChatLeftAudioMessageItem_7_0_18: ViewTree by lazy {
        ViewTree(
                mapOf("nickNameView" to intArrayOf(4, 1, 0),
                        "msgView" to intArrayOf(4, 1, 3, 0, 0),
                        "msgAnimView" to intArrayOf(4, 1, 3, 0, 1),
                        "audioLengthView" to intArrayOf(4, 1, 3, 0, 3),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),//nickNameView
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem("com.tencent.mm.ui.base.AnimImageView"),
                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),//msgView
                                                        ViewTreeItem("com.tencent.mm.ui.base.AnimImageView"),//msgAnimView
                                                        ViewTreeItem(CC.ProgressBar.name),
                                                        ViewTreeItem(CC.TextView.name)//audioLengthView
                                                ))
                                        ))
                                )))),
                        ViewTreeItem(CC.View.name))))
    }
    //endregion

    //region 电话
    val ChatRightCallMessageItem: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(3, 0),
                        "timeView" to intArrayOf(1)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.LinearLayout.name))),
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))))),
                        ViewTreeItem(CC.View.name))))
    }
    val ChatRightCallMessageItem_7_0_18: ViewTree by lazy {
        ViewTree(
                mapOf("bgView" to intArrayOf(4, 0),
                        "icon" to intArrayOf(4, 0, 1),
                        "msgView" to intArrayOf(4, 0, 0),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),//msgView
                                        ViewTreeItem(CC.LinearLayout.name))),//icon
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))))),
                        ViewTreeItem(CC.View.name))))
    }
    val ChatLeftCallMessageItem: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(3, 1),
                        "timeView" to intArrayOf(1)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name),
                                        ViewTreeItem(CC.TextView.name))),
                                ViewTreeItem(CC.ImageView.name))),
                        ViewTreeItem(CC.View.name))))
    }
    val ChatLeftCallMessageItem_7_0_18: ViewTree by lazy {
        ViewTree(
                mapOf("bgView" to intArrayOf(4, 1),
                        "icon" to intArrayOf(4, 1, 0),
                        "msgView" to intArrayOf(4, 1, 1),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(//bgView
                                        ViewTreeItem(CC.LinearLayout.name),//icon
                                        ViewTreeItem(CC.TextView.name))),//msgView
                                ViewTreeItem(CC.ImageView.name))),
                        ViewTreeItem(CC.View.name))))
    }
    //endregion

    //region 引用
    val RefRightMessageItem: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(3, 2, 1, 2, 0),
                        "timeView" to intArrayOf(1)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout"),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ProgressBar.name),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMNeat7extView"),
                                                        ViewTreeItem(CC.RelativeLayout.name),
                                                        ViewTreeItem(CC.ViewStub.name)
                                                )))))))),
                        ViewTreeItem(CC.View.name))))
    }
    val RefRightMessageItem_7_0_18: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(4, 2, 1, 2, 0),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout"),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ProgressBar.name),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMNeat7extView"),
                                                        ViewTreeItem(CC.RelativeLayout.name),
                                                        ViewTreeItem(CC.ViewStub.name)
                                                )))))))),
                        ViewTreeItem(CC.View.name))))
    }
    val RefRightMessageItem_8_0_23: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(4, 2, 1, 2, 0, 2),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout"),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ProgressBar.name),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                null,
                                                                null,
                                                                ViewTreeItem("com.tencent.mm.ui.widget.MMNeat7extView") // msgView
                                                        )),
                                                        ViewTreeItem(CC.RelativeLayout.name),
                                                        ViewTreeItem(CC.ViewStub.name)
                                                )))))))),
                        ViewTreeItem(CC.View.name))))
    }
    val RefLeftMessageItem: ViewTree by lazy {
        ViewTree(
                mapOf("msgView" to intArrayOf(3, 1, 1, 0),
                        "timeView" to intArrayOf(1)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem("com.tencent.mm.ui.widget.MMNeat7extView"),
                                                ViewTreeItem(CC.LinearLayout.name),
                                                ViewTreeItem(CC.ViewStub.name)))
                                )))),
                        ViewTreeItem(CC.View.name))))
    }
    val RefLeftMessageItem_7_0_18: ViewTree by lazy {
        ViewTree(
                mapOf("nickNameView" to intArrayOf(4, 1, 0),
                        "msgView" to intArrayOf(4, 1, 1, 0),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem("com.tencent.mm.ui.widget.MMNeat7extView"),
                                                ViewTreeItem(CC.LinearLayout.name),
                                                ViewTreeItem(CC.ViewStub.name)))
                                )))),
                        ViewTreeItem(CC.View.name))))
    }
    val RefLeftMessageItem_8_0_23: ViewTree by lazy {
        ViewTree(
                mapOf("nickNameView" to intArrayOf(4, 1, 0),
                        "msgView" to intArrayOf(4, 1, 1, 0, 0),
                        "timeView" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.ui.chatting.viewitems.", arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.TextView.name))),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.View.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.View.name))),
                        ViewTreeItem(CC.TextView.name),//timeView
                        ViewTreeItem(CC.CheckBox.name),
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem("com.tencent.mm.ui.base.MaskLayout", arrayOf(
                                        null,
                                        ViewTreeItem(CC.ViewStub.name),
                                        ViewTreeItem(CC.View.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.TextView.name), // nickNameView
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMNeat7extView") // msgView
                                                )),
                                                ViewTreeItem(CC.LinearLayout.name),
                                                ViewTreeItem(CC.ViewStub.name)))
                                )))),
                        ViewTreeItem(CC.View.name))))
    }
    //endregion
    //endregion

    val ActionBarContainerItem: ViewTree by lazy {
        ViewTree(
                mapOf(),
                ViewTreeItem("android.support.v7.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("android.support.v7.widget.Toolbar", arrayOf(
                                ViewTreeItem("android.support.v7.widget.ActionMenuView", arrayOf(
                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ImageView.name))),
                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name),
                                                ViewTreeItem(CC.ImageView.name))))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageView.name))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                        ViewTreeItem(CC.ImageView.name),
                                                        ViewTreeItem(CC.TextView.name))),
                                                ViewTreeItem(CC.TextView.name))))))))))
    }
//    val ActionBarInFriendsgroupItem: ViewTree by lazy {
//        ViewTree(
//                mapOf(),
//                ViewTreeItem("android.support.v7.widget.ActionBarContainer", arrayOf(
//                        ViewTreeItem("android.support.v7.widget.Toolbar", arrayOf(
//                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
//                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
//                                                ViewTreeItem(CC.ImageView.name))),
//                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
//                                                ViewTreeItem("com.tencent.mm.ui.widget.AlbumChooserView"))))))))))
//    }

    //region 聊天界面
    //聊天界面(头)
    val ActionBarInConversationItem: ViewTree by lazy {
        ViewTree(
                mapOf("title" to intArrayOf(0, 1, 1, 0),
                        "goBackButton" to intArrayOf(0, 1, 0, 0)),
//                        "infoButton" to intArrayOf(0, 0, 0, 4, 0)),
                ViewTreeItem("android.support.v7.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("android.support.v7.widget.Toolbar", arrayOf(
                                ViewTreeItem("android.support.v7.widget.ActionMenuView", arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageButton.name),
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.ImageView.name))),
                                                ViewTreeItem(CC.Button.name),
                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                        ViewTreeItem(CC.ImageButton.name),
                                                        ViewTreeItem(CC.ImageView.name))))))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.WeImageView))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.TextView.name))),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name))))),
                        ViewTreeItem("android.support.v7.widget.ActionBarContextView"))))
    }
    val ActionBarInConversationItem_7_0_15: ViewTree by lazy {
        ViewTree(
                mapOf("title" to intArrayOf(0, 1, 1, 0),
                        "goBackButton" to intArrayOf(0, 1, 0, 0)
//                        "infoButton" to intArrayOf(0, 0, 0, 4, 0)
                ),
                ViewTreeItem("android.support.v7.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("android.support.v7.widget.Toolbar", arrayOf(
                                ViewTreeItem("android.support.v7.widget.ActionMenuView", arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageButton.name),
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.ImageView.name))),
                                                ViewTreeItem(CC.Button.name),
                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageView),
                                                        ViewTreeItem(CC.ImageView.name))))))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.WeImageView))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.TextView.name))),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name))))),
                        ViewTreeItem("android.support.v7.widget.ActionBarContextView"))))
    }
    val ActionBarInConversationItem_7_0_16: ViewTree by lazy {
        ViewTree(
                mapOf("title" to intArrayOf(0, 1, 1, 0, 0),
                        "goBackButton" to intArrayOf(0, 1, 0, 0)
//                        "infoButton" to intArrayOf(0, 0, 0, 4, 0)
                ),
                ViewTreeItem("android.support.v7.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("android.support.v7.widget.Toolbar", arrayOf(
                                ViewTreeItem("android.support.v7.widget.ActionMenuView", arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageButton.name),
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.ImageView.name))),
                                                ViewTreeItem(CC.Button.name),
                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageView),
                                                        ViewTreeItem(CC.ImageView.name))))))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.WeImageView))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))))),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name))))),
                        ViewTreeItem("android.support.v7.widget.ActionBarContextView"))))
    }
    val ActionBarInConversationItem_7_0_17: ViewTree by lazy {
        ViewTree(
                mapOf("title" to intArrayOf(0, 1, 2, 0, 0),
                        "goBackButton" to intArrayOf(0, 1, 0, 0)
//                        "infoButton" to intArrayOf(0, 0, 0, 4, 0)
                ),
                ViewTreeItem("android.support.v7.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("android.support.v7.widget.Toolbar", arrayOf(
                                ViewTreeItem("android.support.v7.widget.ActionMenuView", arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageButton.name),
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.ImageView.name))),
                                                ViewTreeItem(CC.Button.name),
                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageView),
                                                        ViewTreeItem(CC.ImageView.name))))))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.WeImageView))),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))))),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name))))),
                        ViewTreeItem("android.support.v7.widget.ActionBarContextView"))))
    }
    val ActionBarInConversationItem_8_0_0: ViewTree by lazy {
        ViewTree(
                mapOf("title" to intArrayOf(0, 1, 2, 0, 0),
                        "goBackButton" to intArrayOf(0, 1, 0, 0)
//                        "infoButton" to intArrayOf(0, 0, 0, 4, 0)
                ),
                ViewTreeItem("android.support.v7.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("android.support.v7.widget.Toolbar", arrayOf(
                                ViewTreeItem("android.support.v7.widget.ActionMenuView", arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageButton.name),
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.ImageView.name))),
                                                ViewTreeItem(CC.Button.name),
                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageView),
                                                        ViewTreeItem(CC.ImageView.name))))))),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.WeImageView))),//goBackButton
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),//title
                                                        ViewTreeItem(CC.WeImageView))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))))))))),
                        ViewTreeItem("android.support.v7.widget.ActionBarContextView"))))
    }
    val ActionBarInConversationItem_8_0_2: ViewTree by lazy {
        ViewTree(
                mapOf(
                        "title" to intArrayOf(0, 1, 1, 0, 0),
                        "goBackButton" to intArrayOf(0, 1, 0, 0, 0)
                ),
                ViewTreeItem("android.support.v7.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("android.support.v7.widget.Toolbar", arrayOf(
                                Item_ActionMenuView_7_0_16,
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageView))),//goBackButton
                                                ViewTreeItem(CC.ImageView.name))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),//title
                                                        ViewTreeItem(CC.WeImageView))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))))))))),
                        ViewTreeItem("android.support.v7.widget.ActionBarContextView"))))
    }
    val ActionBarInConversationItem_8_0_3: ViewTree by lazy {
        ViewTree(
                mapOf(
                        "title" to intArrayOf(0, 1, 1, 0, 0),
                        "goBackButton" to intArrayOf(0, 1, 0, 0, 0)
                ),
                ViewTreeItem("androidx.appcompat.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("androidx.appcompat.widget.Toolbar", arrayOf(
                                ViewTreeItem("androidx.appcompat.widget.ActionMenuView", arrayOf(
                                        Item_ActionMenuView_8_0_3)),
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageView))),//goBackButton
                                                ViewTreeItem(CC.ImageView.name))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),//title
                                                        ViewTreeItem(CC.WeImageView))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))))))))),
                        ViewTreeItem("androidx.appcompat.widget.ActionBarContextView"))))
    }
    val ActionBarInConversationItem_8_0_6: ViewTree by lazy {
        ViewTree(
                mapOf(
                        "title" to intArrayOf(0, 1, 1, 0, 0),
                        "goBackButton" to intArrayOf(0, 1, 0, 0, 0)
                ),
                ViewTreeItem("androidx.appcompat.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("androidx.appcompat.widget.Toolbar", arrayOf(
                                ViewTreeItem("androidx.appcompat.widget.ActionMenuView"),
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageView))),//goBackButton
                                                ViewTreeItem(CC.ImageView.name))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),//title
                                                        ViewTreeItem(CC.WeImageView))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))))),
                                        Item_ActionMenuView_8_0_6)))),
                        ViewTreeItem("androidx.appcompat.widget.ActionBarContextView"))))
    }
    val ActionBarInConversationItem_8_0_10: ViewTree by lazy {
        ViewTree(
                mapOf(
                        "title" to intArrayOf(0, 1, 1, 0, 0),
                        "goBackButton" to intArrayOf(0, 1, 0, 0, 0)
                ),
                ViewTreeItem("androidx.appcompat.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("androidx.appcompat.widget.Toolbar", arrayOf(
                                ViewTreeItem("androidx.appcompat.widget.ActionMenuView"),
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageView))),//goBackButton
                                                ViewTreeItem(CC.ImageView.name))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),//title
                                                        ViewTreeItem(CC.TextView.name),//todo new
                                                        ViewTreeItem(CC.WeImageView))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))))),
                                        Item_ActionMenuView_8_0_6)))),
                        ViewTreeItem("androidx.appcompat.widget.ActionBarContextView"))))
    }
    val ActionBarInConversationItem_8_0_11: ViewTree by lazy {
        ViewTree(
                mapOf(
                        "title" to intArrayOf(0, 1, 1, 0, 0, 0),
                        "goBackButton" to intArrayOf(0, 1, 0, 0, 0)
                ),
                ViewTreeItem("androidx.appcompat.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("androidx.appcompat.widget.Toolbar", arrayOf(
                                ViewTreeItem("androidx.appcompat.widget.ActionMenuView"),
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageView))),//goBackButton
                                                ViewTreeItem(CC.ImageView.name))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.TextView.name),//title
                                                                ViewTreeItem(CC.TextView.name)//todo new
                                                        )),
                                                        ViewTreeItem(CC.WeImageView))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))))),
                                        Item_ActionMenuView_8_0_11)))),
                        ViewTreeItem("androidx.appcompat.widget.ActionBarContextView"))))
    }
    val ActionBarInConversationItem_8_0_19: ViewTree by lazy {
        ViewTree(
                mapOf(
                        "title" to intArrayOf(0, 1, 1, 0, 0, 0),
                        "goBackButton" to intArrayOf(0, 1, 0, 0, 0)
                ),
                ViewTreeItem("androidx.appcompat.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("androidx.appcompat.widget.Toolbar", arrayOf(
                                ViewTreeItem("androidx.appcompat.widget.ActionMenuView"),
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageView))),//goBackButton
                                                ViewTreeItem(CC.ImageView.name))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.TextView.name),//title
                                                                ViewTreeItem(CC.TextView.name)//todo new
                                                        )),
                                                        ViewTreeItem(CC.ImageView.name),
                                                        ViewTreeItem(CC.WeImageView))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))))),
                                        Item_ActionMenuView_8_0_11)))),
                        ViewTreeItem("androidx.appcompat.widget.ActionBarContextView"))))
    }

    //通过搜索进入的聊天界面(头)
    val ActionBarInSearchConversationItem: ViewTree by lazy {
        ViewTree(
                mapOf("title" to intArrayOf(0, 0, 1, 0),
                        "goBackButton" to intArrayOf(0, 0, 0, 0)),
//                        "infoButton" to intArrayOf(0, 1, 0, 4, 0)),
                ViewTreeItem("android.support.v7.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("android.support.v7.widget.Toolbar", arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.WeImageView))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.TextView.name))),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem("android.support.v7.widget.ActionMenuView", arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageButton.name),
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.ImageView.name))),
                                                ViewTreeItem(CC.Button.name),
                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                        ViewTreeItem(CC.ImageButton.name),
                                                        ViewTreeItem(CC.ImageView.name))))))))),
                        ViewTreeItem("android.support.v7.widget.ActionBarContextView"))))
    }
    val ActionBarInSearchConversationItem_7_0_15: ViewTree by lazy {
        ViewTree(
                mapOf("title" to intArrayOf(0, 0, 1, 0),
                        "goBackButton" to intArrayOf(0, 0, 0, 0)),
//                        "infoButton" to intArrayOf(0, 1, 0, 4, 0)),
                ViewTreeItem("android.support.v7.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("android.support.v7.widget.Toolbar", arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.WeImageView))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.TextView.name))),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem("android.support.v7.widget.ActionMenuView", arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.ImageButton.name),
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.ImageView.name))),
                                                ViewTreeItem(CC.Button.name),
                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageView),
                                                        ViewTreeItem(CC.ImageView.name))))))))),
                        ViewTreeItem("android.support.v7.widget.ActionBarContextView"))))
    }
    val ActionBarInSearchConversationItem_7_0_16: ViewTree by lazy {
        ViewTree(
                mapOf("title" to intArrayOf(0, 0, 1, 0, 0),
                        "goBackButton" to intArrayOf(0, 0, 0, 0)),
//                        "infoButton" to intArrayOf(0, 1, 0, 4, 0)),
                ViewTreeItem("android.support.v7.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("android.support.v7.widget.Toolbar", arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.WeImageView))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))))),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                Item_ActionMenuView_7_0_16
                        )),
                        ViewTreeItem("android.support.v7.widget.ActionBarContextView"))))
    }
    val ActionBarInSearchConversationItem_7_0_17: ViewTree by lazy {
        ViewTree(
                mapOf("title" to intArrayOf(0, 0, 2, 0, 0),
                        "goBackButton" to intArrayOf(0, 0, 0, 0)),
//                        "infoButton" to intArrayOf(0, 1, 0, 4, 0)),
                ViewTreeItem("android.support.v7.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("android.support.v7.widget.Toolbar", arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.WeImageView))),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))))),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name))),
                                Item_ActionMenuView_7_0_16
                        )),
                        ViewTreeItem("android.support.v7.widget.ActionBarContextView"))))
    }
    val ActionBarInSearchConversationItem_7_0_21: ViewTree by lazy {
        ViewTree(
                mapOf("title" to intArrayOf(0, 1, 2, 0, 0),
                        "goBackButton" to intArrayOf(0, 1, 0, 0)),
//                        "infoButton" to intArrayOf(0, 1, 0, 4, 0)),
                ViewTreeItem("android.support.v7.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("android.support.v7.widget.Toolbar", arrayOf(
                                Item_ActionMenuView_7_0_16,
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.WeImageView))),//gobackButton
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),//title
                                                        ViewTreeItem(CC.WeImageView))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))))),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.ImageView.name)))
                        )),
                        ViewTreeItem("android.support.v7.widget.ActionBarContextView"))))
    }
    val ActionBarInSearchConversationItem_8_0_0: ViewTree by lazy {
        ViewTree(
                mapOf("title" to intArrayOf(0, 1, 2, 0, 0),
                        "goBackButton" to intArrayOf(0, 1, 0, 0)),
//                        "infoButton" to intArrayOf(0, 1, 0, 4, 0)),
                ViewTreeItem("android.support.v7.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("android.support.v7.widget.Toolbar", arrayOf(
                                Item_ActionMenuView_7_0_16,
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.WeImageView))),//gobackButton
                                        ViewTreeItem(CC.ImageView.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),//title
                                                        ViewTreeItem(CC.WeImageView))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView)))))
                                )))),
                        ViewTreeItem("android.support.v7.widget.ActionBarContextView"))))
    }
    //8.0.2-8.0.27 与 ActionBarInConversationItem 合并
    val ActionBarInSearchConversationItem_8_0_2: ViewTree by lazy {
        ViewTreeRepoThisVersion.ActionBarInConversationItem
    }
    val ActionBarInSearchConversationItem_8_0_27: ViewTree by lazy {
        ViewTree(
                mapOf(
                        "title" to intArrayOf(0, 0, 1, 0, 0, 0),
                        "goBackButton" to intArrayOf(0, 0, 0, 0, 0)
                ),
                ViewTreeItem("androidx.appcompat.widget.ActionBarContainer", arrayOf(
                        ViewTreeItem("androidx.appcompat.widget.Toolbar", arrayOf(
//                                ViewTreeItem("androidx.appcompat.widget.ActionMenuView"),
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageView))),//goBackButton
                                                ViewTreeItem(CC.ImageView.name))),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.TextView.name),//title
                                                                ViewTreeItem(CC.TextView.name)//todo new
                                                        )),
                                                        ViewTreeItem(CC.ImageView.name),
                                                        ViewTreeItem(CC.WeImageView))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.TextView.name),
                                                        ViewTreeItem(CC.WeImageView))))),
                                        Item_ActionMenuView_8_0_11)))),
                        ViewTreeItem("androidx.appcompat.widget.ActionBarContextView"))))
    }

    private val Item_ActionMenuView_7_0_16: ViewTreeItem by lazy {
        ViewTreeItem("android.support.v7.widget.ActionMenuView", arrayOf(
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        ViewTreeItem(CC.ImageButton.name),
                        ViewTreeItem(CC.TextView.name),
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.ImageView.name))),
                        ViewTreeItem(CC.Button.name),
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem(CC.WeImageView),
                                ViewTreeItem(CC.ImageView.name)))))))
    }
    private val Item_ActionMenuView_8_0_3: ViewTreeItem by lazy {
        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                ViewTreeItem(CC.ImageButton.name),
                ViewTreeItem(CC.TextView.name),
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        ViewTreeItem(CC.ImageView.name))),
                ViewTreeItem(CC.Button.name),
                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                        ViewTreeItem(CC.WeImageView),
                        ViewTreeItem(CC.ImageView.name)))))
    }
    private val Item_ActionMenuView_8_0_6: ViewTreeItem by lazy {
        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.ImageButton.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.Button.name),
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.ImageView.name)))))))))
    }
    private val Item_ActionMenuView_8_0_11: ViewTreeItem by lazy {
        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                ViewTreeItem(CC.ImageButton.name),
                                ViewTreeItem(CC.TextView.name),
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.ImageView.name))),
                                ViewTreeItem(CC.Button.name),
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.WeImageView),
                                        ViewTreeItem(CC.ImageView.name)))))))))
    }

    //包含聊天内容和底部的部分(尾)
    val ChattingScrollLayoutItem: ViewTree by lazy {
        ViewTree(
                mapOf("bgGroup" to intArrayOf(0, 0),
                        "chattingBgShade" to intArrayOf(0, 0, 1),
                        "chatFooterChild2" to intArrayOf(1, 0),
                        "chatFooterChild2_switchButton" to intArrayOf(0, 1, 0),
                        "chatFooterChild2_editText" to intArrayOf(0, 1, 1, 0, 0),
                        "chatFooterChild2_talkButton" to intArrayOf(0, 1, 2),
                        "chatFooterChild2_faceButton" to intArrayOf(0, 1, 3, 0),
                        "chatFooterChild2_addButton" to intArrayOf(0, 1, 4, 1, 0),
                        "chatFooterChild2_sendButton" to intArrayOf(0, 1, 4, 1, 2)),

                ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingScrollLayout", arrayOf(
                        ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingContent", arrayOf(
                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                        ViewTreeItem("com.tencent.mm.ui.chatting.ChattingImageBGView"),
                                        ViewTreeItem(CC.ImageView.name))))),
                        ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChatFooter", arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageView),
                                                        ViewTreeItem(CC.WeImageView),
                                                        ViewTreeItem(CC.ImageView.name))),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageButton),
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.MaxHeightScrollView", arrayOf(
                                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMEditText"))),
                                                                ViewTreeItem(CC.RelativeLayout.name))),
                                                        ViewTreeItem(CC.Button.name),
                                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                ViewTreeItem(CC.WeImageButton))),
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.TextView.name),
                                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.WeImageButton),
                                                                        ViewTreeItem(CC.ImageView.name),
                                                                        ViewTreeItem(CC.Button.name))))))))))))))))
    }
    val ChattingScrollLayoutItem_7_0_13: ViewTree by lazy {
        ViewTree(
                mapOf("bgGroup" to intArrayOf(0, 0),
                        "chattingBgShade" to intArrayOf(0, 0, 1),
                        "chatFooterChild2" to intArrayOf(1, 0, 1),
                        "chatFooterChild2_switchButton" to intArrayOf(0, 1, 0),
                        "chatFooterChild2_editText" to intArrayOf(0, 1, 1, 0, 0),
                        "chatFooterChild2_talkButton" to intArrayOf(0, 1, 2),
                        "chatFooterChild2_faceButton" to intArrayOf(0, 1, 3, 0),
                        "chatFooterChild2_addButton" to intArrayOf(0, 1, 4, 1, 0),
                        "chatFooterChild2_sendButton" to intArrayOf(0, 1, 4, 1, 2)),
                ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingScrollLayout", arrayOf(
                        ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingContent", arrayOf(
                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                        ViewTreeItem("com.tencent.mm.ui.chatting.ChattingImageBGView"),
                                        ViewTreeItem(CC.ImageView.name))))),
                        ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChatFooter", arrayOf(
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.RelativeLayout.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.LinearLayout.name),
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.WeImageButton),
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.MaxHeightScrollView", arrayOf(
                                                                                ViewTreeItem("com.tencent.mm.ui.widget.MMEditText"))),
                                                                        ViewTreeItem(CC.RelativeLayout.name))),
                                                                ViewTreeItem(CC.Button.name),
                                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.WeImageButton))),
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.TextView.name),
                                                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                                ViewTreeItem(CC.WeImageButton),
                                                                                ViewTreeItem(CC.ImageView.name),
                                                                                ViewTreeItem(CC.Button.name))))))))))))))))))
    }
    val ChattingScrollLayoutItem_7_0_17: ViewTree by lazy {
        ViewTree(
                mapOf("bgGroup" to intArrayOf(0, 0),
                        "chattingBgShade" to intArrayOf(0, 0, 1),
                        "chatFooterChild2" to intArrayOf(1, 0, 1),
                        "chatFooterChild2_switchButton" to intArrayOf(0, 1, 0),
                        "chatFooterChild2_editText" to intArrayOf(0, 1, 1, 0, 0, 1),
                        "chatFooterChild2_editText_MIUI12" to intArrayOf(0, 1, 1, 0, 0),
                        "chatFooterChild2_talkButton" to intArrayOf(0, 1, 2),
                        "chatFooterChild2_faceButton" to intArrayOf(0, 1, 3, 0),
                        "chatFooterChild2_addButton" to intArrayOf(0, 1, 4, 1, 0),
                        "chatFooterChild2_sendButton" to intArrayOf(0, 1, 4, 1, 2)),
                ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingScrollLayout", arrayOf(
                        ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingContent", arrayOf(
                                ViewTreeItem(CC.FrameLayout.name, arrayOf( //bgGroup
                                        ViewTreeItem("com.tencent.mm.ui.chatting.ChattingImageBGView"),
                                        ViewTreeItem(CC.ImageView.name))))), //chattingBgShade
                        ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChatFooter", arrayOf(
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.FrameLayout.name),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf( //chatFooterChild2
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.LinearLayout.name),
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.WeImageButton), //chatFooterChild2_switchButton
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.MaxHeightScrollView", arrayOf(
                                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf( //chatFooterChild2_editText_MIUI12
                                                                                        ViewTreeItem(CC.ImageView.name),
                                                                                        ViewTreeItem("com.tencent.mm.ui.widget.MMEditText") //chatFooterChild2_editText
                                                                                )))),
                                                                        ViewTreeItem(CC.RelativeLayout.name))),
                                                                ViewTreeItem(CC.Button.name), //chatFooterChild2_talkButton
                                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.WeImageButton))), //chatFooterChild2_faceButton
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.TextView.name),
                                                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                                ViewTreeItem(CC.WeImageButton), //chatFooterChild2_addButton
                                                                                ViewTreeItem(CC.ImageView.name),
                                                                                ViewTreeItem(CC.Button.name)))))))))))))))))) //chatFooterChild2_sendButton
    }
    val ChattingScrollLayoutItem_7_0_22: ViewTree by lazy {
        ViewTree(
                mapOf("bgGroup" to intArrayOf(0, 0),
                        "chattingBgShade" to intArrayOf(0, 0, 1),
                        "chatFooterChild2" to intArrayOf(1, 0, 1),
                        "chatFooterChild2_switchButton" to intArrayOf(0, 1, 0),
                        "chatFooterChild2_editText" to intArrayOf(0, 1, 1, 0, 0, 1, 0),
                        "chatFooterChild2_editText_MIUI12" to intArrayOf(0, 1, 1, 0, 0),
                        "chatFooterChild2_talkButton" to intArrayOf(0, 1, 2),
                        "chatFooterChild2_faceButton" to intArrayOf(0, 1, 3, 0),
                        "chatFooterChild2_addButton" to intArrayOf(0, 1, 4, 1, 0),
                        "chatFooterChild2_sendButton" to intArrayOf(0, 1, 4, 1, 2)),
                ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingScrollLayout", arrayOf(
                        ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingContent", arrayOf(
                                ViewTreeItem(CC.FrameLayout.name, arrayOf( //bgGroup
                                        ViewTreeItem("com.tencent.mm.ui.chatting.ChattingImageBGView"),
                                        ViewTreeItem(CC.ImageView.name))))), //chattingBgShade
//                                         skip chatting contents
                        ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChatFooter", arrayOf(
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.FrameLayout.name),
//                                        skip
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf( //chatFooterChild2
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.LinearLayout.name),
//                                                        skip
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.WeImageButton), //chatFooterChild2_switchButton
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem("com.tencent.mm.view.MaxHeightScrollView", arrayOf(
                                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf( //chatFooterChild2_editText_MIUI12
                                                                                        ViewTreeItem(CC.ImageView.name),
                                                                                        ViewTreeItem("com.tencent.mm.ui.widget.cedit.api.MMFlexEditText", arrayOf(
                                                                                                ViewTreeItem("com.tencent.mm.ui.widget.MMEditText"))) //chatFooterChild2_editText
                                                                                )))),
                                                                        ViewTreeItem(CC.RelativeLayout.name))),
                                                                ViewTreeItem(CC.Button.name), //chatFooterChild2_talkButton
                                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.WeImageButton))), //chatFooterChild2_faceButton
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.TextView.name),
                                                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                                ViewTreeItem(CC.WeImageButton), //chatFooterChild2_addButton
                                                                                ViewTreeItem(CC.ImageView.name),
                                                                                ViewTreeItem(CC.Button.name)))))))))))))))))) //chatFooterChild2_sendButton
    }
    val ChattingScrollLayoutItem_8_0_19: ViewTree by lazy {
        ViewTree(
                mapOf("bgGroup" to intArrayOf(0, 0),
                        "chattingBgShade" to intArrayOf(0, 0, 1),
                        "chatFooterChild2" to intArrayOf(1, 0, 1),
                        "chatFooterChild2_switchButton" to intArrayOf(0, 1, 0),
                        "chatFooterChild2_editText" to intArrayOf(0, 1, 1, 0, 0, 1, 0),
                        "chatFooterChild2_editText_MIUI12" to intArrayOf(0, 1, 1, 0, 0),
                        "chatFooterChild2_talkButton" to intArrayOf(0, 1, 2),
                        "chatFooterChild2_faceButton" to intArrayOf(0, 1, 3, 0),
                        "chatFooterChild2_addButton" to intArrayOf(0, 1, 4, 1, 0),
                        "chatFooterChild2_sendButton" to intArrayOf(0, 1, 4, 1, 2)),
                ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingScrollLayout", arrayOf(
                        ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingContent", arrayOf(
                                ViewTreeItem(CC.FrameLayout.name, arrayOf( //bgGroup
                                        ViewTreeItem("com.tencent.mm.ui.chatting.ChattingImageBGView"),
                                        ViewTreeItem(CC.ImageView.name))))), //chattingBgShade
//                                         skip chatting contents
                        ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChatFooter", arrayOf(
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name),
//                                        skip
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf( //chatFooterChild2
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.LinearLayout.name),
//                                                        skip
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.WeImageButton), //chatFooterChild2_switchButton
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem("com.tencent.mm.view.MaxHeightScrollView", arrayOf(
                                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf( //chatFooterChild2_editText_MIUI12
                                                                                        ViewTreeItem(CC.ImageView.name),
                                                                                        ViewTreeItem("com.tencent.mm.ui.widget.cedit.api.MMFlexEditText", arrayOf(
                                                                                                ViewTreeItem("com.tencent.mm.ui.widget.MMEditText"))) //chatFooterChild2_editText
                                                                                )))),
                                                                        ViewTreeItem(CC.RelativeLayout.name))),
                                                                ViewTreeItem(CC.Button.name), //chatFooterChild2_talkButton
                                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.WeImageButton))), //chatFooterChild2_faceButton
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.TextView.name),
                                                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                                ViewTreeItem(CC.WeImageButton), //chatFooterChild2_addButton
                                                                                ViewTreeItem(CC.ImageView.name),
                                                                                ViewTreeItem(CC.Button.name)))))))))))))))))) //chatFooterChild2_sendButton
    }
    val ChattingScrollLayoutItem_8_0_27: ViewTree by lazy {
        ViewTree(
                mapOf("bgGroup" to intArrayOf(0, 0),
                        "chattingBgShade" to intArrayOf(0, 0, 1),
                        "chatFooterChild2" to intArrayOf(1, 0, 1),
                        "chatFooterChild2_switchButton" to intArrayOf(0, 0, 0, 0, 1),
                        "chatFooterChild2_editText" to intArrayOf(0, 0, 1, 0, 0, 1, 0),
                        "chatFooterChild2_editText_MIUI12" to intArrayOf(0, 0, 1, 0, 0),
                        "chatFooterChild2_talkButton" to intArrayOf(0, 0, 2),
                        "chatFooterChild2_faceButton" to intArrayOf(0, 0, 3, 0),
                        "chatFooterChild2_addButton" to intArrayOf(0, 0, 4, 1, 0),
                        "chatFooterChild2_sendButton" to intArrayOf(0, 0, 4, 1, 2)),
                ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingScrollLayout", arrayOf(
                        ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingContent", arrayOf(
                                ViewTreeItem(CC.FrameLayout.name, arrayOf( //bgGroup
                                        ViewTreeItem("com.tencent.mm.ui.chatting.ChattingImageBGView"),
                                        ViewTreeItem(CC.ImageView.name))))), //chattingBgShade
//                                         skip chatting contents
                        ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChatFooter", arrayOf(
                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name),
//                                        skip
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf( //chatFooterChild2
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                ViewTreeItem(CC.LinearLayout.name),
                                                                                ViewTreeItem(CC.WeImageButton) //chatFooterChild2_switchButton
                                                                        ))
//                                                                        skip
                                                                )),
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem("com.tencent.mm.view.MaxHeightScrollView", arrayOf(
                                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf( //chatFooterChild2_editText_MIUI12
                                                                                        ViewTreeItem(CC.ImageView.name),
                                                                                        ViewTreeItem("com.tencent.mm.ui.widget.cedit.api.MMFlexEditText", arrayOf(
                                                                                                ViewTreeItem("com.tencent.mm.ui.widget.MMEditText"))) //chatFooterChild2_editText
                                                                                )))),
                                                                        ViewTreeItem(CC.RelativeLayout.name))),
                                                                ViewTreeItem(CC.Button.name), //chatFooterChild2_talkButton
                                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.WeImageButton))), //chatFooterChild2_faceButton
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.TextView.name),
                                                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                                ViewTreeItem(CC.WeImageButton), //chatFooterChild2_addButton
                                                                                ViewTreeItem(CC.ImageView.name),
                                                                                ViewTreeItem(CC.Button.name)))))))))))))))))) //chatFooterChild2_sendButton
    }

    //头+尾
    val ChattingUILayoutItem: ViewTree by lazy {
        ViewTree(
                mapOf("ChattingScrollLayoutItem" to intArrayOf(2)),
                ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingUILayout", arrayOf(
                        ViewTreeRepoThisVersion.ActionBarInConversationItem.item,
                        ViewTreeItem("android.view.ViewStub"),
                        ViewTreeRepoThisVersion.ChattingScrollLayoutItem.item)))
    }
    val ChattingUILayoutItem_7_0_16: ViewTree by lazy {
        ViewTree(
                mapOf("ChattingScrollLayoutItem" to intArrayOf(2, 0)),
                ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingUILayout", arrayOf(
                        ViewTreeRepoThisVersion.ActionBarInConversationItem.item,
                        ViewTreeItem("android.view.ViewStub"),
                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                ViewTreeRepoThisVersion.ChattingScrollLayoutItem.item)))))
    }
    val ChattingUILayoutItem_8_0_6: ViewTree by lazy {
        ViewTree(
                mapOf("ChattingScrollLayoutItem" to intArrayOf(3, 0)),
                ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingUILayout", arrayOf(
                        ViewTreeRepoThisVersion.ActionBarInConversationItem.item,
                        ViewTreeItem("android.view.ViewStub"),
                        ViewTreeItem("android.view.ViewStub"),
                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                ViewTreeRepoThisVersion.ChattingScrollLayoutItem.item)//ChattingScrollLayoutItem
                        ))))
    }

    val ChattingUILayoutInSearchItem: ViewTree by lazy {
        ViewTree(
                mapOf("ChattingScrollLayoutItem" to intArrayOf(0, 0, 0, 0, 2)),
                ViewTreeItem("android.support.v7.widget.ActionBarOverlayLayout", arrayOf(
                        ViewTreeItem("android.support.v7.widget.ContentFrameLayout", arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem("com.tencent.mm.ui.LayoutListenerView", arrayOf(
                                                ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingUILayout", arrayOf(
                                                        ViewTreeItem("android.view.ViewStub"),
                                                        ViewTreeItem("android.view.ViewStub"),
                                                        ViewTreeRepoThisVersion.ChattingScrollLayoutItem.item)))
                                        ))
                                ))
                        ))
                ))
    }
    val ChattingUILayoutInSearchItem_7_0_16: ViewTree by lazy {
        ViewTree(
                mapOf("ChattingScrollLayoutItem" to intArrayOf(0, 0, 0, 0, 2, 0)),
                ViewTreeItem("android.support.v7.widget.ActionBarOverlayLayout", arrayOf(
                        ViewTreeItem("android.support.v7.widget.ContentFrameLayout", arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem("com.tencent.mm.ui.LayoutListenerView", arrayOf(
                                                ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingUILayout", arrayOf(
                                                        ViewTreeItem("android.view.ViewStub"),
                                                        ViewTreeItem("android.view.ViewStub"),
                                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                                ViewTreeRepoThisVersion.ChattingScrollLayoutItem.item)))))
                                        ))
                                ))
                        ))
                ))
    }
    val ChattingUILayoutInSearchItem_8_0_3: ViewTree by lazy {
        ViewTree(
                mapOf("ChattingScrollLayoutItem" to intArrayOf(0, 0, 0, 0, 2, 0)),
                ViewTreeItem("androidx.appcompat.widget.ActionBarOverlayLayout", arrayOf(
                        ViewTreeItem("androidx.appcompat.widget.ContentFrameLayout", arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem("com.tencent.mm.ui.LayoutListenerView", arrayOf(
                                                ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingUILayout", arrayOf(
                                                        ViewTreeItem("android.view.ViewStub"),
                                                        ViewTreeItem("android.view.ViewStub"),
                                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                                ViewTreeRepoThisVersion.ChattingScrollLayoutItem.item)))))
                                        ))
                                ))
                        ))
                ))
    }
    val ChattingUILayoutInSearchItem_8_0_6: ViewTree by lazy {
        ViewTree(
                mapOf("ChattingScrollLayoutItem" to intArrayOf(0, 0, 0, 0, 3, 0)),
                ViewTreeItem("androidx.appcompat.widget.ActionBarOverlayLayout", arrayOf(
                        ViewTreeItem("androidx.appcompat.widget.ContentFrameLayout", arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem("com.tencent.mm.ui.LayoutListenerView", arrayOf(
                                                ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingUILayout", arrayOf(
                                                        ViewTreeItem("android.view.ViewStub"),
                                                        ViewTreeItem("android.view.ViewStub"),
                                                        ViewTreeItem("android.view.ViewStub"),
                                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                                ViewTreeRepoThisVersion.ChattingScrollLayoutItem.item)//ChattingScrollLayoutItem
                                                        ))
                                                ))
                                        ))
                                ))
                        ))
                ))
    }
    val ChattingUILayoutInSearchItem_8_0_19: ViewTree by lazy {
        ViewTree(
                mapOf("ChattingScrollLayoutItem" to intArrayOf(0, 0, 0, 0, 3, 0)),
                ViewTreeItem("androidx.appcompat.widget.ActionBarOverlayLayout", arrayOf(
                        ViewTreeItem("androidx.appcompat.widget.ContentFrameLayout", arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem("com.tencent.mm.ui.LayoutListenerView", arrayOf(
                                                ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingUILayout", arrayOf(
                                                        ViewTreeItem("android.view.ViewStub"),
                                                        ViewTreeItem("android.view.ViewStub"),
                                                        ViewTreeItem("android.view.ViewStub"),
                                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                                ViewTreeRepoThisVersion.ChattingScrollLayoutItem.item)//ChattingScrollLayoutItem
                                                        ))
                                                ))
                                        ))
                                ))
                        ))
                ))
    }
    val ChattingUILayoutInSearchItem_8_0_27: ViewTree by lazy {
        ViewTree(
                mapOf("ChattingScrollLayoutItem" to intArrayOf(0, 0, 0, 0, 0, 3, 0)),
                ViewTreeItem("androidx.appcompat.widget.ActionBarOverlayLayout", arrayOf(
                        ViewTreeItem("androidx.appcompat.widget.ContentFrameLayout", arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem("com.tencent.mm.ui.LayoutListenerView", arrayOf(
                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                        ViewTreeItem("com.tencent.mm.pluginsdk.ui.chat.ChattingUILayout", arrayOf(
                                                                ViewTreeItem("android.view.ViewStub"),
                                                                ViewTreeItem("android.view.ViewStub"),
                                                                ViewTreeItem("android.view.ViewStub"),
                                                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                                        ViewTreeRepoThisVersion.ChattingScrollLayoutItem.item)//ChattingScrollLayoutItem
                                                                ))
                                                        ))
                                                ))
                                        ))
                                ))
                        ))
                ))
    }
    //endregion

    //小程序的actionbar
    val ActionBarItem: ViewTree by lazy {
        ViewTree(
                mapOf("miniProgramPage" to intArrayOf(0),
                        "miniProgramPage_actionBarPage" to intArrayOf(1, 1),
                        "actionBarPage_title" to intArrayOf(0),
                        "actionBarPage_searchIcon" to intArrayOf(1),
                        "actionBarPage_addIcon" to intArrayOf(2),
                        "miniProgramPage_appBrandDesktopView" to intArrayOf(0, 0, 2, 0),
                        "appBrandDesktopView_searchText" to intArrayOf(0, 0),
                        "appBrandDesktopView_miniProgramTitle" to intArrayOf(2, 0, 0)),
                ViewTreeItem("com.tencent.mm.plugin.appbrand.widget.desktop.AppBrandDesktopContainer", arrayOf(
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                // 小程序
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.View.name),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem("com.tencent.mm.plugin.appbrand.widget.desktop.AppBrandDesktopView", arrayOf(null)))))))),
                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                        ViewTreeItem("com.tencent.mm.plugin.appbrand.widget.desktop.AppBrandDesktopBottomView"),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                        ViewTreeItem(CC.ImageView.name),
                                                        ViewTreeItem(CC.ImageView.name))),
                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                        ViewTreeItem(CC.ImageView.name),
                                                        ViewTreeItem(CC.ImageView.name))))))))))))
    }
    val ActionBarItem_7_0_16: ViewTree by lazy {
        ViewTree(
                mapOf("miniProgramPage" to intArrayOf(0),
                        "miniProgramPage_actionBarPage" to intArrayOf(1, 1),
                        "actionBarPage_title" to intArrayOf(0),
                        "actionBarPage_searchIcon" to intArrayOf(1),
                        "actionBarPage_addIcon" to intArrayOf(2),
                        "miniProgramPage_appBrandDesktopView" to intArrayOf(0, 0, 2, 0),
                        "appBrandDesktopView_searchText" to intArrayOf(0, 0),
                        "appBrandDesktopView_miniProgramTitle" to intArrayOf(2, 0, 0)),
                ViewTreeItem("com.tencent.mm.plugin.appbrand.widget.desktop.AppBrandDesktopContainer", arrayOf(
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                // 小程序
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.View.name),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem("com.tencent.mm.plugin.appbrand.widget.desktop.AppBrandDesktopView", arrayOf(null)))))))),
                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                        ViewTreeItem("com.tencent.mm.plugin.appbrand.widget.desktop.AppBrandDesktopBottomView"),
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageView),
                                                        ViewTreeItem(CC.ImageView.name))),
                                                ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                        ViewTreeItem(CC.WeImageView),
                                                        ViewTreeItem(CC.ImageView.name))))))))))))
    }
    val ActionBarItem_8_0_3: ViewTree by lazy {
        ViewTree(
                mapOf("miniProgramPage" to intArrayOf(0),

                        "miniProgramPage_actionBarPage" to intArrayOf(3, 0, 1),
                        "actionBarPage_title" to intArrayOf(1, 0, 0, 0),
                        "actionBarPage_searchIcon" to intArrayOf(2),
                        "actionBarPage_addIcon" to intArrayOf(3),

                        "miniProgramPage_appBrandDesktopView" to intArrayOf(0, 0, 2, 0),

                        "appBrandDesktopView_searchText" to intArrayOf(0, 0, 0),
                        "appBrandDesktopView_miniProgramTitle" to intArrayOf(2, 0, 0)),
                ViewTreeItem("com.tencent.mm.plugin.taskbar.ui.TaskBarContainer", arrayOf(
                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(//miniProgramPage
                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                // 小程序
                                                ViewTreeItem(CC.TextView.name),
                                                ViewTreeItem(CC.View.name),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                        ViewTreeItem("com.tencent.mm.plugin.taskbar.ui.TaskBarView", arrayOf(//miniProgramPage_appBrandDesktopView
                                                                ViewTreeItem("com.tencent.mm.plugin.taskbar.ui.section.weapp.", arrayOf(
                                                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(//appBrandDesktopView_searchText
                                                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                                ViewTreeItem(CC.WeImageView),
                                                                                                ViewTreeItem(CC.TextView.name))))))))),
                                                                //最近使用的小程序
                                                                ViewTreeItem("com.tencent.mm.plugin.taskbar.ui.section.weapp."),
                                                                //我的小程序
                                                                ViewTreeItem("com.tencent.mm.plugin.taskbar.ui.section.weapp.")
                                                        )))))))),
                                ViewTreeItem("com.tencent.mm.plugin.taskbar.api.GyroView"),
                                ViewTreeItem("com.tencent.mm.plugin.appbrand.widget.desktop.AppBrandDesktopContainerView"),

                                ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                        ViewTreeItem(CC.FrameLayout.name, arrayOf(
                                                ViewTreeItem("com.tencent.mm.plugin.taskbar.ui.TaskBarBottomView"),
                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(//miniProgramPage_actionBarPage
                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                ViewTreeItem(CC.WeImageView))),//goBackButton(not using)
                                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(
                                                                ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                        ViewTreeItem(CC.LinearLayout.name, arrayOf(
                                                                                ViewTreeItem(CC.TextView.name),//actionBarPage_title
                                                                                ViewTreeItem(CC.WeImageView)))
                                                                )))),
                                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(//actionBarPage_searchIcon
                                                                ViewTreeItem(CC.WeImageView),
                                                                ViewTreeItem(CC.ImageView.name))),
                                                        ViewTreeItem(CC.RelativeLayout.name, arrayOf(//actionBarPage_addIcon
                                                                ViewTreeItem(CC.WeImageView),
                                                                ViewTreeItem(CC.ImageView.name))))))))))))))
    }
}
