package com.xst.webform.element;

import java.util.Map;

public interface ElementInterface {
    /**
     * Sets the element label.
     *
     * @param String label  Element label.
     *
     * @return AbstractElement
     */
    public AbstractElement setLabel(String label);

    /**
     * Returns the element's label.
     *
     * @return String
     */
    public String getLabel();

    /**
     * Sets the element value.
     *
     * @param String value  Element value.
     *
     * @return AbstractElement
     */
    public void setValue(String value);

    /**
     * Returns the element's value.
     *
     * @return String
     */
    public String getValue();

    /**
     * Returns the attributes for the element.
     *
     * @return Map
     */
    public Map<String, String> getAttributes();

    /**
     * Sets the element option.
     *
     * @param String name  Option name.
     * @param String value Options value.
     *
     * @return AbstractElement
     */
    public AbstractElement setAttribute(String name, String value);

    /**
     * Returns the element's option.
     *
     * @param String name Option name.
     *
     * @return String
     */
    public String getAttribute(String name);

    /**
     * Returns the element's name.
     *
     * @return String
     */
    public String getName();

    /**
     * Render element.
     *
     * @return String
     */
    public String render();

    /**
     * Get element html template.
     *
     * @return String
     */
    public String getHtmlTemplate();

    /**
     * Get element default attribute.
     *
     * @return Map
     */
    public Map<String, String> getDefaultAttributes();
}
