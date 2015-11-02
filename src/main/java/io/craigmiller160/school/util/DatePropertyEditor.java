package io.craigmiller160.school.util;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.util.StringUtils;

//TODO document how this class is based on CustomDateEditor from Spring
public class DatePropertyEditor extends PropertyEditorSupport {

	private final boolean allowEmpty;
	private final DateTimeFormatter formatter;
	
	public DatePropertyEditor(DateTimeFormatter formatter, boolean allowEmpty){
		this.allowEmpty = allowEmpty;
		this.formatter = formatter;
	}

	@Override
	public String getAsText() {
		LocalDate date = (LocalDate) getValue();
		return (date != null ? this.formatter.format(date) : "");
	}

	//TODO throws IllegalArgumentException
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(this.allowEmpty && !StringUtils.hasText(text)){
			//Treat empty String as null value
			setValue(null);
		}
		else{
			try{
				setValue(formatter.parse(text, LocalDate::from));
			}
			catch(DateTimeParseException ex){
				throw new IllegalArgumentException("Could not parse date: " + ex.getMessage(), ex);
			}
		}
	}
	
	
	
}
