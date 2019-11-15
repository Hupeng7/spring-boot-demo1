package com.demo.websocket.util;

import cn.hutool.core.lang.Dict;
import com.demo.websocket.model.Server;
import com.demo.websocket.payload.ServerVO;

/**
 * @ClassName ServerUtil
 * @Description TODO
 * @Author Leo
 * @Date 2019/11/15 16:52
 * @Version 1.0
 */
public class ServerUtil {

    /**
     * 包装成 ServerVO
     *
     * @param server
     * @return
     */
    public static ServerVO wrapServerVO(Server server) {
        ServerVO serverVO = new ServerVO();
        serverVO.create(server);
        return serverVO;
    }

    /**
     * 包装成 Dict
     *
     * @param serverVO
     * @return
     */
    public static Dict wrapServerDict(ServerVO serverVO) {
        Dict dict = Dict.create()
                .set("cpu", serverVO.getCpu().get(0).getData())
                .set("mem", serverVO.getMem().get(0).getData())
                .set("sys", serverVO.getSys().get(0).getData())
                .set("jvm", serverVO.getJvm().get(0).getData())
                .set("sysFile", serverVO.getSysFile().get(0).getData());
        return dict;
    }


}
