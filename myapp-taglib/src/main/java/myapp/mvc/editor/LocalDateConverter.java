package myapp.mvc.editor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

public class LocalDateConverter implements GenericConverter {
    private static final Logger LOG = LoggerFactory.getLogger(LocalDateConverter.class);

    private static final Set<ConvertiblePair> CONVERTIBLE_PAIRS = Collections
            .unmodifiableSet(new HashSet<>(Arrays.asList(new ConvertiblePair(LocalDate.class, String.class),
                    new ConvertiblePair(String.class, LocalDate.class))));

    public LocalDateConverter() {
        super();
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return CONVERTIBLE_PAIRS;
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        if (LocalDate.class.equals(sourceType.getType())) {
            return print((LocalDate) source);
        } else {
            return parse((String) source);
        }
    }

    private String print(LocalDate object) {
        LOG.debug("Converting LocalDate {} to a string", object);
        if (object == null) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(object);
    }

    private LocalDate parse(String text) {
        LOG.debug("Converting String {} to a LocalDate", text);
        return LocalDate.parse(text);
    }
}