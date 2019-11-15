package com.demo.websocket.payload.server;

import com.demo.websocket.model.server.SysFile;
import com.demo.websocket.payload.KV;
import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @ClassName SysFileVO
 * @Description 系统文件相关信息实体VO
 * @Author Leo
 * @Date 2019/11/7 10:34
 * @Version 1.0
 */
@Data
public class SysFileVO {
    List<List<KV>> data = Lists.newArrayList();

    public static SysFileVO create(List<SysFile> sysFiles) {
        SysFileVO vo = new SysFileVO();
        for (SysFile sysFile : sysFiles) {
            List<KV> item = Lists.newArrayList();
            item.add(new KV("盘符路径", sysFile.getDirName()));
            item.add(new KV("盘符类型", sysFile.getSysTypeName()));
            item.add(new KV("文件类型", sysFile.getTypeName()));
            item.add(new KV("总大小", sysFile.getTotal()));
            item.add(new KV("剩余大小", sysFile.getFree()));
            item.add(new KV("已经使用量", sysFile.getUsed()));
            item.add(new KV("资源的使用率", sysFile.getUsage() + "%s"));
            vo.data.add(item);
        }
        return vo;
    }


}
