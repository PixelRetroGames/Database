package ciolty.engine.factory;

public interface TypeFormat {
    /**
     * @param object
     * @return string describing object type
     */
    String getFormat(Object object);
}
