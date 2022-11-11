package com.cukespro.pom;

import com.microsoft.playwright.Page;

/**
 * @author Shrinivas
 *
 */
public class Appinit 
{
    /**
     * Reference variable for Page.
     */
    public Page page;
    
    /**
     * Constructor for Appinit which accepts page reference and initializes
     * 
     * @param page
     */
    public Appinit(Page page) {
		this.page=page;
	}
}
