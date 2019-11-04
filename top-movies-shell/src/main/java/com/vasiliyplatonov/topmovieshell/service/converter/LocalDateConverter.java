package com.vasiliyplatonov.topmovieshell.service.converter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
public class LocalDateConverter implements Converter<String, LocalDate> {
    private static final Logger LOG = LoggerFactory.getLogger(LocalDateConverter.class);

    @Nullable
    @Override
    public LocalDate convert(String date) {
        LocalDate lclDate = null;

        if (date.isEmpty()) {
            lclDate = LocalDate.now();
        } else {
            try {
                lclDate = LocalDate.parse(date);
            } catch (DateTimeParseException e) {
                LOG.info("Incorrectly top date entered. Correct text string such as 2007-22-03.");
            }
        }

        return lclDate;
    }
}
