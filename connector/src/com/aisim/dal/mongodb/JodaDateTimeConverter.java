package com.aisim.dal.mongodb;

import org.joda.time.DateTime;
import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;
import org.mongodb.morphia.mapping.MappingException;
import java.util.Date;

/**
 * Class that can be used together with MongoDB Morphia to encode and decode {@link DateTime} objects for the DB.
 * <p/>
 * <p/>
 * Stores the date values as integer.
 */
public class JodaDateTimeConverter extends TypeConverter implements SimpleValueConverter {

    public JodaDateTimeConverter() {
        super(DateTime.class);
    }

    @Override
    public Object decode(Class targetClass, Object fromDBObject, MappedField optionalExtraInfo) throws MappingException {
        if (fromDBObject == null) {
            return null;
        }

        if (fromDBObject instanceof Date) {
            Date d = (Date) fromDBObject;
            return new DateTime(d.getTime());
        }

        throw new RuntimeException(
                "Did not expect " + fromDBObject.getClass().getName());
    }

    @Override
    public Object encode(Object value, MappedField optionalExtraInfo) {
        if (value == null) {
            return null;
        }

        if (!(value instanceof DateTime)) {
            throw new RuntimeException(
                    "Did not expect " + value.getClass().getName());
        }

        DateTime dateTime = (DateTime) value;
        return dateTime.toDate();
    }

}
