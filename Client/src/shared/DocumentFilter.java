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

    private final Predicate<Doc> typeFilter;
    private final Predicate<Doc> dateFilter;
    private final Predicate<Doc> creatorFilter;
    private final Predicate<Doc> idFilter;
    private final Predicate<Doc> stateFilter;
    private final Predicate<Doc> customerFilter;
    private final Predicate<Doc> commodityFilter;
    private final Predicate<Doc> repositoryFilter;

    private final String commodity;
    private final Date start;
    private final Date end;

    public Predicate<Doc> getPredicate() {
        return typeFilter.and(dateFilter).and(creatorFilter).and(idFilter).and(stateFilter)
                .and(customerFilter).and(commodityFilter).and(repositoryFilter);
    }

    public String getCommodity() {
        return commodity;
    }

    public Date getStart() {
        return Optional.ofNullable(start).orElse(new Date(Instant.now().minus(Duration.ofDays(1)).toEpochMilli()));
    }

    public Date getEnd() {
        return Optional.ofNullable(end).orElse(new Date());
    }

    private DocumentFilter(Builder builder) {
        creatorFilter = generatePredicateByEqual(builder.creatorId, Doc::getUserId);
        idFilter = generatePredicateByEqual(builder.docId, Doc::getId);
        stateFilter = generatePredicateByEqual(builder.state, Doc::getState);

        customerFilter = generatePredicateByContain(builder.customerId, Doc::containsCustomer);
        commodityFilter = generatePredicateByContain(builder.commodityName, Doc::containsCommodity);
        repositoryFilter = generatePredicateByContain(builder.repository, Doc::containsRepository);

        commodity = builder.commodityName;
        start = builder.startDate;
        end = builder.endDate;

        // Generate type filter
        if (builder.docTypes.size() == 0) {
            typeFilter = generatePredicateByEqual(null, Doc::getDocType);
        } else {
            Predicate<Doc> typeFilter = po -> false;
            for (DocType type : builder.docTypes) {
                typeFilter = typeFilter.or(po -> po.getDocType() == type);
            }
            this.typeFilter = typeFilter;
        }

        // Generate filter for date
        Predicate<Doc> endDateFilter = po -> Optional.ofNullable(builder.endDate)
                .map(date -> date.compareTo(po.getCreateTime()) >= 0)
                .orElse(true);
        dateFilter = endDateFilter.and(po -> Optional.ofNullable(builder.startDate)
                .map(date -> date.compareTo(po.getCreateTime()) <= 0)
                .orElse(true));

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
