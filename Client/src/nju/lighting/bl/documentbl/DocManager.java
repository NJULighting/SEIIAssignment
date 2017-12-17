package nju.lighting.bl.documentbl;

import nju.lighting.bl.documentbl.accountiodoc.AccountDocFactory;
import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import shared.DocType;
import shared.DocumentFilter;
import shared.ResultMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created on 2017/11/7.
 * Description: 处理单据提交、创建、查找业务
 * @author Liao
 */
public enum DocManager {
    INSTANCE;

    private Map<DocType, Supplier<DocFactory>> voMap;

    DocManager() {
        voMap = new HashMap<>();
        voMap.put(DocType.ACCOUNT_OUT, AccountDocFactory::new);
    }

    /**
     * 提交单据
     * @param doc 需要提交的单据
     * @return 提交的结果（成功或失败）
     */
    public ResultMessage commitDoc(DocVO doc) {
        return null;
    }

    /**
     * 查找单据
     * @param filter 相应的筛选器<code>DocumentFilter</code>
     * @return 根据筛选条件得到的历史单据
     */
    public List<HistoryDocVO> findDocuments(DocumentFilter filter) {
        return null;
    }
}
