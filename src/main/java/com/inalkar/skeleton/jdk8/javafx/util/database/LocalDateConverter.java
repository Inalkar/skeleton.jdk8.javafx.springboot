package com.inalkar.skeleton.jdk8.javafx.util.database;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    private ZoneId defaultZoneId = ZoneId.systemDefault();

    @Override
    public Date convertToDatabaseColumn(final LocalDate date) {
        if (date == null) return null;

        return Date.from(date.atStartOfDay(defaultZoneId).toInstant());
    }

    @Override
    public LocalDate convertToEntityAttribute(final Date value) {
        if (value == null) return null;

        return value.toInstant().atZone(defaultZoneId).toLocalDate();
    }

}
