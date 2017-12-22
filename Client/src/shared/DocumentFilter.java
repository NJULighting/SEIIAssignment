package shared;

import nju.lighting.po.doc.DocPO;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class DocumentFilter {

    private final Predicate<DocPO> typeFilter;
    private final Predicate<DocPO> dateFilter;
    private final Predicate<DocPO> creatorFilter;
    private final Predicate<DocPO> idFilter;
    private final Predicate<DocPO> stateFilter;

    public Predicate<DocPO> getPredicate() {
        return typeFilter.and(dateFilter).and(creatorFilter).and(idFilter).and(stateFilter);
    }

    private DocumentFilter(Builder builder) {
        creatorFilter = generatePredicate(builder.creatorId, DocPO::getUserId);
        idFilter = generatePredicate(builder.docId, DocPO::getId);
        stateFilter = generatePredicate(builder.state, DocPO::getState);

        // Generate type filter
        if (builder.docTypes.size() == 0) {
            typeFilter = generatePredicate(null, DocPO::getDocType);
        } else {
            Predicate<DocPO> typeFilter = po -> false;
            for (DocType type : builder.docTypes) {
                typeFilter = typeFilter.or(po -> po.getDocType() == type);
            }
            this.typeFilter = typeFilter;
        }

        // Generate filter for date
        Predicate<DocPO> endDateFilter = po -> Optional.ofNullable(builder.endDate)
                .map(date -> date.compareTo(po.getCreateTime()) >= 0)
                .orElse(true);
        dateFilter = endDateFilter.and(po -> Optional.ofNullable(builder.startDate)
                .map(date -> date.compareTo(po.getCreateTime()) <= 0)
                .orElse(true));

    }

    private <T> Predicate<DocPO> generatePredicate(T t, Function<DocPO, T> function) {
        return po -> Optional.ofNullable(t).map(target -> function.apply(po).equals(t)).orElse(true);
    }

    public static class Builder {
        private final Set<DocType> docTypes = new HashSet<>();
        private Date startDate;
        private Date endDate;
        private String creatorId;
        private String docId;
        private DocState state;
        private String customerId;
        private String salesmanId;
        private String repository;

        public Builder customer(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder salesman(String salesmanId) {
            this.salesmanId = salesmanId;
            return this;
        }

        public Builder repository(String repository) {
            this.repository = repository;
            return this;
        }

        public Builder docType(DocType docType) {
            docTypes.add(docType);
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
