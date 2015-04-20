package com.je.enterprise.mievento.domain.context;

import java.util.Locale;


public class ContextLocale {
	
	private Locale locale;
	private static ContextLocale contextLocale = null;

	public static ContextLocale getContextLocale(){
		if (contextLocale == null){
			contextLocale = new ContextLocale(new Locale("es"));
		}
		return contextLocale;
	}
	
	private ContextLocale(){	
	}
	
	private ContextLocale(Locale locale) {
		this.locale=locale;
	}
	
	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
}
