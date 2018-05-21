package org.mydotey.scf.type;

/**
 * @author koqizhao
 *
 * May 21, 2018
 */
public abstract class StringConverter<V> extends AbstractTypeConverter<String, V> {

    public StringConverter(Class<V> targetType) {
        super(String.class, targetType);
    }

}
