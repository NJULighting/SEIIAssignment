package nju.lighting.builder.doc;

import nju.lighting.builder.Builder;
import nju.lighting.po.doc.DocPO;
import shared.DocType;

import java.util.Date;

/**
 * Created on 2017/12/31.
 * Description:
 * @author Liao
 */
abstract public class DocBuildInfo {
    protected final Date time;
    protected final String creatorId;
    protected final DocType type;

    protected DocBuildInfo(Date time, String creatorId, DocType type) {
        this.time = time;
        this.creatorId = creatorId;
        this.type = type;
    }

    abstract public DocPO toPO();

    abstract static class DocBuilder implements Builder<DocBuildInfo> {

        protected Date time;
        protected String creatorId;
        protected DocType type;

        public DocBuilder(Date time, String creatorId, DocType type) {
            this.time = time;
            this.creatorId = creatorId;
            this.type = type;
        }
    }
}
