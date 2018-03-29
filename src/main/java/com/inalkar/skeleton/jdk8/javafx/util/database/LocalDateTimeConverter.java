package com.inalkar.skeleton.jdk8.javafx.util.database;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date> {

    private ZoneId defaultZoneId = ZoneId.systemDefault();

    @Override
    public Date convertToDatabaseColumn(final LocalDateTime date) {
        if (date == null) return null;

        return Date.from(date.atZone(defaultZoneId).toInstant());
    }

    @Override
    public LocalDateTime convertToEntityAttribute(final Date value) {
        if (value == null) return null;

        return LocalDateTime.ofInstant(value.toInstant(), defaultZoneId);
    }

}
