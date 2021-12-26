package qyq.example.graphql.kickstart.customize;

import graphql.schema.*;
import org.assertj.core.util.DateUtil;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateScalarType extends GraphQLScalarType {

    public DateScalarType() {
        super("Date", "Date TIME", new Coercing() {
            @Override
            public Object serialize(Object o) throws CoercingSerializeException {
                return ((LocalDateTime) o).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }

            @Override
            public Object parseValue(Object o) throws CoercingParseValueException {
                return o;
            }

            @Override
            public Object parseLiteral(Object o) throws CoercingParseLiteralException {
                if (o == null) {
                    return null;
                }
                return o.toString();
            }
        });
    }
}
