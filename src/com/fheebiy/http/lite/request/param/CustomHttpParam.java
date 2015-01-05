package com.fheebiy.http.lite.request.param;

import com.fheebiy.http.lite.request.Request;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * help to build custom parameters for {@link Request}
 * 
 * @author MaTianyu
 * 2014-1-1上午2:45:11
 */
public interface CustomHttpParam {

	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface CustomValueBuilder {}

	@CustomValueBuilder
	public CharSequence buildValue();

}
