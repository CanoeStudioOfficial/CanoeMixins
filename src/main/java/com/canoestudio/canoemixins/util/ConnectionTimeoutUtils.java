package com.canoestudio.canoemixins.util;

import com.canoestudio.canoemixins.CanoeMixins;
import com.canoestudio.canoemixins.config.CanoeModConfig;

import java.net.URLConnection;

public class ConnectionTimeoutUtils {
    public static void setTimeout(URLConnection urlConnection) {
        urlConnection.setConnectTimeout(CanoeModConfig.connectionTimeout.timeout);
        urlConnection.setReadTimeout(CanoeModConfig.connectionTimeout.timeout);
        if(CanoeMixins.getLogger()!=null)
            CanoeMixins.getLogger().info("Set connection timeout to {}ms for {}",urlConnection.getConnectTimeout(),urlConnection.getURL());
        else
            System.out.printf("[mtepatches]:  Set connection timeout to %dms for %s\n",urlConnection.getConnectTimeout(),urlConnection.getURL());
    }
}
