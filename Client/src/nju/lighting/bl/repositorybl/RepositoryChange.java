package nju.lighting.bl.repositorybl;

import nju.lighting.vo.repository.RepositoryChangeVO;
import shared.RepositoryChangeType;

import java.util.Date;

/**
 * 库存变化信息领域模型对象
 */
public class RepositoryChange {

    private int id;
    private String commodityId;
    private RepositoryChangeType type;
    private int count;
    private double amount;
    private Date date;

    public RepositoryChangeVO toVO() {
        return null;
    }
}