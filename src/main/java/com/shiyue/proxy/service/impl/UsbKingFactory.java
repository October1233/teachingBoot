package com.shiyue.proxy.service.impl;

import com.shiyue.proxy.service.UsbSell;

public class UsbKingFactory  implements UsbSell {

    @Override
    public float sell(int amount) {
        System.out.println("目标类中的方法调用 , UsbKingFactory 中的sell ");
        //一个128G的u盘是 85元。
        //后期根据amount ，可以实现不同的价格，例如10000个，单击是80， 50000个75
        return 85.0f;
    }

}
