package shared;

import nju.lighting.bl.documentbl.Doc;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class DocumentFilter {

    private final String commodity;
    private final Date start;
    private final Date end;
    private final Set<DocType> docTypes;
    private final String creatorId;
    private final String docId;
    private final DocState state;
    private final String customerId;
    private final String repository;

    private DocumentFilter(Builder builder) {

        commodity = builder.commodityName;
        start = Optional.ofNullable(builder.startDate)
                .orElse(new Date(Instant.now().minus(Duration.ofDays(1)).toEpochMilli()));
        end = Optional.ofNullable(builder.endDate)
                .orElse(new Date(Instant.now().plus(Duration.ofDays(1)).toEpochMilli()));
        docTypes = builder.docTypes;
        creatorId = builder.creatorId;
        docId = builder.docId;
        state = builder.state;
        customerId = builder.customerId;
        repository = builder.repository;

    }

    public Predicate<Doc> getPredicateForDoc() {
        Predicate<Doc> creatorFilter = generatePredicateByEqual(creatorId, Doc::getUserId);
        Predicate<Doc> idFilter = generatePredicateByEqual(docId, Doc::getId);
        Predicate<Doc> stateFilter = generatePredicateByEqual(state, Doc::getState);

        Predicate<Doc> customerFilter = generatePredicateByContain(customerId, Doc::containsCustomer);
        Predicate<Doc> commodityFilter = generatePredicateByContain(commodity, Doc::containsCommodity);
        Predicate<Doc> repositoryFilter = generatePredicateByContain(repository, Doc::containsRepository);

        // Generate type filter
        Predicate<Doc> typeFilter;
        if (docTypes.size() == 0) {
            typeFilter = generatePredicateByEqual(null, Doc::getDocType);
        } else {
            typeFilter = po -> false;
            for (DocType type : docTypes) {
                typeFilter = typeFilter.or(po -> po.getDocType() == type);
            }
        }

        // Generate filter for date
        Predicate<Doc> endDateFilter = po -> end.after(po.getCreateTime());
        Predicate<Doc> dateFilter = endDateFilter.and(po -> start.before(po.getCreateTime()));

        return typeFilter.and(dateFilter).and(creatorFilter).and(idFilter).and(stateFilter)
                .and(customerFilter).and(commodityFilter).and(repositoryFilter);
    }

    public String getCommodity() {
        return commodity;
    }

    /**
     * Default start time is yesterday.
     * @return start date of the filter
     */
    public Date getStart() {
        return start;
    }

    /**
     * Default end time is tomorrow.
     * @return end date of the filter
     *
     */
    public Date getEnd() {
        return end;
    }

    public Set<DocType> getDocTypes() {
        return docTypes;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getDocId() {
        return docId;
    }

    public DocState getState() {
        return state;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getRepository() {
        return repository;
    }

    private <T> Predicate<Doc> generatePredicateByEqual(T t, Function<Doc, T> function) {
        return po -> Optional.ofNullable(t).map(target -> function.apply(po).equals(t)).orElse(true);
    }

    private <T> Predicate<Doc> generatePredicateByContain(T t, BiFunction<Doc, T, Boolean> function) {
        return doc -> Optional.ofNullable(t).map(condition -> function.apply(doc, t)).orElse(true);
    }

    /**
     * Builder for DocumentFilter. This class uses builder pattern.
     * This builder can build a filter of DocType(can be multi selected),
     * duration, creator id, doc id, state of approval, customer's id(if exists),
     * repository(if exists), commodity's name(if exists)
     */
    public static class Builder {
        private final Set<DocType> docTypes = new HashSet<>();
        private Date startDate;
        private Date endDate;
        private String creatorId;
        private String docId;
        private DocState state;
        private String customerId;
        private String repository;
        private String commodityName;

        public Builder customer(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder commodity(String commodityName) {
            this.commodityName = commodityName;
            return this;
        }

        public Builder repository(String repository) {
            this.repository = repository;
            return this;
        }

        /**
         * Can add more than one type.
         * @param docType type of doc you want
         */
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
