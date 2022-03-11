package spring.message.impl;

import spring.message.MessageService;

/**
 * @author ice
 */
public class MessageServiceImpl implements MessageService {

	@Override
	public String getMessage() {
		return "hello world";
	}
}