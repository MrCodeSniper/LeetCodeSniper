package mo.gov.safp.portal;

import com.mpaas.nebula.adapter.api.MPNebula;
import com.mpaas.nebula.adapter.api.MPNebulaOfflineInfo;

/**
 * Created by omg on 2018/7/30.
 */

public class PresetAmrPipeline implements Runnable {
    @Override
    public void run() {
        // 此方法为阻塞调用，请不要在主线程上调用内置离线包方法。如果内置多个amr包，要确保文件已存在，如不存在，会造成其他内置离线包失败。
        // 此方法仅能调用一次，多次调用仅第一次调用有效。
        MPNebula.loadOfflineNebula("h5_json.json",
                new MPNebulaOfflineInfo("11111111_0.0.0.10.amr", "11111111", "0.0.0.10"),
                new MPNebulaOfflineInfo("00000000_0.0.0.77.amr", "00000000", "0.0.0.77"));
    }
}
