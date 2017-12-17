package shared;

import nju.lighting.bl.documentbl.Doc;
import nju.lighting.po.doc.DocPO;

import java.util.Date;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class DocumentFilter {

    private final Predicate<DocPO> typeFilter;
    private final Predicate<DocPO> dateFilter;
    private final Predicate<DocPO> creatorFilter;
    private final Predicate<DocPO> idFilter;
    private final Predicate<DocPO> stateFilter;

    public Predicate<DocPO> getPredicate() {
        return typeFilter.and(dateFilter).and(creatorFilter).and(idFilter);
    }

    private DocumentFilter(Builder builder) {
        typeFilter = generatePredicate(builder.docType, DocPO::getDocType);
        creatorFilter = generatePredicate(builder.creatorId, DocPO::getUserId);
        idFilter = generatePredicate(builder.docId, DocPO::getId);
        stateFilter = generatePredicate(builder.state, DocPO::getState);

        // Generate filter for date
        Predicate<DocPO> endDateFilter = po -> Optional.of(builder.endDate)
                .map(date -> date.compareTo(po.getCreateTime()) >= 0)
                .orElse(true);
        dateFilter = endDateFilter.and(po -> Optional.of(builder.startDate)
                .map(date -> date.compareTo(po.getCreateTime()) <= 0)
                .orElse(true));

    }

    private <T> Predicate<DocPO> generatePredicate(T t, Function<DocPO, T> function) {
        return po -> Optional.of(t).map(target -> function.apply(po).equals(t)).orElse(true);
    }

    public static class Builder {
        private DocType docType;
        private Date startDate;
        private Date endDate;
        private String creatorId;
        private String docId;
        private DocState state;

        public Builder docType(DocType docType) {
            this.docType = docType;
            return this;
        }

        public Builder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder creatorID(String creatorId) {
            this.creatorId = creatorId;
            return this;
        }

        public Builder docID(String docId) {
            this.docId = docId;
            return this;
        }

        public Builder docState(DocState state) {
            this.state = state;
            return this;
        }

        public DocumentFilter build() {
            return new DocumentFilter(this);
        }
    }
}
