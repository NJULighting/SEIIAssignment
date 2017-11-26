package nju.lighting.bl.repositorybl;

import nju.lighting.bl.commoditybl.CommodityInfo;
import nju.lighting.vo.repository.RepositoryChangeVO;
import nju.lighting.vo.repository.RepositoryTableVO;
import shared.ResultMessage;

import java.util.ArrayList;
import java.util.Date;

public class RepositoryManager implements RepositoryInfo {

    /**
     * 库存变化情况
     */
    private ArrayList<RepositoryChange> repositoryChanges;

    /**
     * 当前库存
     */
    private RepositoryTable repositoryTable;

    /**
     * 库存依赖于商品
     */
    private CommodityInfo commodityInfo;

    /**
     * 单例
     */
    private static RepositoryManager repositoryManager;

    /**
     * 构造器
     */
    private RepositoryManager() {
        fetchRepositoryInfo();
    }

    /**
     * 判断服务器端数据是否发生变化，八成会被遗弃
     */
    private boolean hasChanged() {
        return false;
    }

    /**
     * 单例方法
     */
    public static RepositoryManager createRepository() {
        if (RepositoryManager.repositoryManager == null)
            RepositoryManager.repositoryManager = new RepositoryManager();
        return RepositoryManager.repositoryManager;
    }

    /**
     * 从服务器端获取数据
     */
    private void fetchRepositoryInfo() {

    }

    /**
     * 为了显示层提供的方法
     */
    public ArrayList<RepositoryChangeVO> getRepositoryChanges(Date startDate, Date endDate) {
        return null;
    }

    public RepositoryTableVO getRepositoryTable() {
        return null;
    }

    /**
     *提供给其他模块用于变更库存信息
     * @param change
     * @return
     */
    @Override
    public ResultMessage changeRepository(RepositoryChange change) {
        return null;
    }
}
