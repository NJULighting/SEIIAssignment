package nju.lighting.bl.documentbl;

import nju.lighting.vo.DocVO;
import nju.lighting.vo.doc.historydoc.HistoryDocVO;
import nju.lighting.vo.viewtables.BusinessHistoryItemVO;
import shared.DocType;
import shared.DocumentFilter;
import shared.ResultMessage;

import java.util.ArrayList;

/**
 * Created on 2017/11/7.
 * Description: 处理单据提交、创建、查找业务
 * @author Liao
 */
public class DocManage {

    /**
     * 根据传入的单据类型进行单据的创建
     * @param type 单据类型
     * @return 相应类型的单据
     */
    public DocVO createDoc(DocType type) {

        return null;
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
    public ArrayList<HistoryDocVO> findDocuments(DocumentFilter filter) {
        return null;
    }
}
