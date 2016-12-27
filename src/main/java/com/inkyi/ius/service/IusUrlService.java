package com.inkyi.ius.service;

public interface IusUrlService {

	String produce(String url);

	String restore(String code);

	Boolean custom(String url, String code);

}
