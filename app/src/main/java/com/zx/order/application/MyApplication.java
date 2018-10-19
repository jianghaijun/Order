package com.zx.order.application;


import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * application
 */
public class MyApplication extends TinkerApplication {
    public MyApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.zx.order.application.MyApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
