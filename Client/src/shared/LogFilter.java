package shared;

import nju.lighting.vo.LogVO;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Created on 2017/11/1.
 * Description:
 * @author Liao
 */
public class LogFilter {
    private final Date from, to; // Time
    private final String userId;
    private final Identity userIdentity;

    private LogFilter(Builder builder) {
        from = Optional.ofNullable(builder.start)
                .orElse(new Date(Instant.now().minus(Duration.ofDays(1)).toEpochMilli()));
        to = Optional.ofNullable(builder.end)
                .orElse(new Date(Instant.now().plus(Duration.ofDays(1)).toEpochMilli()));
        userId = builder.userId;
        userIdentity = builder.userIdentity;
    }

    public Predicate<LogVO> getFilter() {
        // Filter for date
        Predicate<LogVO> dateFilter = vo -> vo.getTime().before(to);
        dateFilter = dateFilter.and(vo -> vo.getTime().after(from));

        // Filter for userId and userIdentity
        Predicate<LogVO> idFilter = generatePredicate(userId, LogVO::getCreatorId);
        Predicate<LogVO> identityFilter = generatePredicate(userIdentity, LogVO::getIdentity);

        return dateFilter.and(identityFilter).and(idFilter);
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    private <T> Predicate<LogVO> generatePredicate(T condition, Function<LogVO, T> function) {
        return vo -> Optional.ofNullable(condition).map(e -> function.apply(vo).equals(condition)).orElse(true);
    }

    public static class Builder {
        private Date start;
        private Date end;
        private String userId;
        private Identity userIdentity;

        public Builder start(Date start) {
            this.start = start;
            return this;
        }

        public Builder end(Date end) {
            this.end = end;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder userIdentity(Identity identity) {
            this.userIdentity = identity;
            return this;
        }

        public LogFilter build() {
            return new LogFilter(this);
        }
    }
}
