package shared;

import java.util.List;

public interface ICommodityTreeNode {

    boolean isCommodity();

    void setChildren(List<ICommodityTreeNode> children);
}
