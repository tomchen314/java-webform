package com.xst.webform.element;

import java.util.List;

public interface ElementContainerInterface extends ElementInterface {
	/**
     * Add element to container.
     *
     * @param AbstractElement $element Element object.
     *
     * @return ElementContainerInterface
     */
    public AbstractElementContainer add(AbstractElement element);

    /**
     * Add element to form.
     *
     * @param int        $order   Element order.
     * @param AbstractElement $element Element object.
     *
     * @return ElementContainerInterface
     */
    public AbstractElementContainer add(int order, AbstractElement element);

    /**
     * Get element by name.
     *
     * @param String $name Element name.
     *
     * @return AbstractElement
     */
    public AbstractElement get(String name);

    /**
     * Check if element is exists.
     *
     * @param String $name Element name.
     *
     * @return boolean
     */
    public boolean has(String name);

    /**
     * Remove element by name.
     *
     * @param String $name Element name.
     *
     * @return ElementContainerInterface
     */
    public AbstractElementContainer remove(String name);

    /**
     * Get elements.
     *
     * @return List<AbstractElement>
     */
    public List<AbstractElement> getElements();

    /**
     * Clear all elements from form.
     *
     * @return ElementContainerInterface
     */
    public AbstractElementContainer clearElements();

    /**
     * Clear all elements from form.
     *
     * @return ElementContainerInterface
     */
    public String renderForUpdate(String updatePanelId);
}
